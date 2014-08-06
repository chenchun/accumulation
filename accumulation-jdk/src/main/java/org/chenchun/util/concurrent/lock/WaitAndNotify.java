package org.chenchun.util.concurrent.lock;

import java.util.concurrent.TimeUnit;

/**
 * https://github.com/llohellohe/cp/blob/master/src/yangqi/jcp/lock/WaitAndNotify.java
 */
public class WaitAndNotify {

  private static final String[] empty = new String[]{"true"};

  /**
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("start");
    Thread reader = new Thread(new Reader(empty));
    reader.start();
    System.out.println("hello");


    Thread writer = new Thread(new Writer(empty));
    writer.start();
  }

  static class Reader implements Runnable {

    private String[] empty;

    Reader(String[] empty) {
      this.empty = empty;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
      for (; ; ) {
        synchronized (empty) {
          try {
            if (empty[0].equals("true")) {
              empty.wait();
              System.out.println("Read yummy ");
              empty[0] = "true";
            }
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        try {
          TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

      }
    }
  }

  static class Writer implements Runnable {

    private String[] empty;

    Writer(String[] empty) {
      this.empty = empty;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
      for (; ; ) {
        synchronized (empty) {
          if (empty[0].equals("true")) {
            System.out.println("Add to queue,because queue is empty " + empty[0]);
            empty.notify();
          } else {
            System.out.println("Can not add to queue because queue is not " + empty[0]);
          }
        }
        try {
          TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }

}

