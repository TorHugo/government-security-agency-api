package com.api.government.security.agency.lib.dto.interpol;

import lombok.Data;

@Data
public class LinkResponseDTO {
    private SelfResponseDTO self;
    private ImageResponseDTO images;
    private ThumbnailResponseDTO thumbnail;
}
