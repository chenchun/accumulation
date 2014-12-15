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
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] S) {
        List<List<Integer>> ret = new ArrayList<>();
        if (S == null || S.length == 0) {
            return ret;
        }
        Arrays.sort(S);
        subsets(S, 0, new ArrayList<Integer>(), ret);
        return ret;
    }

    private void subsets(int[] S, int i, List<Integer> sub, List<List<Integer>> ret) {
        if (i != S.length) {
            List<Integer> list = new ArrayList<>(sub);
            list.add(S[i]);
            i++;
            subsets(S, i, sub, ret);
            subsets(S, i, list, ret);
        } else {
            ret.add(sub);
        }
    }
}
