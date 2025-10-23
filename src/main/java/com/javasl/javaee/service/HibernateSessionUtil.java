package com.javasl.javaee.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.hibernate.Session;

@RequestScoped
public class HibernateSessionUtil {

  @PersistenceContext
  private EntityManager em;

  @Produces
  public Session getSession() {
    return em.unwrap(Session.class);
  }
}
