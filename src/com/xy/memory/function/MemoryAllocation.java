package com.xy.memory.function;

import com.xy.memory.compare.CompareByMaxToMin;
import com.xy.memory.compare.CompareByMinToMax;
import com.xy.memory.entity.Memory;
import com.xy.memory.entity.Process;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @ClassName MemoryAllocation
 * @Description: TODO 动态内存分配算法
 * @Author 杨路恒
 * @Date 2019/12/10 0010
 * @Version V1.0
 **/
public class MemoryAllocation {

    /**
     * @MethodName: getMax
     * @Description: TODO 获取内存分区最大值
     * @Param: [memoryArrayList]
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/11 0011
     **/
    public int getMax(ArrayList<Memory> memoryArrayList){
        int max=0;
        for (Memory memory:memoryArrayList
        ) {
            if (memory.getLeftCapicaty()>max){
                max=memory.getCapicaty();
            }
        }
        return max;
    }

    /**
     * @MethodName: swap
     * @Description: TODO 交换内存分区
     * @Param: [memoryArrayList]
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/11 0011
     **/
    public void swap(ArrayList<Memory> memoryArrayList,int i,int j){
        ArrayList<Memory> newList=new ArrayList<>();
        newList.set(i,memoryArrayList.get(i));
        memoryArrayList.set(i,memoryArrayList.get(j));
        memoryArrayList.set(j,newList.get(i));
    }

    /**
     * @MethodName: printMemory
     * @Description: TODO 打印内存分配和空闲
     * @Param: [processes, memories]
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/11 0011
     **/
    public void printMemory(ArrayList<Process> processes,ArrayList<Memory> memories){
        System.out.println("**********内存分配情况**********");
        System.out.println("\t进程"+"\t\t需要的内存大小"+"\t\t分配空闲区");
        for(int i=0;i<processes.size();i++) {
            System.out.println("\t"+processes.get(i).getName()+"\t\t\t\t"+processes.get(i).getSize()+"\t\t\t\t"+
                    processes.get(i).getLocationId());
        }
        System.out.println("***************内存空闲区表**************");
        System.out.println("分区编号\t\t分区总容量\t\t分区剩余容量");
        for(int i=0;i<memories.size();i++)
        {
            System.out.println("\t"+memories.get(i).getId()+"\t\t\t\t"+memories.get(i).getCapicaty()+"\t\t\t\t"+
                    memories.get(i).getLeftCapicaty());
        }
    }

    /**
     * @MethodName: clear
     * @Description: TODO 清空数据
     * @Param: [processes, memories]
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/11 0011
    **/
    public void clear(ArrayList<Process> processes,ArrayList<Memory> memories){
        processes.clear();
        memories.clear();
    }
    /**
     * @MethodName: FF
     * @Description: TODO 首次适应算法
     * @Param: [processes, memories]
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/11 0011
     **/
    public void FF(ArrayList<Process> processes, ArrayList<Memory> memories){
        System.out.println("*************FF算法*****************");
        for (int i = 0; i <processes.size() ; i++) {
            for (int j = 0; j <memories.size() ; j++) {
                if (processes.get(i).isDone()==false){    //进程没有被分配内存
                    if (processes.get(i).getSize()<=memories.get(j).getLeftCapicaty()){ //当前进程所需内存大小小于内存分区大小
                        processes.get(i).setDone(true);     //当前进程可分配
                        memories.get(j).setLeftCapicaty(memories.get(j).getLeftCapicaty()-processes.get(i).getSize());  //更新内存分区大小
                        processes.get(i).setLocationId(memories.get(j).getId());        //位置指定
                        continue;   //结束内层循环
                    }
                }
                else {
                    continue;       //结束内层循环
                }
            }
        }
        printMemory(processes,memories);        //打印分配信息
        //算法调用完后清空数据以便其他算法调用
       clear(processes,memories);
    }

