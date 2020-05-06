package com.itplh.security;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-05-06 14:39
 * @version: 1.0.0
 */
public class PasswordEncoderTest {

    @Test
    public void encode() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encode1 = passwordEncoder.encode("111");
        System.out.println(encode1);
        Assert.assertTrue(passwordEncoder.matches("111", encode1));

        String encode2 = passwordEncoder.encode("111");
        System.out.println(encode2);
        Assert.assertTrue(passwordEncoder.matches("111", encode2));

        String encode3 = passwordEncoder.encode("222");
        System.out.println(encode3);
        Assert.assertTrue(passwordEncoder.matches("222", encode3));

        String encode4 = passwordEncoder.encode("222");
        System.out.println(encode4);
        Assert.assertTrue(passwordEncoder.matches("222", encode4));
    }
}
