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

public class PopulatingNextRightPointersInEachNode {

  public static class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;

    TreeLinkNode(int x) {
      val = x;
    }
  }

  public void connect(TreeLinkNode root) {
    TreeLinkNode node = root, leftSibling = null, nextStage = null;
    while (node != null) {
      if (leftSibling != null) {
        leftSibling.next = node.left;
      }
      if (node.left != null) {
        node.left.next = node.right;
        nextStage = nextStage == null ? node.left : nextStage;
      }
      if (node.next != null) {
        leftSibling = node.right;
        node = node.next;
      } else {
        node = nextStage;
        leftSibling = null;
        nextStage = null;
      }
    }
  }
}
