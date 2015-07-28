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

import java.util.Stack;

public class BasicCalculatorII {
  public int calculate(String s) {
    Stack<Long> stack = new Stack<>();
    int i = 0;
    boolean flag = true;
    char shouldCal = ' ';
    while (i < s.length()) {
      char c = s.charAt(i);
      switch (c) {
        case ' ':
          break;
        case '+':
          flag = true;
          break;
        case '-':
          flag = false;
          break;
        case '*':
        case '/':
          shouldCal = c;
          break;
        default:
          int j = i;
          while (++i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9');
          long t = Long.parseLong(s.substring(j, i));
          if (shouldCal != ' ') {
            t = shouldCal == '*'? stack.pop()*t : stack.pop()/t;
            shouldCal = ' ';
          }
          stack.push(flag? t : -t);
          flag = true;
          continue;
      }
      i++;
    }
    long sum = 0;
    while (!stack.isEmpty()) {
      sum += stack.pop();
    }
    return (int) sum;
  }

  public static void main(String[] args) {
    BasicCalculatorII b = new BasicCalculatorII();
    System.out.println(b.calculate(" 12-3 * 4 "));
  }
}
