package com.informaticsint.fixed.dto.civilstatus;

import com.informaticsint.common.dto.DescriptionDto;
import com.informaticsint.starter.data.Dto;

/**
 * @author : Steffan Devotta
 * @version : 0.1
 * @date : 2/23/21
 * @copyright : © Informatics International Limited. All Rights Reserved
 */
public class CivilStatusDto extends Dto {
    private String id;
    private String code;
    private DescriptionDto description;

    public CivilStatusDto(String id, String code, DescriptionDto description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    public CivilStatusDto() {
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
        if (!(o instanceof CivilStatusDto)) return false;

        CivilStatusDto that = (CivilStatusDto) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getCode() != null ? !getCode().equals(that.getCode()) : that.getCode() != null) return false;
        return getDescription() != null ? getDescription().equals(that.getDescription()) : that.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCode() != null ? getCode().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
