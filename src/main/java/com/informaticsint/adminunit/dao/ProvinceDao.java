package com.informaticsint.adminunit.dao;

import com.informaticsint.adminunit.model.Province;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author : sheharaj
 * Informatics International Limited. All Rights Reserved
 */

@Repository("provinceDao")
public interface ProvinceDao extends MongoRepository<Province, String> {
    Optional<Province> findByIdAndActiveStatus(String identifier, Integer activeStatus);

    List<Province> findAllByActiveStatus(Integer activeStatus);
}
