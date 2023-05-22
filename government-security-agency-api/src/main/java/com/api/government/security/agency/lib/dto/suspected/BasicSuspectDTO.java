package com.api.government.security.agency.lib.dto.suspected;

import com.api.government.security.agency.lib.dto.BaseDTO;
import com.api.government.security.agency.lib.entity.UserModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BasicSuspectDTO extends BaseDTO {
    @JsonProperty("suspect_id")
    private Long suspectId;
    @JsonProperty("name_or_title")
    private String name;
    private String classification;
    private LinkDTO links;

    public BasicSuspectDTO(final UserModel user){
        this.suspectId = user.getId();
        this.name = validateObjects(user.getDescriptionSuspect());
        this.classification = validateObjects(user.getCriminalClassification());
    }
}
