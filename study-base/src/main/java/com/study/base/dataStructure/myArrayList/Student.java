package com.study.base.dataStructure.myArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 学生类
 *
 * @author zzh
 * @date 2024/9/24 10:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String code;
    private String name;
    private int age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(code, student.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
