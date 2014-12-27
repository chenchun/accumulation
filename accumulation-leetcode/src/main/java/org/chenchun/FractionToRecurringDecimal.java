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

import java.util.HashMap;
import java.util.Map;

public class FractionToRecurringDecimal {
  // 2/3 = 0.(6)
  // 2/30 = 0.0(6)
  // 1/11 = 0.(09)
  // 1/7 = 0.(142857)
  // 3/100 = 0.03
  // -2/5 = -0.4
  public String fractionToDecimal(int numerator, int denominator) {
    if (numerator == 0) {
      return "0";
    }
    StringBuilder sb = new StringBuilder();
    long num = numerator, dnm = denominator;
    if ((num^dnm) < 0) {
      sb.append("-");
      num = Math.abs(num);
      dnm = Math.abs(dnm);
    }
    sb.append(num/dnm);
    long left = num%dnm;
    if (left != 0) {
      sb.append(".");
      // key: reserved, value: index
      Map<Long, Integer> map = new HashMap<>();
      map.put(left, sb.length()-1);
      while (true) {
        left *= 10;
        if (left % dnm == 0) {
          sb.append(left/dnm);
          break;
        }
        if (map.containsKey(left)) {
          int index = map.get(left);
          sb.insert(index, "(");
          sb.append(")");
          break;
        } else {
          sb.append(left/dnm);
          map.put(left, sb.length()-1);
          left %= dnm;
        }
      }
    }
    return sb.toString();
  }
  
  public static void main(String[] args) {
    FractionToRecurringDecimal f = new FractionToRecurringDecimal();
    System.out.println(f.fractionToDecimal(0, -5));
    System.out.println(f.fractionToDecimal(2, -5));
    System.out.println(f.fractionToDecimal(-2, -5));
    System.out.println(f.fractionToDecimal(2, 3));
    System.out.println(f.fractionToDecimal(2, 30));
    System.out.println(f.fractionToDecimal(1, 11));
    System.out.println(f.fractionToDecimal(1, 7));
    System.out.println(f.fractionToDecimal(3, 100));
    System.out.println(f.fractionToDecimal(-1, -2147483648));
  }
}
