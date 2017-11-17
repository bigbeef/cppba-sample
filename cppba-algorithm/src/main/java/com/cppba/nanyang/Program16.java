package com.cppba.nanyang;

import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author winfed
 * @create 2017-11-17 15:10
 */
public class Program16 {
    /**
     * 矩形嵌套
     * 时间限制：3000 ms  |  内存限制：65535 KB
     * 难度：4
     * 描述
     * 有n个矩形，每个矩形可以用a,b来描述，表示长和宽。
     * 矩形X(a,b)可以嵌套在矩形Y(c,d)中当且仅当a<c,b<d或者b<c,a<d（相当于旋转X90度）。
     * 例如（1,5）可以嵌套在（6,2）内，但不能嵌套在（3,4）中。
     * 你的任务是选出尽可能多的矩形排成一行，使得除最后一个外，每一个矩形都可以嵌套在下一个矩形内。
     * <p>
     * 输入
     * 第一行是一个正正数N(0<N<10)，表示测试数据组数，
     * 每组测试数据的第一行是一个正正数n，表示该组测试数据中含有矩形的个数(n<=1000)
     * 随后的n行，每行有两个数a,b(0<a,b<100)，表示矩形的长和宽
     * <p>
     * 输出
     * 每组测试数据都输出一个数，表示最多符合条件的矩形数目，每组输出占一行
     * 样例输入
     * 1
     * 10
     * 1 2
     * 2 4
     * 5 8
     * 6 10
     * 7 9
     * 3 1
     * 5 8
     * 12 10
     * 9 7
     * 2 2
     * 样例输出
     * 5
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Entry> entrySet = new TreeSet<Entry>(new Comparator<Entry>() {
            public int compare(Entry e1, Entry e2) {
                if (e1.x != e2.x) {
                    return e1.x - e2.x;
                }
                return e1.y - e2.y;
            }
        });
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int m = sc.nextInt();
            for (int j = 0; j < m; j++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                Entry entry = new Entry(x, y);
                entrySet.add(entry);
            }
        }
        sc.close();
        Entry preEntry = null;
        int maxCount = 0;
        for (Entry entry : entrySet) {
            if (preEntry == null || (preEntry.x < entry.x && preEntry.y < entry.y)) {
                maxCount++;
                preEntry = entry;
            }
        }
        System.out.println(maxCount);
    }

}
class Entry {

    int x;

    int y;

    Entry(int x, int y) {
        if (x <= y) {
            this.x = x;
            this.y = y;
        } else {
            this.x = y;
            this.y = x;
        }
    }
}