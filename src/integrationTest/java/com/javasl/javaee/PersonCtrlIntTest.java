package com.javasl.javaee;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.javasl.javaee.json.PersonDto;
import com.javasl.javaee.service.PersonService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ArquillianExtension.class)
public class PersonCtrlIntTest {
  @ArquillianResource
  URL baseUrl;

  //@Inject
  //PersonsCtrl personsCtrl;

  @Deployment
  public static WebArchive createDeployment() {
    return ShrinkWrap.create(WebArchive.class)
        .addClasses(PersonsCtrl.class, PersonDto.class, Person.class)
        .addClass(ApiApplication.class)
        .addAsResource("META-INF/persistence.xml")
        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  @Test
  void getPersonAsText() {
    //assertEquals(new Person("Daniel Joshua", "Solomon").toString(), personsCtrl.getPersonAsText());
  }

  @Test
  @RunAsClient
    // ✅ Tells Arquillian this runs outside the container
  void shouldReturnPersonJson() throws Exception{
    URL endpoint = new URL(baseUrl, "api/persons/json");
    RestAssured
        .given()
        .baseUri(endpoint.toExternalForm())
        .accept(ContentType.JSON)
        .when()
        .get()
        .then()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .body("givenNames", equalTo("Daniel Joshua"))
        .body("surname", equalTo("Solomon"));
  }

  @Test
  @RunAsClient
  void getPersonsList() throws Exception{
    URL endpoint = new URL(baseUrl, "api/persons");
    RestAssured
        .given()
        .baseUri(endpoint.toExternalForm())
        .accept(ContentType.JSON)
        .when()
        .get()
        .then()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .body("givenNames", contains("Daniel Joshua", "Hans"))
        .body("surname", contains("Solomon", "Wurst"));

  }
}
