package com.api.government.security.agency.lib.dto;

import lombok.Data;

import java.util.Objects;

@Data
public abstract class BaseDTO {
    public String validateObjects(final String atribute){
        return Objects.nonNull(atribute) ? atribute : "@none";
    }
}
