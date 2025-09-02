package com.example.demo.Model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

@Data
public class Person {
    static int count = 0;
    private int id;

    public Person() {
        this.id = ++count;
    }

    @NotBlank(message = "Name is required")
    private String name;
    @Min(value = 1, message = "Age must be positive")
    @NotNull(message = "Age is required")
    private Integer age;
    private String message;

    public Person(String name, int age) {
        this.id = ++count;
        this.name = name;
        this.age = age;
    }
}
