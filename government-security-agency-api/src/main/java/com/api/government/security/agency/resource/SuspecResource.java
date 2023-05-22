package com.api.government.security.agency.resource;

import com.api.government.security.agency.lib.dto.suspected.BasicSuspectDTO;
import com.api.government.security.agency.lib.dto.suspected.SuspectDTO;
import com.api.government.security.agency.service.SuspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/suspect")
public class SuspecResource {

    @Autowired
    private SuspectService service;

    @GetMapping(value = "/{suspectId}")
    public ResponseEntity<SuspectDTO> requestBySuspectId(@PathVariable final Long suspectId) {
        SuspectDTO recordCount = service.findSuspectById(suspectId);
        return ResponseEntity.ok().body(recordCount);
    }
    @GetMapping()
    public ResponseEntity<List<BasicSuspectDTO>> requestByAML(){
        List<BasicSuspectDTO> lsReturn = service.findAllSuspectAML();
        return ResponseEntity.ok().body(lsReturn);
    }
}
