package com.informaticsint.adminunit.dao;

import com.informaticsint.adminunit.model.District;
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
class DistrictDaoTest {

    private static final String CURRENT_AUDITOR = "test";

    private District district;

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
     * inject DistrictDao
     */
    @Autowired
    private DistrictDao districtDao;

    /**
     * execute before every test case
     */
    @BeforeEach
    void setUp() {
        Mockito.when(customAuditorAware.getCurrentAuditor()).thenReturn(Optional.of(CURRENT_AUDITOR));
        district = new District();
        district.setCode("test");
        district.setProvinceCode("test");
        district.setLifeCode("test");
        district.setActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        district.setDescription(new DescriptionModel("en", "si", "ta"));
        District savedDistrict = districtDao.save(district);
        assertNotNull(savedDistrict);
    }

    /**
     * execute after every test case
     */
    @AfterEach
    void tearDown() {
        districtDao.deleteAll();
    }

    /**
     * test update method
     */
    @Test
    void updateDistrictsTest() {
        String updatedCode = "updatedCode";
        district.setCode(updatedCode);
        District updatedDistrict = districtDao.save(district);
        assertNotNull(updatedDistrict);
        assertEquals(updatedCode, updatedDistrict.getCode());
    }

    /**
     * test findAll method
     */
    @Test
    void findAllDistrictsTest() {
        List<District> districts = districtDao.findAll();
        assertNotNull(districts);
        assertTrue(districts.size() > 0);
    }

    /**
     * test findById method
     */
    @Test
    void findDistrictByIdTest() {
        Optional<District> optionalDistrict = districtDao.findById(district.getId());
        assertNotNull(optionalDistrict);
        assertTrue(optionalDistrict.isPresent());
        assertEquals(district.getId(), optionalDistrict.get().getId());
    }

    /**
     * test findById and it's status method
     */
    @Test
    void findDistrictByIdAndActiveStatusTest() {
        Optional<District> optionalDistrict = districtDao.findByIdAndActiveStatus(district.getId(), ApplicationConstants.ACTIVE_STATUS);
        assertNotNull(optionalDistrict);
        assertTrue(optionalDistrict.isPresent());
        assertEquals(district.getId(), optionalDistrict.get().getId());
    }

    /**
     * test findAllBy it's status method
     */
    @Test
    void findAllDistrictsByActiveStatusTest() {
        List<District> districts = districtDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        assertNotNull(districts);
        assertTrue(districts.size() > 0);
    }

    /**
     * test findAllBy provinceCode and activeStatus
     */
    @Test
    void findAllByProvinceCodeAndActiveStatus() {
        List<District> districts = districtDao.findAllByProvinceCodeAndActiveStatus("test", ApplicationConstants.ACTIVE_STATUS);
        assertNotNull(districts);
        assertTrue(districts.size() > 0);
    }
}