package com.itplh.event;

import com.itplh.Result;
import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 * 参照　{@link org.springframework.security.authentication.event.AbstractAuthenticationEvent}
 */
public class CustomEvent extends ApplicationEvent {

    public CustomEvent(Result result) {
        super(result);
    }

    public Result getResult() {
        return (Result) super.getSource();
    }

    /**
     * 处理该事件
     * 如业务复杂，可将该方法抽象出来，具体处理逻辑由实现类去做
     */
    void handleEvent() {
        System.out.println(getResult().toString());
    }

}
