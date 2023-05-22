package com.api.government.security.agency.lib.dto.suspected;

import com.api.government.security.agency.lib.dto.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SuspectDTO extends BaseDTO {
    @JsonProperty("anti_money_laundering")
    private Boolean antiMoneyLaudering;
    private CriminalDTO criminal;
    private List<String> aliases;
    private List<String> crimes;
    private List<FileDTO> files;
    private List<ImageDTO> images;
    private CharacteristicDTO characteristic;
}
