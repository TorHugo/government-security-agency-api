package com.api.government.security.agency.mapper;

import com.api.government.security.agency.lib.dto.fbi.ItemResponseDTO;
import com.api.government.security.agency.lib.dto.interpol.InterpolResponseNoticeDTO;
import com.api.government.security.agency.lib.dto.interpol.ThumbnailResponseDTO;
import com.api.government.security.agency.lib.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class MapperToRequest {

    public <T> UserModel mapperToUser(final T item){
        UserModel entity = new UserModel();

        if (item instanceof ItemResponseDTO){
            final ItemResponseDTO itemResponse = (ItemResponseDTO) item;

            entity.setUid(itemResponse.getUid());
            entity.setDateOfPublication(itemResponse.getPublication());
            entity.setReward(itemResponse.getRewardText());
            entity.setDescriptionSuspect(Objects.isNull(itemResponse.getCaution()) ? null : itemResponse.getCaution().replaceAll("<p>|</p>", ""));
            entity.setTitlePublication(itemResponse.getTitle());
            entity.setCriminalClassification(itemResponse.getPersonClassification());
        } else {
            final InterpolResponseNoticeDTO itemResponse = (InterpolResponseNoticeDTO) item;
            entity.setTitlePublication(itemResponse.getName().concat(" " + itemResponse.getForename()));
            entity.setCriminalClassification("Main or Red");
        }

        return entity;
    }
    public <T> CharacteristicModel mapperToCharacteristic(final T item, final UserModel userModel){
        CharacteristicModel entity = new CharacteristicModel();

        if (item instanceof ItemResponseDTO) {
            final ItemResponseDTO itemResponse = (ItemResponseDTO) item;

            entity.setUserModel(userModel);
            entity.setScarsAndMarks(itemResponse.getScarsAndMarks());
            entity.setNmSex(itemResponse.getSex());
            entity.setTypeHair(itemResponse.getHair());
            entity.setWeight(itemResponse.getWeight());
            entity.setHeight(itemResponse.getHeight());
            entity.setEthnicity(itemResponse.getRaceRaw());
            entity.setColorEye(itemResponse.getEyesRaw());
            entity.setNationality(itemResponse.getNationality());
            entity.setBirthPlace(itemResponse.getPlaceOfBirth());
            entity.setAge(String.valueOf(Objects.isNull(itemResponse.getAgeMax()) ? itemResponse.getAgeMin() : itemResponse.getAgeMax()));

        } else {
            final InterpolResponseNoticeDTO itemResponse = (InterpolResponseNoticeDTO) item;

            entity.setUserModel(userModel);
            entity.setWeight(itemResponse.getWeight());
            entity.setNmSex(itemResponse.getSex());
            entity.setNationality(Objects.isNull(itemResponse.getNationalities()) ? null : itemResponse.getNationalities().get(0));
            entity.setColorEye(Objects.isNull(itemResponse.getEyesColors()) ? null : itemResponse.getEyesColors().get(0));
            entity.setHeight(itemResponse.getHeight());
            entity.setBirthPlace(itemResponse.getPlaceOfBirth());
            entity.setScarsAndMarks(itemResponse.getDistinguishingMarks());
            entity.setTypeHair(Objects.isNull(itemResponse.getHair()) ? null : itemResponse.getHair().get(0));
        }

        return entity;
    }
    public <T> List<FileModel> mapperToFiles(final T item, final UserModel user){
        List<FileModel> entity = new ArrayList<>();
        FileModel model = new FileModel();

        if (item instanceof ItemResponseDTO) {
            final ItemResponseDTO itemResponse = (ItemResponseDTO) item;

            if (Objects.isNull(itemResponse.getFiles()))
                return Collections.emptyList();

            itemResponse.getFiles().forEach(file -> {
                model.setNmFile(file.getName());
                model.setFileUri(file.getUrl());
                model.setUserModel(user);

                entity.add(model);
            });
        } else {
            final InterpolResponseNoticeDTO itemResponse = (InterpolResponseNoticeDTO) item;
            model.setUserModel(user);
            model.setNmFile(itemResponse.getLinks().getSelf().getClass().getSimpleName());
            model.setFileUri(itemResponse.getLinks().getSelf().getHref());

            entity.add(model);
        }

        return entity;
    }

    public <T> List<ImageModel> mapperToImages(final T item, final UserModel user) {
        List<ImageModel> entity = new ArrayList<>();
        ImageModel model = new ImageModel();

        if (item instanceof ItemResponseDTO) {
            final ItemResponseDTO itemResponse = (ItemResponseDTO) item;
            if (Objects.isNull(itemResponse.getImages()))
                return Collections.emptyList();

            itemResponse.getImages().forEach(image -> {
                model.setUserModel(user);
                model.setUriImage(image.getOriginal());
                model.setCaption(image.getCaption());

                entity.add(model);
            });
        } else {
            final InterpolResponseNoticeDTO itemResponse = (InterpolResponseNoticeDTO) item;
            model.setUserModel(user);
            model.setCaption(Objects.nonNull(itemResponse.getLinks().getThumbnail()) ? itemResponse.getLinks().getThumbnail().getClass().getSimpleName() : "@none");
            model.setUriImage(verifyThumbnail(itemResponse.getLinks().getThumbnail()));

            entity.add(model);
        }

        return entity;
    }

    private String verifyThumbnail(ThumbnailResponseDTO thumbnail) {
        if (Objects.isNull(thumbnail))
            return null;
        if (Objects.isNull(thumbnail.getHref()))
            return null;

        return thumbnail.getHref();
    }

    public <T> List<LanguageModel> mapperToLanguage(final T item, final UserModel userModel) {
        List<LanguageModel> entity = new ArrayList<>();
        LanguageModel model = new LanguageModel();

        if (item instanceof ItemResponseDTO) {
            final ItemResponseDTO itemResponse = (ItemResponseDTO) item;
            if (Objects.isNull(itemResponse.getLanguages()))
                return Collections.emptyList();

            itemResponse.getLanguages().forEach(language -> {
                model.setNmLanguage(language);
                model.setUserModel(userModel);

                entity.add(model);
            });
        } else {
            final InterpolResponseNoticeDTO itemResponse = (InterpolResponseNoticeDTO) item;
            if (Objects.isNull(itemResponse.getLanguagesSpoken()))
                return Collections.emptyList();

            itemResponse.getLanguagesSpoken().forEach(language -> {
                model.setNmLanguage(language);
                model.setUserModel(userModel);

                entity.add(model);
            });
        }

        return entity;
    }
    public <T> List<AliasesModel> mapperToAlias(final T item, final UserModel userModel) {
        List<AliasesModel> entity = new ArrayList<>();

        if (item instanceof ItemResponseDTO) {
            final ItemResponseDTO itemResponse = (ItemResponseDTO) item;
            if (Objects.isNull(itemResponse.getAliases()))
                return Collections.emptyList();

            itemResponse.getAliases().forEach(alias -> {
                AliasesModel model = new AliasesModel();
                model.setNmAliase(alias);
                model.setUserModel(userModel);

                entity.add(model);
            });
        }
        return entity;
    }
    public <T> List<CrimeModel> mapperToCrime(final T item, final UserModel userModel) {
        List<CrimeModel> entity = new ArrayList<>();
        CrimeModel model = new CrimeModel();

        if (item instanceof ItemResponseDTO) {
            final ItemResponseDTO itemResponse = (ItemResponseDTO) item;
            if (Objects.isNull(itemResponse.getDescription()))
                return Collections.emptyList();

            model.setUserModel(userModel);
            model.setNmCrime(itemResponse.getDescription());

            entity.add(model);
        } else {
            final InterpolResponseNoticeDTO itemResponse = (InterpolResponseNoticeDTO) item;
            if (itemResponse.getArrestWarrantes().isEmpty())
                return Collections.emptyList();

            itemResponse.getArrestWarrantes().forEach(crime -> {
                model.setUserModel(userModel);
                model.setNmCrime(crime.getCharge());

                entity.add(model);
            });
        }
        return entity;
    }
}
