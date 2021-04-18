package com.example.init.socket.config;

import com.example.init.socket.handler.MyStringWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocket配置
 *
 * @author 张庆福
 * @date 2021/4/18
 */
@Configuration
@EnableWebSocket // 开启WebSocket相关功能
public class WebSocketServerConfig implements WebSocketConfigurer {

    @Autowired
    private MyStringWebSocketHandler myStringWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 当客户端通过'/connect' url和服务端连接通信时，使用MyStringWebSocketHandler处理会话。
        registry.addHandler(myStringWebSocketHandler, "/connect").withSockJS();
    }
}