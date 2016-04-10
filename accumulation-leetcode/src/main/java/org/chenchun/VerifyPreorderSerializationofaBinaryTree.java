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

public class VerifyPreorderSerializationofaBinaryTree {
  public boolean isValidSerialization(String preorder) {
    Stack<Integer> stack = new Stack<>();
    String[] arr = preorder.split(",");
    for (int i = 0; i < arr.length; i++) {
      if (arr[i].equals("#")) {
        Integer c = null;
        while (true) {
          if (stack.isEmpty()) {
            return i == arr.length-1;
          }
          if (stack.peek() != null) {
            stack.push(c);
            break;
          } else {
            stack.pop();
            if (stack.isEmpty()) {
              return false;
            }
            stack.pop();
          }
        }
      } else {
        stack.push(Integer.parseInt(arr[i]));
      }
    }
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    VerifyPreorderSerializationofaBinaryTree v = new VerifyPreorderSerializationofaBinaryTree();
    System.out.println(v.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
    System.out.println(v.isValidSerialization("9,#,92,#,#"));
  }
}
