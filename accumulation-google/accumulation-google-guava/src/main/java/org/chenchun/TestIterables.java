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

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.sun.istack.internal.Nullable;

/**
 * @author chenchun
 * @version 1.0
 * @created 2014-04-01
 */
public class TestIterables {
  public static void main(String[] args) {
    ImmutableList<String> list = ImmutableList.of("1", "3");
    Iterable<Object> transform = Iterables.transform(list, new Function<String, Object>() {
      @Override
      public Object apply(@Nullable String input) {
        return Integer.parseInt(input);
      }
    });
    for (Object i : transform) {
      System.out.println(i.getClass());
    }
    Iterable<String> filterResult = Iterables.filter(list, new Predicate<String>() {
      @Override
      public boolean apply(@Nullable String input) {
        return "3".equals(input);
      }
    });
    for (String s : filterResult) {
      System.out.println(s);
    }
  }
}
