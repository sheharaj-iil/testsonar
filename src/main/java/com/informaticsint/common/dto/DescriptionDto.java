package com.informaticsint.common.dto;

/**
 * @author : Shane Rathnayake
 * @version : 0.1
 * @date : 2/23/21
 * @copyright : © Informatics International Limited. All Rights Reserved
 */

public class DescriptionDto {

    private String en;
    private String si;
    private String ta;

    public DescriptionDto(String en, String si, String ta) {
        this.en = en;
        this.si = si;
        this.ta = ta;
    }

    public DescriptionDto() {
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
