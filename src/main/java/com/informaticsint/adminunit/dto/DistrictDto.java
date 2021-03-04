package com.informaticsint.adminunit.dto;

import com.informaticsint.common.dto.DescriptionDto;
import com.informaticsint.starter.data.Dto;

import java.util.Objects;

/**
 * @author : sheharaj
 * Informatics International Limited. All Rights Reserved
 */

public class DistrictDto extends Dto {

    private String id;
    private String code;
    private String provinceCode;
    private String lifeCode;
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

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getLifeCode() {
        return lifeCode;
    }

    public void setLifeCode(String lifeCode) {
        this.lifeCode = lifeCode;
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

        DistrictDto that = (DistrictDto) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(code, that.code)) return false;
        if (!Objects.equals(provinceCode, that.provinceCode)) return false;
        if (!Objects.equals(lifeCode, that.lifeCode)) return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (provinceCode != null ? provinceCode.hashCode() : 0);
        result = 31 * result + (lifeCode != null ? lifeCode.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
