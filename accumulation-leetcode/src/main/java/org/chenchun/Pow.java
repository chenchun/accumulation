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

public class Pow {
  public double pow(double x, int n) {
    if (n == 0) {
      return 1;
    }
    if (n < 0) {
      n = -n;
      x = 1/x;
    }
    boolean first = true;
    double ret = 1, powx = 1;
    while (n > 0) {
      if (first) {
        powx *= x;
        first = false;
      } else {
        powx *= powx;
      }
      if (n % 2 == 1) {
        ret *= powx;
      }
      n /= 2;
    }
    return ret;
  }
}
