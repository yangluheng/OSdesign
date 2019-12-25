package com.xy.servlet;

import com.xy.process.entity.Process;
import com.xy.process.function.ProcessScheduling2;
import com.xy.process.function.ProcessScheduling3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ProcessServlet")
public class ProcessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
//        int processNum= Integer.parseInt(request.getParameter("processNum"));
        String[] arrive=request.getParameterValues("arriveTime");
        String[] serve=request.getParameterValues("serveTime");
        String[] prior=request.getParameterValues("priority");
        String button=request.getParameter("button");
        int sliceTime= Integer.parseInt(request.getParameter("sliceTime"));
        int processNum=arrive.length;
        System.out.println(processNum);
        int[] arriveTimes=new int[processNum];
        int[] serveTimes=new int[processNum];
        int[] priorities=new int[processNum];
        ArrayList<Process> processesSort=new ArrayList<>();
        ArrayList<Process> processes=new ArrayList<>();
        double roundTimeSum=0;
        double weightRoundTimeSum=0;
        double averageRoundTime=0;
        double averageWeightRoundTime=0;
       for (int i = 0; i <processNum ; i++) {
            arriveTimes[i]= Integer.parseInt(arrive[i]);
            serveTimes[i]= Integer.parseInt(serve[i]);
            priorities[i]= Integer.parseInt(prior[i]);
            System.out.println("第"+(i+1)+"个进程:到达时间:"+arriveTimes[i]+"\t服务时间:"+serveTimes[i]+"\t优先级:"+priorities[i]);

        }
        ProcessScheduling3 processScheduling3=new ProcessScheduling3();
        processScheduling3.init(processNum,arriveTimes,serveTimes,priorities);
        request.setAttribute("button",button);
        if (button.equals("FCFS")){
            System.out.println("FCFS");
            processesSort=processScheduling3.FCFS();
            for (int i = 0; i <processesSort.size() ; i++) {
                processes.add(processesSort.get(i));
                int roundTime=processesSort.get(i).getRoundTime();
                double aveRoundTime=processesSort.get(i).getAveRoundTime();
                roundTimeSum+=roundTime;
                weightRoundTimeSum+=aveRoundTime;
            }
            averageRoundTime=roundTimeSum/processNum;
            averageWeightRoundTime=weightRoundTimeSum/processNum;
//            sum(processesSort,processes,roundTimeSum,weightRoundTimeSum,processNum,averageRoundTime,averageWeightRoundTime);
            request.setAttribute("averageRoundTime",averageRoundTime);
            request.setAttribute("averageWeightRoundTime",averageWeightRoundTime);
            request.setAttribute("processesSort",processes);
            processScheduling3.new ProcessQueue().printProcess();
            request.getRequestDispatcher("/show.jsp").forward(request,response);

        }

        if (button.equals("SJF")){
            System.out.println("SJF");
            processesSort=processScheduling3.SJF();
            for (int i = 0; i <processesSort.size() ; i++) {
                processes.add(processesSort.get(i));
                int roundTime=processesSort.get(i).getRoundTime();
                double aveRoundTime=processesSort.get(i).getAveRoundTime();
                roundTimeSum+=roundTime;
                weightRoundTimeSum+=aveRoundTime;
            }
            averageRoundTime=roundTimeSum/processNum;
            averageWeightRoundTime=weightRoundTimeSum/processNum;
//            sum(processesSort,processes,roundTimeSum,weightRoundTimeSum,processNum,averageRoundTime,averageWeightRoundTime);
            request.setAttribute("averageRoundTime",averageRoundTime);
            request.setAttribute("averageWeightRoundTime",averageWeightRoundTime);
            request.setAttribute("processesSort",processes);
            processScheduling3.new ProcessQueue().printProcess();
            request.getRequestDispatcher("/show.jsp").forward(request,response);
        }
        if (button.equals("RR")){
            System.out.println("RR");
            processesSort=processScheduling3.RR(sliceTime);
            for (int i = 0; i <processesSort.size() ; i++) {
                processes.add(processesSort.get(i));
                int roundTime=processesSort.get(i).getRoundTime();
                double aveRoundTime=processesSort.get(i).getAveRoundTime();
                roundTimeSum+=roundTime;
                weightRoundTimeSum+=aveRoundTime;
            }
            averageRoundTime=roundTimeSum/processNum;
            averageWeightRoundTime=weightRoundTimeSum/processNum;
//            sum(processesSort,processes,roundTimeSum,weightRoundTimeSum,processNum,averageRoundTime,averageWeightRoundTime);
            request.setAttribute("averageRoundTime",averageRoundTime);
            request.setAttribute("averageWeightRoundTime",averageWeightRoundTime);
            request.setAttribute("processesSort",processes);
            processScheduling3.new ProcessQueue().printProcess();
            request.getRequestDispatcher("/showTime.jsp").forward(request,response);
        }
        if (button.equals("HRRN")){
            System.out.println("HRRN");
            processesSort=processScheduling3.HRRN();
            for (int i = 0; i <processesSort.size() ; i++) {
                processes.add(processesSort.get(i));
                int roundTime=processesSort.get(i).getRoundTime();
                double aveRoundTime=processesSort.get(i).getAveRoundTime();
                roundTimeSum+=roundTime;
                weightRoundTimeSum+=aveRoundTime;
            }
            averageRoundTime=roundTimeSum/processNum;
            averageWeightRoundTime=weightRoundTimeSum/processNum;
//            sum(processesSort,processes,roundTimeSum,weightRoundTimeSum,processNum,averageRoundTime,averageWeightRoundTime);
            request.setAttribute("averageRoundTime",averageRoundTime);
            request.setAttribute("averageWeightRoundTime",averageWeightRoundTime);
            request.setAttribute("processesSort",processes);
            processScheduling3.new ProcessQueue().printProcess();
            request.getRequestDispatcher("/show.jsp").forward(request,response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
