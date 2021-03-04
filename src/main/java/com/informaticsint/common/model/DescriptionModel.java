package com.informaticsint.common.model;

import java.io.Serializable;

/**
 * @author : Shane Rathnayake
 * @version : 0.1
 * @date : 2/23/21
 * @copyright : © Informatics International Limited. All Rights Reserved
 */

public class DescriptionModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String en;
    private String si;
    private String ta;

    public DescriptionModel(String en, String si, String ta) {
        this.en = en.equals(null)?"":en;
        this.si = si.equals(null)?"":si;
        this.ta = ta.equals(null)?"":ta;
    }

    public DescriptionModel() {
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getSi() {
        return si;
    }

    public void setSi(String si) {
        this.si = si;
    }

    public String getTa() {
        return ta;
    }

    public void setTa(String ta) {
        this.ta = ta;
    }
}
