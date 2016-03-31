/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.chenchun;

public class PalindromeLinkeList {
  public boolean isPalindrome(ListNode head) {
    if (head == null) {
      return true;
    }
    ListNode p = head, c = head;
    while (c.next != null) {
      p = p.next;
      c = c.next;
      if (c.next != null) {
        c = c.next;
      }
    }
    c = p;
    p = head;
    ListNode pp = head;
    while (p != c) {
      pp = p;
      p = p.next;
    }
    pp.next = null;
    ListNode n = new ListNode(0);
    while (c != null) {
      p = c.next;
      c.next = n.next;
      n.next = c;
      c = p;
    }
    n = n.next;
    while (head != null && n != null) {
      if (head.val != n.val) {
        return false;
      }
      head = head.next;
      n = n.next;
    }
    return true;
  }
}
