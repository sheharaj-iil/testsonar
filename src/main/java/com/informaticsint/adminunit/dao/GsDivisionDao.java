package com.informaticsint.adminunit.dao;

import com.informaticsint.adminunit.model.GsDivision;
import com.informaticsint.adminunit.model.Secretariat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author : sheharaj
 * Informatics International Limited. All Rights Reserved
 */

@Repository("gsDivisionDao")
public interface GsDivisionDao extends MongoRepository<GsDivision, String> {
    Optional<GsDivision> findByIdAndActiveStatus(String identifier, Integer activeStatus);

    List<GsDivision> findAllByActiveStatus(Integer activeStatus);

    List<GsDivision> findAllBySecretariatCodeAndActiveStatus(String districtCode, Integer activeStatus);
}
