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
package org.chenchun.srm656;

import java.util.HashMap;
import java.util.Map;

public class CorruptedMessage {
  public String reconstructMessage(String s, int k) {
    if (k == 0) {
      return s;
    }
    Map<Character, Integer> map = new HashMap<>();
    char[] chars = s.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      if (!map.containsKey(chars[i])) {
        map.put(chars[i], 0);
      }
      map.put(chars[i], map.get(chars[i])+1);
    }
    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
      if (k + entry.getValue() == chars.length) {
        return construct(entry.getKey(), chars.length);
      }
    }
    for (char c = 'a'; c <= 'z'; c++) {
      if (!map.containsKey(c)) {
        return construct(c, chars.length);
      }
    }
    throw new RuntimeException();
  }

  private String construct(char a, int len) {
    char[] arr = new char[len];
    for (int i = 0; i < len; i++) {
      arr[i] = a;
    }
    return new String(arr);
  }

//  public static void main(String[] args) {
//    CorruptedMessage c = new CorruptedMessage();
//    System.out.println(c.reconstructMessage("aab", 2));
//  }

}
