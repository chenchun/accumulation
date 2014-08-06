/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * 
 */
package org.chenchun.singleton;

import java.io.Serializable;


/**
 * 双重检查锁定double checked locking，懒汉模式
 *
 * @author chenchun
 * @created Sep 21, 2012
 *
 * @version 1.0
 */
public class DoubleCheckSingleton implements Serializable {
    
    /**
     * 加上volatile，每次使用它都到主存中进行读取
     */
    private volatile static DoubleCheckSingleton instance = null;
    
    /**
     * 防止通过反射生成对象
     */
    private DoubleCheckSingleton() {
        if (instance != null) {
            throw new RuntimeException();
        }
    }
    
    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
    
    /**
     * 防止反序列化生成破坏单例
     *
     * @author chenchun
     * @created Sep 21, 2012
     *
     * @return
     */
    private Object readResolve() {
        return getInstance();
    }
    
    private static final long serialVersionUID = 3883766053794926058L;
}
