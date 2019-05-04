package edu.mum.cs490.shoppingcart.config;

import edu.mum.cs490.shoppingcart.utility.AESConverterUtility;
import edu.mum.cs490.shoppingcart.utility.HttpSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Thomas Tibebu,
 * Date April 20, 2019
 **/
@Configuration
public class MockConnectionConfig {

    @Value("${mock.api.url}")
    private String mockApiUrl;
    @Value("${mock.api.user}")
    private String mockApiUser;
    @Value("${mock.api.pass}")
    private String mockApiPass;
    @Value("${mock.api.content.type}")
    private String mockApiContentType;
    @Value("${mock.api.conn.time}")
    private Integer mockApiConnTime;
    @Value("${mock.api.read.time}")
    private Integer mockApiReadTime;
    @Value("${mock.api.secret.key}")
    private String mockApiSecretKey;

    @Bean
    public HttpSender httpSender() {
        System.out.println("HTTP SENDER is created");
        return new HttpSender(mockApiUrl, mockApiUser, mockApiPass, mockApiContentType, mockApiConnTime, mockApiReadTime);
    }

    @Bean
    public AESConverterUtility aesConverter() {
        System.out.println("AES is created");
        return new AESConverterUtility(mockApiSecretKey);
    }
}
