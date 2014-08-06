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

import java.io.Serializable;


/**
 * 内部类方式，懒汉模式
 *
 * @author chenchun
 * @version 1.0
 * @created Sep 21, 2012
 */
public class InnerStaticSingleton implements Serializable {

  private InnerStaticSingleton() {
  }

  public static InnerStaticSingleton getInstance() {
    return Inner.INNER_STATIC_SINGLETON;
  }

  private static class Inner {
    static final InnerStaticSingleton INNER_STATIC_SINGLETON = new InnerStaticSingleton();
  }

  /**
   * 防止反序列化生成破坏单例
   *
   * @return
   * @author chenchun
   * @created Sep 21, 2012
   */
  private Object readResolve() {
    return getInstance();
  }

  private static final long serialVersionUID = -837002944123801665L;
}
