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

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {
  public List<String> restoreIpAddresses(String s) {
    List<String> list = new ArrayList<String>();
    if (s != null) {
      restoreIpAddresses(s, 0, new ArrayList<String>(), list);
    }
    return list;
  }

  public void restoreIpAddresses(String s, int i, List<String> pre, List<String> list) {
    int leftl = s.length() - i, leftD = 4-pre.size();
    if (leftl < leftD || leftl > leftD * 3) {
      return;
    }
    if (pre.size() == 3) {
      String digit = s.substring(i);
      if (isValid(digit)) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 3; j++) {
          sb.append(pre.get(j)).append(".");
        }
        sb.append(digit);
        list.add(sb.toString());
      }
    } else {
      for (int j = 1; j <=3 && i+j <= s.length(); j++) {
        String digit = s.substring(i, i+j);
        if (isValid(digit)) {
          pre.add(digit);
          restoreIpAddresses(s, i+j, pre, list);
          pre.remove(pre.size()-1);
        } else {
          break;
        }
      }
    }
  }

  private boolean isValid(String s) {
    if (s.charAt(0) == '0') {
      if (s.length() != 1) {
        return false;
      }
      return true;
    }
    if (Integer.parseInt(s) <= 255) {
      return true;
    }
    return false;
  }
}
