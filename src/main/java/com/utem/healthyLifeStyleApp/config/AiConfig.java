package com.utem.healthyLifeStyleApp.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AiConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }

    // @Bean
    // public RestTemplate openaiRestTemplate(){
    //     RestTemplate restTemplate = new RestTemplate();
    //     restTemplate.getInterceptors().add((request, body, execution) -> {
    //         // request.getHeaders().add("Authorization", "Bearer "+ openApiKey);
    //         request.getHeaders().setBearerAuth(openApiKey);
    //         return execution.execute(request, body);    
    // });
    // return restTemplate;
// }
}