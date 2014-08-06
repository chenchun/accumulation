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

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * @author chenchun
 * @version 1.0
 * @created 2014-02-11
 */
public class TestGuice {
  public static void main(String[] args) {
    Injector inj = Guice.createInjector(new Module() {
      @Override
      public void configure(Binder binder) {
        binder.bind(Hello.class).to(HelloImpl.class);
        binder.bind(Guy.class);
      }
    });

    Hello hello = inj.getInstance(Hello.class);
    System.out.println(hello.sayHello());

    Guy guy = inj.getInstance(Guy.class);
    System.out.println(guy.say());

    System.out.println(guy.getHello() != hello);
  }
}
