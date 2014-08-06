/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * 
 */
package org.chenchun.singleton;

import java.io.Serializable;


/**
 * 内部类方式，懒汉模式
 *
 * @author chenchun
 * @created Sep 21, 2012
 *
 * @version 1.0
 */
public class InnerStaticSingleton implements Serializable {

    private InnerStaticSingleton() {}
    
    public static InnerStaticSingleton getInstance() {
        return Inner.INNER_STATIC_SINGLETON;
    }

    private static class Inner {
        static final InnerStaticSingleton INNER_STATIC_SINGLETON = new InnerStaticSingleton();
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
    
    private static final long serialVersionUID = -837002944123801665L;
}
