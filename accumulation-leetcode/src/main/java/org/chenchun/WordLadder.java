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

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 */
public class WordLadder {
  public int ladderLength(String start, String end, Set<String> dict) {
    Queue<String> q = new LinkedList<String>();
    q.add(start);
    q.add(".");
    int distance = 2;
    while (!q.isEmpty()) {
      String str = q.remove();
      if (str.equals(".")) {
        distance++;
        if (!q.isEmpty()) {
          q.add(".");
        }
        continue;
      }
      for (int i = 0; i < str.length(); i++) {
        for (int j = 0; j < 26; j++) {
          String replace = str.substring(0, i) + (char)('a' + j) + str.substring(i+1, str.length());
          if (dict.contains(replace)) {
            if (replace.equals(str)) {
              dict.remove(replace);
            } else {
              if (end.equals(replace)) {
                return distance;
              }
              q.add(replace);
              dict.remove(replace);
            }
          }
        }
      }
    }
    return 0;
  }

  public static void main(String[] args) {
    WordLadder w = new WordLadder();
//    String[] strs = new String[] {"hot","cog","dot","dog","hit","lot","log"};
//    Set<String> s = new HashSet<String>(Arrays.asList(strs));
//    System.out.println(w.ladderLength("hit", "cog", s));

//    String[] strs = new String[] {"a","b","c"};
//    Set<String> s = new HashSet<String>(Arrays.asList(strs));
//    System.out.println(w.ladderLength("a", "c", s));
//    String[] strs = new String[] {"lest","leet","lose","code","lode","robe","lost"};
//    Set<String> s = new HashSet<String>(Arrays.asList(strs));
//    System.out.println(w.ladderLength("leet", "code", s));

//    String[] strs = new String[] {"miss","dusk","kiss","musk","tusk","diss","disk","sang","ties","muss"};
//    Set<String> s = new HashSet<String>(Arrays.asList(strs));
//    System.out.println(w.ladderLength("kiss", "tusk", s));

      String[] strs = new String[] {"hot","dog"};
      Set<String> s = new HashSet<String>(Arrays.asList(strs));
      System.out.println(w.ladderLength("hot", "dog", s));
  }
}
