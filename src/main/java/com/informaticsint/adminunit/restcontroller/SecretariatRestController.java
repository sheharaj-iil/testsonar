package com.informaticsint.adminunit.restcontroller;

import com.informaticsint.adminunit.dto.SecretariatDto;
import com.informaticsint.adminunit.service.AdministrativeUnitService;
import com.informaticsint.common.builder.CustomDataBuilder;
import com.informaticsint.starter.interceptor.ErrorResponseProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : sheharaj
 * Informatics International Limited. All Rights Reserved
 */

@RestController
@RequestMapping("/secretariat")
@ErrorResponseProcess
public class SecretariatRestController {

    private static final String ADMIN_SECRETARIAT_NOT_AVAILABLE = "ADMIN_SECRETARIAT_NOT_AVAILABLE";
    private static final String CAN_NOT_FIND_SECRETARIAT_FOR_THE_GIVEN_ID = "Can not find secretariat for the given id {} : ";
    private final AdministrativeUnitService administrativeUnitService;

    /**
     * constructor injection
     *
     * @param administrativeUnitService
     */
    @Autowired
    public SecretariatRestController(AdministrativeUnitService administrativeUnitService) {
        this.administrativeUnitService = administrativeUnitService;
    }

    /**
     * post method for save secretariat
     *
     * @param secretariatDto
     * @return ResponseEntity<Void>
     */
    @PostMapping
    public ResponseEntity<Void> saveSecretariat(@RequestBody SecretariatDto secretariatDto) {
        administrativeUnitService.saveSecretariat(secretariatDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * put method for update secretariat
     *
     * @param identifier
     * @param secretariatDto
     * @return ResponseEntity<Void>
     */
    @PutMapping("/{identifier}")
    public ResponseEntity<Void> updateSecretariat(@PathVariable("identifier") String identifier, @RequestBody SecretariatDto secretariatDto) {
        boolean isUpdated = administrativeUnitService.updateSecretariat(identifier, secretariatDto);
        if (isUpdated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw CustomDataBuilder.buildBusinessRuleException(ADMIN_SECRETARIAT_NOT_AVAILABLE, CAN_NOT_FIND_SECRETARIAT_FOR_THE_GIVEN_ID + identifier);
        }
    }

    /**
     * put method for delete secretariat
     *
     * @param identifier
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{identifier}")
    public ResponseEntity<Void> deleteSecretariat(@PathVariable("identifier") String identifier) {
        boolean isDeleted = administrativeUnitService.deleteSecretariat(identifier);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw CustomDataBuilder.buildBusinessRuleException(ADMIN_SECRETARIAT_NOT_AVAILABLE, CAN_NOT_FIND_SECRETARIAT_FOR_THE_GIVEN_ID + identifier);
        }
    }

    /**
     * get method for retrieve specific secretariat
     *
     * @param identifier
     * @return ResponseEntity<SecretariatDto>
     */
    @GetMapping("/{identifier}")
    public ResponseEntity<SecretariatDto> findSecretariatByIdentifier(@PathVariable("identifier") String identifier) {
        SecretariatDto secretariatDto = administrativeUnitService.getSecretariatByIdentifier(identifier);
        if (secretariatDto != null) {
            return new ResponseEntity<>(secretariatDto, HttpStatus.OK);
        } else {
            throw CustomDataBuilder.buildBusinessRuleException(ADMIN_SECRETARIAT_NOT_AVAILABLE, CAN_NOT_FIND_SECRETARIAT_FOR_THE_GIVEN_ID + identifier);
        }
    }

    /**
     * get method for retrieve all secretariats
     *
     * @return ResponseEntity<List < SecretariatDto>>
     */
    @GetMapping
    public ResponseEntity<List<SecretariatDto>> findAllSecretariats() {
        List<SecretariatDto> secretariatDtos = administrativeUnitService.getAllSecretariats();
        if (!secretariatDtos.isEmpty()) {
            return new ResponseEntity<>(secretariatDtos, HttpStatus.OK);
        } else {
            throw CustomDataBuilder.buildBusinessRuleException(ADMIN_SECRETARIAT_NOT_AVAILABLE, "Can not find secretariats");
        }
    }

    /**
     * get method for retrieve all secretariats by district code
     *
     * @return ResponseEntity<List < SecretariatDto>>
     */
    @GetMapping("/district/{code}")
    public ResponseEntity<List<SecretariatDto>> findAllSecretariatsByDistrictCode(@PathVariable("code") String districtCode) {
        List<SecretariatDto> secretariatDtos = administrativeUnitService.getAllSecretariatsByDistrictCode(districtCode);
        if (!secretariatDtos.isEmpty()) {
            return new ResponseEntity<>(secretariatDtos, HttpStatus.OK);
        } else {
            throw CustomDataBuilder.buildBusinessRuleException(ADMIN_SECRETARIAT_NOT_AVAILABLE, "Can not find secretariats for the given districtCode{} : " + districtCode);
        }
    }
}
