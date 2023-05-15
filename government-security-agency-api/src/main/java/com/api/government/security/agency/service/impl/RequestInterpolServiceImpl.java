package com.api.government.security.agency.service.impl;

import com.api.government.security.agency.lib.dto.interpol.InterpolResponseDTO;
import com.api.government.security.agency.lib.dto.interpol.InterpolResponseNoticeDTO;
import com.api.government.security.agency.request.ClientRequestInterpolApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
@Slf4j
public class RequestInterpolServiceImpl extends RequestServiceImpl{

    @Autowired
    private ClientRequestInterpolApi request;

    static final Integer count = 0;
    static final Integer endCount = 8;
    static Integer countRecords = 0;

    @Override
    public Integer requestForApi() {
        IntStream.range(count, endCount).forEach(item -> {
            log.info("1. Counting number of records found!");
            final InterpolResponseDTO response = request.requestApi(item);

            log.info("3. Filtering notice.");
            response.getEmbedded().getNotices().forEach(notice -> {
                final String hrefNotice = notice.getLinks().getSelf().getHref();
                final InterpolResponseNoticeDTO interpolResponse = request.requestApiForNotice(hrefNotice);

                System.out.println(interpolResponse.toString());
            });

            //TODO implementar método para salvar as entidades no banco de dados.


//            log.info("4. Adding records found. Size: {}", lsLauderingMoney.size());
            countRecords += response.getEmbedded().getNotices().size();
        });

        return countRecords;
    }
}
