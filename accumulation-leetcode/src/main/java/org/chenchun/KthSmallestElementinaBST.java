package org.chenchun;

public class KthSmallestElementinaBST {
    public int kthSmallest(TreeNode root, int k) {
        Integer[] index = new Integer[]{0}, ret = new Integer[1];
        kthSmallest(root, k, index, ret);
        return ret[0];
    }

    public void kthSmallest(TreeNode root, int k, Integer[] index, Integer[] ret) {
        if (root.left != null) {
            kthSmallest(root.left, k, index, ret);
        }
        if (ret[0] != null) {
            return;
        }
        index[0] = index[0]+1;
        if (index[0] == k) {
            ret[0] = root.val;
            return;
        }
        if (root.right != null) {
            kthSmallest(root.right, k, index, ret);
        }
        return;
    }
}
