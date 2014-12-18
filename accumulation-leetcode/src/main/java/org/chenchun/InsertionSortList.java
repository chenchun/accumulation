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

public class InsertionSortList {
  public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  public ListNode insertionSortList(ListNode head) {
    ListNode root = new ListNode(0);
    while (head != null) {
      ListNode n = root;
      while (n.next != null) {
        if (n.next.val < head.val) {
          n = n.next;
        } else {
          ListNode temp = head;
          head = head.next;
          temp.next = n.next;
          n.next = temp;
          break;
        }
      }
      if (n.next == null) {
        n.next = head;
        head = head.next;
        n.next.next = null;
      }
    }
    return root.next;
  }
}
