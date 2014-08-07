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
package org.chenchun.concurrent;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 80 Thread 1000 random/Thread
 * Random cost 1007 ms
 * ThreadLocalRandomWork cost 718 ms
 * JdkThreadLocalRandomWork cost 715 ms
 *
 * @author chenchun
 * @version 1.0
 * @created 2014-08-08
 */
public class RandomVsThreadLocalRandom {

  public static class RandomWork implements Runnable {

    private Random random;
    private long interval;
    private int num;

    private CountDownLatch startSignal;
    private CountDownLatch doneSignal;

    public RandomWork(Random random, long interval, int num,
                      CountDownLatch startSignal, CountDownLatch doneSignal) {
      this.random = random;
      this.interval = interval;
      this.num = num;
      this.startSignal = startSignal;
      this.doneSignal = doneSignal;
    }

    @Override
    public void run() {
      try {
        startSignal.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      while (num -- > 0) {
        generateRandom();
        try {
          Thread.sleep(interval);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      doneSignal.countDown();
    }

    public void generateRandom() {
      System.out.println(RandomWork.class.getSimpleName() + " " + random.nextInt
          ());
    }
  }

  public static class JdkThreadLocalRandomWork extends RandomWork {

    public JdkThreadLocalRandomWork(long interval, int num, CountDownLatch startSignal, CountDownLatch doneSignal) {
      super(null, interval, num, startSignal, doneSignal);
    }

    public void generateRandom() {
      System.out.println(JdkThreadLocalRandomWork.class.getSimpleName() + " " +
          ThreadLocalRandom.current().nextInt());
    }
  }

  public static class ThreadLocalRandomWork extends RandomWork {

    private static final ThreadLocal<Random> RANDOMS = new ThreadLocal<Random>() {
      @Override
      protected Random initialValue() {
        return new Random();
      }
    };

    public ThreadLocalRandomWork(long interval, int num, CountDownLatch startSignal, CountDownLatch doneSignal) {
      super(null, interval, num, startSignal, doneSignal);
    }

    public void generateRandom() {
      System.out.println(ThreadLocalRandomWork.class.getSimpleName() + " " +
          RANDOMS.get().nextInt());
    }
  }

  public static void main(String[] args) throws InterruptedException {

    int threadNum = 80, interval = 0, num = 1000;
    //Random
    Random random = new Random();
    CountDownLatch startSignal = new CountDownLatch(1),
        doneSignal = new CountDownLatch(threadNum);
    for (int i = 0; i < threadNum; i++) {
      new Thread(new RandomWork(random, interval, num, startSignal, doneSignal)).start();
    }
    long startTime = System.currentTimeMillis();
    startSignal.countDown();
    doneSignal.await();
    String randomCost = "With Random cost " + (System.currentTimeMillis() -
        startTime) + " ms";

    //ThreadLocal<Random>
    startSignal = new CountDownLatch(1);
    doneSignal = new CountDownLatch(threadNum);
    for (int i = 0; i < threadNum; i++) {
      new Thread(new ThreadLocalRandomWork(interval, num, startSignal, doneSignal)).start();
    }
    startTime = System.currentTimeMillis();
    startSignal.countDown();
    doneSignal.await();
    String threadLocalRandomCost =  "With ThreadLocalRandomWork cost " + (System
        .currentTimeMillis() - startTime) + " ms";

    //ThreadLocalRandom
    startSignal = new CountDownLatch(1);
    doneSignal = new CountDownLatch(threadNum);
    for (int i = 0; i < threadNum; i++) {
      new Thread(new JdkThreadLocalRandomWork(interval, num, startSignal,
          doneSignal)).start();
    }
    startTime = System.currentTimeMillis();
    startSignal.countDown();
    doneSignal.await();
    String jdkThreadLocalRandomCost = "With JdkThreadLocalRandomWork cost " +
        (System.currentTimeMillis() - startTime) + " ms";

    System.out.println(randomCost);
    System.out.println(threadLocalRandomCost);
    System.out.println(jdkThreadLocalRandomCost);

  }
}
