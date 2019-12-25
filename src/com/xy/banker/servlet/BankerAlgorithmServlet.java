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

@WebServlet("/BankerAlgorithmServlet")
public class BankerAlgorithmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String resource_num= (String) request.getSession().getAttribute("resourceNum");
        String process_num= (String) request.getSession().getAttribute("processNum");
        BankerAlgorithm bankerAlgorithm=new BankerAlgorithm();
        Object Resources=request.getSession().getAttribute("resources");
        int[] resources= (int[]) Resources;
        int resourceNum=Integer.parseInt(resource_num);
        int processNum=Integer.parseInt(process_num);
        int[] available=resources;
        int[] Available=new int[resourceNum];
        int[][] max= (int[][]) request.getSession().getAttribute("max");
        int[][] allocation= (int[][]) request.getSession().getAttribute("allocation");
        int[][] need= (int[][]) request.getSession().getAttribute("need");
        String[] maxP=new String[processNum];
        String[] allocationP=new String[processNum];
        ArrayList<Object> arrayList=new ArrayList<>();
        ArrayList<Object> arrayList1=new ArrayList<>();
        Object[] Smax=new Object[processNum][resourceNum];
        Object[] Sallocation= new Object[processNum][resourceNum];
        String[] requests=request.getParameterValues("requestNum");
        int requestProcessId= Integer.parseInt(request.getParameter("requestProcessId"));
        int[][] Request=new int[processNum][resourceNum];
        int[] work=new int[resourceNum];
        int[][] Work=new int[processNum][resourceNum];
        int[][] workAndAllocation=new int[processNum][resourceNum];
        StringToInt.StringToInt(Request[requestProcessId],requests);
//        for (int i = 0; i <resourceNum; i++) {
//            Available[i]=available[i]-Request[requestProcessId][i];
//        }
        int[] security=bankerAlgorithm.setRequest(processNum,resourceNum,Request,max,allocation,need,available,work,Work,workAndAllocation,requestProcessId);
        request.getSession().setAttribute("need",need);
        request.getSession().setAttribute("max",max);
        request.getSession().setAttribute("allocation",allocation);
        request.getSession().setAttribute("resources",available);
        request.getSession().setAttribute("Work",Work);
        request.getSession().setAttribute("workAndAllocation",workAndAllocation);
        request.getSession().setAttribute("security",security);
        request.getRequestDispatcher("/bankerRequestShow.jsp").forward(request,response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
