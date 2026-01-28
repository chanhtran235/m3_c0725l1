package org.example.demo_jstl_mvc.repository;

import org.example.demo_jstl_mvc.entity.Student;

import java.util.List;

public interface IStudentRepository {
    List<Student> findAll();
    boolean add(Student student);
}
