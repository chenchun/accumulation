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
import java.util.Stack;

public class BinaryTreeZigzagLevelOrderTraversal {
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> list = new ArrayList<>();
    if (root != null) {
      Stack<TreeNode> stack1 = new Stack<>(), stack2 = new Stack<>();
      stack1.push(root);
      while (!stack1.isEmpty()) {
        if (!stack1.isEmpty()) {
          List<Integer> arr = new ArrayList<>();
          while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            arr.add(node.val);
            if (node.left != null) {
              stack2.push(node.left);
            }
            if (node.right != null) {
              stack2.push(node.right);
            }
          }
          list.add(arr);
        }
        if (!stack2.isEmpty()) {
          List<Integer> arr = new ArrayList<>();
          while (!stack2.isEmpty()) {
            TreeNode node = stack2.pop();
            arr.add(node.val);
            if (node.right != null) {
              stack1.push(node.right);
            }
            if (node.left != null) {
              stack1.push(node.left);
            }
          }
          list.add(arr);
        }
      }
    }
    return list;
  }
}
