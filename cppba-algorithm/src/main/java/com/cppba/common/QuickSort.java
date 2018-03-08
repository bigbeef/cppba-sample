package com.cppba.common;

import com.cppba.util.AssertUtils;
import com.cppba.util.MockUtils;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author winfed
 * @created 2018/3/8 14:00
 */
public class QuickSort {

    public static void main(String[] args) {
        long startMillis = System.currentTimeMillis();
        Integer length = 10;
        Integer[] arr = MockUtils.mockNumber(length);
        System.out.println("sort1,before:" + Arrays.toString(arr));
        QuickSort.quickSort(arr, 0, arr.length -1);
        System.out.println("sort1,after:" + Arrays.toString(arr));
        System.out.println("isSortAsc:" + AssertUtils.isSortAsc(arr, length));
        long endMillis = System.currentTimeMillis();
        System.out.println("用时:" + (endMillis - startMillis) +"ms");
    }

    public static void quickSort(Integer arr[], final Integer start, final Integer end) {
        if (start >= end) {
            return;
        }
        Integer startTemp = start, endTemp = end;
        while (startTemp < endTemp) {
            while (startTemp < endTemp && arr[startTemp] < arr[start]) {
                startTemp++;
            }
            while (startTemp < endTemp && arr[endTemp] > arr[start]) {
                endTemp--;
            }
            QuickSort.swap(arr, startTemp, endTemp);
        }
        QuickSort.quickSort(arr, start, startTemp - 1);
        QuickSort.quickSort(arr, startTemp + 1, end);
    }

    /**
     * 交换数组两个数字
     *
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(Integer arr[], Integer a, Integer b) {
        Integer temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
