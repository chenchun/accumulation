/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.chenchun;

public class RectangleArea {
  public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    return area(A,B,C,D)+area(E,F,G,H)-overlapping(A,C,E,G)*overlapping(B,D,F,H);
  }

  private int area(int A, int B, int C, int D) {
    return Math.abs((A-C)*(B-D));
  }

  private int overlapping(int A, int C, int E, int G) {
    if (A == C || E == G) {
      return 0;
    }
    int x1 = Math.min(A, C), x2 = Math.max(A, C), x3 = Math.min(E, G), x4 = Math.max(E, G);
    if (x1 > x3) {
      int t1 = x1, t2 = x2;
      x1 = x3;
      x2 = x4;
      x3 = t1;
      x4 = t2;
    }
    //x1 <= x3
    if (x2 <= x3) {
      return 0;
    } else {
      if (x2 <= x4) {
        return x2-x3;
      } else {
        return x4-x3;
      }
    }
  }
}
