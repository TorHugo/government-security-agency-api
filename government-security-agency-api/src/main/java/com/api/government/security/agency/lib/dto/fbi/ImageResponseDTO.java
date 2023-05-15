package com.api.government.security.agency.lib.dto.fbi;

import lombok.Data;

@Data
public class ImageResponseDTO {
    private String large;
    private String caption;
    private String thumb;
    private String original;
}
