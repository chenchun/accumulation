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

public class BasicCalculator {
  public int calculate(String s) {
    boolean flag = true, accFlag = true;
    int i = 0;
    long left = 0;
    Stack<Boolean> stack = new Stack<>();
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
        case ')':
          boolean t = stack.pop();
          if (!t) {
            accFlag = !accFlag;
          }
          break;
        case '(':
          if (!flag) {
            accFlag = !accFlag;
          }
          stack.push(flag);
          flag = true;
          break;
        default:
          int j = i;
          while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            i++;
          }
          int right = Integer.parseInt(s.substring(j, i));
          if (flag == accFlag) {
            left += right;
          } else {
            left -= right;
          }
          continue;
      }
      i++;
    }
    return (int) left;
  }

  public static void main(String[] args) {
    BasicCalculator b = new BasicCalculator();
    System.out.println(b.calculate("1- (3-( 2-13))"));
    System.out.println(b.calculate("2-(5-6)"));
  }
}
