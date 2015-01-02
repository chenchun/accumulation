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

public class ValidateBinarySearchTree {
  public boolean isValidBST(TreeNode root) {
    return isValidBST(root, null, null);
  }

  private boolean isValidBST(TreeNode node, Integer max, Integer min) {
    if (node != null) {
      if (max != null && node.val >= max) {
        return false;
      }
      if (min != null && node.val <= min) {
        return false;
      }
      if (!isValidBST(node.left, node.val, min)) {
        return false;
      }
      if (!isValidBST(node.right, max, node.val)) {
        return false;
      }
    }
    return true;
  }
}
