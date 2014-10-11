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
import org.apache.twill.api.ResourceSpecification;
import org.apache.twill.api.TwillApplication;
import org.apache.twill.api.TwillController;
import org.apache.twill.api.TwillRunnerService;
import org.apache.twill.api.TwillSpecification;
import org.apache.twill.api.logging.PrinterLogHandler;
import org.apache.twill.common.Services;
import org.apache.twill.yarn.YarnTwillRunnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

/**
 *
 */
public class TwillMultiTask implements TwillApplication {

  public static final Logger LOG = LoggerFactory.getLogger(TwillMultiTask.class);

  @Override
  public TwillSpecification configure() {
    return TwillSpecification.Builder.with()
        .setName(TwillMultiTask.class.getSimpleName())
        .withRunnable()
        .add(RunFor1Sec.class.getSimpleName(), new RunFor1Sec(),
            ResourceSpecification.Builder.with()
                .setVirtualCores(1)
                .setMemory(128, ResourceSpecification.SizeUnit.MEGA)
                .setInstances(3)
                .build()).noLocalFiles()
        .add(RunForever.class.getSimpleName(), new RunForever()).noLocalFiles()
        .anyOrder()
        .build();
  }

  public class RunFor1Sec extends AbstractTwillRunnable {

    @Override
    public void run() {
      LOG.info("Start run " + RunFor1Sec.class.getSimpleName());
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      LOG.info("Stop run " + RunFor1Sec.class.getSimpleName());
    }
  }

  public class RunForever extends AbstractTwillRunnable {

    @Override
    public void run() {
      LOG.info("Start run " + RunForever.class.getSimpleName());
      while (true) {
        LOG.info("I will never stop");
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    String zkStr = "localhost:2181";
    final TwillRunnerService twillRunner =
        new YarnTwillRunnerService(new YarnConfiguration(), zkStr);
    twillRunner.startAndWait();

    final TwillController controller =
        twillRunner.prepare(new TwillMultiTask())
        .addLogHandler(new PrinterLogHandler(new PrintWriter(System.out, true)))
        .start();

    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
      @Override
      public void run() {
        controller.stopAndWait();
        twillRunner.stopAndWait();
      }
    }));

    Services.getCompletionFuture(controller).get();
  }
}
