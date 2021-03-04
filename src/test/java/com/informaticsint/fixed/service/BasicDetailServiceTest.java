package com.informaticsint.fixed.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.informaticsint.common.constants.ApplicationConstants;
import com.informaticsint.fixed.dao.CivilStatusDao;
import com.informaticsint.fixed.dao.GenderDao;
import com.informaticsint.fixed.dto.civilstatus.CivilStatusDto;
import com.informaticsint.fixed.dto.gender.GenderDto;
import com.informaticsint.fixed.model.CivilStatus;
import com.informaticsint.fixed.model.Gender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author : Steffan Devotta
 * Informatics International Limited. All Rights Reserved
 */

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class BasicDetailServiceTest {
    private BasicDetailService basicDetailService;

    /**
     * mock CivilStatusDao
     */
    @MockBean
    private CivilStatusDao civilStatusDao;

    /**
     * mock GenderDao
     */
    @MockBean
    private GenderDao genderDao;

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
        basicDetailService = new BasicDetailService(civilStatusDao, objectMapper, genderDao);
    }

    /**
     * execute after every test
     */
    @AfterEach
    void tearDown() {
        civilStatusDao = null;
        objectMapper = null;
        basicDetailService = null;
        genderDao = null;
    }

    /**
     * test getCivilStatusById method
     * @author : Steffan Devotta
     */
    @Test
    void testGetCivilStatusSuccess() {
        CivilStatus civilStatus = new CivilStatus();
        String id = "id";
        when(civilStatusDao.findByIdAndActiveStatus(id, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.of(civilStatus));
        CivilStatusDto civilStatusDto = new CivilStatusDto();
        when(objectMapper.convertValue(civilStatus, CivilStatusDto.class)).thenReturn(civilStatusDto);
        CivilStatusDto civilStatusDtoById = basicDetailService.getCivilStatusById(id);
        assertNotNull(civilStatusDtoById);
    }
    /**
     * test getCivilStatusById method fail
     * @author : Steffan Devotta
     */
    @Test
    void testGetCivilStatusFail() {
        String id = "id";
        when(civilStatusDao.findByIdAndActiveStatus(id, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.empty());
        CivilStatusDto civilStatusDtoById = basicDetailService.getCivilStatusById(id);
        assertNull(civilStatusDtoById);
    }


    /**
     * test getAllCivilStatus Success
     * @author : Steffan Devotta
     */
    @Test
    void testGetAllCivilStatusSuccess() {
        List<CivilStatus> civilStatuses = Arrays.asList(new CivilStatus());
        when(civilStatusDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS)).thenReturn(civilStatuses);
        CivilStatusDto civilStatusDto = new CivilStatusDto();
        when(objectMapper.convertValue(civilStatuses.get(0), CivilStatusDto.class)).thenReturn(civilStatusDto);
        List<CivilStatusDto> civilStatusDtos = basicDetailService.getAllCivilStatus();
        assertNotNull(civilStatusDtos);
        assertTrue(civilStatusDtos.size() > 0);
    }

    /**
     * test getAllCivilStatus method return empty array
     * @author : Steffan Devotta
     */
    @Test
    void testGetAllCivilStatusEmptyArray() {
        when(civilStatusDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS)).thenReturn(new ArrayList<>());
        List<CivilStatusDto> civilStatusDtos = basicDetailService.getAllCivilStatus();
        assertTrue(civilStatusDtos.size()==0);
    }

    /**
     * test getGenderById method
     * @author : Steffan Devotta
     */
    @Test
    void testGetGenderByIdSuccess() {
        Gender gender = new Gender();
        String id = "id";
        when(genderDao.findByIdAndActiveStatus(id, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.of(gender));
        GenderDto genderDto = new GenderDto();
        when(objectMapper.convertValue(gender, GenderDto.class)).thenReturn(genderDto);
        GenderDto genderDtoById = basicDetailService.getGenderById(id);
        assertNotNull(genderDtoById);
    }

    /**
     * test getGenderById method fail
     * @author : Steffan Devotta
     */
    @Test
    void testGetGenderByIdFail() {
        String id = "id";
        when(genderDao.findByIdAndActiveStatus(id, ApplicationConstants.ACTIVE_STATUS)).thenReturn(Optional.empty());
        GenderDto genderDtoById = basicDetailService.getGenderById(id);
        assertNull(genderDtoById);
    }

    /**
     * test getAllGenders Success
     * @author : Steffan Devotta
     */
    @Test
    void testGetAllGendersSuccess() {
        List<Gender> genderList = Arrays.asList(new Gender());
        when(genderDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS)).thenReturn(genderList);
        GenderDto genderDto = new GenderDto();
        when(objectMapper.convertValue(genderList.get(0), GenderDto.class)).thenReturn(genderDto);
        List<GenderDto> genderDtos = basicDetailService.getAllGenders();
        assertNotNull(genderDtos);
        assertTrue(genderDtos.size() > 0);
    }

    /**
     * test getAllGenders method return empty array
     * @author : Steffan Devotta
     */
    @Test
    void testGetAllGendersEmptyArray() {
        when(genderDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS)).thenReturn(new ArrayList<>());
        List<GenderDto> genderDtos = basicDetailService.getAllGenders();
        assertTrue(genderDtos.size()==0);
    }
}