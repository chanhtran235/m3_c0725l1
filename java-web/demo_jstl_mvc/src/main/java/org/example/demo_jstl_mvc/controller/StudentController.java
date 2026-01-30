package org.example.demo_jstl_mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo_jstl_mvc.entity.Student;
import org.example.demo_jstl_mvc.service.IStudentService;
import org.example.demo_jstl_mvc.service.StudentService;

import java.io.IOException;

@WebServlet(name = "StudentController", value = "/student")
public class StudentController extends HttpServlet {
    private IStudentService studentService = new StudentService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null){
            action ="";
        }
        switch (action){
            case "add":
                req.getRequestDispatcher("/view/student/add.jsp").forward(req,resp);
                break;
            case "search":
                break;
            default:
                showList(req,resp);
        }

    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("studentList",studentService.findAll());
        req.getRequestDispatcher("/view/student/list.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null){
            action ="";
        }
        switch (action){
            case "add":
                save(req,resp);
                break;
            case "delete":
                deleteById(req,resp);
                break;
            default:


        }
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) {
        int deleteId = Integer.parseInt(req.getParameter("deleteId"));
       boolean isDelete= studentService.deleteById(deleteId);
        try {
            resp.sendRedirect("/student");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name= req.getParameter("name");
        boolean gender = Boolean.parseBoolean(req.getParameter("gender"));
        float score = Float.parseFloat(req.getParameter("score"));
        Student student = new Student(id,name,gender,score);
        boolean isAddSuccess = studentService.add(student);
        String mess = isAddSuccess?"Thanh cong":"That bai";
        try {
            resp.sendRedirect("/student?mess="+mess);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
