package com.xy.memory.test;

import com.xy.memory.domain.Block;
import com.xy.memory.domain.PCB;
import com.xy.memory.memory.Memory;
import com.xy.memory.service.MemoryAllocation;

/**
 * @ClassName MemoryAllocationTest
 * @Description: TODO
 * @Author 杨路恒
 * @Date 2019/12/5 0005
 * @Version V1.0
 **/
public class MemoryAllocationTest {
    public static void main(String[] args) {
        MemoryAllocation memoryAllocation=new MemoryAllocation();
        Memory memory=new Memory(100);
        memory.getMemory(20,0,new Block(1,20));
        memory.getMemory(20,1,new Block(2,30));

        for (PCB p:memory.getPcbs()
             ) {
            System.out.println(p);
        }
    }
}
