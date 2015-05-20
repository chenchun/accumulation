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

import java.util.HashMap;

public class ImplementTrie {
  class TrieNode {
    Character c;
    int num;
    HashMap<Character, TrieNode> children;
    // Initialize your data structure here.
    public TrieNode() {
      children = new HashMap<>();
    }

    public TrieNode(Character c) {
      this();
      this.c = c;
      num = 0;
    }
  }

  public class Trie {
    private TrieNode root;

    public Trie() {
      root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
      char[] chars = word.toCharArray();
      int i = 0;
      TrieNode n = root;
      while (i < chars.length) {
        Character c = chars[i];
        if (n.children.containsKey(c)) {
          n = n.children.get(c);
          i++;
        } else {
          break;
        }
      }

      while (i < chars.length) {
        Character c = chars[i];
        TrieNode node = new TrieNode(c);
        n.children.put(c, node);
        n = node;
        i++;
      }
      n.num++;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
      TrieNode trieNode = find(word);
      return trieNode != null && trieNode.num > 0;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
      return find(prefix) != null;
    }

    private TrieNode find(String s) {
      char[] chars = s.toCharArray();
      int i = 0;
      TrieNode n = root;
      while (i < chars.length) {
        Character c = chars[i];
        if (n.children.containsKey(c)) {
          n = n.children.get(c);
          i++;
        } else {
          return null;
        }
      }
      return n;
    }
  }
}
