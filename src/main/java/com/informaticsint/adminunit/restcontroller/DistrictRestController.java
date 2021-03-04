package com.informaticsint.adminunit.restcontroller;

import com.informaticsint.adminunit.dto.DistrictDto;
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
@RequestMapping("/district")
@ErrorResponseProcess
public class DistrictRestController {

    private static final String ADMIN_DISTRICT_NOT_AVAILABLE = "ADMIN_DISTRICT_NOT_AVAILABLE";
    private static final String CAN_NOT_FIND_DISTRICT_FOR_THE_GIVEN_ID = "Can not find district for the given id {} : ";
    private final AdministrativeUnitService administrativeUnitService;

    /**
     * constructor injection
     *
     * @param administrativeUnitService
     */
    @Autowired
    public DistrictRestController(AdministrativeUnitService administrativeUnitService) {
        this.administrativeUnitService = administrativeUnitService;
    }

    /**
     * post method for save district
     *
     * @param districtDto
     * @return ResponseEntity<Void>
     */
    @PostMapping
    public ResponseEntity<Void> saveDistrict(@RequestBody DistrictDto districtDto) {
        administrativeUnitService.saveDistrict(districtDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * put method for update district
     *
     * @param identifier
     * @param districtDto
     * @return ResponseEntity<Void>
     */
    @PutMapping("/{identifier}")
    public ResponseEntity<Void> updateDistrict(@PathVariable("identifier") String identifier, @RequestBody DistrictDto districtDto) {
        boolean isUpdated = administrativeUnitService.updateDistrict(identifier, districtDto);
        if (isUpdated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw CustomDataBuilder.buildBusinessRuleException(ADMIN_DISTRICT_NOT_AVAILABLE, CAN_NOT_FIND_DISTRICT_FOR_THE_GIVEN_ID + identifier);
        }
    }

    /**
     * put method for delete district
     *
     * @param identifier
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{identifier}")
    public ResponseEntity<Void> deleteDistrict(@PathVariable("identifier") String identifier) {
        boolean isDeleted = administrativeUnitService.deleteDistrict(identifier);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw CustomDataBuilder.buildBusinessRuleException(ADMIN_DISTRICT_NOT_AVAILABLE, CAN_NOT_FIND_DISTRICT_FOR_THE_GIVEN_ID + identifier);
        }
    }

    /**
     * get method for retrieve specific district
     *
     * @param identifier
     * @return ResponseEntity<DistrictDto>
     */
    @GetMapping("/{identifier}")
    public ResponseEntity<DistrictDto> findDistrictByIdentifier(@PathVariable("identifier") String identifier) {
        DistrictDto districtDto = administrativeUnitService.getDistrictByIdentifier(identifier);
        if (districtDto != null) {
            return new ResponseEntity<>(districtDto, HttpStatus.OK);
        } else {
            throw CustomDataBuilder.buildBusinessRuleException(ADMIN_DISTRICT_NOT_AVAILABLE, CAN_NOT_FIND_DISTRICT_FOR_THE_GIVEN_ID + identifier);
        }
    }

    /**
     * get method for retrieve all districts
     *
     * @return ResponseEntity<List < DistrictDto>>
     */
    @GetMapping
    public ResponseEntity<List<DistrictDto>> findAllDistricts() {
        List<DistrictDto> districtDtos = administrativeUnitService.getAllDistricts();
        if (!districtDtos.isEmpty()) {
            return new ResponseEntity<>(districtDtos, HttpStatus.OK);
        } else {
            throw CustomDataBuilder.buildBusinessRuleException(ADMIN_DISTRICT_NOT_AVAILABLE, "Can not find districts");
        }
    }

    /**
     * get method for retrieve all districts by province code
     *
     * @return ResponseEntity<List < DistrictDto>>
     */
    @GetMapping("/province/{code}")
    public ResponseEntity<List<DistrictDto>> findAllDistrictsByProvinceCode(@PathVariable("code") String provinceCode) {
        List<DistrictDto> districtDtos = administrativeUnitService.getAllDistrictsByProvinceCode(provinceCode);
        if (!districtDtos.isEmpty()) {
            return new ResponseEntity<>(districtDtos, HttpStatus.OK);
        } else {
            throw CustomDataBuilder.buildBusinessRuleException(ADMIN_DISTRICT_NOT_AVAILABLE, "Can not find districts for the given provinceCode{} : " + provinceCode);
        }
    }
}
