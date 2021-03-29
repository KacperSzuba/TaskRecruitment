package com.RecruitmentTask.config;

import com.RecruitmentTask.model.Hobby;
import com.RecruitmentTask.model.Person;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
class CsvReader {
    private static final Logger logger  = LoggerFactory.getLogger(CsvReader.class);

    @Value("${person_db.path}")
    private String path;

    List<Person> readPersons(){
        try{
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(inputStream));
            Reader reader = new BufferedReader(inputStreamReader);
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .build();

            return parsePersons(csvReader.readAll());
        }
        catch (Exception ex){
            logger.error("Error during reading file!", ex);
            return Collections.emptyList();
        }
    }

    private List<Person> parsePersons(List<String[]> list){
        List<Person> personList = new ArrayList<>();

        for (String[] line : list) {
            parsePerson(personList, line);
        }

        logger.info("File has been parsed!");

        return personList;
    }

    private void parsePerson(List<Person> personList, String[] line){
        try{
            String name = line[0];
            int age = Integer.parseInt(line[1]);
            Hobby hobby = Hobby.valueOf(line[2]);

            personList.stream()
                    .filter(p -> p.getName().equals(name))
                    .findFirst()
                    .ifPresentOrElse(p2 -> p2.addHobby(hobby), () -> personList.add(new Person(name, age, hobby)));
        }
        catch (Exception exception){
            logger.warn("Line has been skipped!");
        }
    }
}
