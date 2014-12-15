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

import java.util.List;

public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int[] mins = new int[triangle.size()];
        mins[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            for (int j = i; j >= 0; j--) {
                if (j == 0) {
                    mins[j] = mins[j]+triangle.get(i).get(0);
                } else if (j == i) {
                    mins[j] = mins[j-1] + triangle.get(i).get(j);
                } else {
                    mins[j] = Math.min(mins[j], mins[j-1]) + triangle.get(i).get(j);
                }
            }
        }
        int min = mins[0];
        for (int i = 1; i < triangle.size(); i++) {
            min = Math.min(min, mins[i]);
        }
        return min;
    }
}
