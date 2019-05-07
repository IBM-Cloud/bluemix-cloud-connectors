package org.terrence.testapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.terrence.testapp.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
}