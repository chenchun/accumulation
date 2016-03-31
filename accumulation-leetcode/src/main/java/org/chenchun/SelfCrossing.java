/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.chenchun;

public class SelfCrossing {
  public boolean isSelfCrossing(int[] x) {
    if (x.length <= 3) {
      return false;
    }
    int t1 = x[0], t2 = x[1], t3 = x[2], t4 = x[3];
    if (t1 >= t3 && t2 <= t4) {
      return true;
    }
    int i = 4;
    if (t4 > t2) {
      //out
      while (i < x.length) {
        t1 = x[i-3];
        t2 = x[i-2];
        t3 = x[i-1];
        t4 = x[i];
        if (t4 <= t2) {
           if (i == x.length - 1) {
             return false;
           } else {
             break;
           }
        }
        if (t3 <= t1) {
          return true;
        }
        i++;
      }
      if (i == x.length) {
        return false;
      }
      if (x[i+1] >= t3) {
        return true;
      } else if (x[i+1]+t1 >= t3 && x[i]+x[i-4] >= t2) {
        // out circle becomes in circle failed
        return true;
      }
      i += 2;
    }
    //in
    while (i < x.length) {
      t1 = x[i-3];
      t2 = x[i-2];
      t3 = x[i-1];
      t4 = x[i];
      if (t4 >= t2 || t3 >= t1) {
        return true;
      }
      i++;
    }
    if (i == x.length) {
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    SelfCrossing s = new SelfCrossing();
//    System.out.println(s.isSelfCrossing(new int[]{2, 1, 1, 2}));
//    System.out.println(s.isSelfCrossing(new int[]{2, 1, 3, 2, 1}));
//    System.out.println(s.isSelfCrossing(new int[]{2, 1, 3, 2, 3}));
//    System.out.println(s.isSelfCrossing(new int[]{2, 1, 3, 2, 4}));
//    //       _ _ _
//    //      |  _  |
//    //      |_|_|_|_
//    //        | | |
//    //        |_ _|
//    //
//    System.out.println(s.isSelfCrossing(new int[]{2, 1, 3, 2, 4, 3, 2, 4}));

    //       _ _ _
    //      |   _  |
    //      |  | | |
    //      |  | |
    //      | _ _|
//    System.out.println(s.isSelfCrossing(new int[]{2, 3, 4, 2, 3, 1, 2}));
    //       _ _ _
    //      |  _  |  _ _
    //      | | | | |   |
    //      | |_ _| |_| |
    //      |_ _ _ _ _ _|
    //
//    System.out.println(s.isSelfCrossing(new int[]{1, 1, 2, 2, 3, 3, 4, 6, 3, 2, 2, 1, 1}));

    System.out.println(s.isSelfCrossing(new int[]{1,1,3,2,1,1, 1}));
  }
}
