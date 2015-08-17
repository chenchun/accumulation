package org.chenchun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class WordSearchII {

  private HashSet<String> ret;

  public List<String> findWords(char[][] board, String[] words) {
    ret = new HashSet<>();
    if (words == null || words.length == 0 || board == null || board.length == 0 || board[0].length == 0) {
      return Collections.emptyList();
    }
    boolean[][] visited = new boolean[board.length][board[0].length];
    Trie trie = new Trie();
    for (String word : words) {
      if (word.length() <= board.length*board[0].length) {
        trie.insert(word);
      }
    }

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (trie.root.children.containsKey(board[i][j])) {
          visited[i][j] = true;
          search(board, trie.root.children.get(board[i][j]), i, j, visited);
          visited[i][j] = false;
        }
      }
    }
    return new ArrayList<>(ret);
  }

  private boolean search(char[][] board, TrieNode node, int i, int j, boolean[][] visited) {
    boolean success = false;
    if (node.num > 0 && node.parent != null) {
      ret.add(node.toWord());
      success = true;
    }
    if (i+1 < board.length && !visited[i+1][j] && node.children.containsKey(board[i+1][j]) && !node.children.get(board[i+1][j]).visit) {
      visited[i+1][j] = true;
      if (search(board, node.children.get(board[i+1][j]), i+1, j, visited)) {
        success = true;
        markVisit(node);
      }
      visited[i+1][j] = false;
    }
    if (j+1 < board[0].length && !visited[i][j+1] && node.children.containsKey(board[i][j+1]) && !node.children.get(board[i][j+1]).visit) {
      visited[i][j+1] = true;
      if (search(board, node.children.get(board[i][j+1]), i, j+1, visited)) {
        success = true;
        markVisit(node);
      }
      visited[i][j+1] = false;
    }
    if (i-1 >= 0 && !visited[i-1][j] && node.children.containsKey(board[i-1][j]) && !node.children.get(board[i-1][j]).visit) {
      visited[i-1][j] = true;
      if (search(board, node.children.get(board[i-1][j]), i-1, j, visited)) {
        success = true;
        markVisit(node);
      }
      visited[i-1][j] = false;
    }
    if (j-1 >= 0 && !visited[i][j-1] && node.children.containsKey(board[i][j-1]) && !node.children.get(board[i][j-1]).visit) {
      visited[i][j-1] = true;
      if (search(board, node.children.get(board[i][j-1]), i, j-1, visited)) {
        success = true;
        markVisit(node);
      }
      visited[i][j-1] = false;
    }
    return success;
  }

  private void markVisit(TrieNode node) {
    if (node.children.size() == 1) {
      for (TrieNode n : node.children.values()) {
          if (n.visit) {
            node.visit = true;
          }
      }
    }
  }


  private static class TrieNode {
    // Initialize your data structure here.
    Character c;
    int num;
    HashMap<Character, TrieNode> children;
    TrieNode parent;
    boolean visit = false;

    public TrieNode() {
      children = new HashMap<>();
    }

    public TrieNode(Character c, TrieNode parent) {
      this();
      this.c = c;
      this.parent = parent;
      num = 0;
    }

    public String toWord() {
      Stack<Character> stack = new Stack<>();
      TrieNode n = this;
      while (n.c != null) {
        stack.push(n.c);
        n = n.parent;
      }
      char[] arr = new char[stack.size()];
      int i = 0;
      while (!stack.isEmpty()) {
        arr[i++] = stack.pop();
      }
      return new String(arr);
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
        TrieNode node = new TrieNode(c, n);
        n.children.put(c, node);
        n = node;
        i++;
      }
      n.num++;
    }
  }
}
