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
package org.chenchun.srm643;

import java.util.ArrayList;
import java.util.List;

public class TheKingsFactorization {

  public long[] getVector(long N, long[] primes) {
    List<Long> list = new ArrayList<>();
    int i = 0;
    while (true) {
      if (i % 2 == 0) {
        N/=primes[i/2];
        list.add(primes[i/2]);
      } else {
        for (long j = list.get(i-1); ; j++) {
          if (N%j == 0) {
            N=N/j;
            list.add(j);
            break;
          }
        }
      }
      if (N == 1) {
        break;
      }
      i++;
    }
    long[] ret = new long[list.size()];
    for (i = 0; i < list.size(); i++) {
      ret[i] = list.get(i);
    }
    return ret;
  }

  public static void main(String[] args) {
    TheKingsFactorization t = new TheKingsFactorization();
    t.getVector(12, new long[]{2, 3});
  }
}
