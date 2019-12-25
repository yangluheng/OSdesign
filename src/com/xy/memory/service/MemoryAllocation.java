package com.xy.memory.service;

import com.xy.memory.domain.Block;
import com.xy.memory.memory.Memory;

/**
 * @ClassName MemoryAllocation
 * @Description: TODO
 * @Author 杨路恒
 * @Date 2019/12/5 0005
 * @Version V1.0
 **/
public class MemoryAllocation {
    public Memory FF(Memory memory,int size){
        int sum = 0;
        //循环内存中所有分区
        for (int i = 0; i < memory.getBlocks().size(); i++) {
            sum++;
            //为循环首次适应算法设置最后寻址的下标
            memory.setLastFind(i);
            Block hole = memory.getBlocks().get(i);   //获得对应的分区
            //若此分区空闲且大小大于申请的大小，则申请内存
            if (hole.isFree() && hole.getSize() >= size) {
                System.out.println("查找" + sum + "次");
                return memory.getMemory(size, i, hole);
            }
        }
        System.err.println("OUT OF MEMORY!");
        return memory;

    }


    public Memory NF(Memory memory,int size){
        Block hole = memory.getBlocks().get(memory.getLastFind());
        //判断最后寻址的分区的大小是否足够
        if (hole.isFree() && hole.getSize() >= size) {
            return memory.getMemory(size, memory.getLastFind(), hole);
        }
        int length = memory.getBlocks().size();
        int sum = 0;    //为区分与首次适应算法循环次数所设置
        //如果不够,则从下一个分区开始循环
        for (int i = (memory.getLastFind() + 1) % length; i != memory.getLastFind(); i = (i + 1) % length) {
            sum++;
            memory.setLastFind(i);
            hole = memory.getBlocks().get(i);
            if (hole.isFree() && hole.getSize() >= size) {
                System.out.println("查找" + sum + "次");
                return memory.getMemory(size, i, hole);
            }
        }
        System.err.println("OUT OF MEMORY!");
        return memory;
    }

    public Memory BF(Memory memory,int size){
        int findIndex = -1;         //最佳分区的下标
        int min = memory.getSize(); //min存储当前找到的最小的合适的分区大小
        for (int i = 0; i < memory.getBlocks().size(); i++) {
            //memory.setLastFind(i);
            Block hole = memory.getBlocks().get(i);
            if (hole.isFree() && hole.getSize() >= size) {
                //若当前找到的分区大小比min还要合适(剩余空间更小),则修改其值
                if (min > hole.getSize() - size){
                    min = hole.getSize() - size;
                    findIndex = i;
                }
            }
        }
        if (findIndex != -1) {  //若存在合适分区
            return memory.getMemory(size, findIndex, memory.getBlocks().get(findIndex));
        }
        System.err.println("OUT OF MEMORY!");
        return memory;

    }

    public Memory WF(Memory memory,int size){
        int findIndex = -1;
        int max = 0;
        for (int i = 0; i < memory.getBlocks().size(); i++) {
            Block hole = memory.getBlocks().get(i);
            if (hole.isFree() && hole.getSize() >= size) {
                if (max < hole.getSize() - size){
                    max = hole.getSize() - size;
                    findIndex = i;
                }
            }
        }
        if (findIndex != -1) {
            return memory.getMemory(size, findIndex, memory.getBlocks().get(findIndex));
        }
        System.err.println("OUT OF MEMORY!");
        return memory;
        
    }
}
