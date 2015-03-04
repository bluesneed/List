package com.dkiong.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Student extends HashMap<String, String> {

    public Student(String name, int age, String school) {
        put("name", name);
        put("age", Integer.toString(age));
        put("school", school);
    }

    public static List<Student> getStudents() {
        List<Student> students = new ArrayList<Student>();
        students.add(new Student("Tan Ah Kow", 12, "Smart School"));
        students.add(new Student("Lim Ah Pin", 11, "Rich School"));
        students.add(new Student("Teo Wong Heng", 14, "Low School"));
        students.add(new Student("Ho Go Woo", 15, "River School"));
        return students;
    }
}