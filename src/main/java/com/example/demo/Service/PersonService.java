package com.example.demo.Service;

import com.example.demo.Model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonService {
    private List<Person> persons = new ArrayList<>();

    public List<Person> all() {
        return persons;
    }

    public void save(Person person) {
        persons.add(person);
    }

    public void delete(int id) {
        persons.removeIf(person -> person.getId() == id);
    }
}
