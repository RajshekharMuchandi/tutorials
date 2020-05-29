package com.application.programs.eurekaclient20.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @RequestMapping("/load-balance")
    public String serverLocation(Model model) {
        String serviceId = "REST-SERVER";


        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);

        if (instances != null && !instances.isEmpty()) {
            RestTemplate restTemplate = new RestTemplate();

            model.addAttribute("serviceId", serviceId);
            model.addAttribute("instances", instances);

            try {

                ServiceInstance serviceInstance = this.loadBalancerClient.choose(serviceId);

                String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort();
                String result = restTemplate.getForObject(url, String.class);

                model.addAttribute("url", url);
                model.addAttribute("result", result);

            } catch (IllegalStateException e) {
                model.addAttribute("Error", e.getMessage());
                e.printStackTrace();
            }
        }
        return "index";
    }

}
