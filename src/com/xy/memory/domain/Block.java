package com.xy.memory.domain;

/**
 * @ClassName Block
 * @Description: TODO
 * @Author 杨路恒
 * @Date 2019/12/5 0005
 * @Version V1.0
 **/
public class Block {
    private int head;       //小内存块的起始地址
    private int size;       //小内存块的大小
    private boolean isFree; //小内存块的空闲状态

    public Block(int head, int size) {
        this.head = head;
        this.size = size;
        this.isFree = true;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    @Override
    public String toString() {
        return "Block{" +
                "head=" + head +
                ", size=" + size +
                ", isFree=" + isFree +
                '}';
    }
}
