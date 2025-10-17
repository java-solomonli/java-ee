package com.javasl.javaee;

import com.javasl.javaee.xml.Person;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import java.util.Arrays;
import java.util.List;

@Path("/persons")
public class PersonsCtrl {

  @Path("/text")
  @GET
  @Produces("text/plain")
  public String getPersonAsText() {
    var person = new com.javasl.javaee.json.Person("Daniel Joshua", "Solomon");
    return person.toString();
  }

  @Path("/json")
  @GET
  @Produces("application/json")
  public com.javasl.javaee.json.Person getPerson() {
    return new com.javasl.javaee.json.Person("Daniel Joshua", "Solomon");
  }

  @GET
  @Produces("application/json")
  public List<com.javasl.javaee.json.Person> getPersons() {
    return Arrays.asList(
        new com.javasl.javaee.json.Person("Daniel Joshua", "Solomon"),
        new com.javasl.javaee.json.Person("Hans", "Wurst"));
  }

  @Path("/xml")
  @GET
  @Produces("application/xml")
  public Person getPersonXml() {
    return new Person("Daniel Joshua", "Solomon");
  }
}
