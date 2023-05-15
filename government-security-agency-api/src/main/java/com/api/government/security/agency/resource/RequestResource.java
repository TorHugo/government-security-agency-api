package com.api.government.security.agency.resource;

import com.api.government.security.agency.service.factory.FactoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/request")
public class RequestResource {

    @Autowired
    private FactoryRequest service;

    @GetMapping(value = "/{agence}")
    public ResponseEntity<Integer> requestForApi(@PathVariable final String agence) {
        Integer recordCount = service.requestService(agence);
        return ResponseEntity.ok().body(recordCount);
    }
}
