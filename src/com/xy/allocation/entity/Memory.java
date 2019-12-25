package com.xy.allocation.entity;

import java.util.LinkedList;

/**
 * @ClassName Memory
 * @Description: TODO
 * @Author 杨路恒
 * @Date 2019/12/15 0015
 * @Version V1.0
 **/
public class Memory {
    private int size;       //总内存大小
    private int pointer;    //上次内存分配分区位置
    private LinkedList<Zone> zones; //内存分区
    private static final int MIN_SIZE=1;    //内存分区最后剩余大小

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }

    public LinkedList<Zone> getZones() {
        return zones;
    }

    public void setZones(LinkedList<Zone> zones) {
        this.zones = zones;
    }

    public static int getMinSize() {
        return MIN_SIZE;
    }

    public Memory(int size) {
        this.size = size;
        this.pointer=0;
        this.zones=new LinkedList<>();
        zones.add(new Zone(size,0));
    }
}
