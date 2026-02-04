package org.example.demo_jstl_mvc.repository;

import org.example.demo_jstl_mvc.entity.ClassCG;
import org.example.demo_jstl_mvc.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassRepository implements IClassRepository{
    @Override
    public List<ClassCG> findAll() {
        List<ClassCG> classCGList = new ArrayList<>();
        try(Connection connection = ConnectDB.getConnectDB();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from classes")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                classCGList.add(new ClassCG(id,name));
            }
        } catch (SQLException e) {
            System.out.println("lỗi kết nối DB");
        }
        return classCGList;
    }
}
