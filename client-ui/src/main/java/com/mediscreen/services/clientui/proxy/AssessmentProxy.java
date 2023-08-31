package com.mediscreen.services.clientui.proxy;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "assessment-service", url = "${spring.cloud.openfeign.client.config.assessment-service.url}")
@LoadBalancerClient(name = "assessment-service")
public interface AssessmentProxy {


    @PostMapping("/assess/id")
    ResponseEntity<String> assessById(@RequestParam("patId") Long patientId);

    @PostMapping("/assess/familyName")
    ResponseEntity<String> assessByFamilyName(@RequestParam("familyName") String familyName);

    }
