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

public class HouseRobberIII {
  public int rob(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int[] arr = dfs(root);
    return Math.max(arr[0], arr[1]);
  }

  /**
   * returns [consist(n), notConsist(n)]
   */
  private int[] dfs(TreeNode n) {
    int[] left = new int[]{0, 0}, right = new int[]{0, 0};
    if (n.left != null) {
      left = dfs(n.left);
    }
    if (n.right != null) {
      right = dfs(n.right);
    }
    int consist = left[1] + right[1] + n.val;
    int notConsist = Math.max(left[0], left[1])+Math.max(right[0], right[1]);
    return new int[]{consist, notConsist};
  }
}
