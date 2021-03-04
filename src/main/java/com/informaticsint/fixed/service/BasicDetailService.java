package com.informaticsint.fixed.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.informaticsint.common.constants.ApplicationConstants;
import com.informaticsint.fixed.dao.CivilStatusDao;
import com.informaticsint.fixed.dao.GenderDao;
import com.informaticsint.fixed.dto.civilstatus.CivilStatusDto;
import com.informaticsint.fixed.dto.gender.GenderDto;
import com.informaticsint.fixed.model.CivilStatus;
import com.informaticsint.fixed.model.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : Steffan Devotta
 * @version : 0.1
 * @date : 2/23/21
 * @copyright : © Informatics International Limited. All Rights Reserved
 */
@Service("basicDetailService")
public class BasicDetailService {

    private final CivilStatusDao civilStatusDao;
    private final ObjectMapper objectMapper;
    private final GenderDao genderDao;

    /**
     * constructor injection
     * @param civilStatusDao
     * @param objectMapper
     * @param genderDao
     */
    @Autowired
    public BasicDetailService(CivilStatusDao civilStatusDao, ObjectMapper objectMapper, GenderDao genderDao) {
        this.civilStatusDao = civilStatusDao;
        this.objectMapper = objectMapper;
        this.genderDao = genderDao;
    }


    /**
     * method to retrieve civil status
     * @param civilStatusId
     * @return CivilStatusGetDto
     */
    public CivilStatusDto getCivilStatusById(String civilStatusId) {
        Optional<CivilStatus> optionalCivilStatus = civilStatusDao.findByIdAndActiveStatus(civilStatusId, ApplicationConstants.ACTIVE_STATUS);
            return objectMapper.convertValue(optionalCivilStatus.orElse(null), CivilStatusDto.class);
    }

    /**
     * method to retrieve all civil status
     * @return List<CivilStatusGetDto>
     */
    public List<CivilStatusDto> getAllCivilStatus() {
        List<CivilStatus> civilStatusList = civilStatusDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS);
            return civilStatusList.parallelStream().map(civilStatus ->
                    objectMapper.convertValue(civilStatus, CivilStatusDto.class)
            ).collect(Collectors.toList());
    }

    /**
     * method to retrieve gender
     * @param genderId
     * @return CivilStatusDto
     */
    public GenderDto getGenderById(String genderId) {
        Optional<Gender> optionalGender = genderDao.findByIdAndActiveStatus(genderId, ApplicationConstants.ACTIVE_STATUS);
        return objectMapper.convertValue(optionalGender.orElse(null), GenderDto.class);
    }

    /**
     * method to retrieve all civil status
     * @return List<CivilStatusGetDto>
     */
    public List<GenderDto> getAllGenders() {
        List<Gender> genderList = genderDao.findAllByActiveStatus(ApplicationConstants.ACTIVE_STATUS);
        return genderList.parallelStream().map(genderModel ->
                objectMapper.convertValue(genderModel, GenderDto.class)
        ).collect(Collectors.toList());
    }
}
