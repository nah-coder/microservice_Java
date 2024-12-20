package com.devteria.gateway.Service;

import com.devteria.gateway.Repository.IdentityClient;
import com.devteria.gateway.dto.ApiResponse;
import com.devteria.gateway.dto.Request.IntrospectRequest;
import com.devteria.gateway.dto.Response.IntrospectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class IdentityService {
    @Autowired
    private IdentityClient identityClient;

    public Mono<ApiResponse<IntrospectResponse>> introspect(String token) {
        return identityClient.introspect(IntrospectRequest.builder().token(token).build());
    }
}
