package com.api.government.security.agency.lib.dto.suspected;

import com.api.government.security.agency.lib.dto.BaseDTO;
import com.api.government.security.agency.lib.entity.FileModel;
import lombok.Data;

@Data
public class FileDTO extends BaseDTO {
    private String uri;
    private String language;

    public FileDTO(final FileModel entity){
        this.uri = entity.getFileUri();
        this.language = super.validateObjects(entity.getNmFile());
    }
}
