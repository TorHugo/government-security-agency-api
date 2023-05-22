package com.api.government.security.agency.service.impl;

import com.api.government.security.agency.lib.entity.*;
import com.api.government.security.agency.repositories.*;
import com.api.government.security.agency.service.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public abstract class RequestServiceImpl implements RequestService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CharacteristicRepository characteristicRepository;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private AliasesRepository aliasesRepository;
    @Autowired
    private CrimeRepository crimeRepository;
    @Override
    public Integer requestForApi() { return 0; }
    @Override
    public <T> void saveToDataBase(List<T> items, String typeClass) {
        switch (typeClass){
            case "ImageModel":
                if (items.isEmpty())
                    return;
                log.info("3.3. Save to Image in the database.");
                List<ImageModel> lsImage = items.stream()
                        .map(item -> (ImageModel) item)
                        .collect(Collectors.toList());
                imageRepository.saveAll(lsImage);
                break;
            case "FileModel":
                log.info("3.4. Save to File in the database.");
                List<FileModel> lsFiles = items.stream()
                        .map(item -> (FileModel) item)
                        .collect(Collectors.toList());
                fileRepository.saveAll(lsFiles);
                break;
            case "LanguageModel":
                log.info("3.5. Save to Language in the database.");
                List<LanguageModel> lsLanguages = items.stream()
                        .map(item -> (LanguageModel) item)
                        .collect(Collectors.toList());
                languageRepository.saveAll(lsLanguages);
                break;
            case "AliasesModel":
                log.info("3.6. Save to Aliases in the database.");
                List<AliasesModel> lsAliases = items.stream()
                        .map(item -> (AliasesModel) item)
                        .collect(Collectors.toList());
                aliasesRepository.saveAll(lsAliases);
                break;
            case "CrimeModel":
                log.info("3.7. Save to Crime in the database.");
                List<CrimeModel> lsCrimes = items.stream()
                        .map(item -> (CrimeModel) item)
                        .collect(Collectors.toList());
                crimeRepository.saveAll(lsCrimes);
                break;
        }
    }
    @Override
    public <T> void saveToDataBase(final T item){
        if (item instanceof UserModel) {
            log.info("3.1. Save to User in the database.");
            userRepository.save((UserModel) item);
        } else if (item instanceof CharacteristicModel){
            log.info("3.2. Save to Characteristic in the database.");
            characteristicRepository.save((CharacteristicModel) item);
        }
    }
}
