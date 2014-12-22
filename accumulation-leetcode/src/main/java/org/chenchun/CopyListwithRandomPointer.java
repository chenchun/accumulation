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

public class CopyListwithRandomPointer {
  class RandomListNode {
    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
      this.label = x;
    }
  }

  public RandomListNode copyRandomList(RandomListNode head) {
    RandomListNode p = head;
    while (p != null) {
      RandomListNode cloned = new RandomListNode(p.label);
      cloned.next = p.next;
      p.next = cloned;
      p = p.next.next;
    }
    p = head;
    while (p != null) {
      RandomListNode cloned = p.next;
      cloned.random = p.random == null ? null : p.random.next;
      p = p.next.next;
    }
    p = head;
    RandomListNode root = new RandomListNode(0), cp = root;
    while (p != null) {
      RandomListNode cloned = p.next;
      cp.next = cloned;
      cp = cp.next;
      p.next = p.next.next;
      p = p.next;
    }
    return root.next;
  }
}
