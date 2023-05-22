package com.api.government.security.agency.mapper;

import com.api.government.security.agency.lib.dto.suspected.*;
import com.api.government.security.agency.lib.entity.AliasesModel;
import com.api.government.security.agency.lib.entity.FileModel;
import com.api.government.security.agency.lib.entity.UserModel;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class SuspectMapper {
    public SuspectDTO mapperBySuspect(final UserModel user, final Boolean antiMoneyLaudering) {
        SuspectDTO suspectDTO = new SuspectDTO();
        suspectDTO.setAntiMoneyLaudering(antiMoneyLaudering);
        suspectDTO.setCriminal(new CriminalDTO(user));
        suspectDTO.setAliases(user.getAliases()
                                .stream()
                                .map(AliasesModel::getNmAliase)
                                .collect(Collectors.toList()));
        suspectDTO.setCrimes(parseToCrimes(user.getDescriptionSuspect()));
        suspectDTO.setFiles(user.getFiles().stream()
                                .map(FileDTO::new)
                                .collect(Collectors.toList()));
        suspectDTO.setImages(user.getImages().stream()
                                .map(ImageDTO::new)
                                .collect(Collectors.toList()));
        suspectDTO.setCharacteristic(new CharacteristicDTO(user.getCharacteristicModel()));
        return suspectDTO;
    }

    private List<String> parseToCrimes(final String input) {
        if (input == null || input.isEmpty() || !input.contains(";")) {
            return Collections.singletonList(input);
        }

        return Arrays.stream(input.split(";"))
                .map(String::trim)
                .filter(trimmedPart -> !trimmedPart.isEmpty())
                .collect(Collectors.toList());
    }

    public List<BasicSuspectDTO> mapperBasicSuspect(final List<UserModel> lsAntiMoneyLaudering) {
        return lsAntiMoneyLaudering.stream()
                .map(user -> {
                    LinkDTO link = new LinkDTO(user.getId());
                    BasicSuspectDTO suspect = new BasicSuspectDTO(user);
                    suspect.setLinks(link);
                    return suspect;
                })
                .collect(Collectors.toList());
    }
}
