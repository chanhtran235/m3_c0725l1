package org.example.demo_servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SumServlet", value = "/sum")
public class SumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // trả về form để liệu
        req.getRequestDispatcher("/form-sum.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // nhân dữ liệu => tính toán và hiển thi kết quả
        int n1 = Integer.parseInt(req.getParameter("n1"));
        int n2 = Integer.parseInt(req.getParameter("n2"));
        int sum = n1+n2;
        req.setAttribute("tong",sum);
        req.getRequestDispatcher("/form-sum.jsp").forward(req,resp);

    }
}
