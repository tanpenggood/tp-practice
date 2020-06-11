package com.itplh.sdk.common.util;

import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

public class TestSHAUtil {

    @Test
    public void encrypt() throws NoSuchAlgorithmException {
        Assert.assertEquals("d4fbabf7d19aa57b270ee3a20ec886b8375c31ab",
                SHAUtil.encrypt("14946597481591801242ZUdp9VMAMQz7EsfM", SHAUtil.SHA));
    }

}
