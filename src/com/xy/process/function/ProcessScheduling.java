package com.xy.process.function;

import com.xy.process.compare.CompareByArrvialTime;
import com.xy.process.compare.CompareByPriority;
import com.xy.process.compare.CompareByServiceTime;
import com.xy.process.entity.Process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @ClassName ProcessScheduling
 * @Description: TODO 进程调度算法实现 FCS SJF RR HRRN
 * @Author 杨路恒
 * @Date 2019/11/29 0029
 * @Version V1.0
 **/
public class ProcessScheduling {
    ArrayList<Process> processes;        //存放所有进程
    LinkedList<Process> processesQueue;   //存放已经进入就绪队列的进程
    ArrayList<Process> processesSort;    //存放已经按照调度算法排序的进程
    Process nowProcess;                 //当前要执行的进程
    Scanner in = new Scanner(System.in);

    /**
     * @MethodName: init
     * @Description: TODO 初始化进程
     * @Param: []
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/11/29 0029
     **/
    public void init() {
        processes = new ArrayList<>();
        processesQueue = new LinkedList<>();
        processesSort = new ArrayList<>();
        System.out.println("进程个数:");
        int processNum = in.nextInt();
        for (int i = 0; i < processNum; i++) {
            System.out.println("请输入进程信息:\t进程名\t到达时间\t\t服务时间\t\t优先级");
            String name = in.next();
            int arriveTime = in.nextInt();
            int serveTime = in.nextInt();
            double priority = in.nextDouble();
            processes.add(new Process(name, arriveTime, serveTime, priority));
            System.out.println("请输入下一个进程信息:");
        }
        //先是将进程按照到达时间排序，方便实现算法，就不需要再定义boolean来判断进程是否已经到达
        //不用每次都从头遍历进程容器，用index记录当前遍历的位置，提高算法效率
        Collections.sort(processesQueue,new CompareByArrvialTime());
        menu();         //进入算法系统
    }

    /**
     * @MethodName: menu
     * @Description: TODO 界面显示
     * @Param: []
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/11/29 0029
    **/
    public void menu(){
        Scanner in=new Scanner(System.in);
        System.out.println("************************操作系统进程调度************************");
        System.out.println("1.先来先服务算法\n2.短作业优先算法\n3.时间片轮转调度算法\n4.高响应比优先算法\n0.退出系统");
        System.out.println("请选择您的操作输入数字0,1,2,3,4:");
        String choice=in.next();
        if (choice.equals("1")){    //选择先来先服务算法
            FCFS();
            new ProcessQueue().printProcess();
            menu();
        }
        if (choice.equals("2")){    //选择短作业优先算法
            SJF();
            new ProcessQueue().printProcess();
            menu();
        }
        if (choice.equals("3")){    //选择时间片轮转调度算法
            System.out.println("请输入CPU时间片:");
            double sliceTime=in.nextDouble();
            RR(sliceTime);
            new ProcessQueue().printProcess();
            menu();
        }
        if (choice.equals("4")){    //选择高响应比优先算法
            HRRN();
            new ProcessQueue().printProcess();
            menu();
        }
        if (choice.equals("0")){    //退出系统
            System.out.println("欢迎下次使用，再见");
            System.exit(0);
        }
        else {
            System.out.println("你输入有误!");
            menu();
        }
    }


    /**
     * @MethodName: test
     * @Description: TODO 给定值调用
     * @Param: []
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/11/29 0029
    **/
    public void test(){
        processes = new ArrayList<Process>();
        processesQueue = new LinkedList<Process>();
        processesSort = new ArrayList<Process>();
        ProcessQueue processQueue=new ProcessQueue();
        Process p1 = new Process("1", 0, 4, 3);
        Process p2 = new Process("2", 1, 3, 2);
        Process p3 = new Process("3", 2, 4, 3);
        Process p4 = new Process("4", 3, 2, 1);
        Process p5 = new Process("5", 4, 4, 5);
        processes.add(p1);
        processes.add(p2);
        processes.add(p3);
        processes.add(p4);
        processes.add(p5);
        //先将processes排序，便于下面的算法实现，就不需要再定义一个标识进程是否已到达的boolean,即无需每次都从头开始扫描processes容器，
        //而是用一个K记录下当前已经扫描到的位置，一次遍历即可，提高了算法效率。
        Collections.sort(processes, new CompareByArrvialTime());
        FCFS();
        processQueue.printProcess();
//        SJF();
//        processQueue.printProcess();
//        RR(1);
//        processQueue.printProcess();
//        HRRN();
//        processQueue.printProcess();
    }

