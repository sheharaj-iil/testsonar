package com.informaticsint.fixed.restcontroller;

import com.informaticsint.adminunit.dto.ProvinceDto;
import com.informaticsint.adminunit.restcontroller.ProvinceRestController;
import com.informaticsint.adminunit.service.AdministrativeUnitService;
import com.informaticsint.fixed.dto.civilstatus.CivilStatusDto;
import com.informaticsint.fixed.dto.gender.GenderDto;
import com.informaticsint.fixed.service.BasicDetailService;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author : Steffan Devotta
 * Informatics International Limited. All Rights Reserved
 */

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class BasicDetailControllerTest {

    private BasicDetailController basicDetailController;

    /**
     * mock BasicDetailService
     */
    @MockBean
    private BasicDetailService basicDetailService;

    /**
     * execute before every test case
     */
    @BeforeEach
    void setUp() {
        basicDetailController = new BasicDetailController(basicDetailService);
    }

    /**
     * execute after every test case
     */
    @AfterEach
    void tearDown() {
        basicDetailController = null;
        basicDetailService = null;
    }

    /**
     * test findProvinceByIdentifier method
     */
    @Test
    void testGetCivilStatusSuccess() {
        String id = "id";
        when(basicDetailService.getCivilStatusById(id)).thenReturn(new CivilStatusDto());
        ResponseEntity<CivilStatusDto> responseEntity = basicDetailController.getCivilStatusById(id);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(responseEntity.getBody());
    }
    /**
     * test getCivilStatusById method throw BusinessRuleException
     * {@link com.informaticsint.starter.exception.BusinessRuleException}
     */
    @Test
    void testGetCivilStatusByIdThrowBusinessRuleException() {
        String id = "id";
        when(basicDetailService.getCivilStatusById(id)).thenReturn(null);
        Exception exception = assertThrows(BusinessRuleException.class, () -> {
            basicDetailController.getCivilStatusById(id);
        });
        String expectedMessage = "Cannot find civil status for the given id {} : id";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    /**
     * test getAllCivilStatus method
     */
    @Test
    void testGetAllCivilStatusSuccess() {
        when(basicDetailService.getAllCivilStatus()).thenReturn(Arrays.asList(new CivilStatusDto()));
        ResponseEntity<List<CivilStatusDto>> responseEntity = basicDetailController.getAllCivilStatus();
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(responseEntity.getBody());
    }

    /**
     * test testGetAllCivilStatus method throw BusinessRuleException
     * {@link com.informaticsint.starter.exception.BusinessRuleException}
     */
    @Test
    void testGetAllCivilStatusThrowBusinessRuleException() {
        when(basicDetailService.getAllCivilStatus()).thenReturn(new ArrayList<>());
        Exception exception = assertThrows(BusinessRuleException.class, () -> {
            basicDetailController.getAllCivilStatus();
        });
        String expectedMessage = "Cannot find civil statuses";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    /**
     * test getGenderById method
     */
    @Test
    void testGetGenderByIdSuccess() {
        String id = "id";
        when(basicDetailService.getGenderById(id)).thenReturn(new GenderDto());
        ResponseEntity<GenderDto> responseEntity = basicDetailController.getGenderById(id);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(responseEntity.getBody());
    }

    /**
     * test getGenderById method throw BusinessRuleException
     * {@link com.informaticsint.starter.exception.BusinessRuleException}
     */
    @Test
    void testGetGenderByIdThrowBusinessRuleException() {
        String id = "id";
        when(basicDetailService.getGenderById(id)).thenReturn(null);
        Exception exception = assertThrows(BusinessRuleException.class, () -> {
            basicDetailController.getGenderById(id);
        });
        String expectedMessage = "Cannot find gender for the given id {} : id";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * test getAllGenders method
     */
    @Test
    void testGetAllGendersSuccess() {
        when(basicDetailService.getAllGenders()).thenReturn(Arrays.asList(new GenderDto()));
        ResponseEntity<List<GenderDto>> responseEntity = basicDetailController.getAllGenders();
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(responseEntity.getBody());
    }

    /**
     * test getAllGenders method throw BusinessRuleException
     * {@link com.informaticsint.starter.exception.BusinessRuleException}
     */
    @Test
    void testGetAllGendersThrowBusinessRuleException() {
        when(basicDetailService.getAllGenders()).thenReturn(new ArrayList<>());
        Exception exception = assertThrows(BusinessRuleException.class, () -> {
            basicDetailController.getAllGenders();
        });
        String expectedMessage = "Cannot find genders";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}