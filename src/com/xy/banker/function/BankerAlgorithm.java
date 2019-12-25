package com.xy.banker.function;

import java.util.Scanner;

/**
 * @ClassName BankerAlgorithm
 * @Description: TODO
 * @Author 杨路恒
 * @Date 2019/12/17 0017
 * @Version V1.0
 **/
public class BankerAlgorithm {
    private int resourceNum=0;      //资源种类
    private int processNum=0;       //进程数量
    private int[][] max={};          //每个进程对每种资源的最大需求量
    private int[][] allocation={};   //每个进程已经分配的每种资源的数量
    private int[][] need={};         //每个进程还需要每种资源的数量
    private int[] available={};      //每种资源剩余量
    private int[][] request={};      //每个进程要请求的每种资源数量
    private int[] work={};           //当前系统可以分配资源数量
    Scanner in=new Scanner(System.in);

    /**
     * @MethodName: setResourceNum
     * @Description: TODO 设置资源种类数
     * @Param: []
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/17 0017
    **/
    public void setResourceNum() {
        System.out.println("请您输入系统资源种类数:");
        resourceNum=in.nextInt();
        available=new int[resourceNum];
    }

    /**
     * @MethodName: setProcessNum
     * @Description: TODO  设置进程数
     * @Param: []
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/17 0017
    **/
    public void setProcessNum() {
        System.out.println("请您输入进程数:");
        processNum=in.nextInt();
    }

    /**
     * @MethodName: setMax
     * @Description: TODO 设置每个进程需要的最大资源数
     * @Param: []
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/17 0017
    **/
    public void setMax() {
        max=new int[processNum][resourceNum];
        System.out.println("设置每个进程需要的最大资源数:");
        for (int i = 0; i <processNum ; i++) {
            for (int j = 0; j <resourceNum ; j++) {
                System.out.println("第"+i+"个进程需要"+(j+1)+"类资源最大资源数:");
                max[i][j]=in.nextInt();
            }
        }
    }

    /**
     * @MethodName: setAllocation
     * @Description: TODO 设置各进程已经分配的资源数
     * @Param: []
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/17 0017
    **/
    public void setAllocation() {
        allocation=new int[processNum][resourceNum];
        System.out.println("设置各进程已经分配的资源数");
        for (int i = 0; i <processNum ; i++) {
            for (int j = 0; j <resourceNum ; j++) {
                System.out.println("第"+i+"个进程已经分配"+(j+1)+"类资源数:");
                allocation[i][j]=in.nextInt();
            }
        }
    }


    public void setNeed(int resourceNum,int processNum,int[][] need,int[][] max,int[][] allocation,int[] available) {
        System.out.println("计算每个进程还需要的每类资源数:");
        for (int i = 0; i <resourceNum ; i++) {
            for (int j = 0; j <processNum ; j++) {
                available[i]=available[i]-allocation[j][i];
            }
        }
        for (int i = 0; i <processNum ; i++) {
            for (int j = 0; j <resourceNum ; j++) {
                need[i][j]=max[i][j]-allocation[i][j];
            }
        }
        System.out.println("计算结束");
    }

    /**
     * @MethodName: reSet
     * @Description: TODO 恢复资源数目
     * @Param: [resourceNum, available, request, allocation, requestProcessId]
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/24 0024
    **/
    public void reSet(int resourceNum,int[] available ,int[][] request,int[][] allocation,int[][] need,int requestProcessId){
        for (int i = 0; i <resourceNum ; i++) {
            available[i]=available[i]+request[requestProcessId][i];
            allocation[requestProcessId][i]=allocation[requestProcessId][i]-request[requestProcessId][i];
            need[requestProcessId][i]=need[requestProcessId][i]+request[requestProcessId][i];
        }
    }
/*    *
//////     * @MethodName: setAvailable
//////     * @Description: TODO 设置每种资源数量
//////     * @Param: [available]
//////     * @Return: void
//////     * @Author: 杨路恒
//////     * @Date: 2019/12/17 0017
//////    **/
    public void setAvailable(int[] available) {
        System.out.println("设置每种资源数量");
        for (int i = 0; i <resourceNum; i++) {
            System.out.println("第"+(i+1)+"类资源数:");
            available[i]=in.nextInt();
        }
    }

