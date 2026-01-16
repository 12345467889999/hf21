package HF21.config;



import HF21.handler.WallWsHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final WallWsHandler wallWsHandler;

    public WebSocketConfig(WallWsHandler wallWsHandler) {
        this.wallWsHandler = wallWsHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(wallWsHandler, "/ws/wall")
                .setAllowedOrigins("*");
    }
}

