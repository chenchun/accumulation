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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PalindromePairs {
  public List<List<Integer>> palindromePairs(String[] words) {
    //empty string
    //suffix string, prefix string lls sssll
    List<List<Integer>> list = new ArrayList<>();
    Trie trie = initTrie(words);
    for (int i = 0; i < words.length; i++) {
      int len = words[i].length();
      String reverse = new StringBuilder(words[i]).reverse().toString();
      for (int j = 0; j <= len; j++) {
        String headStr = words[i].substring(0, len - j);
        String tailStr = words[i].substring(len - j);
        if (isPalindrome(tailStr)) {
          String reverseHeadStr = reverse.substring(j);
          Set<Integer> indexs = trie.search(reverseHeadStr);
          for (Integer index: indexs) {
            if (index == i) {
              continue;
            }
            list.add(Arrays.asList(i, index));
          }
        }
        if (j != 0 && j != len && isPalindrome(headStr)) {
          String reverseTailStr = reverse.substring(0, len - j);
          Set<Integer> indexs = trie.search(reverseTailStr);
          for (Integer index: indexs) {
            if (index == i) {
              continue;
            }
            list.add(Arrays.asList(i, index));
          }
        }
      }
    }
    return list;
  }

  private Trie initTrie(String[] words) {
    Trie trie = new Trie();
    for (int i = 0; i < words.length; i++) {
      trie.insert(words[i], i);
    }
    return trie;
  }

  private boolean isPalindrome(String s) {
    int i = 0, j = s.length()-1;
    while (i < j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }

  public static class Trie {
    private TrieNode root;

    public Trie() {
      root = new TrieNode();
    }

    class TrieNode {
      Character c;
      Set<Integer> set;
      HashMap<Character, TrieNode> children;

      // Initialize your data structure here.
      public TrieNode() {
        this(null);
      }

      public TrieNode(Character c) {
        this.children = new HashMap<>();
        this.c = c;
        this.set = new HashSet<>();
      }
    }

    // Inserts a word into the trie.
    public void insert(String word, int index) {
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
      n.set.add(index);
    }

    // Returns if the word is in the trie.
    public Set<Integer> search(String word) {
      TrieNode trieNode = find(word);
      if (trieNode == null) {
        return Collections.emptySet();
      }
      return trieNode.set;
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

  public static void main(String[] args) {
    PalindromePairs p = new PalindromePairs();
//    Util.print(
//      p.palindromePairs(new String[]{"bbcbb", ""}));
//    Util.print(
//      p.palindromePairs(new String[]{"abbcbb", "bbcbba", "cbba", "a"}));
//    Util.print(p.palindromePairs(new String[]{"ababaabbabaabbbbbaaababaaaabaaabbbbabbaaabbabbbabaabbbaabbbba","aabbbbabbababaabaaaaaaaaabbbabaabbaaaaabababaaaaabbbaabaaabbbbbaba","baabaaababbaabbbbaabbaabaabbabbaaaabbbbabbbbbaababbabbaaabbbabababbbbabababbaaabababbababababaaabaabbbbabbaabaaabbbbaabbb","aabbbaabbbabaabbbabaaabaaaaaaaabababbaaaabbbbaaabaababbbabaabbaaabaaabbabaaaaababaaaa","abababbbaaaaaabaabbbaaabaabbaabbababaaaaababbbbaabbbabaaaaabbbbbbabbababbbbabaabbbbaabbbbbaababbbabbbaabbaaabbbaabbabababaaaaaabaaba","aabbbbbbbaabbaabaaabaaaaaabbabaaaaababbbaaabbabaaababbbabababaabababbaabaaaabaaabbabaabaaabaabbbbabbbbaab","aaaaabaaaababbbbbbbbbabaaabbaabbbbababaababbabbbabb","baaababbbbabbaabbbababbaabbbaabaababbaaabbbbbbabbbbabbbbaabbababbaaaaababbbaabbbaaaabbaa","abbaabbbabbbbabaabbaaaaabbbb","aaabbbbbbbabaabbaaaabaabaaabbaabaaabbbbaabbababbbbbaabababbaaaaaabbbbbbabaababbbbbbbbbaabaaaabaabaaabaaabbaabbbaaaabbbbbbaaaabbaaaaabaabbbbabbbaaabaabbbaaabababaaaababbaabbbabbbaabaabaaaababbabbb"}));
    Util.print(p.palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"}));
  }
}
