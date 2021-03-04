package com.informaticsint.adminunit.restcontroller;

import com.informaticsint.adminunit.dto.ProvinceDto;
import com.informaticsint.adminunit.service.AdministrativeUnitService;
import com.informaticsint.starter.exception.BusinessRuleException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author : sheharaj
 * Informatics International Limited. All Rights Reserved
 */

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class ProvinceRestControllerTest {

    private ProvinceRestController provinceRestController;

    /**
     * mock AdministrativeUnitService
     */
    @MockBean
    private AdministrativeUnitService administrativeUnitService;

    /**
     * execute before every test case
     */
    @BeforeEach
    void setUp() {
        provinceRestController = new ProvinceRestController(administrativeUnitService);
    }

    /**
     * execute after every test case
     */
    @AfterEach
    void tearDown() {
        provinceRestController = null;
        administrativeUnitService = null;
    }

    /**
     * test saveProvince method
     */
    @Test
    void saveProvinceTest() {
        ResponseEntity<Void> responseEntity = provinceRestController.saveProvince(new ProvinceDto());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    /**
     * test updateProvince method
     */
    @Test
    void updateProvinceTest() {
        String identifier = "identifier";
        ProvinceDto provinceDto = new ProvinceDto();
        when(administrativeUnitService.updateProvince(identifier, provinceDto)).thenReturn(true);
        ResponseEntity<Void> responseEntity = provinceRestController.updateProvince(identifier, provinceDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    /**
     * test updateProvince method throw BusinessRuleException
     * {@link com.informaticsint.starter.exception.BusinessRuleException}
     */
    @Test
    void updateProvinceThrowBusinessRuleExceptionTest() {
        ProvinceDto provinceDto = new ProvinceDto();
        String identifier = "identifier";
        when(administrativeUnitService.updateProvince(identifier, provinceDto)).thenReturn(false);
        Exception exception = assertThrows(BusinessRuleException.class, () -> provinceRestController.updateProvince(identifier, provinceDto));
        String expectedMessage = "Can not find province for the given id {} : identifier";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * test deleteProvince method
     */
    @Test
    void deleteProvinceTest() {
        String identifier = "identifier";
        when(administrativeUnitService.deleteProvince(identifier)).thenReturn(true);
        ResponseEntity<Void> responseEntity = provinceRestController.deleteProvince(identifier);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    /**
     * test deleteProvince method throw BusinessRuleException
     * {@link com.informaticsint.starter.exception.BusinessRuleException}
     */
    @Test
    void deleteProvinceThrowBusinessRuleExceptionTest() {
        String identifier = "identifier";
        when(administrativeUnitService.deleteProvince(identifier)).thenReturn(false);
        Exception exception = assertThrows(BusinessRuleException.class, () -> provinceRestController.deleteProvince(identifier));
        String expectedMessage = "Can not find province for the given id {} : identifier";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * test findProvinceByIdentifier method
     */
    @Test
    void findProvinceByIdentifierTest() {
        String identifier = "identifier";
        when(administrativeUnitService.getProvinceByIdentifier(identifier)).thenReturn(new ProvinceDto());
        ResponseEntity<ProvinceDto> responseEntity = provinceRestController.findProvinceByIdentifier(identifier);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    /**
     * test findProvinceByIdentifier method throw BusinessRuleException
     * {@link com.informaticsint.starter.exception.BusinessRuleException}
     */
    @Test
    void findProvinceByIdentifierThrowBusinessRuleExceptionTest() {
        String identifier = "identifier";
        when(administrativeUnitService.getProvinceByIdentifier(identifier)).thenReturn(null);
        Exception exception = assertThrows(BusinessRuleException.class, () -> provinceRestController.findProvinceByIdentifier(identifier));
        String expectedMessage = "Can not find province for the given id {} : identifier";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * test findAllProvinces method
     */
    @Test
    void findAllProvincesTest() {
        when(administrativeUnitService.getAllProvinces()).thenReturn(Collections.singletonList(new ProvinceDto()));
        ResponseEntity<List<ProvinceDto>> responseEntity = provinceRestController.findAllProvinces();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    /**
     * test findAllProvinces method throw BusinessRuleException
     * {@link com.informaticsint.starter.exception.BusinessRuleException}
     */
    @Test
    void findAllProvincesThrowBusinessRuleExceptionTest() {
        when(administrativeUnitService.getAllProvinces()).thenReturn(new ArrayList<>());
        Exception exception = assertThrows(BusinessRuleException.class, () -> provinceRestController.findAllProvinces());
        String expectedMessage = "Can not find provinces";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}