package com.api.government.security.agency.service.factory;

import com.api.government.security.agency.service.impl.RequestFBIServiceImpl;
import com.api.government.security.agency.service.impl.RequestInterpolServiceImpl;
import com.api.government.security.agency.service.impl.RequestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FactoryRequest {

    private final RequestFBIServiceImpl requestFBIService;
    private final RequestInterpolServiceImpl requestInterpolService;

    public Integer requestService(final String agence){
        RequestServiceImpl requestServiceImpl = null;

        switch (agence){
            case "fbi":
                requestServiceImpl = requestFBIService;
                return requestServiceImpl.requestForApi();
            case "interpol":
                requestServiceImpl = requestInterpolService;
                return requestServiceImpl.requestForApi();

            default:
                return null;
        }
    }
}
