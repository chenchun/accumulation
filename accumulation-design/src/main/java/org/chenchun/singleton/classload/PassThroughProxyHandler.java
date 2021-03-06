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
package org.chenchun.singleton.classload;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * An invocation handler that passes on any calls made to it directly to its delegate.
 * This is useful to handle identical classes loaded in different classloaders - the
 * VM treats them as different classes, but they have identical signatures.
 * <p/>
 * Note this is using class.getMethod, which will only work on public methods.
 *
 * @author InigoSurguy
 */
class PassThroughProxyHandler implements InvocationHandler {
  private final Object delegate;

  public PassThroughProxyHandler(Object delegate) {
    this.delegate = delegate;
  }

  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Method delegateMethod = delegate.getClass().getMethod(method.getName(), method.getParameterTypes());
    return delegateMethod.invoke(delegate, args);
  }
}