    public int[] isSecurity(int processNum,int resourceNum,int[][] allocation,int[][] need,int[] available,int[] work,int[][] Work,int[][] workAndAllocation){
        System.out.println("安全性检查:");
        work=new int[resourceNum];
        boolean[] finish=new boolean[processNum];   //初始化finish
        for (int i = 0; i <processNum ; i++) {
            finish[i]=false;
        }
        int count=0;    //完成进程数
        int circle=0;   //循环圈数
        int[] security=new int[processNum]; //安全序列
        for (int i = 0; i <resourceNum ; i++) {     //初始化工作向量
            work[i]=available[i];
        }
        boolean flag=true;
        while (count<processNum){
            if (flag){
                System.out.println("进程\t"+"Work\t\t"+"Allocation\t\t"+"Need\t\t"+"Work+Allocation");
                flag=false;
            }
            for (int i = 0; i <processNum ; i++) {
                boolean safeFlag=true;
                for (int j = 0; j <resourceNum ; j++) {
                    if (finish[i]==true||need[i][j]>work[j]){
                        safeFlag=false;
                    }
                    if (safeFlag){
                        System.out.print("P"+i+"\t");
                        for (int k = 0; k <resourceNum ; k++) {
                            System.out.print(work[k]+"\t");
                            Work[i][k]=work[k];
                        }
                        System.out.print("|\t");
                        for (int k = 0; k <resourceNum ; k++) {
                            work[k]+=allocation[i][k];
                        }
                        finish[i]=true;     //当前进程满足
                        security[count]=i;  //设置当前进程序列排号
                        count++;            //满足进程数加一
                        for (int j1 = 0; j1 <resourceNum ; j1++) {
                            System.out.print(allocation[i][j1]+"\t");
                        }
                        System.out.print("|\t");
                        for (int j2 = 0; j2 <resourceNum ; j2++) {
                            System.out.print(need[i][j2]+"\t");
                        }
                        System.out.print("|\t");
                        for (int j3 = 0; j3 <resourceNum ; j3++) {
                            System.out.print(work[j3]+"\t");
                            workAndAllocation[i][j3]=work[j3];
                        }
                        System.out.println();
                    }
                }
            }
            circle++;       //循环圈数加一
            if (count==processNum){ //判断是否满足所有进程需要
                System.out.println("此时存在一个安全序列:");
                for (int i = 0; i <processNum ; i++) {
                    System.out.print("P"+security[i]+" ");
                }
                System.out.println("\n当前进程可以分配");
                return security;
            }
            if (count<circle){  //判断完成进程数是否小于循环圈数
                count=5;
                System.out.println("当前系统处于不安全状态，不存在安全序列");
                return new int[]{-3};
            }
        }
        return security;
    }

    /**
     * @MethodName: setRequest
     * @Description: TODO 设置进程请求资源数
     * @Param: [processNum, resourceNum, request]
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/17 0017
    **/
    public int[] setRequest(int processNum,int resourceNum,int[][] request,int[][] max,int[][] allocation,int[][] need,int[] available,int[] work,int[][] Work,int[][] workAndAllocation,int requestProcessId) {
        System.out.println("进入银行家算法系统:");
        return bankerAlgorithm(processNum,resourceNum,request,max,allocation,need,available,work,Work,workAndAllocation,requestProcessId);
    }


        /**
         * @MethodName: menu
         * @Description: TODO 算法界面
         * @Param: []
         * @Return: void
         * @Author: 杨路恒
         * @Date: 2019/12/17 0017
        **/

