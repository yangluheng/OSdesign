package com.xy.allocation.entity;

/**
 * @ClassName Zone
 * @Description: TODO
 * @Author 杨路恒
 * @Date 2019/12/15 0015
 * @Version V1.0
 **/
public class Zone {
    private int zoneSize;       //分区大小
    private  int head;          //分区始址
    private boolean isFree;     //分区空闲状态

    public Zone(int zoneSize, int head) {
        this.zoneSize = zoneSize;
        this.head = head;
        this.isFree = true;
    }

    public int getZoneSize() {
        return zoneSize;
    }

    public void setZoneSize(int zoneSize) {
        this.zoneSize = zoneSize;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
