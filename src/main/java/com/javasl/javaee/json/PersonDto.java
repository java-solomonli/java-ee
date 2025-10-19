package com.javasl.javaee.json;

public class PersonDto {
  private String givenNames;
  private String surname;

  public PersonDto() {} // default constructor for JSON deserialization

  public PersonDto(String firstName, String lastName) {
    this.givenNames = firstName;
    this.surname = lastName;
  }

  public String getGivenNames() { return givenNames; }
  public void setGivenNames(String givenNames) { this.givenNames = givenNames; }
  public String getSurname() { return surname; }
  public void setSurname(String surname) { this.surname = surname; }
}