    /**
     * @MethodName: FCFS
     * @Description: TODO 先来先服务算法
     * @Param: []
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/11/29 0029
     **/
    public void FCFS() {
        ProcessQueue pq = new ProcessQueue();
        pq.nowTime=processes.get(0).getArriveTime();
        pq.EnQueue();         //让最先到达的进程入队列
        System.out.println("*************************先来先服务算法****************************");
        while (!processesQueue.isEmpty()) {      //就绪队列不为空时
            pq.displayQueue();        //打印当前队列中的进程
            pq.DeQueue();             //出队，一次一个
            pq.EnQueue();             //已到达的进程继续入队列
        }
    }

    /**
     * @MethodName: SJF
     * @Description: TODO 短作业优先算法
     * @Param: []
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/11/29 0029
     **/
    public void SJF() {
        ProcessQueue pq = new ProcessQueue();
        pq.nowTime=processes.get(0).getArriveTime();
        pq.EnQueue();
        System.out.println("*************************短作业优先算法****************************");
        while (!processesQueue.isEmpty()) {      //就绪队列不为空时
            pq.displayQueue();        //打印当前队列中的进程
            pq.DeQueue();             //出队，一次一个
            pq.EnQueue();             //已到达的进程继续入队列
            Collections.sort(processesQueue, new CompareByServiceTime());        //队列中的进程还需要按照服务时间长度进行排序
        }
    }


    /**
     * @MethodName: RR
     * @Description: TODO 时间片轮转算法
     * @Param: []
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/11/29 0029
     **/
    public void RR(double sliceTime) {
        ProcessQueue pq = new ProcessQueue();
//        pq.nowTime=processes.get(0).getArriveTime();
        pq.EnQueue();
        System.out.println("**************************时间片轮转调度算法***************************");
        while (!processesQueue.isEmpty()) {      //就绪队列不为空时
            pq.displayQueue();        //打印当前队列中的进程
            pq.DeQueue(sliceTime); //出队，一次一个,因为上一次刚出的得让刚到达的进程先入队列，所以入队操作放在这里
        }
    }

    /**
     * @MethodName: HRRN
     * @Description: TODO 高响应比优先算法
     * @Param: []
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/11/29 0029
     **/
    public void HRRN() {
        ProcessQueue pq = new ProcessQueue();
        pq.nowTime=processes.get(0).getArriveTime();
        pq.EnQueue();
        System.out.println("***********************高响应比优先调度算法******************************");
        while (!processesQueue.isEmpty()) {      //就绪队列不为空时
            pq.displayQueue();        //打印当前队列中的进程
            pq.DeQueue();             //出队，一次一个
            pq.EnQueue();             //已到达的进程继续入队列
            Collections.sort(processesQueue, new CompareByPriority());        //队列中的进程还需要按照响应比进行排序
        }
    }
        /**
         * @ClassName ProcessQueue
         * @Description: TODO 进程就绪队列操作
         * @Author 杨路恒
         * @Date 2019/11/29 0029
         * @Version V1.0
         **/
       public class ProcessQueue {
            int index = 0;            //记录进程遍历时的下标
            int count = 0;            //记录进程当前出入队列的次数
            int nowTime =0;         //当前时间
            /**
             * @MethodName: EnQueue
             * @Description: TODO  进程入队列操作，首次入队，可以多个入队
             * @Param: []
             * @Return: void
             * @Author: 杨路恒
             * @Date: 2019/11/29 0029
             **/
            public void EnQueue() {

                while (index<processes.size()) {     //遍历所有进程看是否入队
                    if (processes.get(index).getArriveTime() <=nowTime) {  //已经到达的进程按照到达的先后时间入队列
                        processesQueue.add(processes.get(index));
                        index++;
                    } else {
//                        nowTime++;
                        break;                  //如果当前进程还未入队列，保留当前index，结束遍历
                    }
                }
            }

            /**
             * @MethodName: DeQueue
             * @Description: TODO 进程出队操作，一次只能出一个
             * @Param: []
             * @Return: void
             * @Author: 杨路恒
             * @Date: 2019/11/29 0029
             **/
            public void DeQueue() {
                nowProcess = processesQueue.removeFirst();        //移除就绪队列队首元素并返回给当前进程,进入CPU运行
                //开始运行后要更新进程信息
                nowProcess.setBeginTime(nowTime);               //计算开始时间，就是上一个进程结束的时间
                nowProcess.setFinishTime(nowProcess.getBeginTime() + nowProcess.getServeTime());      //计算完成时间=该进程开始时间+服务时间
                nowProcess.setRoundTime(nowProcess.getFinishTime() - nowProcess.getArriveTime());     //计算周转时间=该进程完成时间-到达时间
                nowProcess.setAveRoundTime((double) nowProcess.getRoundTime() / nowProcess.getServeTime());//计算带权周转时间=该进程周转时间/服务时间
                nowTime = nowProcess.getFinishTime();         //获得当前进程完成时间，即当前时间就是为了判断剩下的进程是否到达
                processesSort.add(nowProcess);                //将调度过的进程加入新进程容器
                for (int i = 0; i < processesQueue.size(); ++i) {   //所有进入就绪队列的进程等待时间+1,此处只为HRRN算法所用
                    int waitTime = processesQueue.get(i).getWaitTime();
                    processesQueue.get(i).setWaitTime(++waitTime);
                }
            }

