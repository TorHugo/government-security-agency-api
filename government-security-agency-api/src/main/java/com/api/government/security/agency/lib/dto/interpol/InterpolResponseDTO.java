package com.api.government.security.agency.lib.dto.interpol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InterpolResponseDTO {

    private Integer total;
    @JsonProperty("_embedded")
    private EmbeddedResponseDTO embedded;
}
