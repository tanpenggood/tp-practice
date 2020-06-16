package com.itplh.sdk.weixin.jssdk.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itplh.sdk.common.util.IdWorker;
import com.itplh.sdk.common.util.SHAUtil;
import com.itplh.sdk.weixin.jssdk.mapper.WeixinJsSdkMapper;
import com.itplh.sdk.weixin.jssdk.pojo.bo.SignatureBO;
import com.itplh.sdk.weixin.jssdk.pojo.entity.WeixinJsSdk;
import com.itplh.sdk.weixin.jssdk.pojo.properties.WeixinJsSdkProperties;
import com.itplh.sdk.weixin.jssdk.pojo.response.AccessTokenResponse;
import com.itplh.sdk.weixin.jssdk.pojo.response.TicketResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;

@Slf4j
@Service
public class WeixinJsSdkService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeixinJsSdkMapper weixinJsSdkMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private WeixinJsSdkProperties weixinJsSdkProperties;

    /**
     * 请求微信接口获取access_token
     *
     * @return
     */
    public AccessTokenResponse getAccessToken() {
        String accessTokenUrl = String.format(weixinJsSdkProperties.getUrlTemplateAccessToken(),
                weixinJsSdkProperties.getAppid(), weixinJsSdkProperties.getSecret());
        AccessTokenResponse accessTokenResponse = restTemplate.getForEntity(accessTokenUrl, AccessTokenResponse.class).getBody();
        log.info(accessTokenResponse.toString());
        Optional.ofNullable(accessTokenResponse.getAccess_token()).orElseThrow(() -> new RuntimeException("获取access_token失败，请检查相关配置"));
        return accessTokenResponse;
    }

    /**
     * 请求微信接口获取ticket
     *
     * @return
     */
    public TicketResponse getTicketResponse() {
        String accessToken = getAccessToken().getAccess_token();
        String ticketUrl = String.format(weixinJsSdkProperties.getUrlTemplateTicket(), accessToken);
        TicketResponse ticketResponse = restTemplate.getForEntity(ticketUrl, TicketResponse.class).getBody();
        log.info(ticketResponse.toString());
        Optional.ofNullable(ticketResponse.getTicket()).orElseThrow(() -> new RuntimeException("获取ticket失败，请检查相关配置"));
        return ticketResponse;
    }

    /**
     * 获取ticket
     *
     * @return
     */
    public String getTicket() {
        String ticket = null;
        IPage<WeixinJsSdk> page = weixinJsSdkMapper.selectPage(new Page<>(1, 1), null);
        WeixinJsSdk weixinJsSdk = (page.getRecords().size() == 1) ? page.getRecords().get(0) : null;
        if (weixinJsSdk == null) {
            TicketResponse ticketResponse = getTicketResponse();
            ticket = ticketResponse.getTicket();

            weixinJsSdk = new WeixinJsSdk();
            weixinJsSdk.setId(idWorker.nextId());
            weixinJsSdk.setTicket(ticket);
            weixinJsSdk.setCreateTime(new Date());
            weixinJsSdkMapper.insert(weixinJsSdk);
        } else if (isExpireOfCacheTicket(weixinJsSdk.getCreateTime())) {
            TicketResponse ticketResponse = getTicketResponse();
            ticket = ticketResponse.getTicket();

            weixinJsSdk.setTicket(ticket);
            weixinJsSdk.setCreateTime(new Date());
            weixinJsSdkMapper.updateById(weixinJsSdk);
        } else {
            ticket = weixinJsSdk.getTicket();
            log.info("从缓存命中ticket");
        }
        log.info("ticket {}", ticket);
        return ticket;
    }

    public SignatureBO getSignatureBO(String url) {
        SignatureBO signatureBO = new SignatureBO();
        signatureBO.setNonceStr(RandomStringUtils.randomAlphanumeric(16));
        signatureBO.setAppId(weixinJsSdkProperties.getAppid());
        signatureBO.setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")) + "");

        Map<String, String> map = new HashMap<>();
        map.put("noncestr", signatureBO.getNonceStr());
        map.put("jsapi_ticket", getTicket());
        map.put("timestamp", signatureBO.getTimestamp());
        map.put("url", url);

        // 按照key的ASCII码从小到大排序（字典序）
        List<String> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList);
        Map<String, String> map2 = new LinkedHashMap<>();
        keyList.forEach(key -> map2.put(key, map.get(key)));
        // 使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串
        StringBuilder stringBuilder = new StringBuilder();
        map2.forEach((key, value) -> stringBuilder.append("&").append(key).append("=").append(value));
        try {
            // 进行sha1加密，获得signature
            String signature = SHAUtil.encrypt(stringBuilder.toString().substring(1), SHAUtil.SHA);
            signatureBO.setSignature(signature);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        log.info(signatureBO.toString());
        return signatureBO;
    }

    /**
     * 判断数据库缓存的accessToken与ticket是否过期
     *
     * @param cacheCreateTime
     * @return
     */
    private boolean isExpireOfCacheTicket(Date cacheCreateTime) {
        LocalDateTime createTime = LocalDateTime.ofInstant(cacheCreateTime.toInstant(), ZoneId.systemDefault());
        LocalDateTime expireTime = createTime.plusSeconds(weixinJsSdkProperties.getExpiresIn());
        return LocalDateTime.now().isAfter(expireTime);
    }

}

