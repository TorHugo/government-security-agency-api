package com.api.government.security.agency.lib.dto.suspected;

import com.api.government.security.agency.lib.dto.BaseDTO;
import com.api.government.security.agency.lib.entity.ImageModel;
import lombok.Data;

@Data
public class ImageDTO extends BaseDTO{
    private String uri;
    private String description;

    public ImageDTO(final ImageModel model){
        this.uri = super.validateObjects(model.getUriImage());
        this.description = super.validateObjects(model.getCaption());
    }
}
