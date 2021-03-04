package com.informaticsint.common.builder;

import com.informaticsint.starter.exception.BusinessRuleException;

/**
 * @author : sheharaj
 * Informatics International Limited. All Rights Reserved
 */

public class CustomDataBuilder {

    private CustomDataBuilder() {

    }

    /**
     * build BusinessRuleException
     *
     * @param message
     * @param i18nKey
     * @return BusinessRuleException
     */
    public static BusinessRuleException buildBusinessRuleException(String i18nKey, String message) {
        BusinessRuleException businessRuleException = new BusinessRuleException();
        businessRuleException.setI18nKey(i18nKey);
        businessRuleException.setMessage(message);
        return businessRuleException;
    }

}
