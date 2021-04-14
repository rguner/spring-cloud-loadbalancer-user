package com.example.springcloudloadbalanceruser;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * you need not use the @LoadBalancerClient annotation and create your own configuration for the LoadBalancer.
 * The most typical way is to use Spring Cloud LoadBalancer with service discovery.
 * If you have any DiscoveryClient on your classpath, the default Spring Cloud LoadBalancer configuration uses it to check for service instances.
 * As a result, you only choose from instances that are up and running.
 */
@Configuration
@LoadBalancerClient(name = "say-hello", configuration = SayHelloConfiguration.class)
public class WebClientConfig {

    @LoadBalanced
    @Bean
    WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

}