package com.api.government.security.agency.service.impl;

import com.api.government.security.agency.lib.dto.fbi.ItemResponseDTO;
import com.api.government.security.agency.lib.dto.interpol.InterpolResponseDTO;
import com.api.government.security.agency.lib.dto.interpol.InterpolResponseNoticeDTO;
import com.api.government.security.agency.lib.entity.UserModel;
import com.api.government.security.agency.mapper.MapperToRequest;
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
    static final Integer endCount = 4;
    static Integer countRecords = 0;

    @Autowired
    private MapperToRequest mapper;

    @Override
    public Integer requestForApi() {
        IntStream.range(count, endCount).forEach(item -> {
            log.info("1. Counting number of records found!");
            final InterpolResponseDTO response = request.requestApi(item);

            log.info("3. Filtering notice.");
            response.getEmbedded().getNotices().forEach(notice -> {
                final String hrefNotice = notice.getLinks().getSelf().getHref();
                final InterpolResponseNoticeDTO interpolResponse = request.requestApiForNotice(hrefNotice);

                savingInTheDatabase(interpolResponse,
                        mapper.mapperToUser(interpolResponse));
            });

            countRecords += response.getEmbedded().getNotices().size();
        });

        return countRecords;
    }

    private void savingInTheDatabase(final InterpolResponseNoticeDTO itemResponse, final UserModel userModel){
        super.saveToDataBase(userModel);
        super.saveToDataBase(mapper.mapperToCharacteristic(itemResponse, userModel));
        super.saveToDataBase(mapper.mapperToImages(itemResponse, userModel), "ImageModel");
        super.saveToDataBase(mapper.mapperToFiles(itemResponse, userModel), "FileModel");
        super.saveToDataBase(mapper.mapperToLanguage(itemResponse, userModel), "LanguageModel");
        super.saveToDataBase(mapper.mapperToAlias(itemResponse, userModel), "AliasesModel");
        super.saveToDataBase(mapper.mapperToCrime(itemResponse, userModel), "CrimeModel");
    }
}
