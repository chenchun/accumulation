package org.chenchun;

import junit.framework.Assert;
import org.junit.Test;

public class TestUglyNumberII {
  @Test
  public void test() {
    UglyNumberII u = new UglyNumberII();
    Assert.assertEquals(1, u.nthUglyNumber(1));
    Assert.assertEquals(2, u.nthUglyNumber(2));
    Assert.assertEquals(15, u.nthUglyNumber(11));
    Assert.assertEquals(16, u.nthUglyNumber(12));
  }
}
