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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class MergeIntervals {
  public static class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }

    @Override
    public String toString() {
      return "[" + start + "," + end + "]";
    }
  }

  public List<Interval> merge(List<Interval> intervals) {
    if (intervals == null || intervals.size() == 0) {
      return intervals;
    }
    Collections.sort(intervals, new Comparator<Interval>() {
      @Override
      public int compare(Interval o1, Interval o2) {
        return o1.start - o2.start;
      }
    });
    ListIterator<Interval> it = intervals.listIterator();
    while (it.hasNext()) {
      Interval p = it.next();
      if (!it.hasNext()) {
        return intervals;
      }
      Interval c = it.next();
      if (p.end >= c.start) {
        p.end = Math.max(p.end, c.end);
        it.remove();
      }
      it.previous();
    }
    return intervals;
  }

  public static void main(String[] args) {
    MergeIntervals m = new MergeIntervals();
    String input = "1,4],[0,2],[3,5";
    ArrayList<Interval> inputs = new ArrayList<>();
    String[] strs = input.split("],\\[");
    for (String str : strs) {
      String[] num = str.split(",");
      inputs.add(new Interval(Integer.parseInt(num[0]),
          Integer.parseInt(num[1])));
    }
    List<Interval> merge = m.merge(inputs);
    for (Interval itv : merge) {
      System.out.print(itv);
    }
  }
}
