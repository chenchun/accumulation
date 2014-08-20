package org.chenchun.util.concurrent.lock;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 适用于很多读，少量写。ReentrantReadWriteLock保证写的时候无法读，且同时只会有一个写者，可以并发读
 *
 * 另一种无锁的实现见
 * @see CopyOnWriteLockPattern
 */
class ReentrantReadWriteLockExample {

  public static class Data {}

  private final Map<String, Data> m = new TreeMap<String, Data>();
  private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
  private final Lock r = rwl.readLock();
  private final Lock w = rwl.writeLock();

  public Data get(String key) {
    r.lock();
    try {
      return m.get(key);
    } finally {
      r.unlock();
    }
  }

  public String[] allKeys() {
    r.lock();
    try {
      return (String[]) m.keySet().toArray();
    } finally {
      r.unlock();
    }
  }

  public Data put(String key, Data value) {
    w.lock();
    try {
      return m.put(key, value);
    } finally {
      w.unlock();
    }
  }

  public void clear() {
    w.lock();
    try {
      m.clear();
    } finally {
      w.unlock();
    }
  }
}