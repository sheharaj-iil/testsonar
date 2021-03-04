package com.informaticsint.adminunit.restcontroller;

import com.informaticsint.adminunit.dto.GsDivisionDto;
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
@RequestMapping("/gs-division")
@ErrorResponseProcess
public class GsDivisionRestController {

    private static final String GS_DIVISION_NOT_AVAILABLE = "GS_DIVISION_NOT_AVAILABLE";
    private static final String CAN_NOT_FIND_GS_DIVISION_FOR_THE_GIVEN_ID = "Can not find gsDivision for the given id {} : ";
    private final AdministrativeUnitService administrativeUnitService;

    /**
     * constructor injection
     *
     * @param administrativeUnitService AdministrativeUnitService
     */
    @Autowired
    public GsDivisionRestController(AdministrativeUnitService administrativeUnitService) {
        this.administrativeUnitService = administrativeUnitService;
    }

    /**
     * post method for save gsDivision
     *
     * @param gsDivisionDto GsDivisionDto
     * @return ResponseEntity<Void>
     */
    @PostMapping
    public ResponseEntity<Void> saveGsDivision(@RequestBody GsDivisionDto gsDivisionDto) {
        administrativeUnitService.saveGsDivision(gsDivisionDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * put method for update gsDivision
     *
     * @param identifier String
     * @param gsDivisionDto GsDivisionDto
     * @return ResponseEntity<Void>
     */
    @PutMapping("/{identifier}")
    public ResponseEntity<Void> updateGsDivision(@PathVariable("identifier") String identifier, @RequestBody GsDivisionDto gsDivisionDto) {
        boolean isUpdated = administrativeUnitService.updateGsDivision(identifier, gsDivisionDto);
        if (isUpdated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw CustomDataBuilder.buildBusinessRuleException(GS_DIVISION_NOT_AVAILABLE, CAN_NOT_FIND_GS_DIVISION_FOR_THE_GIVEN_ID + identifier);
        }
    }

    /**
     * put method for delete gsDivision
     *
     * @param identifier String
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{identifier}")
    public ResponseEntity<Void> deleteGsDivision(@PathVariable("identifier") String identifier) {
        boolean isDeleted = administrativeUnitService.deleteGsDivision(identifier);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw CustomDataBuilder.buildBusinessRuleException(GS_DIVISION_NOT_AVAILABLE, CAN_NOT_FIND_GS_DIVISION_FOR_THE_GIVEN_ID + identifier);
        }
    }

    /**
     * get method for retrieve specific gsDivision
     *
     * @param identifier String
     * @return ResponseEntity<GsDivisionDto>
     */
    @GetMapping("/{identifier}")
    public ResponseEntity<GsDivisionDto> findGsDivisionByIdentifier(@PathVariable("identifier") String identifier) {
        GsDivisionDto gsDivisionDto = administrativeUnitService.getGsDivisionByIdentifier(identifier);
        if (gsDivisionDto != null) {
            return new ResponseEntity<>(gsDivisionDto, HttpStatus.OK);
        } else {
            throw CustomDataBuilder.buildBusinessRuleException(GS_DIVISION_NOT_AVAILABLE, CAN_NOT_FIND_GS_DIVISION_FOR_THE_GIVEN_ID + identifier);
        }
    }

    /**
     * get method for retrieve all gsDivisions
     *
     * @return ResponseEntity<List < GsDivisionDto>>
     */
    @GetMapping
    public ResponseEntity<List<GsDivisionDto>> findAllGsDivisions() {
        List<GsDivisionDto> gsDivisionDtos = administrativeUnitService.getAllGsDivisions();
        if (!gsDivisionDtos.isEmpty()) {
            return new ResponseEntity<>(gsDivisionDtos, HttpStatus.OK);
        } else {
            throw CustomDataBuilder.buildBusinessRuleException(GS_DIVISION_NOT_AVAILABLE, "Can not find gsDivisions");
        }
    }

    /**
     * get method for retrieve all gsDivisions by secretariat code
     *
     * @return ResponseEntity<List < GsDivisionDto>>
     */
    @GetMapping("/secretariat/{code}")
    public ResponseEntity<List<GsDivisionDto>> findAllGsDivisionsBySecretariatCode(@PathVariable("code") String secretariatCode) {
        List<GsDivisionDto> gsDivisionDtos = administrativeUnitService.getAllGsDivisionsBySecretariatCode(secretariatCode);
        if (!gsDivisionDtos.isEmpty()) {
            return new ResponseEntity<>(gsDivisionDtos, HttpStatus.OK);
        } else {
            throw CustomDataBuilder.buildBusinessRuleException(GS_DIVISION_NOT_AVAILABLE, "Can not find gsDivisions for the given secretariatCode{} : " + secretariatCode);
        }
    }
}
