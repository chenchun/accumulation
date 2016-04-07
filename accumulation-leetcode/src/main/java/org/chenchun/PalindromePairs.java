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
import java.util.Comparator;
import java.util.List;

public class PalindromePairs {
  public List<List<Integer>> palindromePairs(String[] words) {
    //empty string
    //suffix string, prefix string lls sssll
    List<List<Integer>> list = new ArrayList<>();
    Radix<Integer> radix = initRadixTree(words);
    for (int i = 0; i < words.length; i++) {
      int len = words[i].length();
      for (int j = 0; j <= len; j++) {
        String headStr = words[i].substring(0, j);
        String tailStr = words[i].substring(j);
        if (isPalindrome(tailStr)) {
          String reverseHeadStr = new StringBuilder(headStr).reverse().toString();
          List<Integer> indexs = radix.search(reverseHeadStr);
          if (indexs != null) {
            for (Integer index : indexs) {
              if (index == i) {
                continue;
              }
              list.add(Arrays.asList(i, index));
            }
          }
        }
        if (j != 0 && isPalindrome(headStr)) {
          String reverseTailStr = new StringBuilder(tailStr).reverse().toString();
          List<Integer> indexs = radix.search(reverseTailStr);
          if (indexs != null) {
            for (Integer index : indexs) {
              if (index == i) {
                continue;
              }
              list.add(Arrays.asList(index, i));
            }
          }
        }
      }
    }
    return list;
  }

  private Radix<Integer> initRadixTree(String[] words) {
    Radix<Integer> radix = new Radix<>();
    for (int i = 0; i < words.length; i++) {
      radix.insert(words[i], i);
    }
    return radix;
  }

