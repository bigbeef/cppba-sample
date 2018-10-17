package com.cppba.common;

public class Binary {
    public static void main(String[] args) {
        short a = 1000;

        System.out.print("1000 -> ");
        for (int i = 0; i < 16; i++) {
            System.out.print((a << i >>15) & 0x1);
        }

        System.out.println();

        short[] src = {1000,2};
        byte[] bytes = shortArrayToByteArray(src);

        System.out.print("bytes[0] -> ");
        for (int i = 0; i < 8; i++) {
            System.out.print((bytes[0] << i >>7) & 0x1);
        }
        System.out.println();
        System.out.print("bytes[1] -> ");
        for (int i = 0; i < 8; i++) {
            System.out.print((bytes[1] << i >>7) & 0x1);
        }
    }

    /**
     * short数组转byte数组
     *
     * @param src
     * @return
     */
    public static byte[] shortArrayToByteArray(short[] src) {
        int length = src.length;
        byte[] bytes = new byte[length << 1];
        for (int i=0;i<length;i++){
            bytes[i*2] = (byte)(src[i] >> 8);
            bytes[i*2+1] = (byte)src[i];
        }
        return bytes;
    }
}
