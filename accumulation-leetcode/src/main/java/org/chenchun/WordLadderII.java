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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 终于Accept了，最开始怎么都TLE，使用树形结构存储时（parent只有一个），每一层会有重复的节点，将parent改为Set
 * 后，每次有重复时，只遍历一次，就过了！
 */
public class WordLadderII {

  private static class Node {
    public final static Node END = new Node(null);
    Set<Node> parents = new HashSet<Node>();
    Set<Node> children = new HashSet<Node>();
    String word;
    public Node(String word) {
      this.word = word;
    }
    public void addParent(Node n) {parents.add(n);}
    public void addChild(Node n) {children.add(n);}
    public int hashCode() {return word == null? 0 : word.hashCode();}
    public boolean equals(Node n) {return this.word == null? n.word == null :
        this.word.equals(n.word);}
  }

  public List<List<String>> findLadders(String start, String end, Set<String> dict) {
//    System.out.println("dict size " + dict.size());
    List<List<String>> ret = new ArrayList<List<String>>();
    Queue<Node> q = new LinkedList<Node>();
    Node root = new Node(start);
    q.add(root);
    q.add(Node.END);
    boolean found = false;
    boolean firstFound = false;
    //每一层可能有重复的节点出现，需要遍历完一层后再从dict中删除
    Map<String, Node> toRemove = new HashMap<String, Node>();
    Set<String> pathSet = new HashSet<String>();
    pathSet.add(start);
    int count = 0;
    while (!q.isEmpty()) {
      Node parent = q.remove();
      if (parent == Node.END) {
        //每层的结束
        if (firstFound) {
          break;
        }
        if (!q.isEmpty()) {
          for (String str : toRemove.keySet()) {
            dict.remove(str);
            pathSet.add(str);
          }
          q.add(Node.END);
        }
        continue;
      }
//      System.out.println(++count + " " + parent.word);
      if (firstFound && !canTransformTo(parent.word, end)) {
        //如果已经找到最短路径并且不同的单词位数已经多于1位，不需要遍历
        continue;
      }
      String word = parent.word;
      for (int i = 0; i < word.length(); i++) {
        char[] chars = word.toCharArray();
        for (char j = 'a'; j <= 'z'; j++) {
          if (j == chars[i]) {
            continue;
          }
          chars[i] = j;
          String replace = new String(chars);
          if (replace.equals(end)) {
            List<String> list = new ArrayList<String>();
            list.add(end);
            backtrack(parent, list, ret);
            firstFound = true;
            found = true;
            break;
          } else if (dict.contains(replace)) {
            if (pathSet.contains(replace)) {
              dict.remove(replace);
            } else {
              if (toRemove.containsKey(replace)) {
                toRemove.get(replace).addParent(parent);
              } else {
                Node node = new Node(replace);
                node.addParent(parent);
                parent.addChild(node);
                q.add(node);
                toRemove.put(replace, node);
              }
            }
          }
        }
        if (found) {
          found = false;
          break;
        }
      }
    }
    return ret;
  }

  private boolean canTransformTo(String begin, String end) {
    int count = 0;
    for (int i = 0; i < begin.length() && i < end.length(); i++) {
      if (begin.charAt(i) != end.charAt(i)) {
        count++;
        if (count == 2) {
          return i == begin.length() && i == end.length();
        }
      }
    }
    return count == 1;
  }

  private void backtrack(Node node, List<String> list, List<List<String>> ret) {
    list.add(node.word);
    Set<Node> set = node.parents;
    if (!set.isEmpty()) {
      int k = 0;
      for (Node parent : set) {
        if (k != set.size() - 1) {
          backtrack(parent, new ArrayList<String>(list), ret);
        } else {
          backtrack(parent, list, ret);
        }
        k++;
      }
    } else {
      Collections.reverse(list);
      ret.add(list);
    }
  }

