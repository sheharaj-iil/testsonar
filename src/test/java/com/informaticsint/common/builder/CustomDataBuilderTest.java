package com.informaticsint.common.builder;

import com.informaticsint.starter.exception.BusinessRuleException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author : sheharaj
 * Informatics International Limited. All Rights Reserved
 */

@ActiveProfiles("test")
class CustomDataBuilderTest {

    private static final String I_18_N_KEY = "TEST";
    private static final String MESSAGE = "Test Message";

    /**
     * test buildBusinessRuleException method
     */
    @Test
    void buildBusinessRuleExceptionTest() {
        BusinessRuleException businessRuleException = CustomDataBuilder.buildBusinessRuleException(I_18_N_KEY, MESSAGE);
        Assertions.assertNotNull(businessRuleException);
        Assertions.assertEquals(I_18_N_KEY, businessRuleException.getI18nKey());
        Assertions.assertEquals(MESSAGE, businessRuleException.getMessage());
    }
}