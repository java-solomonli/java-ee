package com.javasl.javaee;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;


import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ArquillianExtension.class)
class PersonsCtrlTest {

  @Inject
  PersonsCtrl personsCtrl;

  @Deployment
  public static WebArchive createDeployment() {
    return ShrinkWrap.create(WebArchive.class)
        .addClasses(PersonsCtrl.class, Person.class)
        .addClass(ApiApplication.class)
        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  @Test
  void getPersonAsText() {
    assertEquals(new Person("Daniel Joshua", "Solomon").toString(), personsCtrl.getPersonAsText());
  }

  @Test
  void shouldReturnPersonJson() {
    var list = personsCtrl.getPersons();
    assertNotNull(list);
  }

  @Test
  void getPerson() {
  }

  @Test
  void getPersons() {
  }

  @Test
  void getPersonXml() {
  }
}
