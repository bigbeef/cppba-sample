package com.cppba.nowcoder;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author winfed
 * @create 2017-11-17 16:05
 * @deprecated  https://www.nowcoder.net/pat/6/problem/4055
 */
public class Program1019 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String original = sc.next();
        byte[] originalBytes = original.getBytes();

        String enter = sc.next();
        byte[] enterBytes = enter.getBytes();
        ArrayList<String> enterByteList = new ArrayList<String>();
        for (Byte enterByte : enterBytes) {
            enterByteList.add(new String ((char)enterByte.byteValue() + "").toUpperCase());
        }

        ArrayList<String> notContentList = new ArrayList<String>();
        for (Byte originalByte : originalBytes) {
            String originalString = new String ((char)originalByte.byteValue() + "").toUpperCase();
            if (!enterByteList.contains(originalString) && !notContentList.contains(originalString)){
                notContentList.add(originalString);
            }
        }

        for (String str : notContentList) {
            System.out.print(str);
        }
        System.out.println();

        sc.close();
    }
}
