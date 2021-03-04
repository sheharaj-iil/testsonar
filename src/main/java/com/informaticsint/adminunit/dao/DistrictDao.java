package com.informaticsint.adminunit.dao;

import com.informaticsint.adminunit.model.District;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author : sheharaj
 * Informatics International Limited. All Rights Reserved
 */

@Repository("districtDao")
public interface DistrictDao extends MongoRepository<District, String> {
    Optional<District> findByIdAndActiveStatus(String identifier, Integer activeStatus);

    List<District> findAllByActiveStatus(Integer activeStatus);

    List<District> findAllByProvinceCodeAndActiveStatus(String provinceCode, Integer activeStatus);
}
