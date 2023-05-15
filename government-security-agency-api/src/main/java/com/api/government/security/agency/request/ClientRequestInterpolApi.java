package com.api.government.security.agency.request;

import com.api.government.security.agency.lib.dto.interpol.InterpolResponseDTO;
import com.api.government.security.agency.lib.dto.interpol.InterpolResponseNoticeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ClientRequestInterpolApi {
    static final String host = "https://ws-public.interpol.int/notices/v1/red?&resultPerPage=20&page=";

    private final RestTemplate restTemplate;

    public ClientRequestInterpolApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public InterpolResponseDTO requestApi(final Integer pageSize){
        try {
            log.info("2. Endpoint: {}", buildUri(pageSize));
            return restTemplate.getForObject(buildUri(pageSize), InterpolResponseDTO.class);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public InterpolResponseNoticeDTO requestApiForNotice(final String endpoint){
        try {
            log.info("3.1. Endpoint: {}", endpoint);
            return restTemplate.getForObject(endpoint, InterpolResponseNoticeDTO.class);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private String buildUri(final Integer pageSize){
        return host.concat(String.valueOf(pageSize));
    }

}
