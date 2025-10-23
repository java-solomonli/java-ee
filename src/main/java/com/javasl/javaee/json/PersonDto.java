package com.javasl.javaee.json;

public class PersonDto {
  private Integer id;
  private String givenNames;
  private String surname;

  public PersonDto() {} // default constructor for JSON deserialization

  public PersonDto(String firstName, String lastName) {
    this.givenNames = firstName;
    this.surname = lastName;
  }

  public PersonDto(Integer id, String givenNames, String surname) {
    this.id = id;
    this.givenNames = givenNames;
    this.surname = surname;
  }

  public String getGivenNames() { return givenNames; }
  public void setGivenNames(String givenNames) { this.givenNames = givenNames; }
  public String getSurname() { return surname; }
  public void setSurname(String surname) { this.surname = surname; }
  public Integer getId() { return id; }
  public void setId(Integer id) { this.id = id; }

  @Override
  public String toString() {
    return "PersonDto [givenNames=" + givenNames + ", surname=" + surname + "]";
  }
}