    /**
     * @MethodName: bankerAlgorithm
     * @Description: TODO 银行家算法
     * @Param: [requestProcessId]
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/17 0017
    **/
    public int[] bankerAlgorithm(int processNum,int resourceNum,int[][] request,int[][] max,int[][] allocation,int[][] need,int[] available,int[] work,int[][] Work,int[][] workAndAllocation,int requestProcessId){
        boolean T=true,legal=true,wait=false;
        for (int i = 0; i <resourceNum ; i++) {
            if (request[requestProcessId][i]>need[requestProcessId][i]){
                legal=false;
            }
            if (request[requestProcessId][i]>available[i]){
                wait=true;
            }
        }
        if (!legal){
            System.out.println("进程P"+requestProcessId+"已经超出最大需求need");
            T=false;
            return new int[]{-1,requestProcessId};
        }
        if (wait){
            System.out.println("当前没有足够的资源可以分配，进程P"+requestProcessId+"需要等待");
            T=false;
            return new int[]{-2,requestProcessId};
        }
        else {
            for (int i = 0; i <resourceNum ; i++) {
                available[i]=available[i]-request[requestProcessId][i];
                allocation[requestProcessId][i]=allocation[requestProcessId][i]+request[requestProcessId][i];
                need[requestProcessId][i]=need[requestProcessId][i]-request[requestProcessId][i];
            }
            T=true;
        }
        if (T=true){
            System.out.println("安全性检查:");
            return securityAlgorithm(processNum,resourceNum,request,max,allocation,need,available,work,Work,workAndAllocation,requestProcessId);
        }
        return new int[]{-3};
    }


    /**
     * @MethodName: securityAlgorithm
     * @Description: TODO 安全性检查算法
     * @Param: []
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/17 0017
    **/
    public int[] securityAlgorithm(int processNum,int resourceNum,int[][] request,int[][] max,int[][] allocation,int[][] need,int[] available,int[] work,int[][] Work,int[][] workAndAllocation,int requestProcessId){
        work=new int[resourceNum];
        boolean[] finish=new boolean[processNum];   //初始化finish
        for (int i = 0; i <processNum ; i++) {
            finish[i]=false;
        }
        int count=0;    //完成进程数
        int circle=0;   //循环圈数
        int[] security=new int[processNum]; //安全序列
        for (int i = 0; i <resourceNum ; i++) {     //初始化工作向量
            work[i]=available[i];
        }
        boolean flag=true;
        while (count<processNum){
            if (flag){
                System.out.println("进程\t"+"Work\t\t"+"Allocation\t\t"+"Need\t\t"+"Work+Allocation");
                flag=false;
            }
            for (int i = 0; i <processNum ; i++) {
                boolean safeFlag=true;
                for (int j = 0; j <resourceNum ; j++) {
                    if (finish[i] == false&& need[i][j] <= work[j]) {
                        safeFlag = true;
                    }
                    else {
                        safeFlag=false;
                        break;
                    }
                }
                    if (safeFlag){
                        System.out.print("P"+i+"\t");
                        for (int k = 0; k <resourceNum ; k++) {
                            System.out.print(work[k]+"\t");
                            Work[i][k]=work[k];
                        }
                        System.out.print("|\t");
                        for (int k = 0; k <resourceNum ; k++) {
                            work[k]+=allocation[i][k];
                        }
                        finish[i]=true;     //当前进程满足
                        security[count]=i;  //设置当前进程序列排号
                        count++;            //满足进程数加一
                        for (int j1 = 0; j1 <resourceNum ; j1++) {
                            System.out.print(allocation[i][j1]+"\t");
                        }
                        System.out.print("|\t");
                        for (int j2 = 0; j2 <resourceNum ; j2++) {
                            System.out.print(need[i][j2]+"\t");
                        }
                        System.out.print("|\t");
                        for (int j3 = 0; j3 <resourceNum ; j3++) {
                            System.out.print(work[j3]+"\t");
                            workAndAllocation[i][j3]=work[j3];
                        }
                        System.out.println();
                    }
            }
            circle++;       //循环圈数加一
            if (count==processNum){ //判断是否满足所有进程需要
                System.out.println("此时存在一个安全序列:");
                for (int i = 0; i <processNum ; i++) {
                    System.out.print("P"+security[i]+" ");
                }
                System.out.println("\n当前进程可以分配");
//                break;
                return security;
            }
            if (count<circle){  //判断完成进程数是否小于循环圈数
                count=5;
                System.out.println("当前系统处于不安全状态，不存在安全序列");
                reSet(resourceNum,available,request,allocation,need,requestProcessId);
                return new int[]{-3};
            }
        }
//        return security;
        return new int[]{-3};
    }
}