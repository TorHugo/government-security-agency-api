package com.api.government.security.agency.mapper;

import com.api.government.security.agency.lib.dto.fbi.ItemResponseDTO;
import com.api.government.security.agency.lib.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class MapperToFBIRequest {

    public UserModel mapperToUser(final ItemResponseDTO item){
        UserModel entity = new UserModel();
        entity.setUid(item.getUid());
        entity.setDateOfPublication(item.getPublication());
        entity.setReward(item.getRewardText());
        entity.setDescriptionSuspect(Objects.isNull(item.getCaution()) ? null : item.getCaution().replaceAll("<p>|</p>", ""));
        entity.setTitlePublication(item.getTitle());
        entity.setCriminalClassification(item.getPersonClassification());

        return entity;
    }
    public CharacteristicModel mapperToCharacteristic(final ItemResponseDTO item, final UserModel userModel){
        CharacteristicModel entity = new CharacteristicModel();
        entity.setUserModel(userModel);
        entity.setScarsAndMarks(item.getScarsAndMarks());
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
    public List<FileModel> mapperToFiles(final ItemResponseDTO item, final UserModel user){
        List<FileModel> entity = new ArrayList<>();
        if (Objects.isNull(item.getFiles()))
            return Collections.emptyList();

        item.getFiles().forEach(file -> {
            FileModel model = new FileModel();
            model.setNmFile(file.getName());
            model.setFileUri(file.getUrl());
            model.setUserModel(user);

            entity.add(model);
        });
        return entity;
    }

    public List<ImageModel> mapperToImages(ItemResponseDTO item, UserModel userModel) {
        List<ImageModel> entity = new ArrayList<>();
        if (Objects.isNull(item.getImages()))
            return Collections.emptyList();

        item.getImages().forEach(image -> {
            ImageModel model = new ImageModel();
            model.setUriImage(image.getOriginal());
            model.setCaption(image.getCaption());
            model.setUserModel(userModel);

            entity.add(model);
        });
        return entity;
    }
    public List<LanguageModel> mapperToLanguage(ItemResponseDTO item, UserModel userModel) {
        List<LanguageModel> entity = new ArrayList<>();
        if (Objects.isNull(item.getLanguages()))
            return Collections.emptyList();

        item.getLanguages().forEach(language -> {
            LanguageModel model = new LanguageModel();
            model.setNmLanguage(language);
            model.setUserModel(userModel);

            entity.add(model);
        });
        return entity;
    }
    public List<AliasesModel> mapperToAlias(ItemResponseDTO item, UserModel userModel) {
        List<AliasesModel> entity = new ArrayList<>();
        if (Objects.isNull(item.getAliases()))
            return Collections.emptyList();

        item.getAliases().forEach(alias -> {
            AliasesModel model = new AliasesModel();
            model.setNmAliase(alias);
            model.setUserModel(userModel);

            entity.add(model);
        });
        return entity;
    }
    public List<CrimeModel> mapperToCrime(ItemResponseDTO item, UserModel userModel) {
        List<CrimeModel> entity = new ArrayList<>();

        final List<String> crimes = parseToCrimes(item.getDescription());
        if (Objects.isNull(crimes))
            return Collections.emptyList();

        crimes.forEach(crime -> {
            CrimeModel model = new CrimeModel();
            model.setNmCrime(crime);
            model.setUserModel(userModel);

            entity.add(model);
        });
        return entity;
    }
    private List<String> parseToCrimes(final String input){
        if (input == null || input.isEmpty() ||  !input.contains(";")) {
            log.info("Input string is null or empty. Returning empty list.");
            return Collections.singletonList(input);
        }

        String[] parts = input.split(";");
        log.info("Number of parts: " + parts.length);
        List<String> substrings = new ArrayList<>(parts.length);
        for (String part : parts) {
            String trimmedPart = part.trim();
            log.info("Trimmed part: " + trimmedPart);
            if (!trimmedPart.isEmpty()) {
                substrings.add(trimmedPart);
                log.info("Added part to substrings: " + trimmedPart);
            }
        }
        log.info("Final substrings: " + substrings);
        return substrings;
    }
}
