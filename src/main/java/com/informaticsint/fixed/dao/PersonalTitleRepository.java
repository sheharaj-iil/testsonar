package com.informaticsint.fixed.dao;

import com.informaticsint.fixed.model.PersonalTitleModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Shane Rathnayake
 * @version : 0.1
 * @date : 2/23/21
 * @copyright : © Informatics International Limited. All Rights Reserved
 */
@Repository
public interface PersonalTitleRepository extends MongoRepository<PersonalTitleModel, String> {
}
