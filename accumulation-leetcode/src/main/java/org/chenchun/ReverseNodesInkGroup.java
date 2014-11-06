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

/**
 * TODO 在这里编写类的功能描述
 *
 * @author chenchun
 * @version 1.0
 * @created 2014-11-06
 */
public class ReverseNodesInkGroup {

  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || k <= 1) {
      return head;
    }
    ListNode begin = head, end = head, lastTail = null;
    ListNode ret = head;
    while (end != null) {
      int count = 1;
      while (end.next != null && count != k) {
        count++;
        end = end.next;
      }
      if (count == k) {
        if (ret == head) {
          ret = end;
        }
        if (lastTail != null) {
          lastTail.next = end;
        }
        lastTail = begin;
        ListNode nextHead = end.next;
        while (begin != end) {
          ListNode n = begin;
          begin = begin.next;
          n.next = end.next;
          end.next = n;
        }
        begin = nextHead;
        end = nextHead;
      } else {
        break;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    ListNode n = new ListNode(1);
    n.next = new ListNode(2);
    n.next.next = new ListNode(3);
    n.next.next.next = new ListNode(4);
    ReverseNodesInkGroup r = new ReverseNodesInkGroup();
    r.reverseKGroup(n, 2);
  }
}
