package com.javasl.javaee;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import com.javasl.javaee.json.PersonDto;
import com.javasl.javaee.service.Person;
import com.javasl.javaee.service.PersonRepo;
import com.javasl.javaee.service.PersonRepoService;
import com.javasl.javaee.service.PersonService;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ArquillianExtension.class)
public class PersonRepoCtrlIntTest {
  @ArquillianResource
  URL baseUrl;

  @BeforeAll
  static void setup() {
    RestAssured.config = RestAssuredConfig.config()
        .objectMapperConfig(
            new ObjectMapperConfig()
                .defaultObjectMapper(new JsonbRestAssuredMapper())
        );
  }

  @Deployment
  public static WebArchive createDeployment() {
    return ShrinkWrap.create(WebArchive.class)
        .addClasses(PersonRepoCtrl.class, PersonRepoService.class, PersonRepo.class, PersonDto.class, Person.class)
        .addClass(ApiApplication.class)
        .addAsResource("META-INF/persistence.xml")
        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  @Test
  @RunAsClient
  void insertPerson() throws Exception{
    URL endpoint = new URL(baseUrl, "api/v3/persons");

    RestAssured
        .given()
        .baseUri(endpoint.toExternalForm())
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .body(new PersonDto("Daniel Joshua", "Solomon"))
        .when()
        .post()
        .then()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .body("givenNames", equalTo("Daniel Joshua"))
        .body("surname", equalTo("Solomon"))
        .body("id", greaterThanOrEqualTo(0));
  }

  @Test
  @RunAsClient
  void updatePerson() throws Exception{
    URL insertEndpoint = new URL(baseUrl, "api/v2/persons");

    PersonDto created = RestAssured
        .given()
        .baseUri(insertEndpoint.toExternalForm())
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .body(new PersonDto("Daniel Joshua", "Solomon"))
        .post()
        .then()
        .statusCode(200)
        .extract()
        .as(PersonDto.class);  // Or your JSON-mapped PersonDto
    Assertions.assertNotNull(created);
    Assertions.assertNotNull(created.getId());

    var personToUpdate = new PersonDto(created.getId(), "Daniel", "Joshua");
    URL endpoint = new URL(baseUrl, "api/v2/persons/"+(created.getId()));
    RestAssured.given()
        .baseUri(endpoint.toExternalForm())
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .body(personToUpdate)
        .put()
        .then()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .body("givenNames", equalTo("Daniel"))
        .body("surname", equalTo("Joshua"))
        .body("id", equalTo(created.getId()));
  }

  @Test
  @RunAsClient
  void getPersonsList() throws Exception{
    URL insertEndpoint = new URL(baseUrl, "api/v2/persons");

    PersonDto created = RestAssured
        .given()
        .baseUri(insertEndpoint.toExternalForm())
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .body(new PersonDto("Daniel Joshua", "Solomon"))
        .post()
        .then()
        .statusCode(200)
        .extract()
        .as(PersonDto.class);  // Or your JSON-mapped PersonDto
    Assertions.assertNotNull(created);
    Assertions.assertNotNull(created.getId());

    URL getEndpoint = new URL(baseUrl, "api/v2/persons/" + created.getId());
    PersonDto person = RestAssured
        .given()
        .baseUri(getEndpoint.toExternalForm())
        .accept(ContentType.JSON)
        .get()
        .then()
        .statusCode(200)
        .body("givenNames", equalTo("Daniel Joshua"))
        .body("surname", equalTo("Solomon"))
        .body("id", equalTo(created.getId()))
        .extract()
        .as(PersonDto.class);
    Assertions.assertNotNull(person);
    Assertions.assertNotNull(person.getId());
    Assertions.assertEquals(created.getId(), person.getId());
    Assertions.assertEquals("Daniel Joshua", person.getGivenNames());
    Assertions.assertEquals("Solomon", person.getSurname());
  }

}
