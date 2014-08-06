/*
 * Copyright (c) 2010-2011 meituan.com
 * All rights reserved.
 * 
 */
package org.chenchun.singleton;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 自定义类加载器
 * 
 * @author chenchun
 * @created 2012-9-24
 * 
 * @version 1.0
 */
public class DefineLoader extends ClassLoader {

    private String dirName;

    public DefineLoader(String dirName) {
        this.dirName = dirName;
    }

    public Class<?> findClass(String className) throws ClassNotFoundException {
        Class<?> clazz = this.findLoadedClass(className);
        if (null == clazz) {
            byte[] bytes = loadClassBytes(className);
            clazz = defineClass(className, bytes, 0, bytes.length);
        }
        return clazz;
    }

    private byte[] loadClassBytes(String className) throws ClassNotFoundException {
        try {
            String classFile = getClassFile(className);
            FileInputStream fis = new FileInputStream(classFile);
            FileChannel fileC = fis.getChannel();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            WritableByteChannel outC = Channels.newChannel(baos);
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            while (true) {
                int i = fileC.read(buffer);
                if (i == 0 || i == -1) {
                    break;
                }
                buffer.flip();
                outC.write(buffer);
                buffer.clear();
            }
            fis.close();
            return baos.toByteArray();
        } catch (IOException fnfe) {
            throw new ClassNotFoundException(className);
        }
    }

    private String getClassFile(String name) {
        StringBuffer sb = new StringBuffer(dirName);
        name = name.replace('.', File.separatorChar) + ".class";
        sb.append(File.separator + name);
        return sb.toString();
    }
}
