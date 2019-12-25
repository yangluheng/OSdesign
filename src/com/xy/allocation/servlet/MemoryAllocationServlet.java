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

@WebServlet("/MemoryAllocationServlet")
public class MemoryAllocationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String button=request.getParameter("button");
        Allocation allocation= (Allocation) request.getSession().getAttribute("allocation");
        LinkedList<Zone> zones=new LinkedList<>();
        LinkedList<Zone> zoneLinkedList= (LinkedList<Zone>) request.getSession().getAttribute("zones");
        String size_allocation = request.getParameter("size");        //要分配的进程大小
        if (size_allocation==""){
            size_allocation="0";
        }
        int size = Integer.parseInt(size_allocation);
        System.out.println(size);
        if (button.equals("FF")) {       //首次适应算法
            zoneLinkedList = allocation.FF(size, zoneLinkedList);
            allocation.printZones(zoneLinkedList);
            for (int i = 0; i < zoneLinkedList.size(); i++) {
                zones.add(zoneLinkedList.get(i));
            }
            System.out.println(zones.get(0).getZoneSize());
            request.getSession().setAttribute("allocation", allocation);
            request.getSession().setAttribute("zones", zones);
            request.getRequestDispatcher("/memoryShow.jsp").forward(request, response);
        }
        if (button.equals("NF")) {       //循环首次适应算法
            zoneLinkedList = allocation.NF(size, zoneLinkedList);
            allocation.printZones(zoneLinkedList);
            for (int i = 0; i < zoneLinkedList.size(); i++) {
                zones.add(zoneLinkedList.get(i));
            }
            System.out.println(zones.get(0).getZoneSize());
            request.getSession().setAttribute("allocation", allocation);
            request.getSession().setAttribute("zones", zones);
            request.getRequestDispatcher("/memoryShow.jsp").forward(request, response);
        }
        if (button.equals("BF")) {       //最佳适应算法
            zoneLinkedList = allocation.BF(size, zoneLinkedList);
            allocation.printZones(zoneLinkedList);
            for (int i = 0; i < zoneLinkedList.size(); i++) {
                zones.add(zoneLinkedList.get(i));
            }
            System.out.println(zones.get(0).getZoneSize());
            request.getSession().setAttribute("allocation", allocation);
            request.getSession().setAttribute("zones", zones);
            request.getRequestDispatcher("/memoryShow.jsp").forward(request, response);
        }

        if (button.equals("WF")) {       //最坏适应算法
            zoneLinkedList = allocation.WF(size, zoneLinkedList);
            allocation.printZones(zoneLinkedList);
            for (int i = 0; i < zoneLinkedList.size(); i++) {
                zones.add(zoneLinkedList.get(i));
            }
            System.out.println(zones.get(0).getZoneSize());
            request.getSession().setAttribute("allocation", allocation);
            request.getSession().setAttribute("zones", zones);
            request.getRequestDispatcher("/memoryShow.jsp").forward(request, response);
        }


        if (button.equals("Collection")) {   //内存回收
            String id_size = request.getParameter("id");
            int id = Integer.parseInt(id_size);
            zoneLinkedList = allocation.doCollection(id, zoneLinkedList);
            allocation.printZones(zoneLinkedList);
            for (int i = 0; i < zoneLinkedList.size(); i++) {
                zones.add(zoneLinkedList.get(i));
            }
            System.out.println(zones.get(0).getZoneSize());
            request.getSession().setAttribute("allocation", allocation);
            request.getSession().setAttribute("zones", zones);
            request.getRequestDispatcher("/memoryShow.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
