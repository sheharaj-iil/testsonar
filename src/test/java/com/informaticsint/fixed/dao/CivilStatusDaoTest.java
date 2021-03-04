package com.informaticsint.fixed.dao;

import com.informaticsint.common.constants.ApplicationConstants;
import com.informaticsint.common.model.DescriptionModel;
import com.informaticsint.fixed.model.CivilStatus;
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
class CivilStatusDaoTest {

    private static final String CURRENT_AUDITOR = "test";

    private CivilStatus civilStatus;

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
    private CivilStatusDao civilStatusDao;

    /**
     * execute before every test case
     */
    @BeforeEach
    void setUp() {
        Mockito.when(customAuditorAware.getCurrentAuditor()).thenReturn(Optional.of(CURRENT_AUDITOR));
        civilStatus = new CivilStatus();
        civilStatus.setCode("test");
        civilStatus.setActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        civilStatus.setDescription(new DescriptionModel("en", "si", "ta"));
        CivilStatus savedCivilStatus = civilStatusDao.save(civilStatus);
        assertNotNull(savedCivilStatus);
    }

    @AfterEach
    void tearDown() {
        civilStatusDao.deleteAll();
    }

    /**
     * test findById and it's status method
     */
    @Test
    void testFindByIdAndActiveStatus() {
        Optional<CivilStatus> optionalCivilStatusModel = civilStatusDao.findByIdAndActiveStatus(civilStatus.getId(), ApplicationConstants.ACTIVE_STATUS);
        assertNotNull(optionalCivilStatusModel);
        assertTrue(optionalCivilStatusModel.isPresent());
        assertEquals(civilStatus.getId(), optionalCivilStatusModel.get().getId());
    }

    /**
     * test findAllBy it's status method
     */
    @Test
    void testFindAllByActiveStatus() {
        List<CivilStatus> civilStatuses = civilStatusDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        assertNotNull(civilStatuses);
        assertTrue(civilStatuses.size() > 0);
    }
}