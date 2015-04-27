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
package org.chenchun.collection;

import org.junit.Assert;
import org.junit.Test;

public class TestHeap {
  @Test
  public void test() {
    Heap<Integer> heap = new Heap<>();
    Assert.assertEquals(0, heap.size());
    heap.add(5);
    Assert.assertEquals(1, heap.size());
    Assert.assertTrue(heap.equal(5));
    heap.add(1);
    Assert.assertTrue(heap.equal(1, 5));
    heap.add(4);
    Assert.assertTrue(heap.equal(1, 5, 4));
    heap.add(3);
    Assert.assertTrue(heap.equal(1, 3, 4, 5));
    heap.add(2);
    Assert.assertTrue(heap.equal(1, 2, 4, 5, 3));
    Assert.assertEquals(5, heap.size());
    Assert.assertTrue(1 == heap.pop());
    Assert.assertTrue(heap.equal(2, 3, 4, 5));
    Assert.assertEquals(4, heap.size());
    Assert.assertTrue(2 == heap.pop());
    Assert.assertTrue(heap.equal(3, 5, 4));
    Assert.assertTrue(3 == heap.pop());
    Assert.assertTrue(heap.equal(4, 5));
    Assert.assertTrue(4 == heap.pop());
    Assert.assertTrue(heap.equal(5));
    Assert.assertTrue(5 == heap.pop());
    Assert.assertEquals(0, heap.size());
    Assert.assertTrue(null == heap.pop());
    Assert.assertEquals(0, heap.size());
  }

  @Test
  public void testGrowHeap() {
    Heap<Integer> heap = new Heap<>(5, 1, 4, 3, 2);
    Assert.assertEquals(8, heap.capacity());
    heap.addElements(8, 6, 7);
    Assert.assertEquals(8, heap.capacity());
    heap.addElements(9, 10);
    Assert.assertEquals(16, heap.capacity());
    heap.addElements(11, 12, 13, 14, 15, 16);
    Assert.assertEquals(16, heap.capacity());
    heap.addElements(17);
    Assert.assertEquals(32, heap.capacity());
    int i = 0;
    while (++i <= 17) {
      Assert.assertTrue(i == heap.pop());
    }
  }
}
