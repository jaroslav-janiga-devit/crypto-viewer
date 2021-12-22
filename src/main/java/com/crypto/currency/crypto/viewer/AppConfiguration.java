package com.crypto.currency.crypto.viewer;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {AppConfiguration.class})
public class AppConfiguration {

    @Bean
    public OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder().build();
    }


}
