package com.RecruitmentTask.services;

import com.RecruitmentTask.model.Hobby;
import com.RecruitmentTask.model.Person;
import com.RecruitmentTask.model.PersonRepository;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JokeGeneratorServiceTest {

    private JokeGeneratorService jokeGeneratorService;

    @BeforeEach
    void setup(){
        //given
        var mockPersonRepository = mock(PersonRepository.class);
        when(mockPersonRepository.getByName("Kacper")).thenReturn(Optional.of(new Person("Kacper", 22, Hobby.Traveling)));

        jokeGeneratorService = new JokeGeneratorService("http://api.icndb.com/jokes/random", mockPersonRepository);
    }

    @Test
    @Description("For the name of the person not in the repository, the test should throw IllegalArgumentException.")
    void generateJokeShouldThrowIllegalArgumentException(){
        //when + then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> jokeGeneratorService.generateJoke("Anna"));
    }

    @Test
    @Description("For the name of the person in the repository, the test should pass.")
    void generateJokeShouldPass() {
        //when + then
        assertThat(jokeGeneratorService.generateJoke("Kacper")).contains("Kacper", "22");
    }

    @Nested
    class JokeGeneratorServiceTestNested{
        @Test
        @Description("The test has changed url of the site generating the jokes. For a person in the repository of the test should throw exception.")
        void generateJokeWithWrongURIShouldFail() {
            //given
            var mockPersonRepository = mock(PersonRepository.class);
            when(mockPersonRepository.getByName("Kacper")).thenReturn(Optional.of(new Person("Kacper", 22, Hobby.Traveling)));

            jokeGeneratorService = new JokeGeneratorService("http://random-url", mockPersonRepository);
            //when + then
            assertThatThrownBy(() -> jokeGeneratorService.generateJoke("Kacper")).isInstanceOf(Exception.class);
        }

    }
}