  public static void main(String[] args) {
    WordLadderII w = new WordLadderII();
    List<List<String>> ladders = w.findLadders("hit", "cog", new HashSet<String>(Arrays.asList("hot", "dot", "dog", "lot", "log")));
    Util.print(ladders);
    List<List<String>> ladders1 = w.findLadders("red", "tax",
        new HashSet<String>(Arrays.asList("ted", "tex", "red", "tax", "tad",
            "den", "rex", "pee", "rad", "bad")));
    Util.print(ladders1);
    List<List<String>> ladders2 = w.findLadders("nape", "mild", new HashSet<String>(Arrays.asList("dose", "ends", "dine", "jars", "prow", "soap", "guns", "hops", "cray", "hove", "ella", "hour", "lens", "jive", "wiry", "earl", "mara", "part", "flue", "putt", "rory", "bull", "york", "ruts", "lily", "vamp", "bask", "peer", "boat", "dens", "lyre", "jets", "wide", "rile", "boos", "down", "path", "onyx", "mows", "toke", "soto", "dork", "nape", "mans", "loin", "jots", "male", "sits", "minn", "sale", "pets", "hugo", "woke", "suds", "rugs", "vole", "warp", "mite", "pews", "lips", "pals", "nigh", "sulk", "vice", "clod", "iowa", "gibe", "shad", "carl", "huns", "coot", "sera", "mils", "rose", "orly", "ford", "void", "time", "eloy", "risk", "veep", "reps", "dolt", "hens", "tray", "melt", "rung", "rich", "saga", "lust", "yews", "rode", "many", "cods", "rape", "last", "tile", "nosy", "take", "nope", "toni", "bank", "jock", "jody", "diss", "nips", "bake", "lima", "wore", "kins", "cult", "hart", "wuss", "tale", "sing", "lake", "bogy", "wigs", "kari", "magi", "bass", "pent", "tost", "fops", "bags", "duns", "will", "tart", "drug", "gale", "mold", "disk", "spay", "hows", "naps", "puss", "gina", "kara", "zorn", "boll", "cams", "boas", "rave", "sets", "lego", "hays", "judy", "chap", "live", "bahs", "ohio", "nibs", "cuts", "pups", "data", "kate", "rump", "hews", "mary", "stow", "fang", "bolt", "rues", "mesh", "mice", "rise", "rant", "dune", "jell", "laws", "jove", "bode", "sung", "nils", "vila", "mode", "hued", "cell", "fies", "swat", "wags", "nate", "wist", "honk", "goth", "told", "oise", "wail", "tels", "sore", "hunk", "mate", "luke", "tore", "bond", "bast", "vows", "ripe", "fond", "benz", "firs", "zeds", "wary", "baas", "wins", "pair", "tags", "cost", "woes", "buns", "lend", "bops", "code", "eddy", "siva", "oops", "toed", "bale", "hutu", "jolt", "rife", "darn", "tape", "bold", "cope", "cake", "wisp", "vats", "wave", "hems", "bill", "cord", "pert", "type", "kroc", "ucla", "albs", "yoko", "silt", "pock", "drub", "puny", "fads", "mull", "pray", "mole", "talc", "east", "slay", "jamb", "mill", "dung", "jack", "lynx", "nome", "leos", "lade", "sana", "tike", "cali", "toge", "pled", "mile", "mass", "leon", "sloe", "lube", "kans", "cory", "burs", "race", "toss", "mild", "tops", "maze", "city", "sadr", "bays", "poet", "volt", "laze", "gold", "zuni", "shea", "gags", "fist", "ping", "pope", "cora", "yaks", "cosy", "foci", "plan", "colo", "hume", "yowl", "craw", "pied", "toga", "lobs", "love", "lode", "duds", "bled", "juts", "gabs", "fink", "rock", "pant", "wipe", "pele", "suez", "nina", "ring", "okra", "warm", "lyle", "gape", "bead", "lead", "jane", "oink", "ware", "zibo", "inns", "mope", "hang", "made", "fobs", "gamy", "fort", "peak", "gill", "dino", "dina", "tier")));
    Util.print(ladders2);
  }

}
