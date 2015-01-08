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
import java.util.Map;

public class RomanToInteger {
  public int romanToInt(String s) {
    String[] symbol = new String[] {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    int[] value = new int[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < value.length; i++) {
      map.put(symbol[i], value[i]);
    }
    int i = 0;
    int num = 0;
    while (true) {
      if (i+1 < s.length()) {
        String sub = s.substring(i, i+2);
        if (map.containsKey(sub)) {
          num += map.get(sub);
          i += 2;
          continue;
        }
      }
      if (i < s.length()) {
        String sub = s.substring(i, i+1);
        if (map.containsKey(sub)) {
          num += map.get(sub);
          i += 1;
          continue;
        }
      } else {
        break;
      }
    }
    return num;
  }
}
