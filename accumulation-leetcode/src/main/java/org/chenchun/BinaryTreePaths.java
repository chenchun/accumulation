package org.chenchun;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
  private List<String> ret;
  public List<String> binaryTreePaths(TreeNode root) {
    ret = new ArrayList<>();
    if (root == null) {
      return ret;
    }
    previsit(root, null);
    return ret;
  }
  private void previsit(TreeNode root, StringBuilder sb) {
    if (sb == null) {
      sb = new StringBuilder(String.valueOf(root.val));
    } else {
      sb.append("->").append(root.val);
    }
    if (root.left != null && root.right != null) {
      previsit(root.left, new StringBuilder(sb.toString()));
      previsit(root.right, sb);
      return;
    }
    if (root.left != null) {
      previsit(root.left, sb);
      return;
    }
    if (root.right != null) {
      previsit(root.right, sb);
      return;
    }
    ret.add(sb.toString());
  }
}
