package com.informaticsint.adminunit.restcontroller;

import com.informaticsint.adminunit.dto.GsDivisionDto;
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
class GsDivisionRestControllerTest {

    private GsDivisionRestController gsDivisionRestController;

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
        gsDivisionRestController = new GsDivisionRestController(administrativeUnitService);
    }

    /**
     * execute after every test case
     */
    @AfterEach
    void tearDown() {
        gsDivisionRestController = null;
        administrativeUnitService = null;
    }

    /**
     * test saveGsDivision method
     */
    @Test
    void saveGsDivisionTest() {
        ResponseEntity<Void> responseEntity = gsDivisionRestController.saveGsDivision(new GsDivisionDto());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    /**
     * test updateGsDivision method
     */
    @Test
    void updateGsDivisionTest() {
        String identifier = "identifier";
        GsDivisionDto gsDivisionDto = new GsDivisionDto();
        when(administrativeUnitService.updateGsDivision(identifier, gsDivisionDto)).thenReturn(true);
        ResponseEntity<Void> responseEntity = gsDivisionRestController.updateGsDivision(identifier, gsDivisionDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    /**
     * test updateGsDivision method throw BusinessRuleException
     * {@link BusinessRuleException}
     */
    @Test
    void updateGsDivisionThrowBusinessRuleExceptionTest() {
        GsDivisionDto gsDivisionDto = new GsDivisionDto();
        when(administrativeUnitService.updateGsDivision("identifier", gsDivisionDto)).thenReturn(false);
        Exception exception = assertThrows(BusinessRuleException.class, () -> gsDivisionRestController.updateGsDivision("identifier", gsDivisionDto));
        String expectedMessage = "Can not find gsDivision for the given id {} : identifier";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * test deleteGsDivision method
     */
    @Test
    void deleteGsDivisionTest() {
        String identifier = "identifier";
        when(administrativeUnitService.deleteGsDivision(identifier)).thenReturn(true);
        ResponseEntity<Void> responseEntity = gsDivisionRestController.deleteGsDivision(identifier);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    /**
     * test deleteGsDivision method throw BusinessRuleException
     * {@link BusinessRuleException}
     */
    @Test
    void deleteGsDivisionThrowBusinessRuleExceptionTest() {
        Exception exception = assertThrows(BusinessRuleException.class, () -> gsDivisionRestController.deleteGsDivision("identifier"));
        String expectedMessage = "Can not find gsDivision for the given id {} : identifier";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * test findGsDivisionByIdentifier method
     */
    @Test
    void findGsDivisionByIdentifierTest() {
        String identifier = "identifier";
        when(administrativeUnitService.getGsDivisionByIdentifier(identifier)).thenReturn(new GsDivisionDto());
        ResponseEntity<GsDivisionDto> responseEntity = gsDivisionRestController.findGsDivisionByIdentifier(identifier);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    /**
     * test findGsDivisionByIdentifier method throw BusinessRuleException
     * {@link BusinessRuleException}
     */
    @Test
    void findGsDivisionByIdentifierThrowBusinessRuleExceptionTest() {
        String identifier = "identifier";
        when(administrativeUnitService.getGsDivisionByIdentifier(identifier)).thenReturn(null);
        Exception exception = assertThrows(BusinessRuleException.class, () -> gsDivisionRestController.findGsDivisionByIdentifier(identifier));
        String expectedMessage = "Can not find gsDivision for the given id {} : identifier";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * test findAllGsDivisions method
     */
    @Test
    void findAllGsDivisionsTest() {
        when(administrativeUnitService.getAllGsDivisions()).thenReturn(Collections.singletonList(new GsDivisionDto()));
        ResponseEntity<List<GsDivisionDto>> responseEntity = gsDivisionRestController.findAllGsDivisions();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    /**
     * test findAllGsDivisions method throw BusinessRuleException
     * {@link BusinessRuleException}
     */
    @Test
    void findAllGsDivisionsThrowBusinessRuleExceptionTest() {
        when(administrativeUnitService.getAllGsDivisions()).thenReturn(new ArrayList<>());
        Exception exception = assertThrows(BusinessRuleException.class, () -> gsDivisionRestController.findAllGsDivisions());
        String expectedMessage = "Can not find gsDivisions";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * test findAllGsDivisionsBySecretariatCode method
     */
    @Test
    void findAllGsDivisionsBySecretariatCodeTest() {
        String secretariatCode = "test";
        when(administrativeUnitService.getAllGsDivisionsBySecretariatCode(secretariatCode)).thenReturn(Collections.singletonList(new GsDivisionDto()));
        ResponseEntity<List<GsDivisionDto>> responseEntity = gsDivisionRestController.findAllGsDivisionsBySecretariatCode(secretariatCode);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    /**
     * test findAllGsDivisions method throw BusinessRuleException
     * {@link BusinessRuleException}
     */
    @Test
    void findAllGsDivisionsBySecretariatCodeThrowBusinessRuleExceptionTest() {
        String secretariatCode = "test";
        when(administrativeUnitService.getAllGsDivisionsBySecretariatCode(secretariatCode)).thenReturn(new ArrayList<>());
        Exception exception = assertThrows(BusinessRuleException.class, () -> gsDivisionRestController.findAllGsDivisionsBySecretariatCode(secretariatCode));
        String expectedMessage = "Can not find gsDivisions for the given secretariatCode{} : " + secretariatCode;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}