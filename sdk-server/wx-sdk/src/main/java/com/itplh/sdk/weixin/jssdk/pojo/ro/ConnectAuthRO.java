package com.itplh.sdk.weixin.jssdk.pojo.ro;

import com.itplh.sdk.common.util.SHAUtil;
import com.itplh.sdk.weixin.jssdk.config.JsSdkEnum;
import lombok.Data;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Data
public class ConnectAuthRO {

    private String signature;
    private String timestamp;
    private String nonce;
    private String echostr;

    /**
     * example:
     *  signature=d4fbabf7d19aa57b270ee3a20ec886b8375c31ab
     *  timestamp=1591801242
     *  nonce=1494659748
     *  echostr=5954073457602133470
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String hashcodeBySha1() throws NoSuchAlgorithmException {
        String[] array = {JsSdkEnum.TOKEN.getValue(), timestamp, nonce};
        Arrays.sort(array);
        String sortString = String.join("", array);
        return SHAUtil.encrypt(sortString, SHAUtil.SHA);
    }

}
