package com.xy.memory.test;

import com.xy.memory.entity.Memory;
import com.xy.memory.entity.Process;
import com.xy.memory.function.MemoryAllocation;

import java.util.ArrayList;

/**
 * @ClassName Test
 * @Description: TODO
 * @Author 杨路恒
 * @Date 2019/12/11 0011
 * @Version V1.0
 **/
public class Test {
    public static void main(String[] args) {
        ArrayList<Memory> memories=new ArrayList<>();
        ArrayList<Process> processes=new ArrayList<>();
        processes.add(new Process("A",10));
        processes.add(new Process("B",60));
        processes.add(new Process("C",10));
        memories.add(new Memory(1,30));
        memories.add(new Memory(2,80));
//        new MemoryAllocation().FF(processes,memories);
        new MemoryAllocation().NF(processes,memories);
//        new MemoryAllocation().BF(processes,memories);
//        new MemoryAllocation().WF(processes,memories);
    }
}
