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

public class DivideTwoIntegers {
  public int divide(int dividend, int divisor) {
    if (divisor == 0) {
      return Integer.MAX_VALUE;
    }
    int ret = 0;
    long dvd = dividend, div = divisor;
    dvd = Math.abs(dvd);
    div = Math.abs(div);
    int i;
    while (dvd >= div) {
      long a = div;
      for (i = 0; a <= dvd; i++) {
        a <<= 1;
      }
      ret += (1 << i-1);
      dvd -= (div << i-1);
    }
    return ((dividend>0) ^ (divisor>0)) ? -ret : ret;
  }
}
