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

import java.util.Arrays;

public class Autogame {

//  {2,3,4,3}
//  3
//  Returns: 9                           1 - 2 - 3
//  1 2 3 4                                     < >
//   / / X                                       4
//  1 2 3 4
//
//  1 2 3 4
//  0
//  1
//    1
//      1
//        1
//  1 1
//    1 1
//      1 1
//  1     1
//
//
//
//直接走完全程，如果中间某一个点相遇，那么最终也会相遇
  public int wayscnt(int[] a, int K) {
    int[] pos = new int[a.length];
    for (int i = 0; i < a.length; i++) {
      pos[i] = i;
      a[i]--;
    }
    for (int j = 0; j < K; j++) {
      for (int i = 0; i < a.length; i++) {
        pos[i] = a[pos[i]];
      }
    }
    Arrays.sort(pos);
    int last = 0;
    long ret = 1;
    for (int i = 1; i < a.length; i++) {
      if (pos[i] != pos[last]) {
        ret = ret*(i-last+1) % 1000000007;
        last = i;
      }
    }
    ret = ret*(a.length-last+1) % 1000000007;
    return (int) ret;
  }

  public static void main(String[] args) {
    Autogame a = new Autogame();
    System.out.println(a.wayscnt(new int[]{23, 11, 9, 23, 1, 33, 40, 29, 33, 14, 27, 21, 36, 10, 19, 27, 43, 41, 17, 16, 17, 43, 24, 11, 34, 43, 1, 16, 22, 42, 27, 9, 21, 27, 10, 27, 26, 29, 10, 14, 31, 25, 12, 6}, 8));
  }
}
