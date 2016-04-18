package org.chenchun;

import java.util.Arrays;

public class CoinChange {
  public int coinChange(int[] coins, int amount) {
    Arrays.sort(coins);
    return coinChange(coins, coins.length-1, amount, Integer.MAX_VALUE, 0);
  }

  private int coinChange(int[] coins, int i, int amount, int min, int sum) {
    if (amount == 0) {
      return sum;
    }
    if (i >= 0) {
      int j = amount/coins[i];
      for (;j >= 0; j--) {
        if (sum+j >= min) {
          break;
        }
        int num = coinChange(coins, i-1, amount-coins[i]*j, min, sum+j);
        if (num > 0) {
          min = Math.min(min, num);
        }
      }
      if (min != Integer.MAX_VALUE) {
        return min;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    CoinChange c = new CoinChange();
    System.out.println(c.coinChange(new int[]{186,419,83,408}, 6249));
    System.out.println(c.coinChange(new int[]{288,160,10,249,40,77,314,429}, 9208));
  }
}
