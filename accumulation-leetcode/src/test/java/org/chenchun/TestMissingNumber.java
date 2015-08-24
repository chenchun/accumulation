package org.chenchun;

import org.junit.Assert;
import org.junit.Test;

public class TestMissingNumber {
  @Test
  public void test() {
    MissingNumber m = new MissingNumber();
    Assert.assertEquals(2, m.missingNumber(new int[]{1, 0}));
  }
}
