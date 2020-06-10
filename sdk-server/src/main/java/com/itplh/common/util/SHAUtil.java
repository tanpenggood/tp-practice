package com.itplh.common.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: tanpeng
 * @since: 2020-06-10 17:41
 */
@Slf4j
public abstract class SHAUtil {

    // 正数
    private static final int POSITIVE = 1;
    // 16进制
    private static final int RADIX16 = 1<<4;
    // SHA加密算法
    public static final String SHA = "SHA";

    public static String encrypt(String plaintext, String shaN) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance(shaN);
        sha.update(plaintext.getBytes());
        byte[] outputData = sha.digest();
        return new BigInteger(POSITIVE, outputData).toString(RADIX16);
    }

    //---------------测试---------------
    public static void main(String args[]) throws Exception {
        System.out.println(encrypt("14946597481591801242ZUdp9VMAMQz7EsfM", SHA));
    }

}
