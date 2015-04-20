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
package org.chenchun.srm656;

import java.util.LinkedList;

public class RandomPancakeStackDiv2 {
  public double expectedDeliciousness(int[] d) {
    double[] ret = new double[]{0};
    LinkedList<Integer> list = new LinkedList<>();
    for (int i = 0; i < d.length; i++) {
      list.add(i);
    }
    dfs(list, new LinkedList<Integer>(), ret, d);
    return ret[0];
  }

  public void dfs(LinkedList<Integer> list, LinkedList<Integer> part, double[] ret, int[] d) {
    int partSize = part.size();
    boolean all = list.size() == 0;
    boolean biggerThanLast = partSize >= 2 && part.get(partSize-1) > part.get(partSize-2);
    if (all || biggerThanLast) {
      double expected = 1, del = 0;
      int total = d.length;
      for (int i = 0; i < partSize; i++) {
        del += d[part.get(i)];
        expected *= 1.0 / total--;
      }
      if (biggerThanLast) {
        del -= d[part.get(partSize-1)];
      }
      ret[0] += del*expected;
    } else {
      for (int i = 0; i < list.size(); i++) {
        LinkedList<Integer> copy = new LinkedList<>(list), copyPart = new LinkedList<>(part);
        copyPart.add(copy.remove(i));
        dfs(copy, copyPart, ret, d);
      }
    }
  }

  public static void main(String[] args) {
    RandomPancakeStackDiv2 r = new RandomPancakeStackDiv2();
    System.out.println(r.expectedDeliciousness(new int[]{1,1,1}));
    System.out.println(r.expectedDeliciousness(new int[]{10, 20}));
    System.out.println(r.expectedDeliciousness(new int[]{3,6,10,9,2}));
  }
}
