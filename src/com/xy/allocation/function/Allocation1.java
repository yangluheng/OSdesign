package com.xy.allocation.function;

import com.xy.allocation.compare.CompareByAddress;
import com.xy.allocation.entity.Memory;
import com.xy.allocation.entity.Zone;

import java.util.Collections;
import java.util.LinkedList;

/**
 * @ClassName Allocation
 * @Description: TODO
 * @Author 杨路恒
 * @Date 2019/12/15 0015
 * @Version V1.0
 **/
public class Allocation1 {
    Memory memory=null;
    private static final int MIN_SIZE=1;    //内存分区最后剩余大小
    LinkedList<Zone> zones=null;
    int pointer=0;
    /**
     * @MethodName: doAllocation
     * @Description: TODO  进行内存分配
     * @Param: [size, location, tmp]
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/15 0015
    **/
    public void doAllocation(int size, int location, Zone tmp){
        if (tmp.getZoneSize()-size<=MIN_SIZE){      //如果分割后分区剩余大小小于等于1，就将该分区全部分配，否则分割为两个分区
            tmp.setFree(false);
        }
        else {
            Zone spiltZone=new Zone(tmp.getZoneSize()-size,tmp.getHead()+size);
            zones.add(location+1,spiltZone);
            tmp.setFree(false);
            tmp.setZoneSize(size);
        }
        System.out.println("\t系统已经成功分配"+size+"KB内存");
    }

    /**
     * @MethodName: doCollection
     * @Description: TODO 执行回收内存
     * @Param: [id]
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/15 0015
    **/
    public void doCollection(int id){
        if (id>=zones.size()){
            System.out.println("\t您输入有误，无此分区");
            return;
        }
        Zone tmp=zones.get(id);
        int zoneSize=tmp.getZoneSize();
        if (tmp.isFree()){
            System.out.println("\t此分区尚未分配，无需回收");
            return;
        }
        //1.如果该分区不是尾分区且后一个分区空闲，就与后一个分区合并
        if (id<(zones.size()-1)&&zones.get(id+1).isFree()){
            Zone nextZone=zones.get(id+1);
            tmp.setZoneSize(tmp.getZoneSize()+nextZone.getZoneSize());
            zones.remove(nextZone);
        }
        //2.如果该分区不是首分区且前一个分区为空闲，就与前一个分区合并
        if (id>0&&zones.get(id-1).isFree()){
            Zone previousZone=zones.get(id-1);
            previousZone.setZoneSize(previousZone.getZoneSize()+tmp.getZoneSize());
            zones.remove(id);
            id--;           //回到前一个分区
        }
        zones.get(id).setFree(true);
        System.out.println("\t内存回收成功，回收"+zoneSize+"KB内存");
    }

    /**
     * @MethodName: printZones
     * @Description: TODO 打印内存分配情况
     * @Param: []
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/15 0015
    **/
    public void printZones(){
        System.out.println("****************内存分配情况*****************");
        System.out.println("分区编号\t分区始址\t分区大小\t空闲状态\t");
        Collections.sort(zones,new CompareByAddress());         //将集合内存按照地址递增顺序排序
        for (int i = 0; i <zones.size() ; i++) {
            Zone tmp=zones.get(i);
            System.out.println(i+"\t\t\t"+tmp.getHead()+"\t\t\t"+tmp.getZoneSize()+"\t\t\t"+tmp.isFree());
        }
    }

    /**
     * @MethodName: menu
     * @Description: TODO 算法系统界面
     * @Param: []
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/15 0015
    **/
    /*public void menu(){
        System.out.println("***************动态内存分配与回收算法系统*****************");
        System.out.println("请输入系统内存初始大小:");
        Scanner in=new Scanner(System.in);
        int memorySize=in.nextInt();
        memory=new Memory(memorySize);
        zones=memory.getZones();
        printZones();
        while (true){
            System.out.println("1.首次适应算法    2.循环首次适应算法  3.最佳适应算法    4.最坏适应算法    " +
                    "5.内存回收     0.退出\n请选择:");
            String choice=in.next();
            if (choice.equals("1")){
                System.out.println("请输入要分配的进程大小:");
                int size=in.nextInt();
                FF(size);
                printZones();
            }
            if (choice.equals("2")){
                System.out.println("请输入要分配的进程大小:");
                int size=in.nextInt();
                NF(size);
                printZones();
            }
            if (choice.equals("3")){
                System.out.println("请输入要分配的进程大小:");
                int size=in.nextInt();
                BF(size);
                printZones();
            }
            if (choice.equals("4")){
                System.out.println("请输入要分配的进程大小:");
                int size=in.nextInt();
                WF(size);
                printZones();
            }
            if (choice.equals("5")){
                System.out.println("请输入要回收的内存分区编号:");
                int id=in.nextInt();
                doCollection(id);
                printZones();
            }
            if (choice.equals("0")){
                System.exit(0);
            }
        }

    }*/
    public LinkedList<Zone> menu(int memorySize) {
        System.out.println("***************动态内存分配与回收算法系统*****************");
        System.out.println("请输入系统内存初始大小:");
        memory = new Memory(memorySize);
        zones = memory.getZones();
        printZones();
       /* while (true){
            System.out.println("1.首次适应算法    2.循环首次适应算法  3.最佳适应算法    4.最坏适应算法    " +
                    "5.内存回收     0.退出\n请选择:");
            String choice=in.next();
            if (choice.equals("1")){
                System.out.println("请输入要分配的进程大小:");
                int size=in.nextInt();
                FF(size);
                printZones();
            }
            if (choice.equals("2")){
                System.out.println("请输入要分配的进程大小:");
                int size=in.nextInt();
                NF(size);
                printZones();
            }
            if (choice.equals("3")){
                System.out.println("请输入要分配的进程大小:");
                int size=in.nextInt();
                BF(size);
                printZones();
            }
            if (choice.equals("4")){
                System.out.println("请输入要分配的进程大小:");
                int size=in.nextInt();
                WF(size);
                printZones();
            }
            if (choice.equals("5")){
                System.out.println("请输入要回收的内存分区编号:");
                int id=in.nextInt();
                doCollection(id);
                printZones();
            }
            if (choice.equals("0")){
                System.exit(0);
            }
        }*/
       return zones;
    }

