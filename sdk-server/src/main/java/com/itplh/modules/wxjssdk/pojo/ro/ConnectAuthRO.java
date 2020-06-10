package com.itplh.modules.wxjssdk.pojo.ro;

import com.itplh.common.util.SHAUtil;
import com.itplh.modules.wxjssdk.config.WxJsSdkEnum;
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
        String[] array = {WxJsSdkEnum.TOKEN.getValue(), timestamp, nonce};
        Arrays.sort(array);
        String sortString = String.join("", array);
        return SHAUtil.encrypt(sortString, SHAUtil.SHA);
    }

}
