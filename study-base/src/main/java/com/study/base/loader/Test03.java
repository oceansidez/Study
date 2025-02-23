package com.study.base.loader;

public class Test03 {

    public static void main(String[] args) {
        System.out.println(ChildClass.class.getClassLoader());
        System.out.println(ChildClass.class.getClassLoader().getParent());
        System.out.println(ChildClass.class.getClassLoader().getParent().getParent());
    }
}
