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
package org.chenchun.other;

public class StrStrBoyerMoore {
  public String strStr(String haystack, String needle)
  {
    if(haystack== null) return null;
    if(needle==null || needle.length()==0) return haystack;
    if(needle.length()>haystack.length()) return null;

    int pat_length = needle.length();
    int right[] = new int[256];

    for(int i=0;i<256;i++)
      right[i] =-1;
    for(int i=0;i<pat_length;i++)
      right[needle.charAt(i)] =i;

    int rtn = search(right,haystack,needle);
    if(rtn == haystack.length()) return null;
    else
      return haystack.substring(rtn);
  }

  public int search(int[] right, String haystack,String needle)
  {
    int M = haystack.length();
    int N = needle.length();
    int i,j;
    int skip =0;
    for(i=0; i<=M-N; i+=skip)
    {
      skip =0;
      for(j=N-1;j>=0;j--)
      {
        if(needle.charAt(j)!=haystack.charAt(i+j))
        {
          skip = j-right[haystack.charAt(j+i)];
          if(skip<=0) skip=1;
          break;
        }
      }
      if(skip ==0) return i;
    }
    return M;

  }
}
