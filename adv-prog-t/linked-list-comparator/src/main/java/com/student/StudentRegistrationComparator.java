package com.student;
import java.util.Comparator;


public class StudentRegistrationComparator implements Comparator<Student>{
    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o1.getReg(), o2.getReg());
    }
}
