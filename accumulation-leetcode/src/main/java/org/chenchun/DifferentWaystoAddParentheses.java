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
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// 1,2,3 1,3,2 2,1,3 2,3,1 3,2,1
public class DifferentWaystoAddParentheses {
  public List<Integer> diffWaysToCompute(String input) {
    List<Integer> list = new ArrayList<>();
    if (input == null || input.length() == 0) {
      return list;
    }
    Stack<Character> sc = new Stack<>();
    Stack<Integer> si = new Stack<>();
    LinkedList<Character> qc = new LinkedList<>();
    LinkedList<Integer> qi = new LinkedList();
    int i = 0;
    while (i < input.length()) {
      char c = input.charAt(i);
      switch (c) {
        case '+':
        case '-':
        case '*':
          qc.add(c);
          i++;
          break;
        default:
          int b = i;
          while (++i < input.length() && input.charAt(i) >= '0' && input.charAt(i) <= '9');
          qi.add(Integer.parseInt(input.substring(b, i)));
          break;
      }
    }
    si.push(qi.poll());
    compute(qc, qi, sc, si, list);
    return list;
  }

  //push pop push pop push pop
  //push pop push push pop pop
  //push push pop pop push pop
  //push push pop push pop pop
  //push push push pop pop pop
  private void compute(LinkedList<Character> qc, LinkedList<Integer> qi, Stack<Character> sc, Stack<Integer> si, List<Integer> ret) {
    if (qc.size() == 0) {
      while (sc.size() > 0) {
        pop(qc, qi, sc, si);
      }
      ret.add(si.pop());
      return;
    }
    LinkedList<Character> cqc = new LinkedList<>(qc);
    LinkedList<Integer> cqi = new LinkedList<>(qi);
    Stack<Character> csc = (Stack<Character>) sc.clone();
    Stack<Integer> csi = (Stack<Integer>) si.clone();
    push(cqc, cqi, csc, csi);
    compute(cqc, cqi, csc, csi, ret);
    int i = sc.size();
    while (i-- > 0) {
      pop(qc, qi, sc, si);
      cqc = new LinkedList<>(qc);
      cqi = new LinkedList<>(qi);
      csc = (Stack<Character>) sc.clone();
      csi = (Stack<Integer>) si.clone();
      push(cqc, cqi, csc, csi);
      compute(cqc, cqi, csc, csi, ret);
    }
  }

  private void push(LinkedList<Character> qc, LinkedList<Integer> qi, Stack<Character> sc, Stack<Integer> si) {
    sc.push(qc.poll());
    si.push(qi.poll());
  }

  private void pop(LinkedList<Character> qc, LinkedList<Integer> qi, Stack<Character> sc, Stack<Integer> si) {
    Integer j = si.pop(), i = si.pop();
    si.push(compute(i, j, sc.pop()));
  }

  private Integer compute(Integer i, Integer j, char c) {
    switch (c) {
      case '+':
        return i+j;
      case '-':
        return i-j;
      case '*':
        return i*j;
    }
    throw new RuntimeException();
  }

  public static void main(String[] args) {
    DifferentWaystoAddParentheses d = new DifferentWaystoAddParentheses();
    Util.printCollection(d.diffWaysToCompute("2-1-1"));
    Util.printCollection(d.diffWaysToCompute("2*3-4*5"));
    Util.printCollection(d.diffWaysToCompute("15-7*6+24"));
  }
}
