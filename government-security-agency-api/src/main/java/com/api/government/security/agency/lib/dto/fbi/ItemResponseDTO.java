package com.api.government.security.agency.lib.dto.fbi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ItemResponseDTO {
    private List<FileResponseDTO> files;
    @JsonProperty("age_range")
    private String ageRange;
    private String uid;
    private String weight;
    private List<String> occupations;
    @JsonProperty("field_offices")
    private List<String> fieldOffices;
    private List<String> locations;
    @JsonProperty("reward_text")
    private String rewardText;
    private String sex;
    private String hair;
    private String ncic;
    @JsonProperty("dates_of_birth_used")
    private List<String> datesOfBirthUsed;
    private String caution;
    private String nationality;
    @JsonProperty("age_min")
    private Integer ageMin;
    @JsonProperty("age_max")
    private Integer ageMax;
    @JsonProperty("scars_and_marks")
    private String scarsAndMarks;
    private List<String> subjects;
    private List<String> aliases;
    @JsonProperty("race_raw")
    private String raceRaw;
    private String publication;
    private String title;
    @JsonProperty("hair_raw")
    private String hairRaw;
    private List<String> languages;
    private String description;
    @JsonProperty("person_classification")
    private String personClassification;
    private List<ImageResponseDTO> images;
    private String race;
    @JsonProperty("place_of_birth")
    private String placeOfBirth;
    @JsonProperty("@id")
    private String id;
    @JsonProperty("eyes_raw")
    private String eyesRaw;
}
