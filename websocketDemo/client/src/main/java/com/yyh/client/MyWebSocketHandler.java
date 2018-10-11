package com.yyh.client;

import com.yyh.server.message.Greeting;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;


/**
 * @author lhy
 * @Date 2018/10/8 9:53
 * @JDK 1.7
 * @Description TODO
 */
public class MyWebSocketHandler extends StompSessionHandlerAdapter {
    public MyWebSocketHandler() {
        super();
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Greeting.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Greeting greeting = (Greeting) payload;
        System.out.println("来自服务器得到消息 : " + greeting.getContent());
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        super.afterConnected(session, connectedHeaders);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        exception.printStackTrace();
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        super.handleTransportError(session, exception);
    }
}
