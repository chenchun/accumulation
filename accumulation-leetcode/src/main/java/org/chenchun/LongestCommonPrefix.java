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

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        } else if (strs.length == 1) {
            return strs[0];
        }
        String ret = "";
        int j = 0;
        while (true) {
            char first = '0';
            for (int i = 0; i < strs.length; i++) {
                if (strs[i].length() < j+1) {
                    return ret;
                }
                char c = strs[i].charAt(j);
                if (i == 0) {
                    first = c;
                } else if (c != first) {
                    return ret;
                }
            }
            ret = strs[0].substring(0, j+1);
            j++;
        }
    }
}
