package com.xy.memory.test;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @ClassName Internal
 * @Description: TODO
 * @Author 杨路恒
 * @Date 2019/12/6 0006
 * @Version V1.0
 **/
public class Internal {
        //记录内存中未使用碎片总和
        static int recordUsable = 1000;
        //记录已经使用的碎片量
        static int recordUnsable = 0;
        static ArrayList<int[]> inter = new ArrayList<>();
        static Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
            Internal internal = new Internal();

            while (true) {
                System.out.println("请输入操作");
                switch (scanner.nextInt()) {
                    case 1:
                        internal.insertInter();
                        break;
                    case 2:
                        internal.deleteInter();
                        break;
                }
            }

        }
        public void insertInter(){
            System.out.println("未使用：" + recordUsable);
            System.out.println("已使用：" + recordUnsable);

            System.out.print("请输入作业名：");
            int operName = scanner.nextInt();
            System.out.print("请输入作业长度：");
            int operLength = scanner.nextInt();

            //作业长度大于总剩余不可以存
            if (operLength <= recordUsable) {
                System.out.println("1");

                if (inter.size() == 0) {
                    //插入
                    inter.add(0, new int[]{operName, 1, operLength});
                    recordUnsable += operLength;
                    recordUsable -= operLength;
                    System.out.println("插入成功");
                }
                else if (inter.size() != 0) {
                    for (int i = 0; i < inter.size(); i++) {
                        //当前作业的区间
                        int[] eleLength = new int[2];
                        eleLength[0] = inter.get(i)[1];
                        eleLength[1] = inter.get(i)[2];

                        //空白区域区间
                        int[] emptyLength = new int[2];
                        if (i == inter.size() - 1) {
                            emptyLength[0] = inter.get(i)[2] + 1;
                            emptyLength[1] = 1000;
                        } else if((inter.get(i)[1] +1 )== inter.get(i + 1)[1]){
                            continue;
                        }
                        else {
                            emptyLength[0] = inter.get(i)[2] +1;
                            emptyLength[1] = inter.get(i + 1)[1] - 1;
                        }
                        //当前空白区域长度
                        int emptyLeng = emptyLength[1] - emptyLength[0] +1;

                        //插入
                        if (operLength <= emptyLeng) {
                            inter.add(i + 1, new int[]{operName, eleLength[1] + 1, eleLength[1] + operLength});
                            System.out.println("插入成功");
                            recordUnsable += operLength;
                            recordUsable -= operLength;


                            System.out.println("-----------------------------------------------------");
                            System.out.println("当前内存分配情况：");
                            System.out.println("序号\t" + "作业名" + "\t" + "起始地址\t结束地址");
                            for (int i1 = 0; i1 < inter.size(); i1++) {
                                System.out.println((i1 + 1) + "\t\t" + inter.get(i1)[0] + "\t\t" + inter.get(i1)[1] + "\t\t" + inter.get(i1)[2]);
                            }
                            System.out.println("-----------------------------------------------------");

                            break;
                        }
                    }

                }


            }else{
                System.out.println("无法分配这么大内存！");
            }
        }
        public void deleteInter(){

            System.out.println("您要删除哪个作业（序号）？");
            int x = scanner.nextInt();
            inter.remove(x - 1);
            System.out.println("删除成功。");
            System.out.println("-----------------------------------------------------");
            System.out.println("当前内存分配情况：");
            System.out.println("序号\t" + "作业名" + "\t" + "起始地址\t结束地址");
            for (int i1 = 0; i1 < inter.size(); i1++) {
                System.out.println((i1 + 1) + "\t\t" + inter.get(i1)[0] + "\t\t" + inter.get(i1)[1] + "\t\t" + inter.get(i1)[2]);
            }
            System.out.println("-----------------------------------------------------");
        }



}
