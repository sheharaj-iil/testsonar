package com.informaticsint.fixed.dto.gender;

import com.informaticsint.common.dto.DescriptionDto;

/**
 * @author : Steffan Devotta
 * @version : 0.1
 * @date : 2/23/21
 * @copyright : © Informatics International Limited. All Rights Reserved
 */
public class GenderDto {
    private String id;
    private String code;
    private DescriptionDto description;

    public GenderDto(String id, String code, DescriptionDto description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    public GenderDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DescriptionDto getDescription() {
        return description;
    }

    public void setDescription(DescriptionDto description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenderDto)) return false;

        GenderDto genderDto = (GenderDto) o;

        if (getId() != null ? !getId().equals(genderDto.getId()) : genderDto.getId() != null) return false;
        if (getCode() != null ? !getCode().equals(genderDto.getCode()) : genderDto.getCode() != null) return false;
        return getDescription() != null ? getDescription().equals(genderDto.getDescription()) : genderDto.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCode() != null ? getCode().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
