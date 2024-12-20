package com.devteria.gateway.Configuration;

import com.devteria.gateway.Service.IdentityService;
import com.devteria.gateway.dto.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConfigurationFilter implements GlobalFilter , Ordered {
    @Autowired
    private IdentityService identityService;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("fdewfdew");
        List<String> authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
        if(CollectionUtils.isEmpty(authHeader)) {
            try {
                return authentication(exchange.getResponse());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        String token = authHeader.getFirst().replace("Bearer","");
        log.info("token : {}", token);
        return identityService.introspect(token).flatMap(introspectResponseApiResponse -> {
            if(introspectResponseApiResponse.getResult().isValid())
                return Mono.empty();
            else {
                try {
                    return authentication(exchange.getResponse());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        }).onErrorResume(throwable -> {
            try {
                return authentication(exchange.getResponse());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public int getOrder() {
        return -1;
    }
    Mono<Void> authentication(ServerHttpResponse response) throws JsonProcessingException {
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(1401)
                .message("unauthenticated")
                .build();
        String body = objectMapper.writeValueAsString(apiResponse);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return response.writeWith(
                Mono.just(response.bufferFactory().wrap(body.getBytes())));
    }
}
