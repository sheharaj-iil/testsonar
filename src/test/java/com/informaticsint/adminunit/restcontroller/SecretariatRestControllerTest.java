package com.informaticsint.adminunit.restcontroller;

import com.informaticsint.adminunit.dto.SecretariatDto;
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
class SecretariatRestControllerTest {

    private SecretariatRestController secretariatRestController;

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
        secretariatRestController = new SecretariatRestController(administrativeUnitService);
    }

    /**
     * execute after every test case
     */
    @AfterEach
    void tearDown() {
        secretariatRestController = null;
        administrativeUnitService = null;
    }

    /**
     * test saveSecretariat method
     */
    @Test
    void saveSecretariatTest() {
        ResponseEntity<Void> responseEntity = secretariatRestController.saveSecretariat(new SecretariatDto());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    /**
     * test updateSecretariat method
     */
    @Test
    void updateSecretariatTest() {
        String identifier = "identifier";
        SecretariatDto secretariatDto = new SecretariatDto();
        when(administrativeUnitService.updateSecretariat(identifier, secretariatDto)).thenReturn(true);
        ResponseEntity<Void> responseEntity = secretariatRestController.updateSecretariat(identifier, secretariatDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    /**
     * test updateSecretariat method throw BusinessRuleException
     * {@link com.informaticsint.starter.exception.BusinessRuleException}
     */
    @Test
    void updateSecretariatThrowBusinessRuleExceptionTest() {
        SecretariatDto secretariatDto = new SecretariatDto();
        when(administrativeUnitService.updateSecretariat("identifier", secretariatDto)).thenReturn(false);
        Exception exception = assertThrows(BusinessRuleException.class, () -> secretariatRestController.updateSecretariat("identifier", secretariatDto));
        String expectedMessage = "Can not find secretariat for the given id {} : identifier";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * test deleteSecretariat method
     */
    @Test
    void deleteSecretariatTest() {
        String identifier = "identifier";
        when(administrativeUnitService.deleteSecretariat(identifier)).thenReturn(true);
        ResponseEntity<Void> responseEntity = secretariatRestController.deleteSecretariat(identifier);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    /**
     * test deleteSecretariat method throw BusinessRuleException
     * {@link com.informaticsint.starter.exception.BusinessRuleException}
     */
    @Test
    void deleteSecretariatThrowBusinessRuleExceptionTest() {
        Exception exception = assertThrows(BusinessRuleException.class, () -> secretariatRestController.deleteSecretariat("identifier"));
        String expectedMessage = "Can not find secretariat for the given id {} : identifier";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * test findSecretariatByIdentifier method
     */
    @Test
    void findSecretariatByIdentifierTest() {
        String identifier = "identifier";
        when(administrativeUnitService.getSecretariatByIdentifier(identifier)).thenReturn(new SecretariatDto());
        ResponseEntity<SecretariatDto> responseEntity = secretariatRestController.findSecretariatByIdentifier(identifier);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    /**
     * test findSecretariatByIdentifier method throw BusinessRuleException
     * {@link com.informaticsint.starter.exception.BusinessRuleException}
     */
    @Test
    void findSecretariatByIdentifierThrowBusinessRuleExceptionTest() {
        String identifier = "identifier";
        when(administrativeUnitService.getSecretariatByIdentifier(identifier)).thenReturn(null);
        Exception exception = assertThrows(BusinessRuleException.class, () -> secretariatRestController.findSecretariatByIdentifier(identifier));
        String expectedMessage = "Can not find secretariat for the given id {} : identifier";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * test findAllSecretariats method
     */
    @Test
    void findAllSecretariatsTest() {
        when(administrativeUnitService.getAllSecretariats()).thenReturn(Collections.singletonList(new SecretariatDto()));
        ResponseEntity<List<SecretariatDto>> responseEntity = secretariatRestController.findAllSecretariats();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    /**
     * test findAllSecretariats method throw BusinessRuleException
     * {@link com.informaticsint.starter.exception.BusinessRuleException}
     */
    @Test
    void findAllSecretariatsThrowBusinessRuleExceptionTest() {
        when(administrativeUnitService.getAllSecretariats()).thenReturn(new ArrayList<>());
        Exception exception = assertThrows(BusinessRuleException.class, () -> secretariatRestController.findAllSecretariats());
        String expectedMessage = "Can not find secretariats";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * test findAllSecretariatsByDistrictCode method
     */
    @Test
    void findAllSecretariatsByDistrictCodeTest() {
        String districtCode = "test";
        when(administrativeUnitService.getAllSecretariatsByDistrictCode(districtCode)).thenReturn(Collections.singletonList(new SecretariatDto()));
        ResponseEntity<List<SecretariatDto>> responseEntity = secretariatRestController.findAllSecretariatsByDistrictCode(districtCode);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    /**
     * test findAllSecretariats method throw BusinessRuleException
     * {@link com.informaticsint.starter.exception.BusinessRuleException}
     */
    @Test
    void findAllSecretariatsByDistrictCodeThrowBusinessRuleExceptionTest() {
        String districtCode = "test";
        when(administrativeUnitService.getAllSecretariatsByDistrictCode(districtCode)).thenReturn(new ArrayList<>());
        Exception exception = assertThrows(BusinessRuleException.class, () -> secretariatRestController.findAllSecretariatsByDistrictCode(districtCode));
        String expectedMessage = "Can not find secretariats for the given districtCode{} : " + districtCode;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}