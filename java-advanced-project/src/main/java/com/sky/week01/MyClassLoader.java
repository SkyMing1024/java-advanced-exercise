package com.sky.week01;

import com.sky.util.FileUtil;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] newByte = new byte[0];
        try {
            // 读取resources目录下文件
            byte[] bytes = FileUtil.toByteArray("asset/Hello.xlass");
            newByte = new byte[bytes.length];
            // 获取原始字节码
            for (int i = 0; i < bytes.length; i++) {
                newByte[i] = (byte) (255-bytes[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name,newByte,0,newByte.length);
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        MyClassLoader loader = new MyClassLoader();
        Class<?> helloClass = loader.findClass("Hello");
        Object instance = helloClass.newInstance();
        // 获取所有方法
        Method[] declaredMethods = helloClass.getDeclaredMethods();
        // 输出所有方法名称，并执行
        for (int i = 0; i < declaredMethods.length; i++) {
            System.out.println("第"+String.valueOf(i+1)+"个方法："+declaredMethods[i].getName());
            declaredMethods[i].invoke(instance);

        }
    }
}
