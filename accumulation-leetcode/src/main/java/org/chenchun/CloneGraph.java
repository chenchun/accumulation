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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph {

  class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
      label = x;
      neighbors = new ArrayList<UndirectedGraphNode>();
    }
  }

  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    Map<Integer, UndirectedGraphNode> map = new HashMap<>();
    return cloneGraph(node, map);
  }

  private UndirectedGraphNode cloneGraph(UndirectedGraphNode node,
      Map<Integer, UndirectedGraphNode> map) {
    if (node != null) {
      UndirectedGraphNode n = new UndirectedGraphNode(node.label);
      map.put(n.label, n);
      if (node.neighbors != null) {
        for (UndirectedGraphNode neighbor : node.neighbors) {
          UndirectedGraphNode clonedNeighbor = null;
          if (!map.containsKey(neighbor.label)) {
            clonedNeighbor = cloneGraph(neighbor, map);
          } else {
            clonedNeighbor = map.get(neighbor.label);
          }
          n.neighbors.add(clonedNeighbor);
        }
      }
      return n;
    }
    return null;
  }
}