    /**
     * @MethodName: FF
     * @Description: TODO 首次适应算法
     * @Param: [size]
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/15 0015
    **/
    public void FF(int size){
        //因为分区存放到容器里，一开始就是按照地址递增变化，无序再进行排序
        for (pointer=0; pointer <zones.size() ; pointer++) {
            Zone tmp=zones.get(pointer);
            System.out.println(size);
            System.out.println(tmp.getZoneSize());
            if (size<=tmp.getZoneSize()&&tmp.isFree()){ //找到可用分区
                doAllocation(size,pointer,tmp);
                return;
            }
        }
        System.out.println("未找到合适分区，分配失败");         //遍历完后没有大小足够的分区
    }

    /**
     * @MethodName: NF
     * @Description: TODO 循环首次适应算法
     * @Param: [size]
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/15 0015
    **/
    public void NF(int size){
        //循环首次适应算法从上次分配的分区的下一分区开始
        Zone tmp=null;
        int len=zones.size();
        int i=(pointer+1)%len;
        if (i==0||pointer==0){
            tmp=zones.get(i);
            if (size<=tmp.getZoneSize()&&tmp.isFree()){ //找到可用分区
                doAllocation(size,pointer,tmp);
                return;
            }
            else {
                pointer++;
            }
        }
        for (;i!=pointer;i=(i+1)%len){
            tmp=zones.get(i);
            if (size<=tmp.getZoneSize()&&tmp.isFree()){ //找到可用分区
                doAllocation(size,pointer,tmp);
                return;
            }
        }

        System.out.println("i="+i);
        System.out.println("pointer="+pointer);
        System.out.println("未找到合适分区，分配失败");         //遍历完后没有大小足够的分区
    }

    /**
     * @MethodName: BF
     * @Description: TODO 最佳适应算法
     * @Param: [size]
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/15 0015
    **/
    public void BF(int size){
        int minPointer=-1;        //表示最小的分区位置
        int min=memory.getSize();               //最小分区
        for (pointer=0;pointer<zones.size();pointer++){
            Zone tmp=zones.get(pointer);
            if (tmp.getZoneSize()>=size&&tmp.isFree()){
                if (min>=tmp.getZoneSize()){     //找到最小分区
                    min=tmp.getZoneSize();
                    minPointer=pointer;
                }
            }
        }
        if (minPointer==-1){
            System.out.println("未找到合适分区，分配失败");         //遍历完后没有大小足够的分区
        }
        else {
            doAllocation(size,minPointer,zones.get(minPointer));
        }
    }

    /**
     * @MethodName: WF
     * @Description: TODO 最坏适应算法
     * @Param: [size]
     * @Return: void
     * @Author: 杨路恒
     * @Date: 2019/12/15 0015
    **/
    public void WF(int size){
        int maxPointer=-1;        //表示最大的分区位置
        int max=memory.getSize();               //最大分区
        for (pointer=0;pointer<zones.size();pointer++){
            Zone tmp=zones.get(pointer);
            if (tmp.getZoneSize()>=size&&tmp.isFree()){
                if (max>=tmp.getZoneSize()){     //找到最大分区
                    max=tmp.getZoneSize();
                    maxPointer=pointer;
                }
            }
        }
        if (maxPointer==-1){
            System.out.println("未找到合适分区，分配失败");         //遍历完后没有大小足够的分区
        }
        else {
            doAllocation(size,maxPointer,zones.get(maxPointer));
        }
    }
}
