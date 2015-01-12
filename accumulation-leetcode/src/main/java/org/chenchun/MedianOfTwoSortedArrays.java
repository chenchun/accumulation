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
    if (A == null && B == null) {
      return 0;
    }
    int l1 = A.length, l2 = B.length;
    if (l1 == 0) {
      return findMedian(B);
    }
    if (l2 == 0) {
      return findMedian(A);
    }
    if ((l1+l2)%2 == 0) {
      int mid = (l1+l2)/2;
      return (findKth(A, B, 0, 0, mid)+findKth(A, B, 0, 0, mid+1))/2;
    } else {
      return findKth(A, B, 0, 0, (l1+l2+1)/2);
    }
  }

  public double findKth(int A[], int B[], int h1, int h2, int k) {
    int t1 = A.length-1, t2 = B.length-1;
    if (t1-h1 > t2-h2) {
      return findKth(B, A, h2, h1, k);
    }
    if (t1-h1+1 <= 0) {
      return B[h2+k-1];
    }
    if (k == 1) {
      return Math.min(A[h1], B[h2]);
    }
    int k1 = Math.min(k/2, t1-h1+1), k2 = k-k1;
    int m1 = k1+h1-1, m2 = k2+h2-1;
    if (A[m1] == B[m2]) {
      return A[m1];
    } else if (A[m1] > B[m2]) {
      return findKth(A, B, h1, m2+1, k1);
    } else {
      return findKth(A, B, m1+1, h2, k2);
    }
  }

  private double findMedian(int A[]) {
    if (A.length % 2 == 0) {
      int mid = A.length/2;
      return (A[mid-1]+A[mid])/2.0;
    } else {
      return A[(A.length-1)/2];
    }
  }

  public static void main(String[] args) {
    MedianOfTwoSortedArrays m = new MedianOfTwoSortedArrays();
    int[] A = new int[]{1,1,2,3,4,6,7};
    int[] B = new int[]{2,2,5,6};
//    System.out.println(m.findMedianSortedArrays(A, B));
//    System.out.println(m.findKth(A, B, 0, 0, 1));
//    System.out.println(m.findKth(A, B, 0, 0, 2));
//    System.out.println(m.findKth(A, B, 0, 0, 3));
//    System.out.println(m.findKth(A, B, 0, 0, 4));
//    System.out.println(m.findKth(A, B, 0, 0, 5));
//    System.out.println(m.findKth(A, B, 0, 0, 6));
//    System.out.println(m.findKth(A, B, 0, 0, 7));
//    System.out.println(m.findKth(A, B, 0, 0, 8));
//    System.out.println(m.findKth(A, B, 0, 0, 9));
//    System.out.println(m.findKth(A, B, 0, 0, 10));
    System.out.println(m.findMedianSortedArrays(new int[]{}, new int[]{100,101}));
  }
}
