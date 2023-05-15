package com.api.government.security.agency.lib.dto.interpol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NoticeResponseDTO {
    private String forename;
    @JsonProperty("date_of_birth")
    private String dateOfBirth;
    @JsonProperty("entity_id")
    private String entityId;
    private List<String> nationalities;
    private String name;
    @JsonProperty("_links")
    private LinkResponseDTO links;
}
