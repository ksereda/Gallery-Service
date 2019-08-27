package com.example.galleryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "<a href='showAllServices'>Show All Services</a>";
    }

    @ResponseBody
    @RequestMapping(value = "/showAllServices", method = RequestMethod.GET)
    public String showAllServiceIds() {

        List<String> serviceIds = this.discoveryClient.getServices();

        if (serviceIds == null || serviceIds.isEmpty()) {
            return "No services found!";
        }

        String result = "Service Ids:";

        for (String serviceId : serviceIds) {
            result += "<br><a href='showService?serviceId=" + serviceId + "'>" + serviceId + "</a>";
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/showService", method = RequestMethod.GET)
    public String showFirstService(@RequestParam(defaultValue = "") String serviceId) {

        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);

        if (instances == null || instances.isEmpty()) {
            return "No instances for service: " + serviceId;
        }

        String result = "Instances for Service: " + serviceId + "<br>";

        for (ServiceInstance serviceInstance : instances) {
            result += "<br>Instance: " + serviceInstance.getUri();
            result += "<br>" + "Hostname: " + serviceInstance.getHost();
            result += "<br>" + "Port: " + serviceInstance.getPort() + "<br>";
        }

        return result;
    }

}
