package com.javasl.javaee.xml;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {
  private String givennames;
  private String surname;

  public Person() {
  }

  public Person(String givennames, String surname) {
    this.givennames = givennames;
    this.surname = surname;
  }

  public void setGivennames(String givennames) {
    this.givennames = givennames;
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

  @Override
  public String toString() {
    return "PersonXml [givennames=" + givennames + ", surname=" + surname + "]";
  }
}
