package com.application.programs.eurekaclient20.config;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfiguration {

    @Autowired
    IClientConfig ribbonClientConfig;

    @Bean
    public IPing ribbonPing(IClientConfig config) {
        return new PingUrl();
        // ping the server to get some results, then run rules through the results.¿Which rules? see method below(weigh time)
    }

    @Bean
    public IRule ribbonRule() {
        return new WeightedResponseTimeRule();
        // Measure the response time, select the one with smaller response time.
    }
}
