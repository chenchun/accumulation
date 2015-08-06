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

public class LowestCommonAncestorofaBinarySearchTree {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    TreeNode[] ret = new TreeNode[2];
    postOrder(root,p,q,ret);
    return ret[0];
  }

  public int postOrder(TreeNode root, TreeNode p, TreeNode q, TreeNode[] ret) {
    if (root == null) {
      return 0;
    }
    int l = postOrder(root.left,p,q,ret);
    if (l == 2) {
      return 2;
    }
    int r = postOrder(root.right,p,q,ret);
    if (r == 2) {
      return 2;
    }
    int o = (root == p ? 1:0) + (root == q? 1:0);
    if (l+r+o == 2) {
      ret[0] = root;
      return 2;
    }
    return l+r+o;
  }
}
