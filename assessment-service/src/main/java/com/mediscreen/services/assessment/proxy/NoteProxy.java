package com.mediscreen.services.assessment.proxy;

import com.mediscreen.services.assessment.beans.NoteBean;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "note-service")
@LoadBalancerClient(name = "note-service")
public interface NoteProxy {

    @GetMapping("/notes/patients/{id}")
    List<NoteBean> getNoteByPatient(@PathVariable Long id);
}
