package com.RecruitmentTask.config;

import com.RecruitmentTask.model.Person;
import com.RecruitmentTask.model.PersonRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
class PersonInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final PersonRepository personRepository;
    private final CsvReader csvReader;

    PersonInitializer(PersonRepository personRepository, CsvReader csvReader) {
        this.personRepository = personRepository;
        this.csvReader = csvReader;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<Person> personList = csvReader.readPersons();
        personRepository.saveAll(personList);
    }
}
