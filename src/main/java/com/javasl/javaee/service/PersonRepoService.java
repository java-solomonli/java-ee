package com.javasl.javaee.service;

import com.javasl.javaee.json.PersonDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PersonRepoService {
  @Inject
  //private PersonRepo personRepo;
  private PersonService personRepo;

  public List<PersonDto> findAll() {
    return personRepo.findAll(); //.stream().map(this::mapTo).collect(Collectors.toList());
  }

  public PersonDto findById(Integer id) {
    return personRepo.findById(id);
    //var personEntity = personRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("id not found: " + id));
    //return new PersonDto(personEntity.getId(), personEntity.getGivennames(), personEntity.getSurname());
  }

  public PersonDto insert(PersonDto person) {
    return personRepo.insert(person);
    // var personEntity = new Person(null, person.getSurname(), person.getGivenNames());
    // personRepo.save(personEntity);
    // return new PersonDto(personEntity.getId(), personEntity.getGivennames(), personEntity.getSurname());
  }

  public PersonDto edit(PersonDto person) {
    return personRepo.edit(person);
    /**    var personEntity = personRepo.findById(person.getId()).orElseThrow(() -> new IllegalArgumentException("id not found: " + person.getId()));
    personEntity.setGivennames(person.getGivenNames());
    personEntity.setSurname(person.getSurname());
    personRepo.save(personEntity);

    return new PersonDto(personEntity.getId(), personEntity.getGivennames(), personEntity.getSurname()); */
  }

  private PersonDto mapTo(Person person) {
    return new PersonDto(person.getId(), person.getGivennames(), person.getSurname());
  }
}
