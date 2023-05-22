package com.api.government.security.agency.lib.dto.suspected;

import com.api.government.security.agency.lib.dto.BaseDTO;
import com.api.government.security.agency.lib.entity.CharacteristicModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Objects;

@Data
public class CharacteristicDTO extends BaseDTO {
    private String age;
    @JsonProperty("birth_of_place")
    private String birthOfPlace;
    @JsonProperty("color_eye")
    private String colorEye;
    private String ethnicity;
    private String nationatily;
    private String sex;
    private String marks;
    private String hair;

    public CharacteristicDTO(final CharacteristicModel model){
        this.age = super.validateObjects(model.getAge());
        this.birthOfPlace = super.validateObjects(model.getBirthPlace());
        this.colorEye = super.validateObjects(model.getColorEye());
        this.ethnicity = super.validateObjects(model.getEthnicity());
        this.nationatily = super.validateObjects(model.getNationality());
        this.sex = super.validateObjects(model.getNmSex());
        this.marks = super.validateObjects(model.getScarsAndMarks());
        this.hair = super.validateObjects(model.getTypeHair());
    }
}
