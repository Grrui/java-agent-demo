package com.example.targetdemo;

public class TestMain {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            int a = 1;
            int b = 1;
            System.out.printf("%d + %d = %d\n", 1, 1, TestMain.add(a, b));
            Thread.sleep(1000L);
        }
    }

    public static int add(int a, int b) {
        return a + b;
    }
}
