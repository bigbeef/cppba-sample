package com.cppba.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winfed
 * @create 2017-11-01 14:22
 */
public class ListUtils {

    /**
     * 将一个list均分成n个list（不能整除时，多余的会被靠前的list平分一个）
     * 例如：[1,2,3,4,5,6,7,8,9,10] n=4时 -> [[1,2,3],[4,5,6],[7,8],[9,10]]
     *
     * @param source 待切分的list
     * @param n 切分成n份
     * @return
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<>();
        int remaider = source.size() % n;  // (先计算出余数)
        int number = source.size() / n;  // 然后是商
        int offset = 0;// 偏移量
        for (int i = 0; i < n; i++) {
            List<T> value = null;
            if (remaider > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }
}
