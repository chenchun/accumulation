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

public class MedianOfTwoSortedArrays {
  public double findMedianSortedArrays(int A[], int B[]) {
    if (A == null || A.length == 0) {
      return findMedian(B);
    } else if (B == null || B.length == 0) {
      return findMedian(A);
    }
    if ((A.length+B.length) % 2 == 0) {
      return (findMedianSortedArrays(A, B, 0, A.length-1, 0, B.length-1, (A.length+B.length)/2-1) +
          findMedianSortedArrays(A, B, 0, A.length-1, 0, B.length-1, (A.length+B.length)/2))/2.0;
    }
    return findMedianSortedArrays(A, B, 0, A.length-1, 0, B.length-1, (A.length+B.length)/2);
  }

  private double findMedianSortedArrays(int A[], int B[], int b1, int e1, int b2, int e2, int k) {
    int l1 = e1 - b1 + 1, l2 = e2 - b2 + 1;
    if (l1 == 0) {
      return B[k+b2];
    }
    if (l2 == 0) {
      return A[k+b1];
    }
    if (k == 0) {
      return Math.min(A[b1], B[b2]);
    }
    int la = l1*k/(l1+l2), lb = k-la-1;
    la += b1;
    lb += b2;
    if (A[la] > B[lb]) {
      return findMedianSortedArrays(A, B, b1, la, lb+1, e2, k-(lb-b2+1));
    } else if (A[la] == B[lb]) {
      return A[la];
    } else {
      return findMedianSortedArrays(A, B, la+1, e1, b2, lb, k-(la-b1+1));
    }
  }

  private double findMedian(int B[]) {
    if (B == null || B.length == 0) {
      return 0;
    } else {
      if (B.length % 2 == 0) {
        return (B[B.length/2-1] + B[B.length/2])/2.0;
      } else {
        return B[B.length/2];
      }
    }
  }

  public static void main(String[] args) {
    MedianOfTwoSortedArrays m = new MedianOfTwoSortedArrays();
    m.findMedianSortedArrays(new int[]{1, 2}, new int[]{1, 2, 3});
  }
}
