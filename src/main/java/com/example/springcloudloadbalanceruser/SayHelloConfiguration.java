package com.example.springcloudloadbalanceruser;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SayHelloConfiguration {

    @Bean
    @Primary
    ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new DemoServiceInstanceListSuppler("say-hello-serviceId");
    }

}

class DemoServiceInstanceListSuppler implements ServiceInstanceListSupplier {

    private final String serviceId;

    DemoServiceInstanceListSuppler(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(Arrays
                .asList(new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 8090, false),
                        new DefaultServiceInstance(serviceId + "2", serviceId, "localhost", 8091, false),
                        new DefaultServiceInstance(serviceId + "3", serviceId, "localhost", 8092, false)));
    }
}