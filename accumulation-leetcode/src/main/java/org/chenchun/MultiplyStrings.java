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
import java.util.List;

public class MultiplyStrings {
  public String multiply(String num1, String num2) {
    if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) {
      return "";
    }
    if (num1.equals("0") || num2.equals("0")) {
      return "0";
    }
    List<Integer> list = new ArrayList<>();
    char[] n1 = num1.toCharArray(), n2 = num2.toCharArray();
    int flag = 1, l1 = 0, l2 = 0;
    if (n1[0] == '-' || n1[0] == '+') {
      if (n1[0] == '-') {
        flag = -1;
      }
      l1++;
    }
    if (n2[0] == '-' || n2[0] == '+') {
      if (n2[0] == '-') {
        flag = -flag;
      }
      l2++;
    }
    int count = 0;
    for (int i = n1.length-1; i >= l1; i--) {
      int s = n1.length-i-1;
      int b = n1[i]-'0';
      for (int j = n2.length-1; j >= l2; j--, s++) {
        int a = n2[j]-'0';
        a = a*b+count;
        if (list.size() > s) {
          a += list.get(s);
        }
        count = a / 10;
        a %= 10;
        if (list.size() > s) {
          list.set(s, a);
        } else {
          list.add(a);
        }
      }
      if (count > 0) {
        list.add(count);
        count = 0;
      }
    }
    if (count > 0) {
      list.add(count);
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < list.size(); i++) {
      sb.insert(0, String.valueOf(list.get(i)));
    }
    if (flag < 0) {
      sb.insert(0, "-1");
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    MultiplyStrings m = new MultiplyStrings();
    System.out.println(m.multiply("98", "32"));
  }
}
