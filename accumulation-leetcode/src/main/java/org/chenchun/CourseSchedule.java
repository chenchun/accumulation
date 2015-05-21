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

import java.util.LinkedList;
import java.util.Queue;

public class CourseSchedule {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    boolean[][] matrics = new boolean[numCourses][numCourses];
    int[] indegree = new int[numCourses];
    for (int i = 0; i < prerequisites.length; i++) {
      int[] arr = prerequisites[i];
      if (!matrics[arr[1]][arr[0]]) {
        indegree[arr[0]]++;
      }
      matrics[arr[1]][arr[0]] = true;
    }
    Queue<Integer> queue = new LinkedList<>();
    int count = 0;
    for (int i = 0; i < numCourses; i++) {
      if (indegree[i] == 0) {
        queue.offer(i);
      }
    }
    while (!queue.isEmpty()) {
      int course = queue.poll();
      count++;
      for (int i = 0; i < numCourses; i++) {
        if (matrics[course][i]) {
          if (--indegree[i] == 0) {
            queue.add(i);
          }
        }
      }
    }
    return count == numCourses;
  }
}
