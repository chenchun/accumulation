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

public class UniqueBinarySearchTrees {
    //numTrees(i)=numTrees(i-1)+numTrees(i-2)*numTrees(1)+numTrees(i-3)*numTrees(2)+..
    //n(3)=n(2)*n(0)+n(1)*n(1)+n(0)*n(2)=2+1+2=5
    //n(2)=n(1)*n(0)+n(0)*n(1)=2
    //n(1)=1
    public int numTrees(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[n+1];
        dp[0]=1;
        dp[1]=1;
        for (int i = 2; i <=n; i++) {
            dp[i] = 0;
            for (int j = 0; j < i; j++) {
                dp[i]+=dp[i-1-j]*dp[j];
            }
        }
        return dp[n];
    }
}
