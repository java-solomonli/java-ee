package com.javasl.javaee;

import com.javasl.javaee.json.PersonDto;
import com.javasl.javaee.service.PersonRepoService;
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
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("v3/persons")
@ApplicationScoped
public class PersonRepoCtrl {

  private static final Logger log = LoggerFactory.getLogger(PersonRepoCtrl.class);

  @Inject
  private PersonRepoService personService;

  @Path("/ping")
  @GET
  @Produces("text/plain")
  public String getPersonAsText() {
    return "ping";
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<PersonDto> getPersons() {
    log.info("getPersons");
    return personService.findAll();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public PersonDto insertPerson(PersonDto person) {
    log.info("insertPerson: {}", person);
    var personDto = personService.insert(person);
    log.info("insertPerson: {}", personDto);
    return personDto;
  }

  @Path("/{id}")
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public PersonDto editPerson(PersonDto person) {
    log.info("editPerson: {}", person);
    var personDto = personService.edit(person);
    log.info("editPerson: {}", personDto);
    return personDto;
  }

  @Path("/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public PersonDto getPerson(@PathParam("id") Integer id) {
    log.info("getPerson: {}", id);
    var personDto = personService.findById(id);
    log.info("getPerson: {}", personDto);
    return personDto;
  }
}
