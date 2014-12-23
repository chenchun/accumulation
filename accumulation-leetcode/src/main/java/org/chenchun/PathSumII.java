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

public class PathSumII {
  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> ret = new ArrayList<>();
    pathSum(root, sum, new ArrayList<Integer>(), ret);
    return ret;
  }

  private void pathSum(TreeNode root, int sum, List<Integer> list, List<List<Integer>> ret) {
    if (root != null) {
      sum -= root.val;
      if (root.left == null && root.right == null) {
        if (sum == 0) {
          List<Integer> arr = new ArrayList<>(list);
          arr.add(root.val);
          ret.add(arr);
        }
        return;
      }
      list.add(root.val);
      if (root.left != null) {
        pathSum(root.left, sum, list, ret);
      }
      if (root.right != null) {
        pathSum(root.right, sum, list, ret);
      }
      list.remove(list.size()-1);
    }
  }
}
