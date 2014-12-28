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

public class TrappingRainWater {
  public int trap(int[] A) {
    if (A == null || A.length <= 2) {
      return 0;
    }
    int l = A.length;
    int[] maxLeft = new int[l];
    int[] maxRight = new int[l];
    maxLeft[0] = A[0];
    maxRight[l-1] = A[l-1];
    int contains = 0;
    for (int i = 1; i < l; i++) {
      maxLeft[i] = Math.max(maxLeft[i-1], A[i]);
      maxRight[l-i-1] = Math.max(maxRight[l-i], A[l-i-1]);
    }
    for (int i = 1; i < l-1; i++) {
      int min = Math.min(maxLeft[i], maxRight[i]);
      if (min - A[i] > 0) {
        contains += min - A[i];
      }
    }
    return contains;
  }
}
