package com.xy.memory.entity;

import java.util.ArrayList;

/**
 * @ClassName Process
 * @Description: TODO 进程实体类
 * @Author 杨路恒
 * @Date 2019/12/10 0010
 * @Version V1.0
 **/
public class Process {
    private String name;    //进程名称
    private int size;    //进程所需内存大小
    private int locationId; //进程所在内存分区
    private boolean isDone; //进程是否分配完成

    public Process() {
    }

    public Process(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }



}
