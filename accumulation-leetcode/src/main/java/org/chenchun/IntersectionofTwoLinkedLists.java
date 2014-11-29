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

public class IntersectionofTwoLinkedLists {
  public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }
    ListNode p = headA;
    while (p.next != null) {
      p = p.next;
    }
    p.next = headA;
    ListNode tail = p;
    p = headB;
    headA = headB;
    while (p != null) {
      p = p.next;
      if (p != null) {
        p = p.next;
      } else {
        tail.next = null;
        return null;
      }
      headA = headA.next;
      if (p == headA) {
        break;
      }
    }
    if (p == null) {
      tail.next = null;
      return null;
    }
    while (headB != p) {
      p = p.next;
      headB = headB.next;
    }
    tail.next = null;
    return headB;
  }
}
