package org.example.demo_jstl_mvc.service;

import org.example.demo_jstl_mvc.dto.StudentDto;
import org.example.demo_jstl_mvc.entity.Student;
import org.example.demo_jstl_mvc.repository.IStudentRepository;
import org.example.demo_jstl_mvc.repository.StudentRepository;

import java.util.List;

public class StudentService implements IStudentService{
    private IStudentRepository studentRepository = new StudentRepository();
    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<StudentDto> search(String name, int classId) {
        return studentRepository.search(name,classId);
    }

    @Override
    public boolean add(Student student) {
        return studentRepository.add(student);
    }

    @Override
    public boolean deleteById(int id) {
        return studentRepository.deleteById(id);
    }
}
