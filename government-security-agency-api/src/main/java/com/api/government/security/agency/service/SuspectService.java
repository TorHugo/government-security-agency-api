package com.api.government.security.agency.service;

import com.api.government.security.agency.lib.dto.suspected.BasicSuspectDTO;
import com.api.government.security.agency.lib.dto.suspected.SuspectDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SuspectService {
    SuspectDTO findSuspectById(final Long suspectId);

    List<BasicSuspectDTO> findAllSuspectAML();
}
