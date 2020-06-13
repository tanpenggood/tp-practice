package com.itplh.sdk.weixin.jssdk.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itplh.sdk.common.util.IdWorker;
import com.itplh.sdk.weixin.jssdk.config.JsSdkEnum;
import com.itplh.sdk.weixin.jssdk.mapper.JsSdkMapper;
import com.itplh.sdk.weixin.jssdk.pojo.entity.JsSdk;
import com.itplh.sdk.weixin.jssdk.pojo.response.AccessTokenResponse;
import com.itplh.sdk.weixin.jssdk.pojo.response.TicketResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Service
public class JsSdkService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JsSdkMapper jsSdkMapper;

    @Autowired
    private IdWorker idWorker;

    /**
     * 请求微信接口获取access_token
     *
     * @return
     */
    public AccessTokenResponse getAccessToken() {
        String accessTokenUrl = String.format(JsSdkEnum.ACCESS_TOKEN_URL_TEMPLATE.getValue(),
                JsSdkEnum.APPID.getValue(), JsSdkEnum.SECRET.getValue());
        AccessTokenResponse accessTokenResponse = restTemplate.getForEntity(accessTokenUrl, AccessTokenResponse.class).getBody();
        log.info(accessTokenResponse.toString());
        return accessTokenResponse;
    }

    /**
     * 请求微信接口获取ticket
     *
     * @return
     */
    public TicketResponse getTicketResponse() {
        String accessToken = getAccessToken().getAccess_token();
        String ticketUrl = String.format(JsSdkEnum.TICKET_URL_TEMPLATE.getValue(), accessToken);
        TicketResponse ticketResponse = restTemplate.getForEntity(ticketUrl, TicketResponse.class).getBody();
        log.info(ticketResponse.toString());
        return ticketResponse;
    }

    /**
     * 获取ticket
     *
     * @return
     */
    public String getTicket() {
        String ticket = null;
        IPage<JsSdk> page = jsSdkMapper.selectPage(new Page<>(1, 1), null);
        JsSdk jsSdk = (page.getRecords().size() == 1) ? page.getRecords().get(0) : null;
        if (jsSdk == null) {
            TicketResponse ticketResponse = getTicketResponse();
            ticket = ticketResponse.getTicket();

            jsSdk = new JsSdk();
            jsSdk.setId(idWorker.nextId());
            jsSdk.setTicket(ticket);
            jsSdk.setCreateTime(new Date());
            jsSdkMapper.insert(jsSdk);
        } else if (isExpireOfCacheTicket(jsSdk.getCreateTime())) {
            TicketResponse ticketResponse = getTicketResponse();
            ticket = ticketResponse.getTicket();

            jsSdk.setTicket(ticket);
            jsSdk.setCreateTime(new Date());
            jsSdkMapper.updateById(jsSdk);
        } else {
            ticket = jsSdk.getTicket();
        }
        log.info("ticket {}", ticket);
        return ticket;
    }

    /**
     * 判断数据库缓存的accessToken与ticket是否过期
     *
     * @param cacheCreateTime
     * @return
     */
    private boolean isExpireOfCacheTicket(Date cacheCreateTime) {
        LocalDateTime createTime = LocalDateTime.ofInstant(cacheCreateTime.toInstant(), ZoneId.systemDefault());
        LocalDateTime expireTime = createTime.plusSeconds(Long.valueOf(JsSdkEnum.EXPIRES_IN.getValue()));
        return LocalDateTime.now().isAfter(expireTime);
    }

}

