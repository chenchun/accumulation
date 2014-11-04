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

public class PartitionList {

   public class ListNode {
     int val;
     ListNode next;

     ListNode(int x) {
       val = x;
       next = null;
     }
   }

   public ListNode partition(ListNode head, int x) {
    if (head == null) {
      return null;
    }
    ListNode small = null, smallHead = null, big = null, bigHead = null;
    ListNode p = head;
    while (p != null) {
      if (p.val < x) {
        if (small == null) {
          small = new ListNode(p.val);
          smallHead = small;
        } else {
          small.next = new ListNode(p.val);
          small = small.next;
        }
      } else {
        if (big == null) {
          big = new ListNode(p.val);
          bigHead = big;
        } else {
          big.next = new ListNode(p.val);
          big = big.next;
        }
      }
      p = p.next;
    }
    if (small != null) {
      small.next = bigHead;
    } else {
      smallHead = bigHead;
    }
    return smallHead;
  }
}
