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

public class ValidPalindrome {
  public boolean isPalindrome(String s) {
    if (s == null) {
      return false;
    } else if (s.length() == 0) {
      return true;
    }
    int i = 0, j = s.length()-1;
    String lower = s.toLowerCase();
    while (true) {
      while (i < j && !alphanumeric(lower.charAt(i))) {
        i++;
      }
      if (i >= j) {
        return true;
      }
      while (i < j && !alphanumeric(lower.charAt(j))) {
        j--;
      }
      if (i >= j) {
        return true;
      }
      if (lower.charAt(i) != lower.charAt(j)) {
        return false;
      } else {
        i++;
        j--;
      }
    }
  }

  private boolean alphanumeric(char b) {
    return ('a' <= b && b <= 'z') || ('0' <= b && b <= '9');
  }
}
