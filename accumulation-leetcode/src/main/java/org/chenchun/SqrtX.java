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

public class SqrtX {
  public int sqrt(int x) {
    double z = 3;
    int n = 0;
    while (n != (int) Math.floor(z)) {
      n = (int) Math.floor(z);
      z = z - (z*z - x)/2/z;
    }
    return n;
  }

  public static void main(String[] args) {
    SqrtX s = new SqrtX();
    System.out.println(s.sqrt(0));
    System.out.println(s.sqrt(3));
    System.out.println(s.sqrt(4));
    System.out.println(s.sqrt(5));
    System.out.println(s.sqrt(6));
    System.out.println(s.sqrt(7));
    System.out.println(s.sqrt(8));
    System.out.println(s.sqrt(9));
    System.out.println(s.sqrt(2147395599));
  }
}
