package org.chenchun;

import java.util.HashMap;

public class WordDictionary {

  private TrieNode root = new TrieNode();

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

  private TrieNode find(char[] chars, int index, TrieNode n) {
    int i = index;
    while (i < chars.length) {
      Character c = chars[i];
      i++;
      if (c == '.') {
        if (n.children.size() == 0) {
          return null;
        }
        for (TrieNode node : n.children.values()) {
          TrieNode t = find(chars, i, node);
          if (t != null && t.num > 0) {
            return t;
          }
        }
        return null;
      } else if (n.children.containsKey(c)) {
        n = n.children.get(c);
      } else {
        return null;
      }
    }
    return n == null? null : n.num > 0? n : null;
  }

  // Adds a word into the data structure.
  public void addWord(String word) {
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

  // Returns if the word is in the data structure. A word could
  // contain the dot character '.' to represent any one letter.
  public boolean search(String word) {
    TrieNode trieNode = find(word.toCharArray(), 0, root);
    return trieNode != null && trieNode.num > 0;
  }

  public static void main(String[] args) {
    WordDictionary wordDictionary = new WordDictionary();
    wordDictionary.addWord("word");
    wordDictionary.addWord("woa");
    System.out.println(wordDictionary.search("w"));
    System.out.println(wordDictionary.search("wor"));
    System.out.println(wordDictionary.search("woaa"));
    System.out.println(wordDictionary.search("word"));
    System.out.println(wordDictionary.search("woa"));
  }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");