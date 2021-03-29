package com.RecruitmentTask.model;

import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByName;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @CsvBindByName
    private String name;

    @Column(nullable = false)
    @CsvBindByName
    private int age;

    @Column(name = "hobby")
    @ElementCollection(targetClass = Hobby.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "hobbies", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    @CsvBindAndSplitByPosition(elementType = Hobby.class, position = 2)
    private Set<Hobby> hobbies = new HashSet<>();

    public Person(){}

    public Person(String name, int age, Hobby hobby) {
        this.name = name;
        this.age = age;
        addHobby(hobby);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Set<Hobby> getHobbies() {
        return Collections.unmodifiableSet(hobbies);
    }

    public void addHobby(final Hobby hobby){
        hobbies.add(hobby);
    }
}