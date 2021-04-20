package com.example.instrumentstatic;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class MyTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        className = className.replace("/", ".");
        if ("com.example.targetdemo.TestMain".equals(className)) {
            try {
                // 从ClassPool获得CtClass对象
                final ClassPool classPool = ClassPool.getDefault();
                final CtClass clazz = classPool.get("com.example.targetdemo.TestMain");
                CtMethod add = clazz.getDeclaredMethod("add");
                // 设置add方法体为返回3
                String methodBody = "{" +
                        "return 3;" +
                        "}";
                add.setBody(methodBody);

                // 返回字节码，并且detachCtClass对象
                byte[] byteCode = clazz.toBytecode();
                //detach的意思是将内存中曾经被javassist加载过的对象移除，如果下次有需要在内存中找不到会重新走javassist加载
                clazz.detach();
                return byteCode;
            } catch (Throwable ex) {
                ex.printStackTrace();
                System.out.println(ex.getLocalizedMessage());
            }
        }
        // 如果返回null则字节码不会被修改
        return null;
    }
}