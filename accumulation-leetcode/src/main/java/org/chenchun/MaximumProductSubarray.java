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

/**
 *
 */
public class MaximumProductSubarray {
  public int maxProduct(int[] A) {
    if (A == null || A.length == 0) {
      return 0;
    }
    int max = A[0];
    int firstNegProduct = A[0] == 0 ? 1 : A[0], product = firstNegProduct;
    Integer firstNegIndex = firstNegProduct < 0? 0 : null;
    for (int i = 1; i < A.length; i++) {
      if (A[i] != 0) {
        if (firstNegProduct > 0) {
          firstNegProduct *= A[i];
          if (firstNegProduct < 0) {
            firstNegIndex = i;
          }
        }
        product *= A[i];
        if (product < 0) {
          if (i != firstNegIndex) {
            max = Math.max(product/firstNegProduct, max);
          }
        } else {
          max = Math.max(product, max);
        }
      } else {
        firstNegIndex = null;
        firstNegProduct = 1;
        product = 1;
        max = Math.max(max, 0);
      }
    }
    return max;
  }
}
