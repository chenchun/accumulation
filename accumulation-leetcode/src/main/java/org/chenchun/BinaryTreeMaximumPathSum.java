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

public class BinaryTreeMaximumPathSum {
  public int maxPathSum(TreeNode root) {
    int[] max = maxPath(root);
    return max == null? 0 : max[1];
  }

  //[maxCrossPathSum, max]
  private int[] maxPath(TreeNode root) {
    if (root != null) {
      int[] arr = new int[2];
      int[] left = maxPath(root.left);
      int[] right = maxPath(root.right);
      arr[0] = root.val;
      arr[1] = root.val;
      if (left != null) {
        arr[0] = Math.max(left[0] + root.val, arr[0]);
        arr[1] = Math.max(left[1], arr[1]);
      }
      if (right != null) {
        arr[0] = Math.max(right[0] + root.val, arr[0]);
        arr[1] = Math.max(right[1], arr[1]);
      }
      if (left != null && right != null) {
        arr[1] = Math.max(root.val + left[0] + right[0], arr[1]);
      }
      arr[1] = Math.max(arr[1], arr[0]);
      return arr;
    }
    return null;
  }

  //{1,-2,-3,1,3,-2,#,-1}
  public static void main(String[] args) {
    TreeNode t = new TreeNode(1);
    t.left = new TreeNode(-2);
    t.right = new TreeNode(-3);
    t.left.left = new TreeNode(1);
    t.left.right = new TreeNode(3);
    t.right.left = null;
    t.right.right = new TreeNode(-1);
    BinaryTreeMaximumPathSum b = new BinaryTreeMaximumPathSum();
    System.out.println(b.maxPathSum(t));
  }
}
