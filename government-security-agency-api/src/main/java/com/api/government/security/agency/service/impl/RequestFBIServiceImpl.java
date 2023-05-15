package com.api.government.security.agency.service.impl;

import com.api.government.security.agency.lib.dto.fbi.FBIResponseDTO;
import com.api.government.security.agency.lib.dto.fbi.ItemResponseDTO;
import com.api.government.security.agency.request.ClientRequestFBIApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Service
@Slf4j
public class RequestFBIServiceImpl extends RequestServiceImpl{

    @Autowired
    private ClientRequestFBIApi request;

    static final Integer count = 0;
    static final Integer endCount = 25;
    static Integer countRecords = 0;

    @Override
    public Integer requestForApi() {

        IntStream.range(count, endCount).forEach(item -> {
            log.info("1. Counting number of records found!");
            final FBIResponseDTO response = request.requestApi(item);

            log.info("3. Filtering money laundering.");
            final List<ItemResponseDTO> lsLauderingMoney =
                    Objects.requireNonNull(response.getItems()).stream().filter(this::validateLaunderingMoney).toList();

            //TODO implementar método para salvar as entidades no banco de dados.


            log.info("4. Adding records found. Size: {}", lsLauderingMoney.size());
            countRecords += lsLauderingMoney.size();
        });

        return countRecords;
    }

    private Boolean validateLaunderingMoney(final ItemResponseDTO item){
        if (Objects.nonNull(item.getDescription())
                && item.getDescription().toLowerCase().contains("laundering"))
            return true;

        if (Objects.nonNull(item.getDescription())
                && item.getDescription().toLowerCase().contains("launder"))
            return true;

        if (Objects.nonNull(item.getCaution())
                && item.getCaution().toLowerCase().contains("laundering"))
            return true;

        if (Objects.nonNull(item.getCaution())
                && item.getCaution().toLowerCase().contains("launder"))
            return true;

        return false;
    }

}
