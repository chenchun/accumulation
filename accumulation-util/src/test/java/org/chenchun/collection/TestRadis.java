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
package org.chenchun.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class TestRadis {

  @Test
  public void testInsertContainsSearch() {
    /**    root
     *      |
     *      ab
     *     /  \
     *    c   d
     *   / \
     *  de ef
     */
    Radix<Integer> radix = new Radix<>();
    radix.insert("abc", 0);
    radix.insert("abcde", 1);
    radix.insert("abcef", 2);
    radix.insert("abd", 3);
    Assert.assertTrue(radix.contains("abc"));
    Assert.assertTrue(radix.contains("abcde"));
    Assert.assertTrue(radix.contains("abcef"));
    Assert.assertTrue(radix.contains("abd"));
    Assert.assertFalse(radix.contains(""));
    Assert.assertFalse(radix.contains("a"));
    Assert.assertFalse(radix.contains("b"));
    Assert.assertFalse(radix.contains("ab"));
    Assert.assertFalse(radix.contains("abce"));
    Assert.assertFalse(radix.contains("abcd"));

    Assert.assertArrayEquals(new Integer[]{0},
      radix.search("abc").toArray(new Integer[]{}));
    Assert.assertArrayEquals(new Integer[]{1},
      radix.search("abcde").toArray(new Integer[]{}));
    Assert.assertArrayEquals(new Integer[]{2},
      radix.search("abcef").toArray(new Integer[]{}));
    Assert.assertArrayEquals(new Integer[]{3},
      radix.search("abd").toArray(new Integer[]{}));
    Assert.assertNull(radix.search(""));
    Assert.assertNull(radix.search("a"));
    Assert.assertNull(radix.search("b"));
    Assert.assertNull(radix.search("ab"));
    Assert.assertNull(radix.search("abce"));
    Assert.assertNull(radix.search("abcd"));
    Assert.assertEquals(4, radix.size());

    radix.insert("ab", 4);
    Assert.assertArrayEquals(new Integer[]{4},
      radix.search("ab").toArray(new Integer[]{}));
    radix.insert("abc", 5);
    Assert.assertArrayEquals(new Integer[]{0, 5},
      radix.search("abc").toArray(new Integer[]{}));
    radix.insert("", 6);
    Assert.assertArrayEquals(new Integer[]{6},
      radix.search("").toArray(new Integer[]{}));
  }

  @Test
  public void testLongestCommonPrefix() {
    Radix<Integer> radix = new Radix<>();
    Assert.assertEquals(0, radix.longestCommonPrefix("", "abc"));
    Assert.assertEquals(3, radix.longestCommonPrefix("abc", "abc"));
    Assert.assertEquals(3, radix.longestCommonPrefix("abcde", "abc"));
    Assert.assertEquals(3, radix.longestCommonPrefix("abc", "abcde"));
  }
}
