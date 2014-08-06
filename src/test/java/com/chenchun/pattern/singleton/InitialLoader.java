/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * 
 */
package com.chenchun.pattern.singleton;

/**
 * The initiating loader 
 *
 * @author chenchun
 * @created 2012-9-25
 *
 * @version 1.0
 */
public class InitialLoader extends ClassLoader {

    /**
     * @see java.lang.ClassLoader#loadClass(java.lang.String)
     *
     * @author chenchun
     * @created 2012-9-25
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    
}
