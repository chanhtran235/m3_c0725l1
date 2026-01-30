package org.example.demo_jstl_mvc.repository;

import org.example.demo_jstl_mvc.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository {
    private static List<Student> studentList = new ArrayList<>();

    static {
        studentList.add(new Student(1, "chánh", true, 4.5f));
        studentList.add(new Student(2, " thư", false, 9.5f));
        studentList.add(new Student(3, "Hiếu", true, 7.5f));
        studentList.add(new Student(4, "hải heo", true, 5.5f));
    }

    @Override
    public List<Student> findAll() {
        return studentList;
    }

    @Override
    public boolean add(Student student) {
        studentList.add(student);
        return true;
    }

    @Override
    public boolean deleteById(int id) {
        for (int i = 0; i <studentList.size() ; i++) {
            if (studentList.get(i).getId()==id){
                studentList.remove(i);
                return true;
            }
        }
        return false;
    }
}
