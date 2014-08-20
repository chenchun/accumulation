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
package org.chenchun.util.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

  public static void main(String[] args) {
    // 1. tryLock
    ReentrantLock lock = new ReentrantLock();
    if (lock.tryLock()) {  //如果已经被lock，则立即返回false不会等待
      try {
        doSomething();
      } finally {
        lock.unlock();
      }
    }

    // 2. fair vs unfair
    lock = new ReentrantLock(); //参数默认false，不公平锁
    lock = new ReentrantLock(true); //公平锁

    // 3. try lock some time
    try {
      if (lock.tryLock(5, TimeUnit.SECONDS)) {  //如果已经被lock，尝试等待5s，看是否可以获得锁，如果5s后仍然无法获得锁则返回false继续执行
        try {
          doSomething();
        } finally {
          lock.unlock();
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace(); //当前线程被中断时(interrupt)，会抛InterruptedException
    }

    // 4. lockInterruptibly
    try {
      lock.lockInterruptibly();
      doSomething();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }

  }

  public static void doSomething() {
    System.out.println("hello");
  }
}
