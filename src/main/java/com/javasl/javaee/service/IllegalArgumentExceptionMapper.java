package com.javasl.javaee.service;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
  private static final Logger log = LoggerFactory.getLogger(IllegalArgumentExceptionMapper.class);

  @Override
  public Response toResponse(IllegalArgumentException e) {
    log.warn("--> toResponse: IllegalArgumentException: {}", e.getMessage());
    return Response.status(Status.NOT_FOUND)
        .type(MediaType.APPLICATION_JSON)
        .entity(new ErrorDto("404 Not Found", e.getMessage()))
        .build();
  }
}
