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
package org.chenchun.tcor1b;

public class TheNicePair {
  private int[] prime = new int[]{2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,
      53,59,61,67,71,73,79,83,89,97,
      101,103,107,109,113,127,131,137,139,149,
      151,157,163,167,173,179,181,191,193,197,199,
      211,223,227,229,233,239,241,
      251,257,263,269,271,277,281,283,293,
      307,311,313,317,331,337,347,349,
      353,359,367,373,379,383,389,397,
      401,409,419,421,431,433,439,443,449,
      457,461,463,467,479,487,491,499,
      503,509,521,523,541,547,
      557,563,569,571,577,587,593,599,
      601,607,613,617,619,631,641,643,647,
      653,659,661,673,677,683,691,
      701,709,719,727,733,739,743,
      751,757,761,769,773,787,797,
      809,811,821,823,827,829,839,
      853,857,859,863,877,881,883,887,
      907,911,919,929,937,941,947,
      953,967,971,977,983,991,997};

  public int solve(int[] A) {
    int len = A.length, ret = -1;
    for (int i = 0; i < len; i++) {
      for (int j = i; j < len; j++) {
        for (int p : prime) {
          int count = 0;
          for (int s = i; s <= j; s++) {
            if (A[s] % p == 0) {
              count++;
            }
          }
          if (2 * count >= j - i + 1) {
            ret = Math.max(ret, j - i);
            break;
          }
        }
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    TheNicePair t = new TheNicePair();
    System.out.println(t.solve(new int[]{5, 5, 5, 5, 5}));
    System.out.println(t.solve(new int[]{1,1,1,1}));
    System.out.println(t.solve(new int[]{2,3,5,7}));
    System.out.println(t.solve(new int[]{8,8,5,5,5}));
    System.out.println(t.solve(new int[]{1,1000,1000,1,1000,1,999}));
    System.out.println(t.solve(new int[]{1000,1,1,1000}));
    System.out.println(t.solve(new int[]{1,1,953,1,1,1,1,1,1,1,1,1,1,1,1,953,1,953,953,1,1,1,1,1,1,1,953,953,953,1,1,1,1,1,953,953,1,1,1,953,953,953,1}));
  }
}
