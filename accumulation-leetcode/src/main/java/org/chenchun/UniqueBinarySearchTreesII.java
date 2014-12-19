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
import java.util.List;

public class UniqueBinarySearchTreesII {
    public List<TreeNode> generateTrees(int n) {
        int[] num = new int[n];
        for (int i = 1; i <= n; i++) {
            num[i-1] = i;
        }
        return generateTrees(num);
    }

    private List<TreeNode> generateTrees(int[] n) {
        List<TreeNode> list = new ArrayList<>();
        if (n.length == 0) {
            list.add(null);
        } else if (n.length == 1) {
            list.add(new TreeNode(n[0]));
        } else {
            for (int i = 0; i < n.length; i++) {
                int[] left = new int[i];
                int[] right = new int[n.length-i-1];
                System.arraycopy(n, 0, left, 0, i);
                System.arraycopy(n, i+1, right, 0, n.length-i-1);
                List<TreeNode> leftTree = generateTrees(left);
                List<TreeNode> rightTree = generateTrees(right);
                for (int l = 0; l < leftTree.size(); l++) {
                    for (int r = 0; r < rightTree.size(); r++) {
                        TreeNode root = new TreeNode(n[i]);
                        root.left = leftTree.get(l);
                        root.right = rightTree.get(r);
                        list.add(root);
                    }
                }
            }
        }
        return list;
    }
}
