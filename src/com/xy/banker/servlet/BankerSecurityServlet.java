package com.xy.banker.servlet;

import com.xy.banker.function.BankerAlgorithm;
import com.xy.banker.function.StringToInt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/BankerSecurityServlet")
public class BankerSecurityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String resource_num= (String) request.getSession().getAttribute("resourceNum");
        String process_num= (String) request.getSession().getAttribute("processNum");
        BankerAlgorithm bankerAlgorithm=new BankerAlgorithm();
        String[] resources=request.getParameterValues("resources");
        int resourceNum=Integer.parseInt(resource_num);
        int processNum=Integer.parseInt(process_num);
        int[] available=new int[resourceNum];
        int[] Available=new int[resourceNum];
        int[] available1=new int[resourceNum];
        StringToInt.StringToInt(available,resources);
        StringToInt.StringToInt(available1,resources);
        int[][] max=new int[processNum][resourceNum];
        int[][] allocation=new int[processNum][resourceNum];
        int[][] need=new int[processNum][resourceNum];
        int[][] allocation1=new int[processNum][resourceNum];
        int[][] need1=new int[processNum][resourceNum];
        String[] maxP=new String[processNum];
        String[] allocationP=new String[processNum];
        ArrayList<String[]> arrayList=new ArrayList<>();
        ArrayList<String[]> arrayList1=new ArrayList<>();
        String[][] Smax=new String[processNum][resourceNum];
        String[][] Sallocation=new String[processNum][resourceNum];
        String[] requests=request.getParameterValues("requestNum");
        int[][] Request=new int[processNum][resourceNum];
        int[] work=new int[resourceNum];
        int[][] Work=new int[processNum][resourceNum];
        int[][] workAndAllocation=new int[processNum][resourceNum];
        int[] work1=new int[resourceNum];
        int[][] Work1=new int[processNum][resourceNum];
        int[][] workAndAllocation1=new int[processNum][resourceNum];
        for (int i = 0; i <processNum ; i++) {
            maxP[i]="maxP"+i;
            allocationP[i]="allocationP"+i;
            arrayList.add(request.getParameterValues(maxP[i]));
            arrayList1.add(request.getParameterValues(allocationP[i]));
        }
            for (int i = 0; i <processNum ; i++) {
                    Smax[i]=arrayList.get(i);
            }
            for (int i = 0; i <processNum ; i++) {
                Sallocation[i]=arrayList1.get(i);
            }
            StringToInt.StringToInt(max,Smax);
            StringToInt.StringToInt(allocation,Sallocation);
            StringToInt.StringToInt(allocation1,Sallocation);
        bankerAlgorithm.setNeed(resourceNum,processNum,need,max,allocation,available);
        bankerAlgorithm.setNeed(resourceNum,processNum,need1,max,allocation1,available1);
        int[] security=bankerAlgorithm.isSecurity(processNum,resourceNum,allocation1,need1,available1,work1,Work1,workAndAllocation1);
        request.getSession().setAttribute("need",need);
        request.getSession().setAttribute("max",max);
        request.getSession().setAttribute("allocation",allocation);
        request.getSession().setAttribute("resources",available);
        request.getSession().setAttribute("Work",Work);
        request.getSession().setAttribute("workAndAllocation",workAndAllocation);
        request.getSession().setAttribute("security",security);
        request.getSession().setAttribute("need1",need1);
        request.getSession().setAttribute("allocation1",allocation1);
        request.getSession().setAttribute("Work1",Work1);
        request.getSession().setAttribute("workAndAllocation1",workAndAllocation1);
        request.getSession().setAttribute("security1",security);
        request.getRequestDispatcher("/bankerShow.jsp").forward(request,response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
