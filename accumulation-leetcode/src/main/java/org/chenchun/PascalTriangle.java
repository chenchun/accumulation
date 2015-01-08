/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.chenchun;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> list = new ArrayList<>();
    if (numRows > 0) {
      for (int i = 0; i < numRows; i++) {
        int num = 1;
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        for (int j = 1; j < i; j++) {
          num = num*(i-j+1)/j;
          arr.add(num);
        }
        if (i != 0) {
          arr.add(1);
        }
        list.add(arr);
      }
    }
    return list;
  }
}
