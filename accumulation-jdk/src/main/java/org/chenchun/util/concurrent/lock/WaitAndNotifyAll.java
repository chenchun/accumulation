package org.chenchun.util.concurrent.lock;

/**
 *
 * <p><b>在任一时刻内，监视者对象只属于一个活动线程 (Owner) 。</b>
 *
 * <p>调用notifyAll的线程是否需要先释放锁，wait的线程才能继续工作？ yes；
 * 调用notifyAll的线程执行完notifyAll后是否与wait的线程是并行执行的？no；
 * 调用notifyAll后，所有wait线程是否可以并行执行？no
 * 调用notifyAll后，所有wait线程的优先级是？
 * 调用notifyAll后，wait线程和新尝试获得锁的线程的优先级是（entry set 和 wait set的优先级谁高）？
 *
 *
 * <p>线程如果获得监视锁成功，将成为该监视者对象的拥有者。
 * 拥有者线程可以调用 wait 方法自动释放监视锁，进入等待状态。
 */
public class WaitAndNotifyAll {
  private int counter = 0;
  private String name = null;

  public WaitAndNotifyAll(int counter, String name) {
    this.counter = counter;
    this.name = name;
  }

  public synchronized void doSomthing() {
    int tempCounter = --counter;
    String flag = Thread.currentThread().getName() + "-<" + name +
        tempCounter + ">";
    if (tempCounter > 0) {
      try {
        System.out.println(flag + "will invoke WAIT()");
        --tempCounter;
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(flag + " will wake up");
      sleep(3, 1000, flag + " sleeped");
      System.out.println(flag + "has been ACTIVED");
    } else if (tempCounter == 0) {
      customizedNotifyAll();
      sleep(5, 1000, flag + " sleeped");
    } else {
      sleep(2, 1000, flag + " sleeped");
    }
  }

  public void customizedNotifyAll() {
    notifyAll();
    System.out.println(Thread.currentThread().getName() + "-<" + name + counter + ">" + "::" + "INVOKED NOTIFYALL()");
  }

  public static void sleep(int count, long ms, String words) {
    while (count-- > 0) {
      if (words != null) {
        System.out.println(words);
      }
      try {
        Thread.sleep(ms);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static class WaitAndNotifyAllThread extends Thread {

    private WaitAndNotifyAll waitAndNotifyAll;

    public WaitAndNotifyAllThread(WaitAndNotifyAll waitAndNotifyAll) {
      this.waitAndNotifyAll = waitAndNotifyAll;
    }

    public void run() {
      waitAndNotifyAll.doSomthing();
      System.out.println(Thread.currentThread().getName() + " finished");
    }
  }

  public static void main(String[] args) throws InterruptedException {
    WaitAndNotifyAll waitAndNotifyAll = new WaitAndNotifyAll(4, "DAVID");
    Thread t1 = new WaitAndNotifyAllThread(waitAndNotifyAll);
    Thread t2 = new WaitAndNotifyAllThread(waitAndNotifyAll);
    Thread t3 = new WaitAndNotifyAllThread(waitAndNotifyAll);
    Thread t4 = new WaitAndNotifyAllThread(waitAndNotifyAll);
    t1.start();
    t2.start();
    t3.start();
    t4.start();
    t4.join();
    new WaitAndNotifyAllThread(waitAndNotifyAll).start();
  }

}
