package com.xy.banker.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BankerServlet")
public class BankerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String resource_num=request.getParameter("resourceNum");
        String process_num=request.getParameter("processNum");
        int resourceNum=Integer.parseInt(resource_num);
        int processNum=Integer.parseInt(process_num);
        request.getSession().setAttribute("resourceNum",resource_num);
        request.getSession().setAttribute("processNum",process_num);
        request.getRequestDispatcher("/banker.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
