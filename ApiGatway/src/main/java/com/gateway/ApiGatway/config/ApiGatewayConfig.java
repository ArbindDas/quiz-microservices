
package com.gateway.ApiGatway.config;
import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class ApiGatewayConfig {

    private static final String QUIZ_SERVICE_URL = "http://localhost:8081"; // <-- replace with your Quiz-Service port

    @Bean
    public RouterFunction<ServerResponse> gatewayRoutes() {

        // /api/quizzes/**
        RouterFunction<ServerResponse> quizzesRoute = GatewayRouterFunctions.route()
                .GET("/api/quizzes/**", HandlerFunctions.http())
                .POST("/api/quizzes/**", HandlerFunctions.http())
                .PUT("/api/quizzes/**", HandlerFunctions.http())
                .DELETE("/api/quizzes/**", HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri(QUIZ_SERVICE_URL))
                .build();

        // /api/test/**
        RouterFunction<ServerResponse> testRoute = GatewayRouterFunctions.route()
                .GET("/api/test/**", HandlerFunctions.http())
                .POST("/api/test/**", HandlerFunctions.http())
                .PUT("/api/test/**", HandlerFunctions.http())
                .DELETE("/api/test/**", HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri(QUIZ_SERVICE_URL))
                .build();

        // Combine routes
        return quizzesRoute.and(testRoute);
    }
}
