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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NextPermutation {
  public void nextPermutation(int[] num) {
    if (num == null || num.length <= 1) {
      return;
    }
    for (int i = num.length-2; i>=0; i--) {
      for (int j = num.length-1; j> i; j--) {
        if (num[i] < num[j]) {
          int t = num[i];
          num[i] = num[j];
          num[j] = t;
          Arrays.sort(num, i+1, num.length);
          return;
        }
      }
    }
    reverse(num);
  }
  private void reverse(int[] num) {
    for (int i = 0; ; i++) {
      int j = num.length - i - 1;
      if (i >= j) {
        break;
      }
      int t = num[i];
      num[i] = num[j];
      num[j] = t;
    }
  }

  public static void main(String[] args) {
    NextPermutation n = new NextPermutation();
    n.nextPermutation(new int[]{3, 2, 1});
  }
}
