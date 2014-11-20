package org.chenchun;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
  public List<String> fullJustify(String[] words, int L) {
    List<String> ret = new ArrayList<String>();
    int i = 0;
    while (i < words.length) {
      StringBuilder sb = new StringBuilder();
      if (i == words.length - 1) {
        sb.append(words[i]).append(emptyStr(L-words[i].length()));
        i++;
      } else {
        int l = words[i].length();
        int j = i;
        while (++j < words.length) {
          if (l + 1 + words[j].length() <= L) {
            l = l + 1 + words[j].length();
          } else {
            break;
          }
        }
        int ws = j - i;
        if (ws == 1) {
          sb.append(words[i]).append(emptyStr(L-l));
        } else {
          if (j == words.length) {
            for (int k = i; k < j; k++) {
              sb.append(words[k]);
              if (k != j-1) {
                sb.append(" ");
              } else {
                sb.append(emptyStr(L-l));
              }
            }
          } else {
            int evenSpace = (L-l)/(ws-1);
            int moreSpaceSize = (L-l)%(ws-1);
            for (int k = i; k < j; k++) {
              sb.append(words[k]);
              if (k != j-1) {
                sb.append(emptyStr(1+evenSpace+(moreSpaceSize-->0 ? 1 : 0)));
              }
            }
          }
        }
        i=j;
      }
      ret.add(sb.toString());
    }
    return ret;
  }

  private String emptyStr(int n) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      sb.append(" ");
    }
    return sb.toString();
  }
}