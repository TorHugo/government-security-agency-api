package com.api.government.security.agency.request;

import com.api.government.security.agency.lib.dto.fbi.FBIResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
@Slf4j
public class ClientRequestFBIApi {

    static final String host = "https://api.fbi.gov/@wanted?pageSize=1&page=";

    private final RestTemplate restTemplate;

    public ClientRequestFBIApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FBIResponseDTO requestApi(final Integer pageSize){
        try {
            log.info("2. Endpoint: {}", buildUri(pageSize));
            return restTemplate.getForObject(buildUri(pageSize), FBIResponseDTO.class);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private String buildUri(final Integer pageSize){
        return host.concat(String.valueOf(pageSize));
    }
}
