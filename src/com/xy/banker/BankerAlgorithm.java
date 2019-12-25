package com.xy.banker;


import java.util.Scanner;

/**
 * @ClassName BankerAlgorithm
 * @Description: TODO
 * @Author 杨路恒
 * @Date 2019/12/7 0007
 * @Version V1.0
 **/
public class BankerAlgorithm {
    /*
     * 资源的种类
     */
    private final int RESOUCE_NUM = 3;

    /*
     * 进程的数量
     */
    private final int PROCESS_NUM = 5;

    /*
     * 可获取每种资源的数量的数组
     */
    private int[] available = new int[RESOUCE_NUM];

    /*
     * 每个进程对每种资源的最大需求数量
     */
    private int[][] max = new int[PROCESS_NUM][RESOUCE_NUM];
    /*
     * 每种资源已经分配给每个进程的数量
     */
    private int[][] allocation = new int[PROCESS_NUM][RESOUCE_NUM];
    /*
     * 每个进程还需要获取每种进程的数量
     */
    private int[][] need = new int[PROCESS_NUM][RESOUCE_NUM];

    private int[][] request = new int[PROCESS_NUM][RESOUCE_NUM];

    private int[] work = new int[RESOUCE_NUM];
    /*
     * 初始化各数组
     */
    public void initArray(){
        Scanner in = new Scanner(System.in);
        setAvailable(in);
        setMax(in);
        setAllocationAndNeed(in);
    }

    private void setAvailable(Scanner in){
        System.out.println("设置每种资源的数量");
        for(int i = 0; i< RESOUCE_NUM;i++){
            System.out.println("编号为"+(i+1)+"的资源的数量：");
            available[i] = in.nextInt();
        }
    }

    private void setMax(Scanner in){
        System.out.println("设置Max二维数组");
        for(int i = 0;i<PROCESS_NUM;i++){
            System.out.println("编号为"+(i+1)+"的进程所需要的各资源数目");
            for(int j = 0;j<RESOUCE_NUM;j++){
                System.out.println("编号为"+(j+1)+"的资源:");
                max[i][j] = in.nextInt();
            }
        }
    }

    private void setAllocationAndNeed(Scanner in){
        System.out.println("设置为各进程已经分配的资源数目");
        for(int i = 0;i<PROCESS_NUM;i++){
            System.out.println("编号为"+(i+1)+"的进程已经分配的各资源数目");
            for(int j = 0;j<RESOUCE_NUM;j++){
                System.out.println("编号为"+(j+1)+"的资源：");
                allocation[i][j] = in.nextInt();
            }
        }
        System.out.println("各资源的数量减去分配的数量，重新计算...");
        for(int i = 0;i<RESOUCE_NUM;i++){
            for(int j = 0;j<PROCESS_NUM;j++){
                available[i] = available[i]- allocation[j][i];
            }
        }
        System.out.println("各资源可获取的数量计算结束！");

        System.out.println("各进程还需要每种资源的数量，开始计算...");
        for(int i = 0;i<PROCESS_NUM;i++){
            for(int j = 0;j<RESOUCE_NUM;j++){
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
        System.out.println("各进程还需要每种资源的数量计算结束！");

    }

    /*
     * 开始请求
     */
    public void setRequest(){
        int processNum = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("输入发送请求的进程号（min=0,max="+PROCESS_NUM+")");
        processNum = in.nextInt();
        System.out.println("请输入各资源的请求数目----");
        for(int i = 0;i< RESOUCE_NUM;i++){
            System.out.println("编号为"+(i+1)+"的资源：");
            request[processNum][i] = in.nextInt();
        }
        BankerAlgorithm(processNum);
    }

    public void BankerAlgorithm(int processNum) {//银行家算法
        boolean T=true,legal = true,wait = false;
        for(int i = 0 ;i<RESOUCE_NUM;i++){
            if(request[processNum][i]> need[processNum][i]){
                legal = false;
            }
            if(request[processNum][i] > available[i]){
                wait = true;
            }
        }
        if(!legal){
            System.out.println("进程P" + processNum + "请求已经超出最大需求量need.");
            T = false;
        }
        if(wait){
            System.out.println("当前没有足够的资源可分配，进程" + processNum + "需等待。");
            T = false;
        }else{
            for(int i = 0;i< RESOUCE_NUM ; i++){
                available[i] = available[i] - request[processNum][i];
                allocation[processNum][i] = allocation[processNum][i] + request[processNum][i];
                need[processNum][i] = need[processNum][i]- request[processNum][i];
            }
            T = true;
        }
        if(T==true){
            System.out.println("现在进入安全算法：");
            SecurityAlgorithm();
        }
    }


    public void SecurityAlgorithm() {//安全算法
        boolean[] Finish = new boolean[PROCESS_NUM];//初始化Finish
        for (int i = 0; i < PROCESS_NUM; i++) {
            Finish[i] = false;
        }
        int count = 0;//完成进程数
        int circle = 0;//循环圈数
        int[] S = new int[PROCESS_NUM];//安全序列
        for (int i = 0; i < RESOUCE_NUM; i++) {//设置工作向量
            work[i] = available[i];
        }
        boolean flag = true;
        while (count < PROCESS_NUM) {
            if (flag) {
                System.out.println("进程  " + "   Work  " + "   Alloction " + "    Need  " + "     Work+Alloction ");
                flag = false;
            }
            for (int i = 0; i < PROCESS_NUM; i++) {
                boolean safeFlag = true;
                for (int j = 0; j < RESOUCE_NUM; j++) {
                    if (Finish[i] == true || need[i][j] > work[j]) {
                        safeFlag = false;
                    }
                    if (safeFlag) {//判断条件
                        System.out.print("P" + i + "  ");
                        for (int k = 0; k < RESOUCE_NUM; k++) {
                            System.out.print(work[k] + "  ");
                        }
                        System.out.print("|  ");
                        for (int s = 0; s < RESOUCE_NUM; s++) {
                            work[s] += allocation[i][s];
                        }
                        Finish[i] = true;//当当前进程能满足时
                        S[count] = i;//设置当前序列排号

                        count++;//满足进程数加1
                        for (int j1 = 0; j1 < 3; j1++) {
                            System.out.print(allocation[i][j1] + "  ");
                        }
                        System.out.print("|  ");
                        for (int j2 = 0; j2 < 3; j2++) {
                            System.out.print(need[i][j2] + "  ");
                        }
                        System.out.print("|  ");
                        for (int j3 = 0; j3 < 3; j3++) {
                            System.out.print(work[j3] + "  ");
                        }
                        System.out.println();
                    }
                }
            }
            circle++;//循环圈数加1 主要作用：避免一直循环

            if (count == PROCESS_NUM) {//判断是否满足所有进程需要
                System.out.print("此时存在一个安全序列：");
                for (int i = 0; i < PROCESS_NUM; i++) {//输出安全序列
                    System.out.print("P" + S[i] + " ");
                }
                System.out.println("故当前可分配！");
                break;//跳出循环
            }
            if (count < circle) {//判断完成进程数是否小于循环圈数
                count = 5;
                System.out.println("当前系统处于不安全状态，故不存在安全序列。");
                break;//跳出循环
            }
        }
    }

    public static void main(String[] args) {
        BankerAlgorithm bankerAlgorithm=new BankerAlgorithm();
        bankerAlgorithm.initArray();
        bankerAlgorithm.setRequest();
    }
}
