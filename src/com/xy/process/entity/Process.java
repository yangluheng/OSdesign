package com.xy.process.entity;

/**
 * @ClassName Process
 * @Description: TODO    进程块信息实体类
 * @Author 杨路恒
 * @Date 2019/11/29 0029
 * @Version V1.0
 **/
public class Process {
    private String name;//进程名
    private int arriveTime;//到达时间
    private int serveTime;//服务时间
    private int beginTime;//开始时间
    private int finishTime;//结束时间
    private int roundTime;//周转时间
    private double aveRoundTime;//带权周转时间
    private double clock=0;//在时间轮转调度算法中，记录该进程真实服务时间已经用时的时长
    private int waitTime;//记录每个进程到达后的等待时间，只用于最高响应比优先调度算法中
    private double averageRoundTime;    //平均周转时间
    private double averageWeightRoundTime;  //平均带权周转时间

    /**
     * @MethodName: Process
     * @Description: TODO 构造方法，便于前台输入进程数据
     * @Param: [name, arriveTime, serveTime, priority]
     * @Return:
     * @Author: 杨路恒
     * @Date: 2019/11/29 0029
    **/
    public Process(String name, int arriveTime, int serveTime, double priority){
        this.name=name;
        this.arriveTime=arriveTime;
        this.serveTime=serveTime;
        this.waitTime=0;
    }
    @Override
    public String toString() {
        return "进程名:P"+this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(int arriveTime) {
        this.arriveTime = arriveTime;
    }

    public int getServeTime() {
        return serveTime;
    }

    public void setServeTime(int serveTime) {
        this.serveTime = serveTime;
    }

    public int getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(int beginTime) {
        this.beginTime = beginTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getRoundTime() {
        return roundTime;
    }

    public void setRoundTime(int roundTime) {
        this.roundTime = roundTime;
    }

    public double getAveRoundTime() {
        return aveRoundTime;
    }

    public void setAveRoundTime(double aveRoundTime) {
        this.aveRoundTime = aveRoundTime;
    }

    public double getClock() {
        return clock;
    }

    public void setClock(double clock) {
        this.clock = clock;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public double getAverageRoundTime() {
        return averageRoundTime;
    }

    public void setAverageRoundTime(double averageRoundTime) {
        this.averageRoundTime = averageRoundTime;
    }

    public double getAverageWeightRoundTime() {
        return averageWeightRoundTime;
    }

    public void setAverageWeightRoundTime(double averageWeightRoundTime) {
        this.averageWeightRoundTime = averageWeightRoundTime;
    }
}
