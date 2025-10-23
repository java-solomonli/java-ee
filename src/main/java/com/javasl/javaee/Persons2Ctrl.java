package com.javasl.javaee;

import com.javasl.javaee.json.PersonDto;
import com.javasl.javaee.service.PersonService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Path("v2/persons")
public class Persons2Ctrl {

  private static final Logger log = LoggerFactory.getLogger(Persons2Ctrl.class);

  @Inject
  private PersonService personService;

  @Path("/ping")
  @GET
  @Produces("text/plain")
  public String getPersonAsText() {
    return "ping";
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
    log.info("getPersons, get every record");
    var persons = personService.findAll();
    log.info("getPersons: {}", persons);
    return persons;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public PersonDto insertPerson(PersonDto person) {
    log.info("insertPerson: {}", person);
    var personEntity = personService.insert(person);
    log.info("insertPerson: {}", personEntity);
    return personEntity;
  }

  @Path("/{id}")
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public PersonDto editPerson(PersonDto person) {
    log.info("editPerson: {}", person);
    var personEntity = personService.edit(person);
    return personEntity;
  }

  @Path("/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public PersonDto getPerson(@PathParam("id") Integer id) {
    log.info("getPerson: {}", id);
    var person = personService.findById(id);
    log.info("getPerson: {}", person);
    return person;
  }

}
