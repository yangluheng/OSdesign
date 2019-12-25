package com.xy.memory.entity;

/**
 * @ClassName Memory
 * @Description: TODO 内存分区实体类
 * @Author 杨路恒
 * @Date 2019/12/10 0010
 * @Version V1.0
 **/
public class Memory {
    private int id; //分区编号
    private int capicaty;  //分区总容量
    private int leftCapicaty;   //分区剩余容量

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapicaty() {
        return capicaty;
    }

    public void setCapicaty(int capicaty) {
        this.capicaty = capicaty;
    }

    public int getLeftCapicaty() {
        return leftCapicaty;
    }

    public void setLeftCapicaty(int leftCapicaty) {
        this.leftCapicaty = leftCapicaty;
    }

    public Memory() {
    }

    public Memory(int id, int capicaty) {
        this.id = id;
        this.capicaty = capicaty;
        this.leftCapicaty=capicaty; //使用总容量初始化剩余容量
    }
}
