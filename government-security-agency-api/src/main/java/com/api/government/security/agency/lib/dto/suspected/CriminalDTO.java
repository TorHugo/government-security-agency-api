package com.api.government.security.agency.lib.dto.suspected;

import com.api.government.security.agency.lib.dto.BaseDTO;
import com.api.government.security.agency.lib.entity.UserModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CriminalDTO extends BaseDTO {
    @JsonProperty("name_or_title")
    private String nameOrTitle;
    private String description;
    private String reward;

    public CriminalDTO(final UserModel userModel){
        this.nameOrTitle = userModel.getTitlePublication();
        this.description = super.validateObjects(userModel.getDescriptionSuspect());
        this.reward = super.validateObjects(userModel.getReward());
    }


}
