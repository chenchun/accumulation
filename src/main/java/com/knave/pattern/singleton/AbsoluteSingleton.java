/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * 
 */
package com.knave.pattern.singleton;

import java.lang.reflect.Method;

/**
 * 在多个类加载器环境下的单例
 * 
 * @author chenchun
 * @created 2012-9-24
 * 
 * @version 1.0
 */
public class AbsoluteSingleton implements SingletonInterface {

    private static SingletonInterface instance = null;

    public synchronized static SingletonInterface getInstance() {
        ClassLoader myClassLoader = AbsoluteSingleton.class.getClassLoader();
        if (instance == null) {
            if (!myClassLoader.toString().startsWith("sun.")) {
                try {
                    ClassLoader parentClassLoader = AbsoluteSingleton.class.getClassLoader().getParent();
                    Class<?> otherClassInstance = parentClassLoader.loadClass(AbsoluteSingleton.class.getName());
                    Method getInstanceMethod = otherClassInstance.getDeclaredMethod("getInstance", new Class[] { });
                    Object otherAbsoluteSingleton = getInstanceMethod.invoke(null, new Object[] { } );
                    return (SingletonInterface) otherAbsoluteSingleton;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                instance = new AbsoluteSingleton();
            }
        }
        return instance;
    }

    private AbsoluteSingleton() {}

}
