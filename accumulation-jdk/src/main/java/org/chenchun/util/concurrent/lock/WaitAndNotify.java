package org.chenchun.util.concurrent.lock;

import java.util.concurrent.TimeUnit;

/**
 * https://github.com/llohellohe/cp/blob/master/src/yangqi/jcp/lock/WaitAndNotify.java
 * 内置锁synchronized的获取和释放都在同一个代码块中，而显示锁则可以将锁的获得和释放分开。
 * 同时，显示锁可以提供轮训锁和定时锁，同时可以提供公平锁或者非公平锁。
 * 公平锁，是指线程将按照请求锁的顺序获得锁，而非公平所则允许插队。
 *
 * <p>如果依赖的状态没有满足，调用者可以选择等待或者自旋（不断重试）。
 * 自旋会浪费CPU的时钟周期，等待会降低响应性。
 * 依赖状态的操作可以一直阻塞直到可以操作为止，这比先失败再重试方便许多。
 * 正如每个Java对象可以作为一个锁，每个对象可以同样作为一个条件队列，Object的wait.notify,notifyAll构成了条件队列的API。
 * 对象的条件队列和对象的锁是相关联的，要调用条件队列的相关方法，必须先拥有这个锁。
 *
 * <p>wait\notify
 * 在获得对象的监视器后，调用它的wait方法将导致当前线程等待，改线程将被放在对象的条件等待队列中，直到其它线程调用notify或者notifyAll方法通知它。
 *
 * <p>WAIT
 * 声明释放对象的锁，并请求操作系统挂起当前线程，等待唤醒。
 *
 * <p>notify
 * notify唤醒等待对象的线程,并在线程唤醒前获得对象锁。发出通知的线程应该尽快释放锁，从而保证正在等待的线程能尽快解除阻塞。
 *
 * <p>cwait和notify只能在拥有对象的锁后才能调用，否则将抛出IllegalMonitorStateException。它锁住的不是对象而是对象的线程。
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

    private final static String READER = "[" + Reader.class.getSimpleName() +
        "]";
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
              System.out.println(READER + " no food await");
              empty.wait();
            }
            System.out.println(READER + " eat the food");
            empty[0] = "true";
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

      }
    }
  }

  static class Writer implements Runnable {

    private final static String WRITER = "[" + Writer.class.getSimpleName() +
        "]";

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
            empty[0] = "false";
            System.out.println(WRITER + " add to queue " + empty[0]);
            empty.notify();
            System.out.println(WRITER + " notified");
          } else {
            System.out.println(WRITER + " can not add to queue because queue " +
                "is not " + empty[0]);
          }
        }
        try {
          TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

}

