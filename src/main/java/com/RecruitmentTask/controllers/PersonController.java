package com.RecruitmentTask.controllers;

import com.RecruitmentTask.model.Hobby;
import com.RecruitmentTask.model.Person;
import com.RecruitmentTask.services.JokeGeneratorService;
import com.RecruitmentTask.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class PersonController {
    private static final Logger logger  = LoggerFactory.getLogger(PersonController.class);

    private final PersonService personService;
    private final JokeGeneratorService jokeGeneratorService;

    PersonController(PersonService personService, JokeGeneratorService jokeGeneratorService) {
        this.personService = personService;
        this.jokeGeneratorService = jokeGeneratorService;
    }

    @GetMapping("/person/joke")
    public ResponseEntity<String> generateJoke(@RequestParam String name){
        try{
            return ResponseEntity.ok(jokeGeneratorService.generateJoke(name));
        }
        catch (Exception ex){
            logger.error("Error during generating joke!", ex);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getPersonList(){
        return ResponseEntity.ok(personService.getPersonList());
    }

    @GetMapping("/persons/details")
    public ResponseEntity<List<Person>> getPersonListByAgeIsGreaterThan(@RequestParam int age){
        return ResponseEntity.ok(personService.getPersonListByAgeIsGreaterThan(age));
    }

    @GetMapping("/persons/age/average")
    public ResponseEntity<Integer> getPersonListAverageAge(){
        return ResponseEntity.ok(personService.getAverageAge());
    }

    @GetMapping("/persons/hobbies")
    public ResponseEntity<List<Hobby>> getPersonListHobbies(){
        return ResponseEntity.ok(personService.getPersonsHobbies());
    }
}
