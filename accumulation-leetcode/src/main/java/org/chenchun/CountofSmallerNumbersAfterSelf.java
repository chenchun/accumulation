package org.chenchun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * binary index tree
 */
public class CountofSmallerNumbersAfterSelf {
  public List<Integer> countSmaller(int[] nums) {
    if (nums.length == 0) {
      return new ArrayList<Integer>();
    }
    int min = nums[0];
    for (int i = 1; i < nums.length; i++) {
      min = Math.min(min, nums[i]);
    }
    int max = 1;
    for (int i = 0; i < nums.length; i++) {
      nums[i] = nums[i]-min+1;
      max = Math.max(max, nums[i]);
    }
    BinaryIndexTree tree = new BinaryIndexTree(max);
    Integer[] ret = new Integer[nums.length];
    for (int i = nums.length-1; i>=0; i--) {
      ret[i]=tree.sum(nums[i]-1);
      tree.insert(nums[i]);
    }
    return Arrays.asList(ret);
  }

  private static class BinaryIndexTree {
    int[] tree;
    public BinaryIndexTree(int max) {
      tree = new int[max+1];
    }
    /**
     * starting from 1...to tree.length-1
     **/
    public void insert(int n) {
      int i = 0;
      tree[n]++;
      while (n < tree.length) {
        if ((n&(1<<i)) != 0) {
          n=n+(1<<i);
          if (n<tree.length) {
            tree[n]++;
          }
        }
        i++;
      }
    }
    /**
     * return the num of elements between [1-i]
     *
     * sum(11)=tree(11)+tree(10)+tree(8)
     *
     * 1011-1=1010
     * 1010-10=1000
     * 1000-1000=0
     **/
    public int sum(int n) {
      int i = 0;
      int sum = tree[n];
      while (n != 0) {
        if ((n&(1<<i)) != 0) {
          n=n-(1<<i);
          sum+=tree[n];
        }
        i++;
      }
      return sum;
    }
    /**
     * return the next index that affected by this one
     *
     * 9-10-12-16-32
     * 1001+1=1010
     * 1010+10=1100
     * 1100+100=10000
     * 10000+10000=100000
     **/
    public int next(int index) {
      int i = 0;
      while (i < 32) {
        if ((index&(1<<i)) != 0) {
          return index+(1<<i);
        }
        i++;
      }
      return -1;
    }
  }
}
