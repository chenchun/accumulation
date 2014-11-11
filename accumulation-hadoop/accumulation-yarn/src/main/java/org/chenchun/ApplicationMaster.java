/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.chenchun;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.api.ApplicationConstants;
import org.apache.hadoop.yarn.api.protocolrecords.AllocateResponse;
import org.apache.hadoop.yarn.api.records.Container;
import org.apache.hadoop.yarn.api.records.ContainerLaunchContext;
import org.apache.hadoop.yarn.api.records.ContainerStatus;
import org.apache.hadoop.yarn.api.records.FinalApplicationStatus;
import org.apache.hadoop.yarn.api.records.Priority;
import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.hadoop.yarn.client.api.AMRMClient;
import org.apache.hadoop.yarn.client.api.NMClient;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.apache.hadoop.yarn.util.Records;

import java.io.IOException;
import java.util.Collections;

/**
 * Application Master
 */
public class ApplicationMaster {

  public static void main(String[] args) throws IOException, YarnException,
      InterruptedException {
    final String params = args[0];
    final int containerNum = Integer.valueOf(args[1]);

    // Initialize clients to ResourceManager and NodeManagers
    Configuration conf = new YarnConfiguration();

    AMRMClient<AMRMClient.ContainerRequest> rmClient = AMRMClient
        .createAMRMClient();
    rmClient.init(conf);
    rmClient.start();

    NMClient nmClient = NMClient.createNMClient();
    nmClient.init(conf);
    nmClient.start();

    // Register with ResourceManager
    System.out.println("registerApplicationMaster 0");
    rmClient.registerApplicationMaster("", 0, "");
    System.out.println("registerApplicationMaster 1");

    // Priority for worker containers - priorities are intra-application
    Priority priority = Records.newRecord(Priority.class);
    priority.setPriority(0);

    // Resource requirements for worker containers
    Resource capability = Records.newRecord(Resource.class);
    capability.setMemory(128);
    capability.setVirtualCores(1);

    // Make container requests to ResourceManager
    for (int i = 0; i < containerNum; ++i) {
      AMRMClient.ContainerRequest containerAsk = new AMRMClient.ContainerRequest(
          capability, null, null, priority);
      System.out.println("Making res-req " + i);
      rmClient.addContainerRequest(containerAsk);
    }

    // Obtain allocated containers, launch and check for responses
    int responseId = 0;
    int completedContainers = 0;
    while (completedContainers < containerNum) {
      AllocateResponse response = rmClient.allocate(responseId++);
      System.out.println("Allocate response " + response.getAMCommand() + " " +
          "allocate " + response.getAllocatedContainers().size() + "contains");
      for (Container container : response.getAllocatedContainers()) {
        // Launch container by create ContainerLaunchContext
        ContainerLaunchContext ctx = Records
            .newRecord(ContainerLaunchContext.class);
        ctx.setCommands(Collections.singletonList(params +
                " 1>" + ApplicationConstants.LOG_DIR_EXPANSION_VAR + "/stdout" +
                " 2>" + ApplicationConstants.LOG_DIR_EXPANSION_VAR + "/stderr"));
        System.out.println(
            "Launching container " + container.getId() + " on " + container
                .getNodeId());
        nmClient.startContainer(container, ctx);
      }
      for (ContainerStatus status : response.getCompletedContainersStatuses()) {
        ++completedContainers;
        System.out.println("Completed container " + status.getContainerId());
      }
      Thread.sleep(1000);
    }
    System.out.println("Unregister ApplicationMaster");

    // Un-register with ResourceManager
    rmClient
        .unregisterApplicationMaster(FinalApplicationStatus.SUCCEEDED, "", "");
  }
}
