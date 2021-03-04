package com.informaticsint.adminunit.restcontroller;

import com.informaticsint.adminunit.dto.ProvinceDto;
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
@RequestMapping("/province")
@ErrorResponseProcess
public class ProvinceRestController {

    private static final String ADMIN_PROVINCE_NOT_AVAILABLE = "ADMIN_PROVINCE_NOT_AVAILABLE";
    private static final String CAN_NOT_FIND_PROVINCE_FOR_THE_GIVEN_ID = "Can not find province for the given id {} : ";
    private final AdministrativeUnitService administrativeUnitService;

    /**
     * constructor injection
     *
     * @param administrativeUnitService AdministrativeUnitService
     */
    @Autowired
    public ProvinceRestController(AdministrativeUnitService administrativeUnitService) {
        this.administrativeUnitService = administrativeUnitService;
    }

    /**
     * post method for save province
     *
     * @param provinceDto ProvinceDto
     * @return ResponseEntity<Void>
     */
    @PostMapping
    public ResponseEntity<Void> saveProvince(@RequestBody ProvinceDto provinceDto) {
        administrativeUnitService.saveProvince(provinceDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * put method for update province
     *
     * @param identifier
     * @param provinceDto
     * @return ResponseEntity<Void>
     */
    @PutMapping("/{identifier}")
    public ResponseEntity<Void> updateProvince(@PathVariable("identifier") String identifier, @RequestBody ProvinceDto provinceDto) {
        boolean isUpdated = administrativeUnitService.updateProvince(identifier, provinceDto);
        if (isUpdated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw CustomDataBuilder.buildBusinessRuleException(ADMIN_PROVINCE_NOT_AVAILABLE, CAN_NOT_FIND_PROVINCE_FOR_THE_GIVEN_ID + identifier);
        }
    }

    /**
     * put method for delete province
     *
     * @param identifier
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{identifier}")
    public ResponseEntity<Void> deleteProvince(@PathVariable("identifier") String identifier) {
        boolean isDeleted = administrativeUnitService.deleteProvince(identifier);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw CustomDataBuilder.buildBusinessRuleException(ADMIN_PROVINCE_NOT_AVAILABLE, CAN_NOT_FIND_PROVINCE_FOR_THE_GIVEN_ID + identifier);
        }
    }

    /**
     * get method for retrieve specific province
     *
     * @param identifier
     * @return ResponseEntity<ProvinceDto>
     */
    @GetMapping("/{identifier}")
    public ResponseEntity<ProvinceDto> findProvinceByIdentifier(@PathVariable("identifier") String identifier) {
        ProvinceDto provinceDto = administrativeUnitService.getProvinceByIdentifier(identifier);
        if (provinceDto != null) {
            return new ResponseEntity<>(provinceDto, HttpStatus.OK);
        } else {
            throw CustomDataBuilder.buildBusinessRuleException(ADMIN_PROVINCE_NOT_AVAILABLE, CAN_NOT_FIND_PROVINCE_FOR_THE_GIVEN_ID + identifier);
        }
    }

    /**
     * get method for retrieve all provinces
     *
     * @return ResponseEntity<List < ProvinceDto>>
     */
    @GetMapping
    public ResponseEntity<List<ProvinceDto>> findAllProvinces() {
        List<ProvinceDto> provinceDtos = administrativeUnitService.getAllProvinces();
        if (!provinceDtos.isEmpty()) {
            return new ResponseEntity<>(provinceDtos, HttpStatus.OK);
        } else {
            throw CustomDataBuilder.buildBusinessRuleException(ADMIN_PROVINCE_NOT_AVAILABLE, "Can not find provinces");
        }
    }
}
