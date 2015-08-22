package org.chenchun;

public class SingleNumberIII {
  public int[] singleNumber(int[] nums) {
    int ret = 0;
    for (int i = 0; i < nums.length; i++) {
      ret ^= nums[i];
    }
    int first = 0;
    for (int i = 0; i < 32; i++) {
      int n = 1 << i;
      if ((ret & n) != 0) {
        int c = 0;
        for (int j = 0; j < nums.length; j++) {
          if ((nums[j] & n) != 0) {
            c++;
          }
        }
        if (c % 2 == 1) {
          for (int j = 0; j < nums.length; j++) {
            if ((nums[j] & n) != 0) {
              first ^= nums[j];
            }
          }
          break;
        }
      }
    }
    return new int[]{first, ret^first};
  }
}
