package com.informaticsint.adminunit.dao;

import com.informaticsint.adminunit.model.Secretariat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author : sheharaj
 * Informatics International Limited. All Rights Reserved
 */

@Repository("secretariatDao")
public interface SecretariatDao extends MongoRepository<Secretariat, String> {
    Optional<Secretariat> findByIdAndActiveStatus(String identifier, Integer activeStatus);

    List<Secretariat> findAllByActiveStatus(Integer activeStatus);

    List<Secretariat> findAllByDistrictCodeAndActiveStatus(String districtCode, Integer activeStatus);
}