            /**
             * @MethodName: DeQueue
             * @Description: TODO 重载DeQueue方法为了RR算法出队调用
             * @Param: [sliceTime]
             * @Return: void
             * @Author: 杨路恒
             * @Date: 2019/11/29 0029
             **/
            public void DeQueue(double sliceTime) {
                nowProcess = processesQueue.removeFirst();
                nowProcess.setBeginTime(nowTime);//计算开始时间，就是上一个进程结束的时间
//                if (nowProcess.getServeTime()<sliceTime){
////                    nowTime=nowProcess.getFinishTime();
////                }
////                else nowTime += sliceTime;             //每个进程出队，都要消耗一个时间片，更新当前时间
//                double clock = nowProcess.getClock() + sliceTime;       //更新当前已经出队列的进程的已服务时间
                double clock=0;
                if (nowProcess.getServeTime()<=sliceTime){
//                    nowProcess.setBeginTime(nowTime);//计算开始时间，就是上一个进程结束的时间
                    clock=nowProcess.getServeTime();
                    nowTime+=nowProcess.getServeTime();
//                    nowProcess.setFinishTime();
                    nowProcess.setClock(clock);
                }
                else {
//                    nowProcess.setBeginTime(nowTime);
                   clock = nowProcess.getClock() + sliceTime;       //更新当前已经出队列的进程的已服务时间
                    nowTime += sliceTime;
                    nowProcess.setClock(clock);

                }
                //判断当前进程在一个时间片中是否运行完成
                if (nowProcess.getClock() >= nowProcess.getServeTime()) {      //如果当前进程已服务时间大于等于要求服务时间就完成出队
                        nowProcess.setFinishTime(nowTime);          //当前时间为该进程完成时间
                        nowProcess.setRoundTime(nowProcess.getFinishTime() - nowProcess.getArriveTime());     //计算周转时间
                        nowProcess.setAveRoundTime((double) nowProcess.getRoundTime() / nowProcess.getServeTime());    //计算带权周转时间
                        processesSort.add(nowProcess);              //将调度过的进程加入新进程容器
                        EnQueue();                                  //已经到达的进程先入队列
                    } else {      //当前进程在一个时间片内未运行完毕,加入到就绪队列队尾
                        EnQueue();
                    processesQueue.addLast(nowProcess);
                }
            }

            /**
             * @MethodName: displayQueue
             * @Description: TODO 打印就绪队列中等待的进程
             * @Param: []
             * @Return: void
             * @Author: 杨路恒
             * @Date: 2019/11/29 0029
             **/
            public void displayQueue() {
                count++;
                System.out.println("第"+count+ "次就绪队列中排队的进程:" + processesQueue);
            }

            /**
             * @MethodName: printProcess
             * @Description: TODO 打印调度完成的所有进程
             * @Param: []
             * @Return: void
             * @Author: 杨路恒
             * @Date: 2019/11/29 0029
             **/
            public void printProcess() {
                double roundTimeSum=0;
                double weightRoundTimeSum=0;
                double averageRoundTime=0;
                double averageWeightRoundTime=0;
                int processNum=processes.size();    //进程大小
                System.out.println("进程名 到达时间  服务时间   开始时间  完成时间  周转时间  带权周转时间");
                for (int i = 0; i < processesSort.size(); ++i) {
                    //获取已经运行完的进程各项参数信息
                    String name=processesSort.get(i).getName();
                    int arriveTime=processesSort.get(i).getArriveTime();
                    int serveTime= processesSort.get(i).getServeTime();
                    int beginTime=processesSort.get(i).getBeginTime();
                    int finishTime=processesSort.get(i).getFinishTime();
                    int roundTime=processesSort.get(i).getRoundTime();
                    double aveRoundTime=processesSort.get(i).getAveRoundTime();
                    roundTimeSum+=roundTime;
                    weightRoundTimeSum+=aveRoundTime;
                    System.out.println("P" + name + "\t\t\t" +arriveTime+ "\t\t" +
                            serveTime + "\t\t\t" + beginTime + "\t\t" + finishTime + "\t\t\t" +
                            roundTime + "\t\t" + aveRoundTime+"\t\t");
                }
                averageRoundTime=roundTimeSum/processNum;
                averageWeightRoundTime=weightRoundTimeSum/processNum;
                System.out.println("平均周转时间:"+averageRoundTime+"\n平均带权周转时间"+averageWeightRoundTime);
                processesSort.clear();          //清空已经调度的进程信息，以便于存储其他算法进行显示
            }
        }
}
