package com.javasl.javaee.service;

import com.javasl.javaee.json.PersonDto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;

@Stateless
public class PersonService {

  @PersistenceContext
  private EntityManager em;

  public Optional<Person> findById(Integer id) {
    return Optional.ofNullable(em.find(Person.class, id));
  }

  public Integer insert(PersonDto person) {
    var personEntity = new Person(null, person.getSurname(), person.getGivenNames());
    em.persist(personEntity);
    return personEntity.getId();
  }

}
