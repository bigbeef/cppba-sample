package com.cppba.nanyang;

import java.util.Scanner;

/**
 * @author winfed
 * @create 2017-11-17 16:05
 */
public class Program39 {
    /**
     * 水仙花数
     * 时间限制：1000 ms  |  内存限制：65535 KB
     * 难度：0
     * 描述
     * 请判断一个数是不是水仙花数。
     * 其中水仙花数定义各个位数立方和等于它本身的三位数。
     * 输入
     * 有多组测试数据，每组测试数据以包含一个整数n(100<=n<1000)
     * 输入0表示程序输入结束。
     * 输出
     * 如果n是水仙花数就输出Yes
     * 否则输出No
     * 样例输入
     * 153
     * 154
     * 0
     * 样例输出
     * Yes
     * No
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            int count = 0;
            int tempN = n;
            while (tempN > 0) {
                int m = tempN % 10;
                tempN = tempN / 10;
                count += (m * m * m);
            }
            if (count == n) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
        sc.close();
    }
}
