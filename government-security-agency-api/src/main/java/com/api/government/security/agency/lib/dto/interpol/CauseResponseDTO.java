package com.api.government.security.agency.lib.dto.interpol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CauseResponseDTO {
    @JsonProperty("issuing_country_id")
    private String issuingCountry;
    private String charge;
}
