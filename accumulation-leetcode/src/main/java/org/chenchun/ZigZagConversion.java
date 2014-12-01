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

public class ZigZagConversion {
  public String convert(String s, int nRows) {
    if (s == null || s.length() == 0 || nRows == 1) {
      return s;
    }
    char[] arr = new char[s.length()];
    int k = 0;
    for (int i = 0; i < nRows && k < arr.length; i++) {
      int fst = 2*nRows - 2*(i+1);
      int sec = 2*i;
      arr[k++] = s.charAt(i);
      int last = i;
      while (fst+last < arr.length && k < arr.length) {
        if (fst != 0) {
          arr[k++] = s.charAt(fst+last);
          last += fst;
        }
        if (sec+last < arr.length && k < arr.length) {
          if (sec != 0) {
            arr[k++] = s.charAt(sec+last);
            last += sec;
          }
        } else {
          break;
        }
      }
    }
    return new String(arr);
  }
}
