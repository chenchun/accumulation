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
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ReconstructItinerary {
  public List<String> findItinerary(String[][] tickets) {
    Map<String, LinkedList<String>> map = new HashMap<>();
    for (int i = 0; i < tickets.length; i++) {
      if (!map.containsKey(tickets[i][0])) {
        map.put(tickets[i][0], new LinkedList<String>());
      }
      map.get(tickets[i][0]).add(tickets[i][1]);
    }
    for (String key : map.keySet()) {
      Collections.sort(map.get(key));
    }
    List<String> list = new ArrayList<>();
    String start = "JFK";
    list.add(start);
    dfs(map, start, list);
    return list;
  }

  public boolean dfs(Map<String, LinkedList<String>> map, String start, List<String> list) {
    if (map.size() == 0) {
      return true;
    }
    if (map.containsKey(start)) {
      int n = map.get(start).size();
      for (int i = 0; i < n; i++) {
        LinkedList<String> q = map.get(start);
        String next = q.remove(i);
        if (q.size() == 0) {
          map.remove(start);
        }
        list.add(next);
        if (dfs(map, next, list)) {
          return true;
        }
        q = map.get(start);
        if (q == null) {
          q = new LinkedList<String>();
        }
        q.add(i, next);
        map.put(start, q);
        list.remove(list.size()-1);
      }
    }
    return false;
  }

  public static void main(String[] args) {
    ReconstructItinerary r = new ReconstructItinerary();
    List<String> list = r.findItinerary(new String[][]{new String[]{"JFK","KUL"}, new String[]{"JFK","NRT"}, new String[]{"NRT","JFK"}});
    Util.printCollection(list);
  }
}
