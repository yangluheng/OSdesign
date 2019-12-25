package com.xy.memory.domain;

/**
 * @ClassName PCB
 * @Description: TODO
 * @Author 杨路恒
 * @Date 2019/12/5 0005
 * @Version V1.0
 **/
public class PCB {
    private int id;     //进程id
    private int state;  //进程状态 0为空闲 1为就绪 2为执行 3为阻塞
    private Block block;  //进程所对应的小内存块

    public PCB(int id, int state, Block block) {
        this.id = id;
        this.state = state;
        this.block=block;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    @Override
    public String toString() {
        return "PCB{" +
                "id=" + id +
                ", state=" + state +
                ", block=" + block +
                '}';
    }
}
