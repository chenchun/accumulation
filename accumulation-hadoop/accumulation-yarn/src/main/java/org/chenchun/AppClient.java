package org.chenchun;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.net.NetUtils;
import org.apache.hadoop.yarn.api.ApplicationClientProtocol;
import org.apache.hadoop.yarn.api.ApplicationConstants;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationReportRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationReportResponse;
import org.apache.hadoop.yarn.api.protocolrecords.GetNewApplicationRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetNewApplicationResponse;
import org.apache.hadoop.yarn.api.protocolrecords.SubmitApplicationRequest;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ApplicationSubmissionContext;
import org.apache.hadoop.yarn.api.records.ContainerLaunchContext;
import org.apache.hadoop.yarn.api.records.LocalResource;
import org.apache.hadoop.yarn.api.records.LocalResourceType;
import org.apache.hadoop.yarn.api.records.LocalResourceVisibility;
import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.hadoop.yarn.api.records.YarnApplicationState;
import org.apache.hadoop.yarn.client.ClientRMProxy;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.apache.hadoop.yarn.util.Apps;
import org.apache.hadoop.yarn.util.ConverterUtils;
import org.apache.hadoop.yarn.util.Records;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world Yarn App!
 * To show the procedure of submitting a new app more clear,
 * I didn't use YarnClient Api
 * you can use YarnClient to simplify the code, see https://github.com/hortonworks/simple-yarn-app
 */
public class AppClient {

  private final static Logger LOG = LoggerFactory.getLogger(AppClient.class);

  private ApplicationClientProtocol applicationClientProtocol;
  private ApplicationId applicationId;
  private Configuration conf;

  public void run(String appName, String params, int containerNum,
      String jarPath) throws IOException, YarnException, InterruptedException {
    conf = new Configuration();
    YarnConfiguration yarnConf = new YarnConfiguration(conf);
    InetSocketAddress rmAddress = NetUtils.createSocketAddr(yarnConf
        .get(YarnConfiguration.RM_ADDRESS,
            YarnConfiguration.DEFAULT_RM_ADDRESS));
    LOG.info("Connecting to ResourceManager at " + rmAddress);
    applicationClientProtocol = ClientRMProxy
        .createRMProxy(conf, ApplicationClientProtocol.class);

    applicationId = getNewApplicationId();
    LOG.info("Got new ApplicationId=" + applicationId);

    // Set up resource type requirements for ApplicationMaster
    Resource capability = Records.newRecord(Resource.class);
    capability.setMemory(256);
    capability.setVirtualCores(1);

    // Create a new ApplicationSubmissionContext
    ApplicationSubmissionContext appContext = Records
        .newRecord(ApplicationSubmissionContext.class);
    appContext.setApplicationId(applicationId);
    appContext.setApplicationName(appName);
    appContext.setResource(capability);
    appContext.setAMContainerSpec(
        getContainerLaunchContext(new Path(jarPath), params, containerNum));

    // Create the request to send to the ApplicationsManager
    SubmitApplicationRequest appRequest = Records
        .newRecord(SubmitApplicationRequest.class);
    appRequest.setApplicationSubmissionContext(appContext);

    // Submit the application to the ApplicationsManager
    // Ignore the response as either a valid response object is returned on
    // success or an exception thrown to denote the failure
    applicationClientProtocol.submitApplication(appRequest);

    YarnApplicationState appState = getYarnApplicationState();
    while (appState != YarnApplicationState.FINISHED &&
        appState != YarnApplicationState.KILLED &&
        appState != YarnApplicationState.FAILED) {
      Thread.sleep(100);
      appState = getYarnApplicationState();
    }

    System.out.println("Application " + applicationId + " finished with" +
        " state " + appState);
  }

  private ApplicationId getNewApplicationId() throws IOException,
      YarnException {
    // Get a new appId from RM
    GetNewApplicationRequest request = Records
        .newRecord(GetNewApplicationRequest.class);
    GetNewApplicationResponse response = applicationClientProtocol
        .getNewApplication(request);
    return response.getApplicationId();
  }

