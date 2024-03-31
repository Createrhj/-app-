package com.gateway.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        HttpHeaders headers = response.getHeaders();
        if(!headers.containsKey("Access-Control-Allow-Origin")){
            //为了防止重复添加跨域响应头，所以先判断是否已经存在跨域响应头
            headers.add("Access-Control-Allow-Headers", "*");//允许跨域的请求头
            headers.add("Access-Control-Allow-Origin", "*");//允许跨域的请求地址
            headers.add("Access-Control-Allow-Methods", "*");//允许跨域的请求方法
        }
        return chain.filter(exchange);
    }
}
