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

public class DecodeWays {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '0') {
                if (i == 0) {
                    return 0;
                } else {
                    char before = s.charAt(i-1);
                    if (isValid(before, c)) {
                        dp[i] = i-2 >= 0 ? dp[i-2] : 1;
                    } else {
                        return 0;
                    }
                }
            } else {
                if (i != 0) {
                    char before = s.charAt(i-1);
                    if (isValid(before, c)) {
                        dp[i] = (i-2>=0? dp[i-2] : 1) + dp[i-1];
                    } else {
                        dp[i] = dp[i-1];
                    }
                } else {
                    dp[i] = 1;
                }
            }
        }
        return dp[s.length()-1];
    }

    private boolean isValid(char before, char after) {
        if (before < '1' || before > '2') {
            return false;
        } else if (before == '1') {
            return true;
        } else {
            return after <= '6';
        }
    }
}
