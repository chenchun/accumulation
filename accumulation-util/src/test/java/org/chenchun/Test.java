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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * TODO 在这里编写类的功能描述
 *
 * @author chenchun
 * @version 1.0
 * @created 2014-08-07
 */
public class Test {
  public static void main(String[] args) {
    Integer[] arr;
    Map<Integer, Integer> set = new HashMap<>();
    set.put(1, 1);
    set.put(2, 2);
    set.put(1, 1);
    arr = set.keySet().toArray(new Integer[0]);
    System.out.println(arr.length);
    System.out.println(arr[0]);
    System.out.println(arr[1]);
  }

}
