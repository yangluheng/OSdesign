package com.xy.memory.memory;

import com.xy.memory.domain.Block;
import com.xy.memory.domain.PCB;

import java.util.LinkedList;

/**
 * @ClassName Memory
 * @Description: TODO
 * @Author 杨路恒
 * @Date 2019/12/5 0005
 * @Version V1.0
 **/
public class Memory {
    private int size;              //内存块大小
    private int lastFind;          //上次寻址结束的下标
    private LinkedList<PCB> pcbs;  //记录内存块中进程的双向链表
    private LinkedList<Block> blocks;       //记录内存块分区的双向链表
    private static final int MIN_SIZE = 5;  //最小剩余分区大小

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLastFind() {
        return lastFind;
    }

    public void setLastFind(int lastFind) {
        this.lastFind = lastFind;
    }

    public LinkedList<PCB> getPcbs() {
        return pcbs;
    }

    public void setPcbs(LinkedList<PCB> pcbs) {
        this.pcbs = pcbs;
    }

    public LinkedList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(LinkedList<Block> blocks) {
        this.blocks = blocks;
    }

    public static int getMinSize() {
        return MIN_SIZE;
    }

    public Memory(int size){
        this.size = size;                //初始化内存大小
        this.pcbs = new LinkedList<>();  //初始化两个链表
        this.blocks = new LinkedList<>();
        blocks.add(new Block(0, size));    //分区链表的首项为大小是内存大小的空闲分区
    }


    public Memory getMemory(int size, int location, Block block) {    //size为申请大小 location为分配分区的下标 block为location对应的分区
        if (block.getSize() - size >= MIN_SIZE) {                    //若分配后当前分区剩余大小大于最小分区大小，则把当前分区分为两块
            Block newBlock = new Block(block.getHead() + size, block.getSize() - size);
            blocks.add(location + 1, newBlock);
            block.setSize(size);
        }
        pcbs.add(new PCB(10000 + (int)(89999 * Math.random()), 1, block));   //模拟添加一个就绪状态的进程，此进程id随机生成(忽略id重复的情况哈)
        block.setFree(false);    //设置当前分区为非空闲状态
        System.out.println("成功分配大小为" + size + "的内存");
        return this;
    }


    public Memory releaseMemory(int id) {
        PCB pcb = null;     //记录此id对应进程(忽略进程id与分区id相同，但进程不同的情况哈)
        if (id >= blocks.size()) {   //若id大于blocks的表长度，则需要判断此id是否是进程id
            boolean flag = false;
            for (int i = 0; i < pcbs.size(); i++) { //循环比对此id是否是pcbs链表中进程的id
                if (pcbs.get(i).getId() == id) {
                    pcb = pcbs.get(i);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.err.println("无此分区:" + id);
                return this;
            }
        }
        if (pcb != null) {  //若是通过进程id释放内存，则用下列循环获取进程对应的block对应blocks链表的下标(获取分区id)
            for (int i = 0; i < blocks.size(); i++) {
                Block block = blocks.get(i);
                if ((pcb.getBlock().getSize() == block.getSize()) && (pcb.getBlock().getHead() == block.getHead())) {
                    id = i;
                    break;
                }
            }
        }
        Block block = blocks.get(id);  //此id为分区id
        if (block.isFree()) {
            System.out.println("此分区空闲,无需释放:\t" + id);
            return this;
        }
        //用分区id释放pcb
        for (int i = 0; i < pcbs.size(); i++) {
            PCB pcb2 = pcbs.get(i);
            if ((pcb2.getBlock().getSize() == block.getSize()) && (pcb2.getBlock().getHead() == block.getHead())) {
                pcbs.remove(i);
                break;
            }
        }
        //如果回收分区不是尾分区且后一个分区为空闲, 则与后一个分区合并
        if (id < blocks.size() - 1 && blocks.get(id + 1).isFree()) {
            Block nextBlock = blocks.get(id + 1);
            block.setSize(block.getSize() + nextBlock.getSize());
            blocks.remove(nextBlock);
        }
        //如果回收分区不是首分区且前一个分区为空闲, 则与前一个分区合并
        if (id > 0 && blocks.get(id - 1).isFree()) {
            Block lastBlock = blocks.get(id - 1);
            lastBlock.setSize(block.getSize() + lastBlock.getSize());
            blocks.remove(id);
            id--;
        }
        blocks.get(id).setFree(true);
        System.out.println("内存回收成功!");
        return this;
    }

}
