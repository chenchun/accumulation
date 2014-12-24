package org.chenchun;

public class ExcelSheetColumnTitle {
  public String convertToTitle(int n) {
    return n == 0? "" : convertToTitle(--n/26) + (char)(n%26 + 'A');
  }
}