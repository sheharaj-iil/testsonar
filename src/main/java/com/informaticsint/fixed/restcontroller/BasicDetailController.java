package com.informaticsint.fixed.restcontroller;

import com.informaticsint.common.builder.CustomDataBuilder;
import com.informaticsint.fixed.dto.civilstatus.CivilStatusDto;
import com.informaticsint.fixed.dto.gender.GenderDto;
import com.informaticsint.fixed.service.BasicDetailService;
import com.informaticsint.starter.interceptor.ErrorResponseProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Steffan Devotta
 * @version : 0.1
 * @date : 2/23/21
 * @copyright : © Informatics International Limited. All Rights Reserved
 */
@ErrorResponseProcess
@RestController
@RequestMapping("/basic-detail")
public class BasicDetailController {

    private final BasicDetailService basicDetailService;

    /**
     * constructor injection
     * @param basicDetailService
     */
    @Autowired
    public BasicDetailController(BasicDetailService basicDetailService) {
        this.basicDetailService = basicDetailService;
    }


    /**
     * get method to retrieve civil status
     * @param civilStatusId
     * @return ResponseEntity<CivilStatusGetDto>
     */
    @GetMapping("/civil-status/{civilStatusId}")
    public ResponseEntity<CivilStatusDto> getCivilStatusById(@PathVariable("civilStatusId") String civilStatusId){
        CivilStatusDto civilStatusDto = basicDetailService.getCivilStatusById(civilStatusId);
        if(civilStatusDto == null){
            throw CustomDataBuilder.buildBusinessRuleException("CIVIL_STATUS_NOT_AVAILABLE", "Cannot find civil status for the given id {} : " + civilStatusId);
        }
        return new ResponseEntity<>(civilStatusDto, HttpStatus.OK);
    }

    /**
     * get method to retrieve civil status
     * @return ResponseEntity<CivilStatusGetDto>
     */
    @GetMapping("/civil-status")
    public ResponseEntity<List<CivilStatusDto>> getAllCivilStatus(){
        List<CivilStatusDto> civilStatusGetAll = basicDetailService.getAllCivilStatus();
        if(civilStatusGetAll.isEmpty()){
            throw CustomDataBuilder.buildBusinessRuleException("CIVIL_STATUS_NOT_AVAILABLE", "Cannot find civil statuses");
        }
        return new ResponseEntity<>(civilStatusGetAll, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * get method to retrieve gender by id
     * @param genderId
     * @return ResponseEntity<GenderDto>
     */
    @GetMapping("/gender/{genderId}")
    public ResponseEntity<GenderDto> getGenderById(@PathVariable("genderId") String genderId){
        GenderDto genderDto = basicDetailService.getGenderById(genderId);
        if(genderDto == null){
            throw CustomDataBuilder.buildBusinessRuleException("GENDER_NOT_AVAILABLE", "Cannot find gender for the given id {} : " + genderId);
        }
        return new ResponseEntity<>(genderDto, HttpStatus.OK);
    }

    /**
     * get method to retrieve all genders
     * @return ResponseEntity<List<GenderDto>>
     */
    @GetMapping("/gender")
    public ResponseEntity<List<GenderDto>> getAllGenders(){
        List<GenderDto> genderDtoGetAll = basicDetailService.getAllGenders();
        if(genderDtoGetAll.isEmpty()){
            throw CustomDataBuilder.buildBusinessRuleException("GENDER_NOT_AVAILABLE", "Cannot find genders");
        }
        return new ResponseEntity<>(genderDtoGetAll, new HttpHeaders(), HttpStatus.OK);
    }

}
