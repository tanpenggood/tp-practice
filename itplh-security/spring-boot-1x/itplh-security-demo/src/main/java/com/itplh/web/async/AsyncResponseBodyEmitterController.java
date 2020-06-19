package com.itplh.web.async;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itplh.pojo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author: tanpeng
 * @since: 2020-06-19 10:49
 */
@Slf4j
@RestController
@RequestMapping("/async/emitter")
public class AsyncResponseBodyEmitterController {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private Map<String, ResponseBodyEmitter> map = new HashMap<>();

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBodyEmitter> emitter(@PathVariable String id) {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        map.put(id, emitter);
        return ResponseEntity.ok(emitter);
    }

    @GetMapping("/{id}/invoke")
    public void invokeEmitter(@PathVariable String id,
                              ObjectMapper objectMapper) {
        log.info("主线程开始...");
        ResponseBodyEmitter emitter = map.get(id);
        Optional.ofNullable(emitter).ifPresent(emi ->
                CompletableFuture.runAsync(() -> {
                    log.info("副线程开始...");
                    try {
                        emitter.send("hello response body emitter", MediaType.TEXT_PLAIN);
                        Thread.sleep(1000);

                        UserVO userVO = new UserVO();
                        userVO.setId("1");
                        userVO.setBirthday(new Date());
                        userVO.setUsername("tanpeng");
                        userVO.setPassword("123456");
                        emitter.send(objectMapper.writeValueAsString(userVO), MediaType.APPLICATION_JSON_UTF8);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("副线程结束...");
                }, threadPoolTaskExecutor)
        );
        log.info("主线程结束...");
    }

    @GetMapping("/{id}/close")
    public void close(@PathVariable String id) {
        ResponseBodyEmitter emitter = map.get(id);
        Optional.ofNullable(emitter).ifPresent(e -> {
            emitter.complete();
            map.remove(id);
        });
    }

}
