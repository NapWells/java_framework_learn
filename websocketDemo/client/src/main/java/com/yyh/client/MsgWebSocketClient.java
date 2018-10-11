package com.yyh.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyh.server.message.Greeting;
import com.yyh.server.message.HelloMessage;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;


import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author lhy
 * @Date 2018/10/10 14:53
 * @JDK 1.7
 * @Description TODO
 */
public class MsgWebSocketClient {
    public static void main(String[] args) throws ExecutionException, InterruptedException, JsonProcessingException {

        Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
        List<Transport> transports = Collections.singletonList(webSocketTransport);

        SockJsClient sockJsClient = new SockJsClient(transports);
        sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        String url = "ws://127.0.0.1:8080/gs-guide-websocket";

        TaskScheduler taskScheduler = new ConcurrentTaskScheduler();
        stompClient.setTaskScheduler(taskScheduler);

        StompSessionHandler stompFrameHandler = new MyWebSocketHandler();
        ListenableFuture<StompSession> f = stompClient.connect(url, stompFrameHandler, "localhost", 8080);

        StompSession stompSession = f.get();
        stompSession.setAutoReceipt(true);

        HelloMessage helloMessage = new HelloMessage();
        helloMessage.setName("cc");

        System.out.println("Subscribing to greeting topic using session " + stompSession);

        stompSession.send("/app/hello",helloMessage);

        stompSession.subscribe("/topic/greetings", stompFrameHandler);

    }

}
