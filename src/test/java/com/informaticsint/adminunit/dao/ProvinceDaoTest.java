package com.informaticsint.adminunit.dao;

import com.informaticsint.adminunit.model.Province;
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
class ProvinceDaoTest {

    private static final String CURRENT_AUDITOR = "test";

    private Province province;

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
     * inject ProvinceDao
     */
    @Autowired
    private ProvinceDao provinceDao;

    /**
     * execute before every test case
     */
    @BeforeEach
    void setUp() {
        Mockito.when(customAuditorAware.getCurrentAuditor()).thenReturn(Optional.of(CURRENT_AUDITOR));
        province = new Province();
        province.setCode("test");
        province.setAbbreviationCode("test");
        province.setActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        province.setDescription(new DescriptionModel("en", "si", "ta"));
        Province savedProvince = provinceDao.save(province);
        assertNotNull(savedProvince);
    }

    /**
     * execute after every test case
     */
    @AfterEach
    void tearDown() {
        provinceDao.deleteAll();
    }

    /**
     * test update method
     */
    @Test
    void updateProvincesTest() {
        String updatedCode = "updatedCode";
        province.setCode(updatedCode);
        Province updatedProvince = provinceDao.save(province);
        assertNotNull(updatedProvince);
        assertEquals(updatedCode, updatedProvince.getCode());
    }

    /**
     * test findAll method
     */
    @Test
    void findAllProvincesTest() {
        List<Province> provinces = provinceDao.findAll();
        assertNotNull(provinces);
        assertTrue(provinces.size() > 0);
    }

    /**
     * test findById method
     */
    @Test
    void findProvinceByIdTest() {
        Optional<Province> optionalProvince = provinceDao.findById(province.getId());
        assertNotNull(optionalProvince);
        assertTrue(optionalProvince.isPresent());
        assertEquals(province.getId(), optionalProvince.get().getId());
    }

    /**
     * test findById and it's status method
     */
    @Test
    void findProvinceByIdAndActiveStatusTest() {
        Optional<Province> optionalProvince = provinceDao.findByIdAndActiveStatus(province.getId(), ApplicationConstants.ACTIVE_STATUS);
        assertNotNull(optionalProvince);
        assertTrue(optionalProvince.isPresent());
        assertEquals(province.getId(), optionalProvince.get().getId());
    }

    /**
     * test findAllBy it's status method
     */
    @Test
    void findAllProvincesByActiveStatusTest() {
        List<Province> provinces = provinceDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        assertNotNull(provinces);
        assertTrue(provinces.size() > 0);
    }

}