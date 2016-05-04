package org.chenchun;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FindMedianfromDataStream {
  public class MedianFinder {
    PriorityQueue<Integer> maxHeap, minHeap;
    public MedianFinder() {
      minHeap = new PriorityQueue<>();
      maxHeap = new PriorityQueue<>(11, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
          return o2-o1;
        }
      });
    }
    // Adds a number into the data structure.
    public void addNum(int num) {
      if (maxHeap.size() == 0 || num <= maxHeap.peek()) {
        if (maxHeap.size() != minHeap.size()) {
          minHeap.add(maxHeap.poll());
        }
        maxHeap.add(num);
      } else if (num > maxHeap.peek() && (minHeap.size() == 0 || num < minHeap.peek())) {
        if (maxHeap.size() == minHeap.size()) {
          maxHeap.add(num);
        } else {
          minHeap.add(num);
        }
      } else {
        if (maxHeap.size() == minHeap.size()) {
          maxHeap.add(minHeap.poll());
        }
        minHeap.add(num);
      }
    }

    // Returns the median of current data stream
    public double findMedian() {
      if (maxHeap.size() == minHeap.size()) {
        return (maxHeap.peek()+minHeap.peek()*1.0)/2;
      }
      return maxHeap.peek();
    }
  }
}
