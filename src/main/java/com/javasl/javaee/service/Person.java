package com.javasl.javaee.service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String surname;
  private String givennames;

  public Person() {}

  public Person(Integer id, String surname, String givennames) {
    this.id = id;
    this.surname = surname;
    this.givennames = givennames;
  }
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getSurname() {
    return surname;
  }
  public void setSurname(String surname) {
    this.surname = surname;
  }
  public String getGivennames() {
    return givennames;
  }
  public void setGivennames(String givennames) {
    this.givennames = givennames;
  }

  @Override
  public String toString() {
    return "Person [id=" + id + ", surname=" + surname + ", givennames=" + givennames + "]";
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Person person = (Person) o;
    return Objects.equals(id, person.id) && Objects.equals(surname,
        person.surname) && Objects.equals(givennames, person.givennames);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, surname, givennames);
  }

}
