package com.api.government.security.agency.service.impl;

import com.api.government.security.agency.lib.entity.*;
import com.api.government.security.agency.repositories.*;
import com.api.government.security.agency.service.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public <T> void saveToDataBase(List<T> items) {
        items.forEach(item -> {
            switch (item.getClass().getSimpleName()){
                case "ImageModel":
                    log.info("3.3. Save to Image in the database.");
                    imageRepository.save((ImageModel)item);
                    break;
                case "FileModel":
                    log.info("3.4. Save to File in the database.");
                    fileRepository.save((FileModel) item);
                    break;
                case "LanguageModel":
                    log.info("3.5. Save to Language in the database.");
                    languageRepository.save((LanguageModel) item);
                    break;
                case "AliasesModel":
                    log.info("3.6. Save to Aliases in the database.");
                    aliasesRepository.save((AliasesModel) item);
                    break;
                case "CrimeModel":
                    log.info("3.7. Save to Crime in the database.");
                    crimeRepository.save((CrimeModel) item);
                    break;
            }
        });
    }
    @Override
    public void saveToDataBase(final UserModel user){
        log.info("3.1. Save to User in the database.");
        userRepository.save(user);
    }
    @Override
    public void saveToDataBase(final CharacteristicModel model){
        log.info("3.2. Save to Characteristic in the database.");
        characteristicRepository.save(model);
    }
}
