package com.xy.servlet;

import com.xy.process.entity.Process;
import com.xy.process.function.ProcessScheduling2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ProcessSchedulingServlet")
public class ProcessSchedulingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("test");
        int processNum= Integer.parseInt(request.getParameter("processNum"));
        System.out.println(processNum);
        request.setAttribute("processNum",processNum);
        request.getRequestDispatcher("/process.jsp").forward(request,response);
//        ProcessScheduling2 processScheduling=new ProcessScheduling2();
//        processScheduling.init(processNum);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
