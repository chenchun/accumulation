/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * 
 */
package com.knave.pattern.singleton;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import junit.framework.Assert;

import org.junit.Test;

/**
 * 测试在多个类加载器环境下的单例模式
 *
 * @author chenchun
 * @created 2012-9-24
 *
 * @version 1.0
 */
public class ClassLoadSingletonTest {
    
    @Test
    public void test() {
        SingletonInterface instance = AbsoluteSingleton.getInstance();
        try {
            MyClassLoader myClassLoader = new MyClassLoader("/Users/cc3514772b/project/accumulation/target/classes");
            Class<?> clazz = myClassLoader.findClass("com.knave.pattern.singleton.AbsoluteSingleton");
            Method m = clazz.getMethod("getInstance");
            Object another = m.invoke(null);
            Assert.assertTrue(another instanceof AbsoluteSingleton);
            Assert.assertTrue(another == instance);
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

}
