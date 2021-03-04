package com.informaticsint.adminunit.dto;

import com.informaticsint.common.dto.DescriptionDto;
import com.informaticsint.starter.data.Dto;

import java.util.Objects;

/**
 * @author : sheharaj
 * Informatics International Limited. All Rights Reserved
 */

public class ProvinceDto extends Dto {

    private String id;
    private String code;
    private String abbreviationCode;
    private DescriptionDto description;

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

    public String getAbbreviationCode() {
        return abbreviationCode;
    }

    public void setAbbreviationCode(String abbreviationCode) {
        this.abbreviationCode = abbreviationCode;
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
        if (o == null || getClass() != o.getClass()) return false;

        ProvinceDto that = (ProvinceDto) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(code, that.code)) return false;
        if (!Objects.equals(abbreviationCode, that.abbreviationCode))
            return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (abbreviationCode != null ? abbreviationCode.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
