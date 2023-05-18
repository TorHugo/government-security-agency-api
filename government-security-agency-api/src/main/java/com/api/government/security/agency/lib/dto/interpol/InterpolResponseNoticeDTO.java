package com.api.government.security.agency.lib.dto.interpol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class InterpolResponseNoticeDTO {

    @JsonProperty("arrest_warrants")
    private List<CauseResponseDTO> arrestWarrantes;
    private String weight;
    private String forename;
    @JsonProperty("date_of_birth")
    private String dateOfBirth;
    private String uid;
    @JsonProperty("languages_spoken_ids")
    private List<String> languagesSpoken;
    private List<String> nationalities;
    private String height;
    @JsonProperty("sex_id")
    private String sex;
    @JsonProperty("country_of_birth_id")
    private String countyOfBirth;
    private String name;
    @JsonProperty("distinguishing_marks")
    private String distinguishingMarks;
    @JsonProperty("eyes_colors_id")
    private List<String> eyesColors;
    @JsonProperty("hairs_id")
    private List<String> hair;
    @JsonProperty("place_of_birth")
    private String placeOfBirth;
    @JsonProperty("_links")
    private LinkResponseDTO links;
}
