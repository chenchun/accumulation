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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerfectSquares {

  List<Integer> squares;
  int min;
  Map<Integer, Integer> map;

  public int numSquares(int n) {
    int max = (int)Math.floor(Math.sqrt(n));
    squares = squareList(max);
    min = Integer.MAX_VALUE;
    map = new HashMap<>();
    numSquares(n, 0);
    return min;
  }

  private void numSquares(int n, int num) {
    if (n == 0) {
      min = num;
      return;
    }
    if (map.containsKey(n) && map.get(n) <= num) {
      return;
    }
    map.put(n, num);
    if (num == min) {
      return;
    }
    int biggest = le(squares, n);
    for (int i = biggest; i >= 0; i--) {
      int part = n- squares.get(i);
      numSquares(part, num+1);
    }
  }

  private ArrayList<Integer> squareList(int max) {
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 1; i <= max; i++) {
      list.add(i*i);
    }
    return list;
  }

  private int le(List<Integer> list, int n) {
    for (int i = list.size()-1; i >= 0; i--) {
      if (list.get(i) <= n) {
        return i;
      }
    }
    return 0;
  }

  public static void main(String[] args) {
    PerfectSquares p = new PerfectSquares();
    System.out.println(p.numSquares(32));
  }
}
