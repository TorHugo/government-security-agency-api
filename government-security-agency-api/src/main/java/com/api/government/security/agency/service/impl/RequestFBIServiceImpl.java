package com.api.government.security.agency.service.impl;

import com.api.government.security.agency.lib.dto.fbi.FBIResponseDTO;
import com.api.government.security.agency.lib.dto.fbi.ItemResponseDTO;
import com.api.government.security.agency.lib.entity.CharacteristicModel;
import com.api.government.security.agency.lib.entity.UserModel;
import com.api.government.security.agency.repositories.CharacteristicRepository;
import com.api.government.security.agency.repositories.UserRepository;
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
    static final Integer endCount = 1;
    static Integer countRecords = 0;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CharacteristicRepository characteristicRepository;

    @Override
    public Integer requestForApi() {

        IntStream.range(count, endCount).forEach(item -> {
            log.info("1. Counting number of records found!");
            final FBIResponseDTO response = request.requestApi(item);

//            log.info("3. Filtering money laundering.");
//            final List<ItemResponseDTO> lsLauderingMoney =
//                    Objects.requireNonNull(response.getItems()).stream().filter(this::validateLaunderingMoney).toList();

            log.info("3. Save in the DataBase.");
            response.getItems().forEach(itemResponse -> {
                final UserModel userModel = mapperToUser(itemResponse);

                saveToDataBase(userModel);
                saveToDataBase(mapperToCharacteristic(itemResponse, userModel));
            });
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

    private UserModel mapperToUser(final ItemResponseDTO item){
        UserModel entity = new UserModel();
        entity.setUid(item.getUid());
        entity.setDateOfPublication(item.getPublication());
        entity.setReward(item.getRewardText());
        entity.setDescriptionSuspect(item.getDescription());
        entity.setTitlePublication(item.getTitle());
        entity.setCriminalClassification(item.getPersonClassification());

        return entity;
    }

    private CharacteristicModel mapperToCharacteristic(final ItemResponseDTO item, final UserModel userModel){
        CharacteristicModel entity = new CharacteristicModel();
        entity.setUserModel(userModel);
        entity.setNmSex(item.getSex());
        entity.setTypeHair(item.getHair());
        entity.setWeight(item.getWeight());
        entity.setHeight(item.getHeight());
        entity.setEthnicity(item.getRaceRaw());
        entity.setColorEye(item.getEyesRaw());
        entity.setNationality(item.getNationality());
        entity.setBirthPlace(item.getPlaceOfBirth());
        entity.setAge(String.valueOf(Objects.isNull(item.getAgeMax()) ? item.getAgeMin() : item.getAgeMax()));

        return entity;
    }

    private void saveToDataBase(final UserModel user){
        log.info("3.1. Save to User in the database.");
        userRepository.save(user);
    }

    private void saveToDataBase(final CharacteristicModel model){
        log.info("3.2. Save to Characteristic in the database.");
        characteristicRepository.save(model);
    }

}
