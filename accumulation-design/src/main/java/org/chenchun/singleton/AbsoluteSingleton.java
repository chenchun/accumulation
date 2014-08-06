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
package org.chenchun.singleton;

import java.lang.reflect.Method;

/**
 * 在多个类加载器环境下的单例
 *
 * @author chenchun
 * @version 1.0
 * @created 2012-9-24
 */
public class AbsoluteSingleton {

  private static AbsoluteSingleton instance = null;

  public synchronized static AbsoluteSingleton getInstance() {
    ClassLoader myClassLoader = AbsoluteSingleton.class.getClassLoader();
    if (instance == null) {
      if (!myClassLoader.toString().startsWith("sun.")) {
        try {
          ClassLoader parentClassLoader = AbsoluteSingleton.class.getClassLoader().getParent();
          Class<?> otherClassInstance = parentClassLoader.loadClass(AbsoluteSingleton.class.getName());
          Method getInstanceMethod = otherClassInstance.getDeclaredMethod("getInstance", new Class[]{});
          Object otherAbsoluteSingleton = getInstanceMethod.invoke(null, new Object[]{});
          System.out.println(Thread.currentThread().getContextClassLoader());
          return (AbsoluteSingleton) otherAbsoluteSingleton;
        } catch (Exception e) {
          e.printStackTrace();
        }
      } else {
        instance = new AbsoluteSingleton();
      }
    }
    return instance;
  }

  private AbsoluteSingleton() {
  }

}
