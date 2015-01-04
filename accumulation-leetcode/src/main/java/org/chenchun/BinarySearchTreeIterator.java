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

import java.util.Stack;

public class BinarySearchTreeIterator {

  public class BSTIterator {

    private TreeNode node;
    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
      stack = new Stack<>();
      node = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
      if (stack.isEmpty() && node == null) {
        return false;
      }
      return true;
    }

    /** @return the next smallest number */
    public int next() {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }
      if (stack.isEmpty()) {
        throw new RuntimeException("There is no next element");
      }
      TreeNode n = stack.pop();
      node = n.right;
      return n.val;
    }
  }

}