  private boolean isPalindrome(String s) {
    int i = 0, j = s.length() - 1;
    while (i < j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }

  public static class Radix<T> {
    private Node<T> root;
    private int size;

    public Radix() {
      this.root = new Node<T>();
      this.size = 0;
    }

    private static class Node<T> {
      String prefix;
      //Edges to children
      List<Edge<T>> edges;

      LeafData<T> leafData;

      private Node() {
        this("", null);
      }

      /**
       * Create a new Node
       *
       * @param key
       * @param t null if node is not a leaf node, otherwise the value
       */
      public Node(String key, T t) {
        this.prefix = key;
        this.edges = new ArrayList<>();
        this.leafData = new LeafData<T>();
        if (t != null) {
          this.leafData.add(t);
        }
      }

      public int getEdgeIndex(Character i) {
        return binarySearch(this.edges, i);
      }

      private Edge<T> getEdge(Character i) {
        int index = getEdgeIndex(i);
        if (index == -1) {
          return null;
        }
        return this.edges.get(index);
      }

      private int binarySearch(List<Edge<T>> list, Character i) {
        int begin = 0, end = list.size()-1;
        while (begin <= end) {
          int mid = (begin+end)/2;
          int compare = list.get(mid).label - i;
          if (compare < 0) {
            begin = mid+1;
          } else if (compare == 0) {
            return mid;
          } else {
            end = mid-1;
          }
        }
        return -1;
      }

      private void addEdge(Edge<T> edge) {
        this.edges.add(edge);
        Collections.sort(this.edges, new Comparator<Edge<T>>() {
          @Override
          public int compare(Edge<T> o1, Edge<T> o2) {
            return o1.label - o2.label;
          }
        });
      }
    }

    private static class Edge<T> {
      Character label;
      //Child node
      Node<T> node;
      public Edge(Node<T> node, Character label) {
        this.node = node;
        this.label = label;
      }
    }

    private static class LeafData<T> {
      int num;
      List<T> data;

      public LeafData() {
        this.data = new ArrayList<>();
      }

      public LeafData<T> add(T t) {
        this.data.add(t);
        this.num++;
        return this;
      }
    }

    public void insert(String key, T t) {
      Node<T> node = root;
      String k = key;
      this.size++;
      while (true) {
        if (k.length() == 0) {
          node.leafData.add(t);
          return;
        }
        Node<T> parent = node;
        int index = node.getEdgeIndex(k.charAt(0));
        if (index == -1) {
          parent.addEdge(new Edge<T>(new Node<T>(k, t), k.charAt(0)));
          return;
        }
        Edge<T> edge = node.edges.get(index);
        node = edge.node;
        int commonLen = longestCommonPrefix(node.prefix, k);
        if (commonLen == node.prefix.length()) {
          if (k.length() == node.prefix.length()) {
            node.leafData.add(t);
            return;
          } else {
            k = k.substring(node.prefix.length());
            continue;
          }
        }
        // split node           parent
        //   parent               |
        //     |        ->      child
        //    node                |
        //                       node
        //
        // insert child node between parent and node
        Node<T> child = new Node<T>(k.substring(0, commonLen), null);
        parent.edges.set(index, new Edge<T>(child, k.charAt(0)));
        node.prefix = node.prefix.substring(commonLen);
        child.addEdge(new Edge<T>(node, node.prefix.charAt(0)));
        k = k.substring(commonLen);
        if (k.length() == 0) {
          // if k is empty now, add a leaf at this node
          child.leafData.add(t);
          return;
        }
        // if k is not empty, add a node
        child.addEdge(new Edge<T>(new Node<T>(k, t), k.charAt(0)));
        return;
      }
    }

    public boolean contains(String key) {
      return search(key) != null;
    }

    public List<T> search(String key) {
      Node<T> node = root;
      String k = key;
      while (true) {
        if (k.length() == 0) {
          if (node.leafData.data.size() != 0) {
            return node.leafData.data;
          }
          return null;
        }
        Edge<T> edge = node.getEdge(k.charAt(0));
        if (edge == null) {
          return null;
        }
        node = edge.node;
        if (k.startsWith(node.prefix)) {
          k = k.substring(node.prefix.length());
          continue;
        }
        return null;
      }
    }


    /**
     * @param str1
     * @param str2
     * @return the length of the longest common prefix
     */
    public int longestCommonPrefix(String str1, String str2) {
      int m = Math.min(str1.length(), str2.length());
      for (int i = 0; i < m; i++) {
        if (str1.charAt(i) != str2.charAt(i)) {
          return i;
        }
      }
      return m;
    }

    public int size() {
      return this.size;
    }
  }

  public static void main(String[] args) {
    PalindromePairs p = new PalindromePairs();
//    Util.print(p.palindromePairs(new String[]{"bbcbb", ""}));
//    Util.print(p.palindromePairs(new String[]{"abbcbb", "bbcbba", "cbba", "a"}));
//    Util.print(p.palindromePairs(new String[]{"ababaabbabaabbbbbaaababaaaabaaabbbbabbaaabbabbbabaabbbaabbbba","aabbbbabbababaabaaaaaaaaabbbabaabbaaaaabababaaaaabbbaabaaabbbbbaba","baabaaababbaabbbbaabbaabaabbabbaaaabbbbabbbbbaababbabbaaabbbabababbbbabababbaaabababbababababaaabaabbbbabbaabaaabbbbaabbb","aabbbaabbbabaabbbabaaabaaaaaaaabababbaaaabbbbaaabaababbbabaabbaaabaaabbabaaaaababaaaa","abababbbaaaaaabaabbbaaabaabbaabbababaaaaababbbbaabbbabaaaaabbbbbbabbababbbbabaabbbbaabbbbbaababbbabbbaabbaaabbbaabbabababaaaaaabaaba","aabbbbbbbaabbaabaaabaaaaaabbabaaaaababbbaaabbabaaababbbabababaabababbaabaaaabaaabbabaabaaabaabbbbabbbbaab","aaaaabaaaababbbbbbbbbabaaabbaabbbbababaababbabbbabb","baaababbbbabbaabbbababbaabbbaabaababbaaabbbbbbabbbbabbbbaabbababbaaaaababbbaabbbaaaabbaa","abbaabbbabbbbabaabbaaaaabbbb","aaabbbbbbbabaabbaaaabaabaaabbaabaaabbbbaabbababbbbbaabababbaaaaaabbbbbbabaababbbbbbbbbaabaaaabaabaaabaaabbaabbbaaaabbbbbbaaaabbaaaaabaabbbbabbbaaabaabbbaaabababaaaababbaabbbabbbaabaabaaaababbabbb"}));
//    Util.print(p.palindromePairs(new String[]{"abcd", "dcba", "lls", "s", "sssll"}));
//    Util.print(p.palindromePairs(new String[]{"a","abc","aba",""}));
    Util.print(p.palindromePairs(new String[]{"bb","bababab","baab","abaabaa","aaba","","bbaa","aba","baa","b"}));
  }
}
