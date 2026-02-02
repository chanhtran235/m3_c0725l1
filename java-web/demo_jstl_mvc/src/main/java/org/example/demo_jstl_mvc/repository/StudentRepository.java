package org.example.demo_jstl_mvc.repository;

import org.example.demo_jstl_mvc.entity.Student;
import org.example.demo_jstl_mvc.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository {
    private static List<Student> studentList = new ArrayList<>();
    private final String SELECT_ALL = "select * from students;";
    private final String INSERT_INTO = "insert into students(name,gender,score) values(?,?,?);";
    private final String DELETE_BY_ID = "delete from students where id = ?;";


    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try(Connection connection = ConnectDB.getConnectDB();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                boolean gender = resultSet.getBoolean("gender");
                float score = resultSet.getFloat("score");
                students.add(new Student(id,name,gender,score));
            }
        } catch (SQLException e) {
            System.out.println("lỗi kết nối DB");
        }
        return students;
    }

    @Override
    public boolean add(Student student) {
        try(Connection connection = ConnectDB.getConnectDB();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {
            preparedStatement.setString(1,student.getName());
            preparedStatement.setBoolean(2,student.isGender());
            preparedStatement.setFloat(3,student.getScore());
            int effectRow = preparedStatement.executeUpdate();
            return  effectRow ==1;
        } catch (SQLException e) {
            System.out.println("lỗi kết nối DB");
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try(Connection connection = ConnectDB.getConnectDB();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1,id);
            int effectRow = preparedStatement.executeUpdate();
            return  effectRow ==1;
        } catch (SQLException e) {
            System.out.println("lỗi kết nối DB");
        }
        return false;
    }
}
