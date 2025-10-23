package com.javasl.javaee.service;

import com.javasl.javaee.json.PersonDto;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@ApplicationScoped
public class PersonService {

  @PersistenceContext
  private EntityManager em;

  public List<PersonDto> findAll() {
    return em.createQuery("SELECT p FROM Person p ORDER BY p.surname", Person.class).getResultList().stream()
        .map(this::mapTo).toList();
  }

  public PersonDto findById(Integer id) {
    var personEntity = Optional.ofNullable(em.find(Person.class, id)).orElseThrow(() -> new IllegalArgumentException("id " + id + " not found"));
    return mapTo(personEntity);
  }

  public PersonDto insert(PersonDto person) {
    var personEntity = new Person(null, person.getSurname(), person.getGivenNames());
    em.persist(personEntity);
    return mapTo(personEntity);
  }

  public PersonDto edit(PersonDto person) {
    var personEntity = Optional.ofNullable(em.find(Person.class, person.getId())).orElseThrow(() ->
      new IllegalArgumentException("id " + person.getId() + " not found"));
    personEntity.setGivennames(person.getGivenNames());
    personEntity.setSurname(person.getSurname());
    em.merge(personEntity);
    return mapTo(personEntity);
  }

  private PersonDto mapTo(Person person) {
    return new PersonDto(person.getId(), person.getGivennames(), person.getSurname());
  }
}
