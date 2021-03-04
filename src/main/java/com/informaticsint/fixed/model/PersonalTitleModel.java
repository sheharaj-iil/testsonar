package com.informaticsint.fixed.model;

import com.informaticsint.common.model.DescriptionModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : Shane Rathnayake
 * @version : 0.1
 * @date : 2/23/21
 * @copyright : © Informatics International Limited. All Rights Reserved
 */

@Document(collection = "PersonalTitle")
public class PersonalTitleModel {
    @Id
    private String id;
    @Indexed(unique = true)
    private String title;
    private DescriptionModel description;

    public PersonalTitleModel(String id, String title, DescriptionModel description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        if (!(o instanceof PersonalTitleModel)) return false;

        PersonalTitleModel that = (PersonalTitleModel) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getTitle() != null ? !getTitle().equals(that.getTitle()) : that.getTitle() != null) return false;
        return getDescription() != null ? getDescription().equals(that.getDescription()) : that.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
