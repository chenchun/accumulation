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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecoverBinarySearchTree {
  public void recoverTree(TreeNode root) {
    TreeNode cur = root, pre = null;
    TreeNode previewNode = null;
    List<TreeNode> list = new ArrayList<>();
    while (cur != null) {
      if (cur.left == null) {
        //visit n
        if (previewNode == null) {
          previewNode = cur;
        } else {
          if (previewNode.val > cur.val) {
            list.add(previewNode);
            list.add(cur);
//            if (list.size() >= 4) {
                //can't break, since it might change the tree
//              break;
//            }
          }
        }
        previewNode = cur;
//        System.out.println(cur.val);
        cur = cur.right;
      } else {
        pre = cur.left;
        while (pre.right != null && pre.right != cur) {
          pre = pre.right;
        }
        if (pre.right == null) {
          pre.right = cur;
          cur = cur.left;
        } else {
          //visit n
          pre.right = null;
          previewNode = pre;
          if (previewNode.val > cur.val) {
            list.add(previewNode);
            list.add(cur);
//            if (list.size() >= 4) {
                //can't break, since it might change the tree
//              break;
//            }
          }
          previewNode = cur;
//          System.out.println(cur.val);
          cur = cur.right;
        }
      }
    }
    Collections.sort(list, new Comparator<TreeNode>() {
      public int compare(TreeNode a, TreeNode b) {
        return a.val - b.val;
      }
    });
    TreeNode first = list.get(0), second = list.get(list.size()-1);
    int t = first.val;
    first.val = second.val;
    second.val = t;
  }

  public static void main(String[] args) {
    //10,5,15,0,8,13,20,2,-5,6,9,12,14,18,25
    TreeNode root = new TreeNode(10);
    root.left = new TreeNode(5);
    root.right = new TreeNode(15);
    root.left.left = new TreeNode(0);
    root.left.right = new TreeNode(8);
    root.right.left = new TreeNode(13);
    root.right.right = new TreeNode(20);
    root.left.left.left = new TreeNode(2);
    root.left.left.right = new TreeNode(-5);
    root.left.right.left = new TreeNode(6);
    root.left.right.right = new TreeNode(9);
    root.right.left.left = new TreeNode(12);
    root.right.left.right = new TreeNode(14);
    root.right.right.left = new TreeNode(18);
    root.right.right.right = new TreeNode(25);
    RecoverBinarySearchTree r = new RecoverBinarySearchTree();
    r.recoverTree(root);
  }
}
