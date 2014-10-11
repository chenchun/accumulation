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
package org.chenchun.twill;

import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.twill.api.AbstractTwillRunnable;
import org.apache.twill.api.TwillController;
import org.apache.twill.api.TwillRunnerService;
import org.apache.twill.api.logging.PrinterLogHandler;
import org.apache.twill.common.Services;
import org.apache.twill.yarn.YarnTwillRunnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

/**
 * twill echo server, successfully running against latest hadoop trunk version cluster
 *
 * just run in idea with config:
 * 1. add program arguments (zookeeper server) : localhost:2181
 * 2. add HADOOP_HOME env
 * 3. ln -s ${hadoop_install_home}/etc/hadoop src/main/resources
 */
public class EchoServer extends AbstractTwillRunnable {

  public static final Logger LOG = LoggerFactory.getLogger(EchoServer.class);

  @Override
  public void run() {
    while (true) {
      LOG.info("Hello !");
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("Arguments format: <host:port of zookeeper server>");
      System.exit(1);
    }

    String zkStr = args[0];

    final TwillRunnerService twillRunner =
        new YarnTwillRunnerService(
            new YarnConfiguration(), zkStr);
    twillRunner.startAndWait();

    final TwillController controller =
        twillRunner.prepare(new EchoServer())
            .addLogHandler(new PrinterLogHandler(new PrintWriter(System.out, true)))
            .start();

    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        controller.stopAndWait();
        twillRunner.stopAndWait();
      }
    });

    try {
      Services.getCompletionFuture(controller).get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
}
