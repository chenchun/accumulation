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

public class SumRoottoLeafNumbers {
  public int sumNumbers(TreeNode root) {
    return sumNumbers(root, new StringBuilder());
  }

  private int sumNumbers(TreeNode root, StringBuilder sb) {
    if (root != null) {
      sb.append(root.val);
      if (root.left == null && root.right == null) {
        int sum = Integer.parseInt(sb.toString());
        sb.deleteCharAt(sb.length()-1);
        return sum;
      }
      int sum = 0;
      if (root.left != null) {
        sum += sumNumbers(root.left, sb);
      }
      if (root.right != null) {
        sum += sumNumbers(root.right, sb);
      }
      sb.deleteCharAt(sb.length()-1);
      return sum;
    }
    return 0;
  }
}
