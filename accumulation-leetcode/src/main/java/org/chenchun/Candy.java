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

public class Candy {
  public int candy(int[] ratings) {
    if (ratings == null || ratings.length == 0) {
      return 0;
    }
    int l = ratings.length;
    int[] candys = new int[l];
    for (int i = 0; i < l; i++) {
      if (i > 0 && ratings[i] > ratings[i-1]) {
        candys[i] = candys[i-1]+1;
      } else {
        candys[i] = 1;
      }
    }
    for (int i = l-2; i >= 0; i--) {
      if (ratings[i]>ratings[i+1]) {
        candys[i] = Math.max(candys[i+1]+1, candys[i]);
      }
    }
    int num = 0;
    for (int i = 0; i < l; i++) {
      num += candys[i];
    }
    return num;
  }

  public static void main(String[] args) {
    Candy c = new Candy();
    int candy = c.candy(new int[]{3, 1, 4, 2, 2, 3});
    System.out.println(candy);
  }

  public static void print(int[] arr) {
    for (int a : arr) {
      System.out.print(a+" ");
    }
    System.out.println();
  }
}
