package com.cppba.common;

import java.util.Arrays;

/**
 * @author winfed
 * @created 2018/3/5 15:06
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr1 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println("sort1,before:" + Arrays.toString(arr1));
        sort(arr1);
        System.out.println("sort1,after:" + Arrays.toString(arr1));
        int[] arr2 = {4, 6, 8, 5, 9, 2, 3, 7, 1};
        System.out.println("sort1,before:" + Arrays.toString(arr2));
        sort(arr2);
        System.out.println("sort1,after:" + Arrays.toString(arr2));
    }

    /**
     * 排序
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        //1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int i = arr.length - 1; i > 0; i--) {
            // 将堆顶元素与末尾元素进行交换
            swap(arr, 0, i);
            // 重新调整堆
            adjustHeap(arr, 0, i);
        }
    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     *
     * @param arr
     * @param i
     * @param length
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int max = i;
        // 左节点检查
        if ( 2*i+1 < length ){
            max = arr[max] < arr[2*i+1] ? 2*i+1 : max;
        }
        // 右节点检查
        if ( 2*i+2 < length ){
            max = arr[max] < arr[2*i+2] ? 2*i+2 : max;
        }

        if ( arr[i] < arr[max]){
            int temp = arr[i];
            arr[i] = arr[max];
            arr[max] = temp;
            adjustHeap(arr, max, length);
        }
    }

    /**
     * 交换元素
     *
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int[] arr, int a, int b) {
        int temp=arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
