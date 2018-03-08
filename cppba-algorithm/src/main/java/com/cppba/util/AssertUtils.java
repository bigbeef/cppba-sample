package com.cppba.util;

/**
 * 检测类
 * @author winfed
 * @created 2018/3/8 16:04
 */
public class AssertUtils {
    /**
     * 检查数组是否升序排列
     *
     * @param arr
     * @param length
     * @return
     */
    public static boolean isSortAsc(Integer[] arr, int length) {
        if (length <= 1) {
            return true;
        }
        for (int i = 1; i < length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }
}
