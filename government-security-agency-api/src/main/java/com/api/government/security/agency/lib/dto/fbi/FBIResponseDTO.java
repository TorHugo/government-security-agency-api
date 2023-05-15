package com.api.government.security.agency.lib.dto.fbi;

import lombok.Data;

import java.util.List;

@Data
public class FBIResponseDTO {
    private Long total;
    private List<ItemResponseDTO> items;
    private Integer page;
}
