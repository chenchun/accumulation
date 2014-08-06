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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import junit.framework.Assert;

import org.junit.Test;

/**
 * 测试在多个类加载器环境下的单例模式
 *
 * @author chenchun
 * @version 1.0
 * @created 2012-9-24
 */
public class ClassLoadSingletonTest {

  @Test
  public void test() {
    AbsoluteSingleton instance = (AbsoluteSingleton) AbsoluteSingleton.getInstance();
    try {
      DefineLoader myClassLoader = new DefineLoader("/Users/cc3514772b/project/accumulation/target/classes");
      Class<?> clazz = myClassLoader.findClass("AbsoluteSingleton");
      Method m = clazz.getMethod("getInstance");
      Object another = m.invoke(null);
      Assert.assertTrue(another instanceof AbsoluteSingleton);
      Assert.assertTrue(another == instance);
      AbsoluteSingleton anotherSingleton = (AbsoluteSingleton) another;
      Assert.assertNotNull(anotherSingleton);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testInstanceof() {
    Integer a = 5;
    Object b = (Object) a;
    Assert.assertTrue(a == b);
  }

}
