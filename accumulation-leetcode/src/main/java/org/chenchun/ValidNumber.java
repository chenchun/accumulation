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

public class ValidNumber {
  public boolean isNumber(String s) {
    if (s == null || s.trim().length() == 0) {
      return false;
    }
    s = s.trim();
    boolean scientic = false, dot = false, negOrPos = false;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == 'e') {
        if (i == 0 || i == s.length()-1 || scientic || ((dot||negOrPos) && i==1)) {
          return false;
        }
        scientic = true;
      } else if (c == '.') {
        if (dot || scientic || s.length()==1 || (s.length()==2 && negOrPos)) {
          return false;
        }
        dot = true;
      } else if (c == '-' || c == '+') {
        if (i == s.length()-1 || (i != 0 && !(scientic && s.charAt(i-1)=='e'))) {
          return false;
        }
        negOrPos = true;
      } else if (c < '0' || c > '9') {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    ValidNumber v = new ValidNumber();
    System.out.println(v.isNumber("-1."));
  }
}
