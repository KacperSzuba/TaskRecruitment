package com.RecruitmentTask.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, String> {
    List<Person> findAll();
    List<Person> getAllByAgeIsGreaterThan(int age);

    @Query(value = "select avg(age) from Person")
    int getAverageAge();

    Optional<Person> getByName(String name);

    @Query(value = "SELECT distinct h.hobby FROM hobbies h", nativeQuery = true)
    List<Hobby> getPersonsHobbies();
}
