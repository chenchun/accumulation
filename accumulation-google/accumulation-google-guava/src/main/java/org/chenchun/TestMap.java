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

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * @author chenchun
 * @version 1.0
 * @created 2014-03-10
 */
public class TestMap {

  public static void main(String[] args) {
    ImmutableMap<String, String> immutableMap = ImmutableMap.of("a", "1", "b", "1");
    ImmutableMap<Object, Object> build = ImmutableMap.builder().put("a", "1").put("b", "2").build();
    BiMap<String, String> biMap = HashBiMap.create(immutableMap.size());
    for (Map.Entry<String, String> entry : immutableMap.entrySet()) {
      biMap.forcePut(entry.getKey(), entry.getValue());
    }
    biMap.inverse();
    System.out.println();
  }
}
