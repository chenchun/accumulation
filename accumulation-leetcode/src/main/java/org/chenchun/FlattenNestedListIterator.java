package org.chenchun;

import java.util.LinkedList;
import java.util.List;

public class FlattenNestedListIterator {
  public interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
  }

  public interface Iterator<T> {
    T next();
    boolean hasNext();
  }

  public class NestedIterator implements Iterator<Integer> {
    private LinkedList<NestedInteger> list;

    public NestedIterator(List<NestedInteger> nestedList) {
      list = new LinkedList<NestedInteger>(nestedList);
    }

    @Override
    public Integer next() {
      while (list.size() > 0) {
        NestedInteger ni = list.get(0);
        if (ni.isInteger()) {
          list.remove(0);
          return ni.getInteger();
        }
        list.remove(0);
        int n = 0;
        for (NestedInteger i : ni.getList()) {
          list.add(n, i);
          n++;
        }
      }
      return null;
    }

    @Override
    public boolean hasNext() {
      while (list.size() > 0) {
        NestedInteger ni = list.get(0);
        if (ni.isInteger()) {
          return true;
        }
        list.remove(0);
        int n = 0;
        for (NestedInteger i : ni.getList()) {
          list.add(n, i);
          n++;
        }
      }
      return false;
    }
  }
}
