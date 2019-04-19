package org.terrence.testapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.terrence.testapp.domain.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
}