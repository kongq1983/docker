package com.kq.product.util;

public class StackDemo {

    public void test(int i) {
        System.out.println("i="+i);
        i=i+1;
        test(i);

    }




    public static void main(String[] args) {
        new StackDemo().test(1);
    }

}
