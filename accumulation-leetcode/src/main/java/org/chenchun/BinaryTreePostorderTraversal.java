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

public class BinaryTreePostorderTraversal {
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> ret = new ArrayList<>();
    if (root != null) {
      Stack<TreeNode> s = new Stack<>();
      s.push(root);
      Stack<Integer> res = new Stack<>();
      while (!s.isEmpty()) {
        TreeNode n = s.pop();
        res.push(n.val);
        if (n.left != null) {
          s.push(n.left);
        }
        if (n.right != null) {
          s.push(n.right);
        }
      }
      while (!res.isEmpty()) {
        ret.add(res.pop());
      }
    }
    return ret;
  }
}
