/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
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
 * @created 2015-12-12
 *
 * @version 1.0
 */
public class WordPattern {
  public boolean wordPattern(String pattern, String str) {
    if (pattern == null || pattern.length() == 0) {
      if (str == null || str.length() == 0) {
        return true;
      }
      return false;
    }
    Map<Character, String> map = new HashMap<>();
    Set<String> set = new HashSet<>();
    String[] strs = str.split(" ");
    if (strs.length != pattern.length()) {
      return false;
    }
    for (int i = 0; i < pattern.length(); i++) {
      char c = pattern.charAt(i);
      if (!map.containsKey(c)) {
        if (set.contains(strs[i])) {
          return false;
        }
        map.put(c, strs[i]);
        set.add(strs[i]);
        continue;
      }
      if (!map.get(c).equals(strs[i])) {
        return false;
      }
    }
    return true;
  }
}
