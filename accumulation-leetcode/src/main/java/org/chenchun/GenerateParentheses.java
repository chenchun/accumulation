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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class GenerateParentheses {
  public List<String> generateParenthesis(int n) {
    Set<String> ret = new HashSet<String>();
    generateParenthesis(n, 0, ret, "");
    return new ArrayList<String>(ret);
  }

  public void generateParenthesis(int n, int rp, Set<String> set, String str) {
    if (n == 0) {
      while (rp-- > 0) {
        str += ")";
      }
      set.add(str);
    } else {
      if (n > 0) {
        String newStr = str+"(";
        generateParenthesis(n-1, rp+1, set, newStr);
      }
      if (rp > 0) {
        String newStr = str+")";
        generateParenthesis(n, rp-1, set, newStr);
      }
    }
  }

  public static void main(String[] args) {
    GenerateParentheses g = new GenerateParentheses();
    g.generateParenthesis(4);
  }

}
