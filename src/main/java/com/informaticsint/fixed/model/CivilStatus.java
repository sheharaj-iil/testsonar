package com.informaticsint.fixed.model;

import com.informaticsint.common.model.DescriptionModel;
import com.informaticsint.starter.util.audit.AuditModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author : Steffan Devotta
 * @version : 0.1
 * @date : 2/23/21
 * @copyright : © Informatics International Limited. All Rights Reserved
 */
@Document(collection = "civilStatus")
public class CivilStatus extends AuditModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @Indexed(unique = true)
    private String code;
    private DescriptionModel description;
    private Integer activeStatus;



    public CivilStatus(String id, String code, DescriptionModel description, Integer activeStatus) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.activeStatus = activeStatus;
    }

    public CivilStatus() {
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

    public DescriptionModel getDescription() {
        return description;
    }

    public void setDescription(DescriptionModel description) {
        this.description = description;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CivilStatus)) return false;

        CivilStatus that = (CivilStatus) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getCode() != null ? !getCode().equals(that.getCode()) : that.getCode() != null) return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        return getActiveStatus() != null ? getActiveStatus().equals(that.getActiveStatus()) : that.getActiveStatus() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCode() != null ? getCode().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getActiveStatus() != null ? getActiveStatus().hashCode() : 0);
        return result;
    }
}
