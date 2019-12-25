package com.xy.banker.function;

/**
 * @ClassName StringToInt
 * @Description: TODO
 * @Author 杨路恒
 * @Date 2019/12/22 0022
 * @Version V1.0
 **/
public class StringToInt {
    public static void StringToInt(int[] arr,String[] s){
        for (int i = 0; i < s.length; i++) {
            arr[i]= Integer.parseInt(s[i]);
        }
    }

    public static void StringToInt(int[][] arr,String[][] s){
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j <s[0].length ; j++) {
                arr[i][j]= Integer.parseInt(s[i][j]);
            }
        }
    }
}
