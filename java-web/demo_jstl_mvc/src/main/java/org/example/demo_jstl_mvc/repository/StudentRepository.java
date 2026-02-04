package org.example.demo_jstl_mvc.repository;

import org.example.demo_jstl_mvc.dto.StudentDto;
import org.example.demo_jstl_mvc.entity.Student;
import org.example.demo_jstl_mvc.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository {
    private final String SELECT_ALL = "select s.*,c.name as class_name from students s join classes c on s.class_id=c.id;";
    private final String SEARCH_BY_NAME = "call search_by_name(?);";
    private final String SEARCH_BY_NAME_AND_CLASS = "call search_by_name_and_class_id(?,?);";
    private final String INSERT_INTO = "insert into students(name,gender,score,class_id) values(?,?,?,?);";
    private final String DELETE_BY_ID = "delete from students where id = ?;";

//    delimiter //
//    create procedure search_by_name(IN p_name varchar(50))
//    begin
//    select s.*,c.name as class_name from students s join classes c on s.class_id=c.id where s.name like concat('%',p_name,'%');
//    end //
//    // delimiter ;
    @Override
    public List<StudentDto> findAll() {
        List<StudentDto> students = new ArrayList<>();
        try(Connection connection = ConnectDB.getConnectDB();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                boolean gender = resultSet.getBoolean("gender");
                float score = resultSet.getFloat("score");
                String className = resultSet.getString("class_name");
                students.add(new StudentDto(id,name,gender,score,className));
            }
        } catch (SQLException e) {
            System.out.println("lỗi kết nối DB");
        }
        return students;
    }

    @Override
    public List<StudentDto> search(String searchName, int classId) {
        List<StudentDto> students = new ArrayList<>();

        try{
            Connection connection = ConnectDB.getConnectDB();
            PreparedStatement preparedStatement = null;
            if (classId==0){
                preparedStatement = connection.prepareStatement(SEARCH_BY_NAME);
                preparedStatement.setString(1,searchName);
            } else {
                preparedStatement = connection.prepareStatement(SEARCH_BY_NAME_AND_CLASS);
                preparedStatement.setString(1,searchName);
                preparedStatement.setInt(2,classId);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                boolean gender = resultSet.getBoolean("gender");
                float score = resultSet.getFloat("score");
                String className = resultSet.getString("class_name");
                students.add(new StudentDto(id,name,gender,score,className));
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
            preparedStatement.setInt(4,student.getClassId());
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
