package com.itplh.sdk.weixin.jssdk.pojo.ro;

import com.itplh.sdk.common.util.SHAUtil;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Data
public class ConnectAuthRO {

    @NotBlank
    private String signature;
    @NotBlank
    private String timestamp;
    @NotBlank
    private String nonce;
    @NotBlank
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
    public String hashcodeBySha1(String token) throws NoSuchAlgorithmException {
        String[] array = {token, timestamp, nonce};
        Arrays.sort(array);
        String sortString = String.join("", array);
        return SHAUtil.encrypt(sortString, SHAUtil.SHA);
    }

}
