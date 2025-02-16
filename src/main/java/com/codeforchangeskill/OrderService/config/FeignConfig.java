package com.codeforchangeskill.OrderService.config;

import com.codeforchangeskill.OrderService.external.decoder.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    ErrorDecoder errorDecoder()
    {
        return new CustomErrorDecoder();
    }

}
