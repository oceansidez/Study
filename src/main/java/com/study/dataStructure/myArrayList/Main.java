package com.study.dataStructure.myArrayList;

/**
 * @author zzh
 * @date 2024/9/23 17:30
 */
public class Main {
    public static void main(String[] args) {
        MyArrayList<Student> myArrayList = new MyArrayList();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setCode("A" + i);
            student.setName("zhangsan" + i);
            student.setAge(10 + i % 10);
            myArrayList.add(student);
        }
        Student student = new Student();

        myArrayList.set(0, student);
        System.out.println("myArrayList = " + myArrayList);

        Student student2 = new Student();
        student2.setCode("A1");
        System.out.println("myArrayList.contains(student2) = " + myArrayList.contains(student2));

        System.out.println("myArrayList.remove(0) = " + myArrayList.remove(0));
        System.out.println("myArrayList = " + myArrayList);

        myArrayList.clear();
        System.out.println("myArrayList = " + myArrayList);

        myArrayList.add(new Student("ccc", "zhang", 18));
        System.out.println("myArrayList = " + myArrayList);
    }
}