package org.chenchun;

public class NumberofDigitOne {
  public int countDigitOne(int n) {
    if (n <= 0) {
      return 0;
    }
    int d = 1, t = n;
    while (t/10 > 0) {
      t/=10;
      d++;
    }
    int first = (int) Math.pow(10, d-1);
    int fn = one(d-1); //f(n-1)
    int num = 0;
    if (n < first*2) {
      num = (n-first+1)+countDigitOne(n-first);
    } else {
      num = first+fn*(n/first-1)+countDigitOne(n-n/first*first);
    }
    return num+fn;
  }
  //f(1) = 1
  //f(2) = 1+1*9+10
  //f(n) = f(n-1)+f(n-1)*9+10^(n-1)
  //4 1000
  private int one(int n) {
    if (n == 0) {
      return 0;
    } else if (n == 1) {
      return 1;
    }
    int fn = one(n-1);
    return (int)(fn*10+Math.pow(10, n-1));
  }

  public static void main(String[] args) {
    NumberofDigitOne n = new NumberofDigitOne();
    System.out.println(n.countDigitOne(0));
    System.out.println(n.countDigitOne(1));
    System.out.println(n.countDigitOne(3));
    System.out.println(n.countDigitOne(13));
    System.out.println(n.countDigitOne(20));
    System.out.println(n.countDigitOne(21));
    System.out.println(n.countDigitOne(100));
    System.out.println(n.countDigitOne(102));
    System.out.println(n.countDigitOne(100));
    System.out.println(n.countDigitOne(101));
    System.out.println(n.countDigitOne(110));
  }
}