  private ContainerLaunchContext getContainerLaunchContext(Path jarPath,
      String params, int containerNum) throws IOException {
    // Create a new container launch context for the AM's container
    ContainerLaunchContext amContainer = Records
        .newRecord(ContainerLaunchContext.class);

    // Lets assume the jar we need for our ApplicationMaster is available in
    // HDFS at a certain known path to us and we want to make it available to
    // the ApplicationMaster in the launched container
    FileStatus jarStatus = FileSystem.get(conf).getFileStatus(jarPath);
    LocalResource amJarLocalResource = Records.newRecord(LocalResource.class);
    // Set the type of resource - file or archive
    // archives are untarred at the destination by the framework
    amJarLocalResource.setType(LocalResourceType.FILE);
    // Set visibility of the resource
    // Setting to most private option i.e. this file will only
    // be visible to this instance of the running application
    amJarLocalResource.setVisibility(LocalResourceVisibility.APPLICATION);
    // Set the location of resource to be copied over into the
    // working directory
    amJarLocalResource.setResource(ConverterUtils.getYarnUrlFromPath(jarPath));
    // Set timestamp and length of file so that the framework
    // can do basic sanity checks for the local resource
    // after it has been copied over to ensure it is the same
    // resource the client intended to use with the application
    amJarLocalResource.setTimestamp(jarStatus.getModificationTime());
    amJarLocalResource.setSize(jarStatus.getLen());
    // The framework will create a symlink called AppMaster.jar in the
    // working directory that will be linked back to the actual file.
    // The ApplicationMaster, if needs to reference the jar file, would
    // need to use the symlink filename.
    // Set the local resources into the launch context
    amContainer.setLocalResources(
        Collections.singletonMap("AppMaster.jar", amJarLocalResource));

    amContainer.setEnvironment(setupAppMasterEnv());

    // Set the command array into the container spec
    amContainer
        .setCommands(Collections.singletonList("/data/jdk1.6.0_24/bin/java" +
            " " + ApplicationMaster.class.getName() +
            " " + params +
            " " + containerNum +
            " 1>" + ApplicationConstants.LOG_DIR_EXPANSION_VAR + "/stdout" +
            " 2>" + ApplicationConstants.LOG_DIR_EXPANSION_VAR + "/stderr"));

    return amContainer;
  }

  private Map<String, String> setupAppMasterEnv() {
    // Set up the environment needed for the launch context
    Map<String, String> appMasterEnv = new HashMap<String, String>();
    // For example, we could setup the classpath needed.
    // Assuming our classes or jars are available as local resources in the
    // working directory from which the command will be run, we need to append
    // "." to the path.
    // By default, all the hadoop specific classpaths will already be available
    // in $CLASSPATH, so we should be careful not to overwrite it.
    for (String c : conf
        .getStrings(YarnConfiguration.YARN_APPLICATION_CLASSPATH,
            YarnConfiguration.DEFAULT_YARN_APPLICATION_CLASSPATH)) {
      Apps.addToEnvironment(appMasterEnv,
          ApplicationConstants.Environment.CLASSPATH.name(), c.trim());
    }
    Apps.addToEnvironment(appMasterEnv,
        ApplicationConstants.Environment.CLASSPATH.name(),
        ApplicationConstants.Environment.PWD.$() + File.separator + "*");
    return appMasterEnv;
  }

  private YarnApplicationState getYarnApplicationState() throws IOException,
      YarnException {
    GetApplicationReportRequest reportRequest = Records
        .newRecord(GetApplicationReportRequest.class);
    reportRequest.setApplicationId(applicationId);
    GetApplicationReportResponse reportResponse = applicationClientProtocol
        .getApplicationReport(reportRequest);
    return reportResponse.getApplicationReport().getYarnApplicationState();
  }

  /**
   * bin/hadoop fs -copyFromLocal accumulation-yarn-1.0.jar /tmp/accumulation-yarn-1.0.jar
   * bin/hadoop jar accumulation-yarn-1.0.jar org.chenchun.AppClient /bin/date 4 hdfs://host:port/tmp/accumulation-yarn-1.0.jar
   */
  public static void main(String[] args) throws IOException, YarnException,
      InterruptedException {

    String appName = "Hello Yarn";
    String params = args[0];
    int containerNum = Integer.parseInt(args[1]);
    String jarPathAtHdfs = args[2];
    AppClient appClient = new AppClient();
    appClient.run(appName, params, containerNum, jarPathAtHdfs);
  }
}
