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

public class ConstructBinaryTree {

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder == null || inorder == null || preorder.length != inorder.length || preorder.length == 0) {
      return null;
    }
    return buildTree(preorder, 0, preorder.length, inorder, 0, inorder.length);
  }
  public TreeNode buildTree(int[] preorder, int b1, int e1, int[] inorder, int b2, int e2) {
    TreeNode root = new TreeNode(preorder[b1]);
    int i = b2;
    for (; i < e2; i++) {
      if (inorder[i] == preorder[b1]) {
        break;
      }
    }
    if (i-b2 > 0) {
      root.left = buildTree(preorder, b1+1, b1+1+i-b2, inorder, b2, i);
    }
    if (e2-i-1>0) {
      root.right = buildTree(preorder, b1+1+i-b2, e1, inorder, i+1, e2);
    }
    return root;
  }
}
