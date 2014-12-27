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

public class CompareVersionNumbers {
  public int compareVersion(String version1, String version2) {
    String[] v1 = version1.split("\\.");
    String[] v2 = version2.split("\\.");
    for (int i = 0; i < v1.length || i < v2.length; i++) {
      int n1 = 0, n2 = 0;
      if (i < v1.length) {
        n1 = Integer.parseInt(v1[i]);
      }
      if (i < v2.length) {
        n2 = Integer.parseInt(v2[i]);
      }
      if (n1 != n2) {
        return n1 > n2 ? 1 : -1;
      }
    }
    return 0;
  }
}
