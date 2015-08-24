package org.chenchun;

public class MissingNumber {
  public int missingNumber(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    for (int i = 0; i < nums.length; i++) {
      int j = i;
      if (nums[j] != j && nums[j] < nums.length) {
        int t = nums[nums[j]];
        nums[nums[j]] = nums[j];
        j = t;
        while (j < nums.length && nums[j] != j) {
          t = nums[j];
          nums[j] = j;
          j = t;
        }
      }
    }
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != i) {
        return i;
      }
    }
    return nums.length;
  }
}
