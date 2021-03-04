package com.informaticsint.adminunit.dao;

import com.informaticsint.adminunit.model.Secretariat;
import com.informaticsint.common.constants.ApplicationConstants;
import com.informaticsint.common.model.DescriptionModel;
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
 * @author : sheharaj
 * Informatics International Limited. All Rights Reserved
 */

@ActiveProfiles("test")
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@ExtendWith(SpringExtension.class)
class SecretariatDaoTest {

    private static final String CURRENT_AUDITOR = "test";

    private Secretariat secretariat;

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
     * inject SecretariatDao
     */
    @Autowired
    private SecretariatDao secretariatDao;

    /**
     * execute before every test case
     */
    @BeforeEach
    void setUp() {
        Mockito.when(customAuditorAware.getCurrentAuditor()).thenReturn(Optional.of(CURRENT_AUDITOR));
        secretariat = new Secretariat();
        secretariat.setCode("test");
        secretariat.setDistrictCode("test");
        secretariat.setAddressLine1("test");
        secretariat.setAddressLine2("test");
        secretariat.setPostalCode("test");
        secretariat.setPhoneNumber("test");
        secretariat.setMobileNumber("test");
        secretariat.setFaxNumber("test");
        secretariat.setEmail("test");
        secretariat.setLifeCode("test");
        secretariat.setActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        secretariat.setDescription(new DescriptionModel("en", "si", "ta"));
        Secretariat savedSecretariat = secretariatDao.save(secretariat);
        assertNotNull(savedSecretariat);
    }

    /**
     * execute after every test case
     */
    @AfterEach
    void tearDown() {
        secretariatDao.deleteAll();
    }

    /**
     * test update method
     */
    @Test
    void updateSecretariatsTest() {
        String updatedCode = "updatedCode";
        secretariat.setCode(updatedCode);
        Secretariat updatedSecretariat = secretariatDao.save(secretariat);
        assertNotNull(updatedSecretariat);
        assertEquals(updatedCode, updatedSecretariat.getCode());
    }

    /**
     * test findAll method
     */
    @Test
    void findAllSecretariatsTest() {
        List<Secretariat> secretariats = secretariatDao.findAll();
        assertNotNull(secretariats);
        assertTrue(secretariats.size() > 0);
    }

    /**
     * test findById method
     */
    @Test
    void findSecretariatByIdTest() {
        Optional<Secretariat> optionalSecretariat = secretariatDao.findById(secretariat.getId());
        assertNotNull(optionalSecretariat);
        assertTrue(optionalSecretariat.isPresent());
        assertEquals(secretariat.getId(), optionalSecretariat.get().getId());
    }

    /**
     * test findById and it's status method
     */
    @Test
    void findSecretariatByIdAndActiveStatusTest() {
        Optional<Secretariat> optionalSecretariat = secretariatDao.findByIdAndActiveStatus(secretariat.getId(), ApplicationConstants.ACTIVE_STATUS);
        assertNotNull(optionalSecretariat);
        assertTrue(optionalSecretariat.isPresent());
        assertEquals(secretariat.getId(), optionalSecretariat.get().getId());
    }

    /**
     * test findAllBy it's status method
     */
    @Test
    void findAllSecretariatsByActiveStatusTest() {
        List<Secretariat> secretariats = secretariatDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        assertNotNull(secretariats);
        assertTrue(secretariats.size() > 0);
    }

    /**
     * test findAllBy districtCode and activeStatus
     */
    @Test
    void findAllByDistrictCodeAndActiveStatus() {
        List<Secretariat> secretariats = secretariatDao.findAllByDistrictCodeAndActiveStatus("test", ApplicationConstants.ACTIVE_STATUS);
        assertNotNull(secretariats);
        assertTrue(secretariats.size() > 0);
    }
}