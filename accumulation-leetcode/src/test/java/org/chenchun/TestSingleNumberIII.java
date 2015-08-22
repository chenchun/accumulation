package org.chenchun;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TestSingleNumberIII {
  @Test
  public void test() {
    SingleNumberIII s = new SingleNumberIII();
    assertEqual(new int[]{3, 5}, s.singleNumber(new int[] {1,2,1,3,5,2}));
  }

  public void assertEqual(int[] expect, int[] real) {
    Arrays.sort(expect);
    Arrays.sort(real);
    Assert.assertArrayEquals(expect, real);
  }
}
