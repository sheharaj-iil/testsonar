package com.informaticsint.fixed.dao;

import com.informaticsint.common.constants.ApplicationConstants;
import com.informaticsint.common.model.DescriptionModel;
import com.informaticsint.fixed.model.Gender;
import com.informaticsint.starter.util.audit.AuditUser;
import com.informaticsint.starter.util.audit.CustomAuditorAware;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Steffan Devotta
 * Informatics International Limited. All Rights Reserved
 */

@ActiveProfiles("test")
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@ExtendWith(SpringExtension.class)
class GenderDaoTest {

    private static final String CURRENT_AUDITOR = "test";

    private Gender gender;

    /**
     * mock AuditUser
     */
    @MockBean
    private AuditUser auditUser;

    /**
     * mock CustomAuditorAware
     */
    @MockBean
    private CustomAuditorAware customAuditorAware;

    /**
     * inject CivilStatusDao
     */
    @Autowired
    private GenderDao genderDao;

    /**
     * execute before every test case
     */
    @BeforeEach
    void setUp() {
        Mockito.when(customAuditorAware.getCurrentAuditor()).thenReturn(Optional.of(CURRENT_AUDITOR));
        gender = new Gender();
        gender.setCode("test");
        gender.setActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        gender.setDescription(new DescriptionModel("en", "si", "ta"));
        Gender savedGender = genderDao.save(gender);
        assertNotNull(savedGender);
    }

    @AfterEach
    void tearDown() {
        genderDao.deleteAll();
    }

    /**
     * test findById and it's status method
     */
    @Test
    void testFindByIdAndActiveStatus() {
        Optional<Gender> optionalGenderModel = genderDao.findByIdAndActiveStatus(gender.getId(), ApplicationConstants.ACTIVE_STATUS);
        assertNotNull(optionalGenderModel);
        assertTrue(optionalGenderModel.isPresent());
        assertEquals(gender.getId(), optionalGenderModel.get().getId());
    }

    /**
     * test findAllBy it's status method
     */
    @Test
    void findAllByActiveStatus() {
        List<Gender> genderList = genderDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        assertNotNull(genderList);
        assertTrue(genderList.size() > 0);
    }
}