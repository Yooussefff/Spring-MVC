package com.example.demo.Controller;

import com.example.demo.Model.Person;
import com.example.demo.Service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @ModelAttribute("person")
    public Person person() {
        return new Person();
    }

    @GetMapping("/Person")
    public String listPersons(Model model) {
        model.addAttribute("persons", personService.all());
        return "Persons";
    }

    @PostMapping("/Person/new")
    public String createPerson(@Valid @ModelAttribute("person") Person person, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> log.error(error.toString()));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.person", result);
            redirectAttributes.addFlashAttribute("person", person);
            return "redirect:/Person";
        }
        personService.save(person);
        log.info("Person saved: " + person);
        return "redirect:/Person";
    }

    @PostMapping("/Person/delete/{id}")
    public String deletePerson(@PathVariable int id) {
        personService.delete(id);
        return "redirect:/Person";
    }
}
