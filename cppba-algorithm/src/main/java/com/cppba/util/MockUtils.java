package com.cppba.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 模拟生成类
 * @author winfed
 * @created 2018/3/8 14:41
 */
public class MockUtils {
    /**
     * 模拟一组length长度的乱序数组
     * @param length
     * @return
     */
    public static Integer[] mockNumber(int length) {
        Integer max = length;
        List<Integer> list = new ArrayList<Integer>();
        Random random = new Random();
        for (int i = 0; i < max; i++) {
            int r = random.nextInt(max);
            while (list.contains(r)) {
                r = random.nextInt(max);
            }
            list.add(r);
        }
        return list.toArray(new Integer[0]);
    }
}
