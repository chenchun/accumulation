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
package org.chenchun.srm654;

public class SquareScoresDiv2 {
  public int getscore(String s) {
    //f1=1
    //f2=1+2=3
    //f3=1+2+3=6
    //fn=n*(n+1)/2
    if (s == null || s.length() == 0) {
      return 0;
    }
    int count = 0, last = 0;
    char[] chars = s.toCharArray();
    for (int i = 1; i < s.length(); i++) {
      if (chars[last] != chars[i]) {
        count += (i-last)*(i-last+1)/2;
        last = i;
      }
    }
    count += (s.length()-last)*(s.length()-last+1)/2;
    return count;
  }
}
