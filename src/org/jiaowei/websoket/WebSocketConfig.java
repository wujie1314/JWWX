package org.jiaowei.websoket;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements
        WebSocketConfigurer {

    private FlashPolicyServer fpServer;

    private static Logger logger = Logger.getLogger(WebSocketConfig.class);

    public WebSocketConfig() {
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        fpServer=new FlashPolicyServer(10844);
        fpServer.start();
        registry.addHandler(systemWebSocketHandler(), "/chatSocket").addInterceptors(new HandshakeInterceptor());
        registry.addHandler(appWebSocketHandler(), "/appChatSocket").addInterceptors(new HandshakeInterceptor());
        logger.info("registed!");
    }

    @Bean
    public WebSocketHandler systemWebSocketHandler() {
        //return new InfoSocketEndPoint();
        return new SystemWebSocketHandler();
    }

    @Bean
    public WebSocketHandler appWebSocketHandler() {
    	//return new InfoSocketEndPoint();
    	return new AppWebSocketHandler();
    }
    
}
