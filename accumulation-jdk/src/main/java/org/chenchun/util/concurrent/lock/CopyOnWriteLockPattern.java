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

import java.util.HashMap;
import java.util.Map;

/**
 * 无锁实现，并发读，单个写
 * http://stackoverflow.com/questions/5982588/reentrantreadwritelock-many-readers-at-a-time-one-writer-at-a-time/5987536#5987536
 *
 * @author chenchun
 * @version 1.0
 * @created 2014-08-20
 */
public class CopyOnWriteLockPattern {

  /**
   * 如果不加volatile，其他线程可能会看到一个陈旧的myStuff_对象，也可能造成重排序的问题
   * Having a stale reference is one consequence, but reordering can cause more serious problems.
   * Another thread may see the new reference (myStuff_) before the setter operation is complete due to reordering
   */
  private volatile Map<String,HashMap> myStuff_ = new HashMap<String,HashMap>();

  public HashMap getter(String a) {
    return myStuff_.get(a);
  }

  /**
   * If there is only one writer, synchronization may be skipped
   */
  public synchronized void setter() {
    // create a copy from the original
    Map<String,HashMap> copy = new HashMap<String,HashMap>(myStuff_);
    // populate the copy
    // replace copy with the original
    myStuff_ = copy;
  }
}
