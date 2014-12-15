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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] num) {
        Set<List<Integer>> set = new HashSet<>();
        if (num != null && num.length != 0) {
            Arrays.sort(num);
            subsets(num, 0, new ArrayList<Integer>(), set);
        }
        return new ArrayList<>(set);
    }

    private void subsets(int[] S, int i, List<Integer> sub, Set<List<Integer>> set) {
        if (i != S.length) {
            List<List<Integer>> lists = new ArrayList<>();
            lists.add(sub);
            int j = i;
            List<Integer> last = sub;
            while (j < S.length && (i == j || S[j] == S[i])) {
                List<Integer> list = new ArrayList<>(last);
                list.add(S[i]);
                last = list;
                lists.add(list);
                j++;
            }
            i = j;
            for (j = 0; j < lists.size(); j++) {
                subsets(S, i, lists.get(j), set);
            }
        } else {
            set.add(sub);
        }
    }
}
