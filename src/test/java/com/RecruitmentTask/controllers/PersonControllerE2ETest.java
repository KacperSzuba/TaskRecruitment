package com.RecruitmentTask.controllers;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Description("For the name of the person in the repository, the test should pass.")
    void generateJokeShouldPass() throws Exception {
        this.mockMvc.perform(get("/person/joke?name=Anna")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @Description("For the name of the person not in the repository, the test should fail.")
    void generateJokeShouldFail() throws Exception {
        this.mockMvc.perform(get("/person/joke?name=Kacper")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void getPersonListShouldPass() throws Exception {
        this.mockMvc.perform(get("/persons")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getPersonListByAgeIsGreaterThanShouldPass() throws Exception {
        this.mockMvc.perform(get("/persons/details?age=40")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getPersonListAverageAgeShouldPass() throws Exception {
        this.mockMvc.perform(get("/persons/age/average")).andDo(print()).andExpect(status().isOk());
    }
    @Test
    void getPersonListHobbiesShouldPass() throws Exception {
        this.mockMvc.perform(get("/persons/hobbies")).andDo(print()).andExpect(status().isOk());
    }
}