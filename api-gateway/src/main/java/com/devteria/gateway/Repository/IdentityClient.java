package com.devteria.gateway.Repository;

import com.devteria.gateway.dto.ApiResponse;
import com.devteria.gateway.dto.Request.IntrospectRequest;
import com.devteria.gateway.dto.Response.IntrospectResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

@Repository
public interface IdentityClient {
    @PostExchange(url = "/auth/introspect",contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<IntrospectResponse>> introspect (@RequestBody IntrospectRequest request);
}
