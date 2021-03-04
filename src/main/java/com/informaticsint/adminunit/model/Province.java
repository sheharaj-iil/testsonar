package com.informaticsint.adminunit.model;

import com.informaticsint.common.model.DescriptionModel;
import com.informaticsint.starter.util.audit.AuditModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author : sheharaj
 * Informatics International Limited. All Rights Reserved
 */

@Document(collection = "province")
public class Province extends AuditModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @Indexed
    private String code;
    private String abbreviationCode;
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

    public String getAbbreviationCode() {
        return abbreviationCode;
    }

    public void setAbbreviationCode(String abbreviationCode) {
        this.abbreviationCode = abbreviationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Province province = (Province) o;

        if (id != null ? !id.equals(province.id) : province.id != null) return false;
        if (code != null ? !code.equals(province.code) : province.code != null) return false;
        if (abbreviationCode != null ? !abbreviationCode.equals(province.abbreviationCode) : province.abbreviationCode != null)
            return false;
        if (activeStatus != null ? !activeStatus.equals(province.activeStatus) : province.activeStatus != null)
            return false;
        return description != null ? description.equals(province.description) : province.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (abbreviationCode != null ? abbreviationCode.hashCode() : 0);
        result = 31 * result + (activeStatus != null ? activeStatus.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
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

}
