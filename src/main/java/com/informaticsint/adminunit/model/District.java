package com.informaticsint.adminunit.model;

import com.informaticsint.common.model.DescriptionModel;
import com.informaticsint.starter.util.audit.AuditModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author : sheharaj
 * Informatics International Limited. All Rights Reserved
 */

@Document(collection = "district")
public class District extends AuditModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @Indexed
    private String code;
    @Indexed
    private String provinceCode;
    private String lifeCode;
    private Integer activeStatus;
    private DescriptionModel description;

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

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }

    public DescriptionModel getDescription() {
        return description;
    }

    public void setDescription(DescriptionModel description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        District district = (District) o;

        if (!Objects.equals(id, district.id)) return false;
        if (!Objects.equals(code, district.code)) return false;
        if (!Objects.equals(provinceCode, district.provinceCode))
            return false;
        if (!Objects.equals(lifeCode, district.lifeCode)) return false;
        if (!Objects.equals(activeStatus, district.activeStatus))
            return false;
        return Objects.equals(description, district.description);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (provinceCode != null ? provinceCode.hashCode() : 0);
        result = 31 * result + (lifeCode != null ? lifeCode.hashCode() : 0);
        result = 31 * result + (activeStatus != null ? activeStatus.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
