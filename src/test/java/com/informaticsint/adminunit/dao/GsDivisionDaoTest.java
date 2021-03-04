package com.informaticsint.adminunit.dao;

import com.informaticsint.adminunit.model.GsDivision;
import com.informaticsint.common.constants.ApplicationConstants;
import com.informaticsint.common.model.DescriptionModel;
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
 * @author : sheharaj
 * Informatics International Limited. All Rights Reserved
 */

@ActiveProfiles("test")
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@ExtendWith(SpringExtension.class)
class GsDivisionDaoTest {

    private static final String CURRENT_AUDITOR = "test";

    private GsDivision gsDivision;

    /**
     * mock CustomAuditorAware
     */
    @MockBean
    private CustomAuditorAware customAuditorAware;

    /**
     * inject GsDivisionDao
     */
    @Autowired
    private GsDivisionDao gsDivisionDao;

    /**
     * execute before every test case
     */
    @BeforeEach
    void setUp() {
        Mockito.when(customAuditorAware.getCurrentAuditor()).thenReturn(Optional.of(CURRENT_AUDITOR));
        gsDivision = new GsDivision();
        gsDivision.setCode("test");
        gsDivision.setSecretariatCode("test");
        gsDivision.setAddressLine1("test");
        gsDivision.setAddressLine2("test");
        gsDivision.setPostalCode("test");
        gsDivision.setPhoneNumber("test");
        gsDivision.setMobileNumber("test");
        gsDivision.setFaxNumber("test");
        gsDivision.setEmail("test");
        gsDivision.setLifeCode("test");
        gsDivision.setActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        gsDivision.setDescription(new DescriptionModel("en", "si", "ta"));
        GsDivision savedGsDivision = gsDivisionDao.save(gsDivision);
        assertNotNull(savedGsDivision);
    }

    /**
     * execute after every test case
     */
    @AfterEach
    void tearDown() {
        gsDivisionDao.deleteAll();
    }

    /**
     * test update method
     */
    @Test
    void updateGsDivisionsTest() {
        String updatedCode = "updatedCode";
        gsDivision.setCode(updatedCode);
        GsDivision updatedGsDivision = gsDivisionDao.save(gsDivision);
        assertNotNull(updatedGsDivision);
        assertEquals(updatedCode, updatedGsDivision.getCode());
    }

    /**
     * test findAll method
     */
    @Test
    void findAllGsDivisionsTest() {
        List<GsDivision> gsDivisions = gsDivisionDao.findAll();
        assertNotNull(gsDivisions);
        assertTrue(gsDivisions.size() > 0);
    }

    /**
     * test findById method
     */
    @Test
    void findGsDivisionByIdTest() {
        Optional<GsDivision> optionalGsDivision = gsDivisionDao.findById(gsDivision.getId());
        assertNotNull(optionalGsDivision);
        assertTrue(optionalGsDivision.isPresent());
        assertEquals(gsDivision.getId(), optionalGsDivision.get().getId());
    }

    /**
     * test findById and it's status method
     */
    @Test
    void findGsDivisionByIdAndActiveStatusTest() {
        Optional<GsDivision> optionalGsDivision = gsDivisionDao.findByIdAndActiveStatus(gsDivision.getId(), ApplicationConstants.ACTIVE_STATUS);
        assertNotNull(optionalGsDivision);
        assertTrue(optionalGsDivision.isPresent());
        assertEquals(gsDivision.getId(), optionalGsDivision.get().getId());
    }

    /**
     * test findAllBy it's status method
     */
    @Test
    void findAllGsDivisionsByActiveStatusTest() {
        List<GsDivision> gsDivisions = gsDivisionDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        assertNotNull(gsDivisions);
        assertTrue(gsDivisions.size() > 0);
    }

    /**
     * test findAllBy secretariatCode and activeStatus
     */
    @Test
    void findAllBySecretariatCodeAndActiveStatus() {
        List<GsDivision> gsDivisions = gsDivisionDao.findAllBySecretariatCodeAndActiveStatus("test", ApplicationConstants.ACTIVE_STATUS);
        assertNotNull(gsDivisions);
        assertTrue(gsDivisions.size() > 0);
    }
}