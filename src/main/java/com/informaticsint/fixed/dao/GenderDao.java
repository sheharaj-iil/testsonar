package com.informaticsint.fixed.dao;

import com.informaticsint.fixed.model.Gender;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author : Steffan Devotta
 * @version : 0.1
 * @date : 2/23/21
 * @copyright : © Informatics International Limited. All Rights Reserved
 */
@Repository
public interface GenderDao extends MongoRepository<Gender,String> {
    Optional<Gender> findByIdAndActiveStatus(String genderId, Integer activeStatus);

    List<Gender> findAllByActiveStatus(Integer activeStatus);
}
