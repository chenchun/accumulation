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

public class ConvertSortedArrayToBinarySearchTree {
  public TreeNode sortedArrayToBST(int[] num) {
    if (num == null) {
      return null;
    }
    return sortedArrayToBST(num, 0, num.length);
  }

  private TreeNode sortedArrayToBST(int[] num, int begin, int end) {
    if (end-begin == 0) {
      return null;
    }
    int mid = (begin+end)/2;
    TreeNode root = new TreeNode(num[mid]);
    root.left = sortedArrayToBST(num, begin, mid);
    root.right = sortedArrayToBST(num, mid+1, end);
    return root;
  }
}
