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

public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        ListNode p1 = head, p2 = head;
        while (p1 != null) {
            p1 = p1.next;
            if (p1 == null) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
            if (p1 == p2) {
                return true;
            }
        }
        return false;
    }
}
