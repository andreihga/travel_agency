package org.com.sda.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ContinentDTO {
    @NotNull
    @NotEmpty
    private String continentName;

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }
}
