package com.itplh.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: tanpeng
 * @since: 2020-06-10 17:41
 */
@Slf4j
public abstract class Sha1Util {

    public static String encode(String plaintext) {
        MessageDigest sha1;
        try {
            sha1 = MessageDigest.getInstance("SHA-1");
            byte[] plaintextByteArray = plaintext.getBytes("UTF-8");
            sha1.update(plaintextByteArray);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return new BigInteger(sha1.digest()).toString(16);
    }

    //---------------测试---------------
    public static void main(String args[]) throws Exception {
        String str = "amigoxiexiexingxing";
        System.out.println("原始：" + str);
        System.out.println("SHA1后：" + Sha1Util.encode(str));
    }

}
