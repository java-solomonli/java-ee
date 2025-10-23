package com.javasl.javaee;

import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class JsonbRestAssuredMapper implements ObjectMapper {
  private static final Jsonb JSONB = JsonbBuilder.create();

  @Override
  public Object deserialize(ObjectMapperDeserializationContext context) {
    return JSONB.fromJson(context.getDataToDeserialize().asString(), context.getType());
  }

  @Override
  public Object serialize(ObjectMapperSerializationContext context) {
    return JSONB.toJson(context.getObjectToSerialize());
  }
}
