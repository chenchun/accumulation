package org.chenchun;

public class IntegerBreak {
  public int integerBreak(int n) {
    int[] max = new int[n+1];
    max[1] = 1;
    max[2] = 1;
    for (int i = 3; i <= n; i++) {
      for (int j = 1; j < i-1; j++) {
        // max[i-j] only accounts for at least 2 nums, we should take single num into account
        max[i] = Math.max(max[i], j*Math.max(i-j, max[i-j]));
      }
    }
    return max[n];
  }

  public static void main(String[] args) {
    IntegerBreak i = new IntegerBreak();
    System.out.println(i.integerBreak(2));
    System.out.println(i.integerBreak(3));
    System.out.println(i.integerBreak(4));
    System.out.println(i.integerBreak(5));
    System.out.println(i.integerBreak(6));
    System.out.println(i.integerBreak(7));
    System.out.println(i.integerBreak(8));
  }
}
