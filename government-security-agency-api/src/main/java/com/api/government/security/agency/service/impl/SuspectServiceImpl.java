package com.api.government.security.agency.service.impl;

import com.api.government.security.agency.lib.dto.suspected.BasicSuspectDTO;
import com.api.government.security.agency.lib.dto.suspected.SuspectDTO;
import com.api.government.security.agency.lib.entity.*;
import com.api.government.security.agency.lib.exception.impl.DataBaseException;
import com.api.government.security.agency.mapper.SuspectMapper;
import com.api.government.security.agency.repositories.*;
import com.api.government.security.agency.service.SuspectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class SuspectServiceImpl implements SuspectService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AliasesRepository aliasesRepository;

    @Autowired
    private CrimeRepository crimeRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CharacteristicRepository characteristicRepository;

    @Autowired
    private SuspectMapper mapper;

    @Override
    public SuspectDTO findSuspectById(final Long suspectId) {
        log.info("[1] - Find suspect by id. suspectId: {}", suspectId);
        final UserModel user = findUserById(suspectId);
        log.info("[2] - Find all aliases.");
        final List<AliasesModel> lsAliases = findAllBySuspectId(AliasesModel.class.getSimpleName(), user);
        log.info("[3] - Find all crimes.");
        final List<CrimeModel> lsCrimes = findAllBySuspectId(CrimeModel.class.getSimpleName(), user);
        log.info("[4] - Find all files.");
        final List<FileModel> lsFiles = findAllBySuspectId(FileModel.class.getSimpleName(), user);
        log.info("[5] - Find all images.");
        final List<ImageModel> lsImages = findAllBySuspectId(ImageModel.class.getSimpleName(), user);
        log.info("[6] - Find characteristic.");
        final CharacteristicModel characteristicModel = findCharacteristicBySuspectId(user);
        mapperToUserAtributes(user, lsAliases, lsCrimes, lsFiles, lsImages, characteristicModel);
        log.info("[7] - Mapping for response.");
        return mapper.mapperBySuspect(user, validateLaunderingMoney(user));
    }

    @Override
    public List<BasicSuspectDTO> findAllSuspectAML() {
        log.info("[1] - Recuperate all users.");
        final List<UserModel> lsUsers = userRepository.findAll();
        log.info("[2] - Recuperate all crimes.");
        lsUsers.forEach(user -> {
            List<CrimeModel> lsCrimes = crimeRepository.findAllByUserModel(user);
            user.setCrimes(lsCrimes);
        });
        log.info("[3] - Validation all users of anti money laundering.");
        final List<UserModel> lsAntiMoneyLaudering =
                Objects.requireNonNull(lsUsers).stream().filter(this::validateLaunderingMoney).toList();
        log.info("[4] - Mapping all users.");
        return mapper.mapperBasicSuspect(lsAntiMoneyLaudering);
    }

    private boolean validateLaunderingMoney(UserModel userModel) {
        List<String> keywords = Arrays.asList("laundering", "launder");
        if (Objects.nonNull(userModel.getDescriptionSuspect()) && containsKeyword(userModel.getDescriptionSuspect(), keywords))
            return true;
        if (Objects.nonNull(userModel.getCrimes()) && containsMatchingCrime(userModel.getCrimes(), keywords))
            return true;

        return false;
    }

    private boolean containsKeyword(String text, List<String> keywords) {
        String lowercaseText = text.toLowerCase();
        for (String keyword : keywords) {
            if (lowercaseText.contains(keyword))
                return true;
        }
        return false;
    }

    private boolean containsMatchingCrime(List<CrimeModel> crimes, List<String> keywords) {
        return crimes.stream()
                .filter(crime -> Objects.nonNull(crime.getNmCrime()) && Boolean.parseBoolean(crime.getNmCrime()))
                .anyMatch(crime -> containsKeyword(crime.getNmCrime(), keywords));
    }

    private void mapperToUserAtributes(final UserModel user, final List<AliasesModel> lsAliases, final List<CrimeModel> lsCrimes,
                                       final List<FileModel> lsFiles, final List<ImageModel> lsImages, final CharacteristicModel characteristicModel) {
        user.setAliases(lsAliases);
        user.setCrimes(lsCrimes);
        user.setFiles(lsFiles);
        user.setImages(lsImages);
        user.setCharacteristicModel(characteristicModel);
    }

    private UserModel findUserById(final Long suspectId){
        return userRepository.findById(suspectId).orElseThrow(() -> new DataBaseException("Suspect not found!"));
    }
    private CharacteristicModel findCharacteristicBySuspectId(final UserModel userModel) {
        return characteristicRepository.findByUserModel(userModel);
    }



    @SuppressWarnings("unchecked")
    private <T> List<T> findAllBySuspectId(String typeRepository, UserModel model) {
        switch (typeRepository) {
            case "ImageModel":
                return (List<T>) imageRepository.findAllByUserModel(model);
            case "FileModel":
                return (List<T>) fileRepository.findAllByUserModel(model);
            case "AliasesModel":
                return (List<T>) aliasesRepository.findAllByUserModel(model);
            case "CrimeModel":
                return (List<T>) crimeRepository.findAllByUserModel(model);
            default:
                throw new DataBaseException("Entity not found!");
        }
    }
}
