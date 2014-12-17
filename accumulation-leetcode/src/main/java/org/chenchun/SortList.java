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

public class SortList {

  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  public ListNode sortList(ListNode head) {
    if (head == null) {
      return null;
    } else if (head.next == null) {
      return head;
    }
    ListNode p1 = new ListNode(0), p2 = new ListNode(0), p = head.next, mid = head, p3 = mid;
    ListNode h1 = p1, h2 = p2;
    while (p != null) {
      if (p.val < mid.val) {
        p1.next = p;
        p1 = p;
      } else if (p.val > mid.val) {
        p2.next = p;
        p2 = p;
      } else {
        p3.next = p;
        p3 = p;
      }
      p = p.next;
    }
    p1.next = null;
    p2.next = null;
    p3.next = null;
    ListNode n1 = sortList(h1.next);
    ListNode n2 = sortList(h2.next);
    p3.next = n2;
    if (n1 != null) {
      head = n1;
      while (n1.next != null) n1 = n1.next;
      n1.next = mid;
      return head;
    } else {
      return mid;
    }
  }

  public static void main(String[] args) {
    ListNode n = new ListNode(3);
    n.next = new ListNode(2);
    n.next.next = new ListNode(4);
    SortList sl = new SortList();
    sl.sortList(n);
  }
}
