/*******************************************************************************
 * Copyright (C) 2018 - 2019, The YUAN Authors.
 * All Rights Reserved.
 ******************************************************************************/

package cn.l.x.bean;

public class A {
    private static String[] a = new String[3];

    public A(String s1, String s2, String s3, String s4) {
        this(s1, s2, s3, a);
    }

    public A(String s1, String s2, String s3, String[] arr) {
        System.out.println("我在这里:" + s1 + s2 + s3);
    }

    // public static void main(String[] args) {
    // new A("你", "好", "啊", "呀");
    // }

}
