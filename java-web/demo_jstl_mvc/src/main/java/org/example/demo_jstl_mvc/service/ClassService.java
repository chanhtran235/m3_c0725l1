package org.example.demo_jstl_mvc.service;

import org.example.demo_jstl_mvc.entity.ClassCG;
import org.example.demo_jstl_mvc.entity.Student;
import org.example.demo_jstl_mvc.repository.ClassRepository;
import org.example.demo_jstl_mvc.repository.IClassRepository;
import org.example.demo_jstl_mvc.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassService implements IClassService {
    private IClassRepository classRepository = new ClassRepository();
    @Override
    public List<ClassCG> findAll() {
      return classRepository.findAll();
    }
}
