package com.informaticsint.adminunit.restcontroller;

import com.informaticsint.adminunit.dto.DistrictDto;
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
class DistrictRestControllerTest {

    private DistrictRestController districtRestController;

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
        districtRestController = new DistrictRestController(administrativeUnitService);
    }

    /**
     * execute after every test case
     */
    @AfterEach
    void tearDown() {
        districtRestController = null;
        administrativeUnitService = null;
    }

    /**
     * test saveDistrict method
     */
    @Test
    void saveDistrictTest() {
        ResponseEntity<Void> responseEntity = districtRestController.saveDistrict(new DistrictDto());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    /**
     * test updateDistrict method
     */
    @Test
    void updateDistrictTest() {
        String identifier = "identifier";
        DistrictDto districtDto = new DistrictDto();
        when(administrativeUnitService.updateDistrict(identifier, districtDto)).thenReturn(true);
        ResponseEntity<Void> responseEntity = districtRestController.updateDistrict(identifier, districtDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    /**
     * test updateDistrict method throw BusinessRuleException
     * {@link com.informaticsint.starter.exception.BusinessRuleException}
     */
    @Test
    void updateDistrictThrowBusinessRuleExceptionTest() {
        DistrictDto districtDto = new DistrictDto();
        when(administrativeUnitService.updateDistrict("identifier", districtDto)).thenReturn(false);
        Exception exception = assertThrows(BusinessRuleException.class, () -> districtRestController.updateDistrict("identifier", districtDto));
        String expectedMessage = "Can not find district for the given id {} : identifier";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * test deleteDistrict method
     */
    @Test
    void deleteDistrictTest() {
        String identifier = "identifier";
        when(administrativeUnitService.deleteDistrict(identifier)).thenReturn(true);
        ResponseEntity<Void> responseEntity = districtRestController.deleteDistrict(identifier);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    /**
     * test deleteDistrict method throw BusinessRuleException
     * {@link com.informaticsint.starter.exception.BusinessRuleException}
     */
    @Test
    void deleteDistrictThrowBusinessRuleExceptionTest() {
        Exception exception = assertThrows(BusinessRuleException.class, () -> districtRestController.deleteDistrict("identifier"));
        String expectedMessage = "Can not find district for the given id {} : identifier";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * test findDistrictByIdentifier method
     */
    @Test
    void findDistrictByIdentifierTest() {
        String identifier = "identifier";
        when(administrativeUnitService.getDistrictByIdentifier(identifier)).thenReturn(new DistrictDto());
        ResponseEntity<DistrictDto> responseEntity = districtRestController.findDistrictByIdentifier(identifier);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    /**
     * test findDistrictByIdentifier method throw BusinessRuleException
     * {@link com.informaticsint.starter.exception.BusinessRuleException}
     */
    @Test
    void findDistrictByIdentifierThrowBusinessRuleExceptionTest() {
        String identifier = "identifier";
        when(administrativeUnitService.getDistrictByIdentifier(identifier)).thenReturn(null);
        Exception exception = assertThrows(BusinessRuleException.class, () -> districtRestController.findDistrictByIdentifier(identifier));
        String expectedMessage = "Can not find district for the given id {} : identifier";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * test findAllDistricts method
     */
    @Test
    void findAllDistrictsTest() {
        when(administrativeUnitService.getAllDistricts()).thenReturn(Collections.singletonList(new DistrictDto()));
        ResponseEntity<List<DistrictDto>> responseEntity = districtRestController.findAllDistricts();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    /**
     * test findAllDistricts method throw BusinessRuleException
     * {@link com.informaticsint.starter.exception.BusinessRuleException}
     */
    @Test
    void findAllDistrictsThrowBusinessRuleExceptionTest() {
        when(administrativeUnitService.getAllDistricts()).thenReturn(new ArrayList<>());
        Exception exception = assertThrows(BusinessRuleException.class, () -> districtRestController.findAllDistricts());
        String expectedMessage = "Can not find districts";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * test findAllDistrictsByProvinceCode method
     */
    @Test
    void findAllDistrictsByProvinceCodeTest() {
        String provinceCode = "test";
        when(administrativeUnitService.getAllDistrictsByProvinceCode(provinceCode)).thenReturn(Collections.singletonList(new DistrictDto()));
        ResponseEntity<List<DistrictDto>> responseEntity = districtRestController.findAllDistrictsByProvinceCode(provinceCode);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    /**
     * test findAllDistricts method throw BusinessRuleException
     * {@link com.informaticsint.starter.exception.BusinessRuleException}
     */
    @Test
    void findAllDistrictsByProvinceCodeThrowBusinessRuleExceptionTest() {
        String provinceCode = "test";
        when(administrativeUnitService.getAllDistrictsByProvinceCode(provinceCode)).thenReturn(new ArrayList<>());
        Exception exception = assertThrows(BusinessRuleException.class, () -> districtRestController.findAllDistrictsByProvinceCode(provinceCode));
        String expectedMessage = "Can not find districts for the given provinceCode{} : " + provinceCode;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}