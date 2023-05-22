package com.api.government.security.agency.lib.dto.suspected;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LinkDTO {
    @JsonProperty("@method")
    private String method;
    private String href;

    public LinkDTO(final Long suspectId){
        this.method = "find_by_id";
        this.href = "http://localhost:8080/api/suspect/".concat(String.valueOf(suspectId));
    }
}
