package org.chenchun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UglyNumberII {
  public int nthUglyNumber(int n) {
    List<Integer> ugly = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 8, 9, 10, 12));
    if (n <= 10) {
      return ugly.get(n-1);
    }

    Integer[][] factor = new Integer[][]{new Integer[]{2, 1}, new Integer[]{3, 1}, new Integer[]{5, 1}};
    n -= 10;
    while (--n >= 0) {
      Integer min = Integer.MAX_VALUE;
      for (int i = 0; i < 3; i++) {
        while (factor[i][0] * ugly.get(factor[i][1]) <= ugly.get(ugly.size()-1)) {
          factor[i][1]++;
        }
        min = Math.min(min, factor[i][0] * ugly.get(factor[i][1]));
      }
      ugly.add(min);
    }
    return ugly.get(ugly.size()-1);
  }
}
