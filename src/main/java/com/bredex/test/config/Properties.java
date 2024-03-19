package com.bredex.test.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Properties {
    @Value("${url}")
    private String defaultUrl;

    @Value("${secretKey}")
    private String secretKey;
}
