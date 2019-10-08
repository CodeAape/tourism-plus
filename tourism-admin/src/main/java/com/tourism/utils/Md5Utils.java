package com.tourism.utils;

import java.security.MessageDigest;

/**
 * MD5加密
 */
public class Md5Utils {
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (byte element : b) {
            resultSb.append(byteToHexString(element));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 单次加密
     *
     * @param origin
     * @return
     */
    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        } catch (Exception ex) {
        }
        return resultString;
    }

    /**
     * 加密三次
     *
     * @param password
     * @return
     */
    public static String encrypt3Times(String password) {
        return MD5Encode(MD5Encode(MD5Encode(password)));
    }

    public static void main(String[] args) {
        //System.err.println(MD5Encode("mjbmjb"));
        System.out.println(encrypt3Times("123456"));
        //c56d0e9a7ccec67b4ea131655038d604
    }
}