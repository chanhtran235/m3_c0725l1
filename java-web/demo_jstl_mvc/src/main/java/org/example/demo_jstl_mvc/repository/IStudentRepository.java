package org.example.demo_jstl_mvc.repository;

import org.example.demo_jstl_mvc.dto.StudentDto;
import org.example.demo_jstl_mvc.entity.Student;

import java.util.List;

public interface IStudentRepository {
    List<StudentDto> findAll();
    List<StudentDto> search(String name, int classId);
    boolean add(Student student);
    boolean deleteById(int id);
}
