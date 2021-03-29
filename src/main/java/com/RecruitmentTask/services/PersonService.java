package com.RecruitmentTask.services;

import com.RecruitmentTask.model.Hobby;
import com.RecruitmentTask.model.Person;
import com.RecruitmentTask.model.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersonList(){
        return personRepository.findAll();
    }

    public List<Person> getPersonListByAgeIsGreaterThan(int age){
        return personRepository.getAllByAgeIsGreaterThan(age);
    }

    public int getAverageAge() {
        return personRepository.getAverageAge();
    }

    public List<Hobby> getPersonsHobbies() {
        return personRepository.getPersonsHobbies();
    }
}
