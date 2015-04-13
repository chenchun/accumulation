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
package org.chenchun.tco;

public class Similars {
  //R will be between 2 and 100,000, inclusive.
  //L will be between 1 and R - 1, inclusive.
  // 0 1 2 3 4 5 6 7 8 9
  public int digits(int n) {
    int ret = 0;
    while (n > 0) {
      ret |= (1 << (n % 10));
      n /= 10;
    }
    return ret;
  }

  public int contains(int n) {
    int ret = 0;
    while (n > 0) {
      if (n % 2 != 0) {
        ret++;
      }
      n >>= 1;
    }
    return ret;
  }

  public int maxsim(int L, int R) {
    int num = 1024;
    int[] find = new int[num];
    for (int i = L; i <= R; i++) {
      find[digits(i)]++;
    }
    int max = 0;
    for (int i = 1; i < num; i++) {
      if (find[i] > 0) {
        for (int j = 1; j < num; j++) {
          if (find[j] > 0) {
            if (i == j) {
              if (find[i] > 1) {
                max = Math.max(max, contains(i));
              }
            } else {
              max = Math.max(max, contains(i & j));
            }
          }
        }
      }
    }
    return max;
  }

  public static void main(String[] args) {
    Similars s = new Similars();
    System.out.println(s.maxsim(10523, 10524));
    System.out.println(s.maxsim(1, 119));
    System.out.println(s.maxsim(1, 121));
    System.out.println(s.maxsim(1, 99));
  }

}
