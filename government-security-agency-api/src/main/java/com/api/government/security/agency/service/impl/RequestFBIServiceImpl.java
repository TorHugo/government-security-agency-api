package com.api.government.security.agency.service.impl;

import com.api.government.security.agency.MapperToFBIRequest;
import com.api.government.security.agency.lib.dto.fbi.FBIResponseDTO;
import com.api.government.security.agency.lib.dto.fbi.FileResponseDTO;
import com.api.government.security.agency.lib.dto.fbi.ItemResponseDTO;
import com.api.government.security.agency.lib.entity.*;
import com.api.government.security.agency.repositories.*;
import com.api.government.security.agency.request.ClientRequestFBIApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

@Service
@Slf4j
public class RequestFBIServiceImpl extends RequestServiceImpl{

    @Autowired
    private ClientRequestFBIApi request;

    static final Integer count = 1;
    static final Integer endCount = 50;
    static Integer countRecords = 0;

    @Autowired
    private MapperToFBIRequest mapper;

    @Override
    public Integer requestForApi() {

        IntStream.range(count, endCount).forEach(item -> {
            log.info("1. Counting number of records found!");
            final FBIResponseDTO response = request.requestApi(item);

            log.info("3. Save in the DataBase.");
            response.getItems().forEach(itemResponse -> {
                savingInTheDatabase(itemResponse,
                        mapper.mapperToUser(itemResponse));
            });
        });

        return countRecords;
    }

    private void savingInTheDatabase(final ItemResponseDTO itemResponse, final UserModel userModel){
        this.saveToDataBase(userModel);
        this.saveToDataBase(mapper.mapperToCharacteristic(itemResponse, userModel));
        this.saveToDataBase(mapper.mapperToImages(itemResponse, userModel));
        this.saveToDataBase(mapper.mapperToFiles(itemResponse, userModel));
        this.saveToDataBase(mapper.mapperToLanguage(itemResponse, userModel));
        this.saveToDataBase(mapper.mapperToAlias(itemResponse, userModel));
        this.saveToDataBase(mapper.mapperToCrime(itemResponse, userModel));
    }
}
