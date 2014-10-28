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

public class RemoveDuplicatesFromSortedArrayII {
  public int removeDuplicates(int[] A) {
    if (A != null) {
      int ret = A.length;
      int x = 0, y = 1, z = 2;
      int[] arr = new int[A.length];
      int count = 2;
      for (int i = 0; i < A.length && i < 2; i++) {
        arr[i] = A[i];
      }
      while (z < A.length) {
        if (A[x] == A[y]) {
          if (A[y] == A[z]) {
            ret--;
          } else {
            arr[count] = A[z];
            count++;
          }
        } else {
          arr[count] = A[z];
          count++;
        }
        x = y;
        y = z;
        z++;
      }
      for (int i = 0; i < count && i < A.length; i++) {
        A[i] = arr[i];
      }
      return ret;
    }
    return 0;
  }
}
