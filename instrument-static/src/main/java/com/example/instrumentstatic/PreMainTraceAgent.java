package com.example.instrumentstatic;

import java.lang.instrument.Instrumentation;

public class PreMainTraceAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("=========premain方法执行========");
        // 添加Transformer
        inst.addTransformer(new MyTransformer(), true);
    }
}
