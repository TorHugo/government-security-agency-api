package com.api.government.security.agency.lib.dto.interpol;

import lombok.Data;

import java.util.List;

@Data
public class EmbeddedResponseDTO {

    private List<NoticeResponseDTO> notices;
}
