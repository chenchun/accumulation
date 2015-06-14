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

public class CountCompleteTreeNodes {
  public int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int hl = height(root.left), hr = height(root.right);
    if (hl == hr) {
      return (int) Math.pow(2, hl) + countNodes(root.right);
    } else {
      return (int) Math.pow(2, hr) + countNodes(root.left);
    }
  }

  private int height(TreeNode n) {
    int i = 0;
    while (n != null) {
      i++;
      n = n.left;
    }
    return i;
  }
}
