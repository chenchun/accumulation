/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * 
 */
package com.chenchun.pattern.singleton;

/**
 * Effective Java作者Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象
 *
 * @author chenchun
 * @created Sep 21, 2012
 *
 * @version 1.0
 */
public enum EnumSingleton {

    INSTANCE;
}
