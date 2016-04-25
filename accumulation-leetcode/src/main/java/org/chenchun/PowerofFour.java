package org.chenchun;

/**
 * Just like power of three. But you need to excluse those which are in face power of 2, eg 2^1, 2^3, 2^5...
 */
public class PowerofFour {
  public boolean isPowerOfFour(int num) {
    return num > 0 && 1073741824%num == 0 && ((num&0xaaaaaaaa) == 0);
  }
}
