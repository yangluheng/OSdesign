package com.xy.allocation.servlet;

import com.xy.allocation.entity.Zone;
import com.xy.allocation.function.Allocation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

@WebServlet("/MemoryServlet")
public class MemoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String memory_ize=request.getParameter("memorySize");
        int memorySize=Integer.parseInt(memory_ize);
        System.out.println(memorySize);
        LinkedList<Zone> zones=new LinkedList<>();
        LinkedList<Zone> zoneLinkedList=new LinkedList<>();
        Allocation allocation=new Allocation();
        zoneLinkedList=allocation.menu(memorySize);
        request.getSession().setAttribute("allocation",allocation);
        for (int i = 0; i <zoneLinkedList.size() ; i++) {
            zones.add(zoneLinkedList.get(i));
        }
        request.getSession().setAttribute("zones",zones);
        System.out.println(zones.get(0).getZoneSize());
        request.getRequestDispatcher("/memory.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
