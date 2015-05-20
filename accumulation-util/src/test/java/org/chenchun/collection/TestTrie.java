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

public class TestTrie {
  @Test
  public void test() {
    Trie trie = new Trie();
    trie.insert("abc");
    trie.insert("abd");
    trie.insert("e");
    Assert.assertTrue(trie.search("abc"));
    Assert.assertTrue(trie.search("abd"));
    Assert.assertTrue(trie.search("e"));
    Assert.assertFalse(trie.search("ab"));
    Assert.assertTrue(trie.startsWith("a"));
    Assert.assertTrue(trie.startsWith("ab"));
    Assert.assertTrue(trie.startsWith("abc"));
    Assert.assertTrue(trie.startsWith("abd"));
    Assert.assertTrue(trie.startsWith("e"));
    Assert.assertFalse(trie.startsWith("aba"));
  }
}
