package com.javasl.javaee;

import com.javasl.javaee.json.PersonDto;
import com.javasl.javaee.service.PersonService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
@Path("v2/persons")
public class Persons2Ctrl {
  @Inject
  private PersonService personService;

  @Path("/text")
  @GET
  @Produces("text/plain")
  public String getPersonAsText() {
    var person = new Person("Daniel Joshua", "Solomon");
    return person.toString();
  }

  @Path("/json")
  @GET
  @Produces("application/json")
  public PersonDto getPerson() {
    return new PersonDto("Daniel Joshua", "Solomon");
  }

  @GET
  @Produces("application/json")
  public List<PersonDto> getPersons() {
    return Arrays.asList(
        new PersonDto("Daniel Joshua", "Solomon"),
        new PersonDto("Hans", "Wurst"));
  }

  @POST
  @Produces("text/plain")
  public Integer insertPerson(PersonDto person) {
    var id = personService.insert(person);
    return id;
  }

  @Path("/{id}")
  @GET
  @Produces("application/json")
  public PersonDto getPerson(@PathParam("id") Integer id) {
    var personEntity = personService.findById(id);
    var entityToMap = personEntity.orElse(new com.javasl.javaee.service.Person());

    return new PersonDto(entityToMap.getGivennames(), entityToMap.getSurname());
  }

  @Path("/xml")
  @GET
  @Produces("application/xml")
  public Person getPersonXml() {
    return new Person("Daniel Joshua", "Solomon");
  }
}
