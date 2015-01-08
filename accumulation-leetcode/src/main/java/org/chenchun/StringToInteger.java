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

public class StringToInteger {
  public int atoi(String str) {
    if (str == null || str.trim().length() == 0) {
      return 0;
    }
    str = str.trim();
    int i = 0;
    long val = 0;
    int symbol = -1;
    boolean begin = false;
    while (i < str.length()) {
      char c = str.charAt(i);
      if (c == '+') {
        if (symbol != -1) {
          break;
        }
        symbol = 1;
        begin = true;
      } else if (c == '-') {
        if (symbol != -1) {
          break;
        }
        symbol = 2;
        begin = true;
      } else if (c == ' ') {
        if (begin) {
          break;
        }
      } else if (c >= '0' && c <= '9') {
        begin = true;
        val = val*10 + (c-'0');
        if (symbol == 2) {
          if (-val < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
          }
        } else {
          if (val > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
          }
        }
      } else {
        break;
      }
      i++;
    }
    return symbol == 2? (int)(-val) : (int)val;
  }
}
