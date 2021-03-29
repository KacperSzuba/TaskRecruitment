package com.RecruitmentTask.services;

import com.RecruitmentTask.model.Person;
import com.RecruitmentTask.model.PersonRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class JokeGeneratorService {

    private final String jokeGeneratorURL;
    private final PersonRepository personRepository;

    public JokeGeneratorService(@Value("${joke.generator.url}") String jokeGeneratorURL, PersonRepository personRepository) {
        this.jokeGeneratorURL = jokeGeneratorURL;
        this.personRepository = personRepository;
    }

    public String generateJoke(String name){
        Person person = personRepository.getByName(name)
                .orElseThrow(() -> new IllegalArgumentException("The person named " + name + " doesn't exist"));

        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(jokeGeneratorURL)
                .queryParam("firstName", person.getName())
                .queryParam("lastName", person.getAge());

        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);

        return response.getBody();
    }
}
