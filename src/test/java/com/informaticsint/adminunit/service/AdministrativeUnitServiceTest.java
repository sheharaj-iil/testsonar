package com.informaticsint.adminunit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.informaticsint.adminunit.dao.DistrictDao;
import com.informaticsint.adminunit.dao.GsDivisionDao;
import com.informaticsint.adminunit.dao.ProvinceDao;
import com.informaticsint.adminunit.dao.SecretariatDao;
import com.informaticsint.adminunit.dto.DistrictDto;
import com.informaticsint.adminunit.dto.GsDivisionDto;
import com.informaticsint.adminunit.dto.ProvinceDto;
import com.informaticsint.adminunit.dto.SecretariatDto;
import com.informaticsint.adminunit.model.District;
import com.informaticsint.adminunit.model.GsDivision;
import com.informaticsint.adminunit.model.Province;
import com.informaticsint.adminunit.model.Secretariat;
import com.informaticsint.common.constants.ApplicationConstants;
import com.informaticsint.common.dto.DescriptionDto;
import com.informaticsint.common.model.DescriptionModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author : sheharaj
 * Informatics International Limited. All Rights Reserved
 */

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class AdministrativeUnitServiceTest {

    private AdministrativeUnitService administrativeUnitService;

    /**
     * mock ProvinceDao
     */
    @MockBean
    private ProvinceDao provinceDao;

    /**
     * mock DistrictDao
     */
    @MockBean
    private DistrictDao districtDao;

    /**
     * mock SecretariatDao
     */
    @MockBean
    private SecretariatDao secretariatDao;

    /**
     * mock SecretariatDao
     */
    @MockBean
    private GsDivisionDao gsDivisionDao;


    /**
     * mock ObjectMapper
     */
    @MockBean
    private ObjectMapper objectMapper;

    /**
     * execute before every test
     */
    @BeforeEach
    void setUp() {
        administrativeUnitService = new AdministrativeUnitService(provinceDao, districtDao, secretariatDao, gsDivisionDao, objectMapper);
    }

    /**
     * execute after every test
     */
    @AfterEach
    void tearDown() {
        provinceDao = null;
        districtDao = null;
        secretariatDao = null;
        gsDivisionDao = null;
        objectMapper = null;
        administrativeUnitService = null;
    }

    /*start province related test cases*/

    /**
     * test saveProvince method
     */
    @Test
    void saveProvinceTest() {
        ProvinceDto provinceDto = new ProvinceDto();
        provinceDto.setCode("test");
        provinceDto.setAbbreviationCode("test");
        provinceDto.setDescription(new DescriptionDto("EN", "SI", "TA"));
        Province province = new Province();
        when(objectMapper.convertValue(provinceDto, Province.class)).thenReturn(province);
        when(provinceDao.save(province)).thenReturn(province);
        administrativeUnitService.saveProvince(provinceDto);
        verify(provinceDao, times(1)).save(province);
    }

    /**
     * test updateProvince method return true
     */
    @Test
    void updateProvinceAndReturnTrueTest() {
        ProvinceDto provinceDto = new ProvinceDto();
        provinceDto.setCode("test");
        provinceDto.setAbbreviationCode("test");
        provinceDto.setDescription(new DescriptionDto("EN", "SI", "TA"));
        Province province = new Province();
        province.setDescription(new DescriptionModel("EN", "SI", "TA"));
        String identifier = "identifier";
        when(provinceDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.of(province));
        when(provinceDao.save(province)).thenReturn(province);
        boolean isUpdated = administrativeUnitService.updateProvince(identifier, provinceDto);
        verify(provinceDao, times(1)).save(province);
        assertTrue(isUpdated);
    }

    /**
     * test updateProvince method return false
     */
    @Test
    void updateProvinceAndReturnFalseTest() {
        ProvinceDto provinceDto = new ProvinceDto();
        String identifier = "identifier";
        when(provinceDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.empty());
        boolean isUpdated = administrativeUnitService.updateProvince(identifier, provinceDto);
        assertFalse(isUpdated);
    }

    /**
     * test deleteProvince method return true
     */
    @Test
    void deleteProvinceAndReturnTrueTest() {
        Province province = new Province();
        String identifier = "identifier";
        when(provinceDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.of(province));
        when(provinceDao.save(province)).thenReturn(province);
        boolean isDeleted = administrativeUnitService.deleteProvince(identifier);
        verify(provinceDao, times(1)).save(province);
        assertTrue(isDeleted);
    }

    /**
     * test deleteProvince method return false
     */
    @Test
    void deleteProvinceAndReturnFalseTest() {
        String identifier = "identifier";
        when(provinceDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.empty());
        boolean isDeleted = administrativeUnitService.deleteProvince(identifier);
        assertFalse(isDeleted);
    }

    /**
     * test getProvinceByIdentifier method
     */
    @Test
    void getProvinceByIdentifierTest() {
        Province province = new Province();
        String identifier = "identifier";
        when(provinceDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.of(province));
        ProvinceDto provinceDto = new ProvinceDto();
        when(objectMapper.convertValue(province, ProvinceDto.class)).thenReturn(provinceDto);
        ProvinceDto provinceDtoByIdentifier = administrativeUnitService.getProvinceByIdentifier(identifier);
        assertNotNull(provinceDtoByIdentifier);
    }

    /**
     * test getProvinceByIdentifier method return null
     */
    @Test
    void getProvinceByIdentifierReturnNullTest() {
        String identifier = "identifier";
        when(provinceDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.empty());
        ProvinceDto provinceByIdentifier = administrativeUnitService.getProvinceByIdentifier(identifier);
        assertNull(provinceByIdentifier);
    }

    /**
     * test getAllProvinces method
     */
    @Test
    void getAllProvincesTest() {
        List<Province> provinces = Collections.singletonList(new Province());
        when(provinceDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS)).thenReturn(provinces);
        ProvinceDto provinceDto = new ProvinceDto();
        when(objectMapper.convertValue(provinces.get(0), ProvinceDto.class)).thenReturn(provinceDto);
        List<ProvinceDto> provinceDtos = administrativeUnitService.getAllProvinces();
        assertNotNull(provinceDtos);
        assertTrue(provinceDtos.size() > 0);
    }

    /**
     * test getAllProvinces method return empty array
     */
    @Test
    void getAllProvincesReturnEmptyArrayTest() {
        when(provinceDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS)).thenReturn(new ArrayList<>());
        List<ProvinceDto> provinces = administrativeUnitService.getAllProvinces();
        assertEquals(0, provinces.size());
    }

    /*end province related test cases*/

    /*start district related test cases*/

    /**
     * test saveDistrict method
     */
    @Test
    void saveDistrictTest() {
        DistrictDto districtDto = new DistrictDto();
        districtDto.setCode("test");
        districtDto.setProvinceCode("test");
        districtDto.setLifeCode("test");
        districtDto.setDescription(new DescriptionDto("EN", "SI", "TA"));
        District district = new District();
        when(objectMapper.convertValue(districtDto, District.class)).thenReturn(district);
        when(districtDao.save(district)).thenReturn(district);
        administrativeUnitService.saveDistrict(districtDto);
        verify(districtDao, times(1)).save(district);
    }

    /**
     * test updateDistrict method return true
     */
    @Test
    void updateDistrictAndReturnTrueTest() {
        DistrictDto districtDto = new DistrictDto();
        districtDto.setCode("test");
        districtDto.setProvinceCode("test");
        districtDto.setLifeCode("test");
        districtDto.setDescription(new DescriptionDto("EN", "SI", "TA"));
        District district = new District();
        district.setDescription(new DescriptionModel("EN", "SI", "TA"));
        String identifier = "identifier";
        when(districtDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.of(district));
        when(districtDao.save(district)).thenReturn(district);
        boolean isUpdated = administrativeUnitService.updateDistrict(identifier, districtDto);
        verify(districtDao, times(1)).save(district);
        assertTrue(isUpdated);
    }

    /**
     * test updateDistrict method return false
     */
    @Test
    void updateDistrictAndReturnFalseTest() {
        DistrictDto districtDto = new DistrictDto();
        String identifier = "identifier";
        when(districtDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.empty());
        boolean isUpdated = administrativeUnitService.updateDistrict(identifier, districtDto);
        assertFalse(isUpdated);
    }

    /**
     * test deleteDistrict method return true
     */
    @Test
    void deleteDistrictAndReturnTrueTest() {
        District district = new District();
        String identifier = "identifier";
        when(districtDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.of(district));
        when(districtDao.save(district)).thenReturn(district);
        boolean isDeleted = administrativeUnitService.deleteDistrict(identifier);
        verify(districtDao, times(1)).save(district);
        assertTrue(isDeleted);
    }

    /**
     * test deleteDistrict method return false
     */
    @Test
    void deleteDistrictAndReturnFalseTest() {
        String identifier = "identifier";
        when(districtDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.empty());
        boolean isDeleted = administrativeUnitService.deleteDistrict(identifier);
        assertFalse(isDeleted);
    }

    /**
     * test getDistrictByIdentifier method
     */
    @Test
    void getDistrictByIdentifierTest() {
        District district = new District();
        String identifier = "identifier";
        when(districtDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.of(district));
        DistrictDto districtDto = new DistrictDto();
        when(objectMapper.convertValue(district, DistrictDto.class)).thenReturn(districtDto);
        DistrictDto districtDtoByIdentifier = administrativeUnitService.getDistrictByIdentifier(identifier);
        assertNotNull(districtDtoByIdentifier);
    }

    /**
     * test getDistrictByIdentifier method return null
     */
    @Test
    void getDistrictByIdentifierReturnNullTest() {
        String identifier = "identifier";
        when(districtDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.empty());
        DistrictDto districtByIdentifier = administrativeUnitService.getDistrictByIdentifier(identifier);
        assertNull(districtByIdentifier);
    }

    /**
     * test getAllDistricts method
     */
    @Test
    void getAllDistrictsTest() {
        List<District> districts = Collections.singletonList(new District());
        when(districtDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS)).thenReturn(districts);
        DistrictDto districtDto = new DistrictDto();
        when(objectMapper.convertValue(districts.get(0), DistrictDto.class)).thenReturn(districtDto);
        List<DistrictDto> districtDtos = administrativeUnitService.getAllDistricts();
        assertNotNull(districtDtos);
        assertTrue(districtDtos.size() > 0);
    }

    /**
     * test getAllDistrictsByProvinceCode method
     */
    @Test
    void getAllDistrictsByProvinceCodeTest() {
        List<District> districts = Collections.singletonList(new District());
        String provinceCode = "test";
        when(districtDao.findAllByProvinceCodeAndActiveStatus(provinceCode, ApplicationConstants.ACTIVE_STATUS)).thenReturn(districts);
        DistrictDto districtDto = new DistrictDto();
        when(objectMapper.convertValue(districts.get(0), DistrictDto.class)).thenReturn(districtDto);
        List<DistrictDto> districtDtos = administrativeUnitService.getAllDistrictsByProvinceCode(provinceCode);
        assertNotNull(districtDtos);
        assertTrue(districtDtos.size() > 0);
    }

    /*end district related test cases*/

    /*start secretariat related test cases*/

    /**
     * test saveSecretariat method
     */
    @Test
    void saveSecretariatTest() {
        SecretariatDto secretariatDto = getSecretariatDto();
        Secretariat secretariat = new Secretariat();
        when(objectMapper.convertValue(secretariatDto, Secretariat.class)).thenReturn(secretariat);
        when(secretariatDao.save(secretariat)).thenReturn(secretariat);
        administrativeUnitService.saveSecretariat(secretariatDto);
        verify(secretariatDao, times(1)).save(secretariat);
    }

    /**
     * build SecretariatDto
     *
     * @return SecretariatDto
     */
    private SecretariatDto getSecretariatDto() {
        SecretariatDto secretariatDto = new SecretariatDto();
        secretariatDto.setCode("test");
        secretariatDto.setDistrictCode("test");
        secretariatDto.setAddressLine1("test");
        secretariatDto.setAddressLine2("test");
        secretariatDto.setPostalCode("test");
        secretariatDto.setPhoneNumber("test");
        secretariatDto.setMobileNumber("test");
        secretariatDto.setFaxNumber("test");
        secretariatDto.setEmail("test");
        secretariatDto.setLifeCode("test");
        secretariatDto.setDescription(new DescriptionDto("EN", "SI", "TA"));
        return secretariatDto;
    }

    /**
     * test updateSecretariat method return true
     */
    @Test
    void updateSecretariatAndReturnTrueTest() {
        SecretariatDto secretariatDto = getSecretariatDto();
        Secretariat secretariat = new Secretariat();
        secretariat.setDescription(new DescriptionModel("EN", "SI", "TA"));
        String identifier = "identifier";
        when(secretariatDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.of(secretariat));
        when(secretariatDao.save(secretariat)).thenReturn(secretariat);
        boolean isUpdated = administrativeUnitService.updateSecretariat(identifier, secretariatDto);
        verify(secretariatDao, times(1)).save(secretariat);
        assertTrue(isUpdated);
    }

    /**
     * test updateSecretariat method return false
     */
    @Test
    void updateSecretariatAndReturnFalseTest() {
        SecretariatDto secretariatDto = new SecretariatDto();
        String identifier = "identifier";
        when(secretariatDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.empty());
        boolean isUpdated = administrativeUnitService.updateSecretariat(identifier, secretariatDto);
        assertFalse(isUpdated);
    }

    /**
     * test deleteSecretariat method return true
     */
    @Test
    void deleteSecretariatAndReturnTrueTest() {
        Secretariat secretariat = new Secretariat();
        String identifier = "identifier";
        when(secretariatDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.of(secretariat));
        when(secretariatDao.save(secretariat)).thenReturn(secretariat);
        boolean isDeleted = administrativeUnitService.deleteSecretariat(identifier);
        verify(secretariatDao, times(1)).save(secretariat);
        assertTrue(isDeleted);
    }

    /**
     * test deleteSecretariat method return false
     */
    @Test
    void deleteSecretariatAndReturnFalseTest() {
        String identifier = "identifier";
        when(secretariatDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.empty());
        boolean isDeleted = administrativeUnitService.deleteSecretariat(identifier);
        assertFalse(isDeleted);
    }

    /**
     * test getSecretariatByIdentifier method
     */
    @Test
    void getSecretariatByIdentifierTest() {
        Secretariat secretariat = new Secretariat();
        String identifier = "identifier";
        when(secretariatDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.of(secretariat));
        SecretariatDto secretariatDto = new SecretariatDto();
        when(objectMapper.convertValue(secretariat, SecretariatDto.class)).thenReturn(secretariatDto);
        SecretariatDto secretariatDtoByIdentifier = administrativeUnitService.getSecretariatByIdentifier(identifier);
        assertNotNull(secretariatDtoByIdentifier);
    }

    /**
     * test getSecretariatByIdentifier method return null
     */
    @Test
    void getSecretariatByIdentifierReturnNullTest() {
        String identifier = "identifier";
        when(secretariatDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.empty());
        SecretariatDto secretariatByIdentifier = administrativeUnitService.getSecretariatByIdentifier(identifier);
        assertNull(secretariatByIdentifier);
    }

    /**
     * test getAllSecretariats method
     */
    @Test
    void getAllSecretariatsTest() {
        List<Secretariat> secretariats = Collections.singletonList(new Secretariat());
        when(secretariatDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS)).thenReturn(secretariats);
        SecretariatDto secretariatDto = new SecretariatDto();
        when(objectMapper.convertValue(secretariats.get(0), SecretariatDto.class)).thenReturn(secretariatDto);
        List<SecretariatDto> secretariatDtos = administrativeUnitService.getAllSecretariats();
        assertNotNull(secretariatDtos);
        assertTrue(secretariatDtos.size() > 0);
    }

    /**
     * test getAllSecretariatsByDistrictCode method
     */
    @Test
    void getAllSecretariatsByDistrictCodeTest() {
        List<Secretariat> secretariats = Collections.singletonList(new Secretariat());
        String districtCode = "test";
        when(secretariatDao.findAllByDistrictCodeAndActiveStatus(districtCode, ApplicationConstants.ACTIVE_STATUS)).thenReturn(secretariats);
        SecretariatDto secretariatDto = new SecretariatDto();
        when(objectMapper.convertValue(secretariats.get(0), SecretariatDto.class)).thenReturn(secretariatDto);
        List<SecretariatDto> secretariatDtos = administrativeUnitService.getAllSecretariatsByDistrictCode(districtCode);
        assertNotNull(secretariatDtos);
        assertTrue(secretariatDtos.size() > 0);
    }

    /*end secretariat related test cases*/

    /*start gsDivision related test cases*/

    /**
     * test saveGsDivision method
     */
    @Test
    void saveGsDivisionTest() {
        GsDivisionDto gsDivisionDto = getGsDivisionDto();
        GsDivision gsDivision = new GsDivision();
        when(objectMapper.convertValue(gsDivisionDto, GsDivision.class)).thenReturn(gsDivision);
        when(gsDivisionDao.save(gsDivision)).thenReturn(gsDivision);
        administrativeUnitService.saveGsDivision(gsDivisionDto);
        verify(gsDivisionDao, times(1)).save(gsDivision);
    }

    /**
     * build GsDivisionDto
     *
     * @return GsDivisionDto
     */
    private GsDivisionDto getGsDivisionDto() {
        GsDivisionDto gsDivisionDto = new GsDivisionDto();
        gsDivisionDto.setCode("test");
        gsDivisionDto.setSecretariatCode("test");
        gsDivisionDto.setAddressLine1("test");
        gsDivisionDto.setAddressLine2("test");
        gsDivisionDto.setPostalCode("test");
        gsDivisionDto.setPhoneNumber("test");
        gsDivisionDto.setMobileNumber("test");
        gsDivisionDto.setFaxNumber("test");
        gsDivisionDto.setEmail("test");
        gsDivisionDto.setLifeCode("test");
        gsDivisionDto.setDescription(new DescriptionDto("EN", "SI", "TA"));
        return gsDivisionDto;
    }

    /**
     * test updateGsDivision method return true
     */
    @Test
    void updateGsDivisionAndReturnTrueTest() {
        GsDivisionDto gsDivisionDto = getGsDivisionDto();
        GsDivision gsDivision = new GsDivision();
        gsDivision.setDescription(new DescriptionModel("EN", "SI", "TA"));
        String identifier = "identifier";
        when(gsDivisionDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.of(gsDivision));
        when(gsDivisionDao.save(gsDivision)).thenReturn(gsDivision);
        boolean isUpdated = administrativeUnitService.updateGsDivision(identifier, gsDivisionDto);
        verify(gsDivisionDao, times(1)).save(gsDivision);
        assertTrue(isUpdated);
    }

    /**
     * test updateGsDivision method return false
     */
    @Test
    void updateGsDivisionAndReturnFalseTest() {
        GsDivisionDto gsDivisionDto = new GsDivisionDto();
        String identifier = "identifier";
        when(gsDivisionDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.empty());
        boolean isUpdated = administrativeUnitService.updateGsDivision(identifier, gsDivisionDto);
        assertFalse(isUpdated);
    }

    /**
     * test deleteGsDivision method return true
     */
    @Test
    void deleteGsDivisionAndReturnTrueTest() {
        GsDivision gsDivision = new GsDivision();
        String identifier = "identifier";
        when(gsDivisionDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.of(gsDivision));
        when(gsDivisionDao.save(gsDivision)).thenReturn(gsDivision);
        boolean isDeleted = administrativeUnitService.deleteGsDivision(identifier);
        verify(gsDivisionDao, times(1)).save(gsDivision);
        assertTrue(isDeleted);
    }

    /**
     * test deleteGsDivision method return false
     */
    @Test
    void deleteGsDivisionAndReturnFalseTest() {
        String identifier = "identifier";
        when(gsDivisionDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.empty());
        boolean isDeleted = administrativeUnitService.deleteGsDivision(identifier);
        assertFalse(isDeleted);
    }

    /**
     * test getGsDivisionByIdentifier method
     */
    @Test
    void getGsDivisionByIdentifierTest() {
        GsDivision gsDivision = new GsDivision();
        String identifier = "identifier";
        when(gsDivisionDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.of(gsDivision));
        GsDivisionDto gsDivisionDto = new GsDivisionDto();
        when(objectMapper.convertValue(gsDivision, GsDivisionDto.class)).thenReturn(gsDivisionDto);
        GsDivisionDto gsDivisionDtoByIdentifier = administrativeUnitService.getGsDivisionByIdentifier(identifier);
        assertNotNull(gsDivisionDtoByIdentifier);
    }

    /**
     * test getGsDivisionByIdentifier method return null
     */
    @Test
    void getGsDivisionByIdentifierReturnNullTest() {
        String identifier = "identifier";
        when(gsDivisionDao.findByIdAndActiveStatus(identifier, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.empty());
        GsDivisionDto gsDivisionByIdentifier = administrativeUnitService.getGsDivisionByIdentifier(identifier);
        assertNull(gsDivisionByIdentifier);
    }

    /**
     * test getAllGsDivisions method
     */
    @Test
    void getAllGsDivisionsTest() {
        List<GsDivision> gsDivisions = Collections.singletonList(new GsDivision());
        when(gsDivisionDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS)).thenReturn(gsDivisions);
        GsDivisionDto gsDivisionDto = new GsDivisionDto();
        when(objectMapper.convertValue(gsDivisions.get(0), GsDivisionDto.class)).thenReturn(gsDivisionDto);
        List<GsDivisionDto> gsDivisionDtos = administrativeUnitService.getAllGsDivisions();
        assertNotNull(gsDivisionDtos);
        assertTrue(gsDivisionDtos.size() > 0);
    }

    /**
     * test getAllGsDivisionsBySecretariatCode method
     */
    @Test
    void getAllGsDivisionsBySecretariatCodeTest() {
        List<GsDivision> gsDivisions = Collections.singletonList(new GsDivision());
        String secretariatCode = "test";
        when(gsDivisionDao.findAllBySecretariatCodeAndActiveStatus(secretariatCode, ApplicationConstants.ACTIVE_STATUS)).thenReturn(gsDivisions);
        GsDivisionDto gsDivisionDto = new GsDivisionDto();
        when(objectMapper.convertValue(gsDivisions.get(0), GsDivisionDto.class)).thenReturn(gsDivisionDto);
        List<GsDivisionDto> gsDivisionDtos = administrativeUnitService.getAllGsDivisionsBySecretariatCode(secretariatCode);
        assertNotNull(gsDivisionDtos);
        assertTrue(gsDivisionDtos.size() > 0);
    }

    /*end gsDivision related test cases*/
}