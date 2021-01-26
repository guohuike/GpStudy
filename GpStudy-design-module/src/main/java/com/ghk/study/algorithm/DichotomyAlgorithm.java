package com.ghk.study.algorithm;

import java.util.Arrays;

/**
 * @author guohuike
 * @Description 二分法查找
 * @date 2021/1/26
 */
public class DichotomyAlgorithm {
    public static void main(String[] args) {
        int[] array={78,100,30,58,66,20,-3};
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        //System.out.println(dichotomy(array,66));
        System.out.println(nearNum(array,33));
    }

    /**
     * 二分法
     * @param arry 数组
     * @param key key信息
     */
    private static Integer dichotomy(int[] arry,int key){
        Integer resultIndex = null;
        int maxIndex = arry.length-1;
        int initIndex = 0;
        while (initIndex <= maxIndex){
            int mid = (initIndex + maxIndex) / 2;
            if(key < arry[mid]){
                maxIndex = mid - 1;
            }else if(key > arry[mid]){
                initIndex = mid + 1;
            }else if(key == arry[mid]){
                resultIndex = mid;
                break;
            }
        }
        return resultIndex;
    }

    /**
     * 二分法
     * @param arry 数组
     * @param key key信息
     */
    private static Integer nearNum(int[] arry,int key){
        Integer resultIndex = null;
        int maxIndex = arry.length-1;
        int initIndex = 0;
        Integer temp = null;
        while (initIndex <= maxIndex){
            int mid = initIndex + (maxIndex - initIndex ) / 2;
            if(key < arry[mid]){
                maxIndex = mid - 1;
                if(null != temp){
                    //肯定就是正数
                    if(temp > (arry[mid] - key)){
                        temp = arry[mid] - key;
                        resultIndex = mid;
                    }
                }else{
                    temp = arry[mid] - key;
                    resultIndex = mid;
                }
            }else if(key > arry[mid]){
                initIndex = mid + 1;
                if(null != temp){
                    //肯定负数
                    if(temp < 0 && temp > (arry[mid] - key)){
                        temp = arry[mid] - key;
                        resultIndex = mid;
                    }
                }else{
                    temp = arry[mid] - key;
                    resultIndex = mid;
                }
            }else if(key == arry[mid]){
                //比较时间 ....
                temp = arry[mid];
                return mid;
            }
        }
        return resultIndex;
    }
}
