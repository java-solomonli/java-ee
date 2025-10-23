package com.javasl.javaee.service;

import com.javasl.javaee.json.PersonDto;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.Optional;

@Transactional
@ApplicationScoped
public class PersonService {

  @PersistenceContext
  private EntityManager em;

  public Person findById(Integer id) {
    return Optional.ofNullable(em.find(Person.class, id)).orElseThrow(() -> new IllegalArgumentException("id " + id + " not found"));
  }

  public Person insert(PersonDto person) {
    var personEntity = new Person(null, person.getSurname(), person.getGivenNames());
    em.persist(personEntity);
    return personEntity;
  }

  public Person edit(PersonDto person) {
    var personEntity = Optional.ofNullable(em.find(Person.class, person.getId())).orElseThrow(() ->
      new IllegalArgumentException("id " + person.getId() + " not found"));
    personEntity.setGivennames(person.getGivenNames());
    personEntity.setSurname(person.getSurname());
    em.merge(personEntity);
    return personEntity;
  }
}
