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

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueues {
  private Queue<Integer> queue = new LinkedList<>();
  int t = 0;
  // Push element x onto stack.
  public void push(int x) {
    queue.add(x);
    t = x;
  }

  // Removes the element on top of the stack.
  public void pop() {
    int s = queue.size();
    while (--s > 0) {
      t = queue.poll();
      queue.add(t);
    }
    queue.poll();
  }

  // Get the top element.
  public int top() {
    return t;
  }

  // Return whether the stack is empty.
  public boolean empty() {
    return queue.size() == 0;
  }
}