    /**
     * @MethodName: NF
     * @Description: TODO 循环首次适应算法
     * @Param: [processes, memories]
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/11 0011
     **/
    public void NF(ArrayList<Process> processes,ArrayList<Memory> memories){
        System.out.println("**************NF算法********************");
        int i=0,j=0,current=0;  //current用来表示指向下一个内存分区的位置
        int newCurrent=0;
        for (;i<processes.size();i++){
            for (;j<memories.size();j++){
                newCurrent=(current+j)%memories.size();
                if (processes.get(i).isDone()==false){
                    if (processes.get(i).getSize()<=memories.get(newCurrent).getLeftCapicaty()){
                        processes.get(i).setDone(true);
                        processes.get(i).setLocationId(memories.get(newCurrent).getId());
                        memories.get(j).setLeftCapicaty(memories.get(j).getLeftCapicaty()-processes.get(i).getSize());
                        current=(newCurrent+1)%memories.size();
                        continue;
                    }
                }
                else {
                    continue;
                }
            }
//            if (j==memories.size()){    //当前进程没有找到合适的分区
//                System.out.println("当前进程"+processes.get(i).getName()+"没有空闲分区可分配\t无法分配");
//                return;
//            }
        }
        printMemory(processes,memories);
        clear(processes,memories);
    }


    /**
     * @MethodName: BF
     * @Description: TODO 最佳适应算法
     * @Param: [processes, memories]
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/11 0011
     **/
    public void BF(ArrayList<Process> processes,ArrayList<Memory> memories){
        System.out.println("******************BF算法*************");
        Collections.sort(memories,new CompareByMinToMax());     //按照由小到大把内存排序
        for (int i = 0; i <processes.size() ; i++) {
            for (int j = 0; j <memories.size() ; j++) {
                if (processes.get(i).isDone()==false){    //进程没有被分配内存
                    if (processes.get(i).getSize()<=memories.get(j).getLeftCapicaty()){ //当前进程所需内存大小小于内存分区大小
                        processes.get(i).setDone(true);     //当前进程可分配
                        memories.get(j).setLeftCapicaty(memories.get(j).getLeftCapicaty()-processes.get(i).getSize());  //更新内存分区大小
                        processes.get(i).setLocationId(memories.get(j).getId());        //位置指定
                        continue;   //结束内层循环
                    }
                }
                else {
                    continue;       //结束内层循环
                }
            }
            if (processes.get(i).getSize()>getMax(memories)){       //当前进程所需内存大于内存最大剩余
                System.out.println("当前进程"+processes.get(i).getName()+"所需内存大小:"+processes.get(i).getSize()+
                        ",当前内存最大容量:"+getMax(memories)+"\t无法分配");
                return;
            }
        }
        printMemory(processes,memories);
        clear(processes,memories);
    }

    /**
     * @MethodName: WF
     * @Description: TODO 最坏适应算法
     * @Param: [processes, memories]
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/11 0011
     **/
    public void WF(ArrayList<Process> processes,ArrayList<Memory> memories){
        System.out.println("************WF*****************");
        Collections.sort(memories,new CompareByMaxToMin());     //按照内存由大到小排序
        for (int i = 0; i <processes.size() ; i++) {
            for (int j = 0; j <memories.size() ; j++) {
                if (processes.get(i).isDone()==false){    //进程没有被分配内存
                    if (processes.get(i).getSize()<=memories.get(j).getLeftCapicaty()){ //当前进程所需内存大小小于内存分区大小
                        processes.get(i).setDone(true);     //当前进程可分配
                        memories.get(j).setLeftCapicaty(memories.get(j).getLeftCapicaty()-processes.get(i).getSize());  //更新内存分区大小
                        processes.get(i).setLocationId(memories.get(j).getId());        //位置指定
                        continue;   //结束内层循环
                    }
                }
                else {
                    continue;       //结束内层循环
                }
            }
            if (processes.get(i).getSize()>getMax(memories)){       //当前进程所需内存大于内存最大剩余
                System.out.println("当前进程"+processes.get(i).getName()+"所需内存大小:"+processes.get(i).getSize()+
                        ",当前内存最大容量:"+getMax(memories)+"\t无法分配");
                return;
            }
        }
        printMemory(processes,memories);
        clear(processes,memories);
    }
}
