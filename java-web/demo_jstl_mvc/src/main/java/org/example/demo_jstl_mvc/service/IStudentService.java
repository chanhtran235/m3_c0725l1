package org.example.demo_jstl_mvc.service;

import org.example.demo_jstl_mvc.dto.StudentDto;
import org.example.demo_jstl_mvc.entity.Student;

import java.util.List;

public interface IStudentService {
    List<StudentDto> findAll();
    List<StudentDto> search(String name, int classId);
    boolean add(Student student);
    boolean deleteById(int id);
}
