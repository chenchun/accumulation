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

public class ConvertSortedListToBinarySearchTree {
  public TreeNode sortedListToBST(ListNode head) {
    if (head == null) {
      return null;
    } else if (head.next == null) {
      return new TreeNode(head.val);
    }
    ListNode p1 = head, p2 = head, leftTail = null;
    while (p1 != null && p1.next != null) {
      p1 = p1.next.next;
      leftTail = p2;
      p2 = p2.next;
    }
    leftTail.next = null;
    TreeNode root = new TreeNode(p2.val);
    root.left = sortedListToBST(head);
    root.right = sortedListToBST(p2.next);
    return root;
  }
}
