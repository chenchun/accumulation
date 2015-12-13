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
import java.util.List;
import java.util.PriorityQueue;

/**
 * TODO 在这里编写类的功能描述
 *
 * @author chenchun
 * @created 2015-12-13
 *
 * @version 1.0
 */
public class SuperUglyNumber {
  public int nthSuperUglyNumber(int n, int[] primes) {
    List<Integer> ugly = new ArrayList<>();
    ugly.add(1);
    PriorityQueue<Factor> q = new PriorityQueue<>();
    for (int i = 0; i < primes.length; i++) {
      q.add(new Factor(primes[i], ugly));
    }
    while (ugly.size() < n) {
      Factor f = q.poll();
      long v = f.value();
      ugly.add((int) v);
      q.add(f.increment());
      while (q.peek().value() == v) {
        q.add(q.poll().increment());
      }
    }
    return ugly.get(n-1);
  }

  public static class Factor implements Comparable<Factor> {
    private int factor;
    private int i;
    private List<Integer> ugly;
    public Factor(int f, List<Integer> ugly) {
      this.factor = f;
      this.i = 0;
      this.ugly = ugly;
    }
    public Factor increment() {
      this.i++;
      return this;
    }
    public long value() {
      return this.factor*ugly.get(i);
    }

    @Override
    public int compareTo(Factor o) {
      long v = this.value() - o.value();
      if (v == 0) {
        return 0;
      } else if (v > 0) {
        return 1;
      }
      return -1;
    }
  }

  public static void main(String[] args) {
    SuperUglyNumber s = new SuperUglyNumber();
    System.out.println(s.nthSuperUglyNumber(8, new int[]{2, 3, 5}));
  }
}
