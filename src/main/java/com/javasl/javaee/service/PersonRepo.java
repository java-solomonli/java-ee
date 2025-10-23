package com.javasl.javaee.service;

import jakarta.data.repository.Find;
import jakarta.data.repository.OrderBy;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Save;

import java.util.List;
import java.util.Optional;

//@Repository Not suppported yet
public interface PersonRepo {
  @Save
  void save(Person person);

  @Find
  Optional<Person> findById(Integer id);

  @OrderBy(value = "surname")
  @Find
  List<Person> personBySurname(String surname);

  @OrderBy(value = "surname")
  @Find
  List<Person> findAll();
}
