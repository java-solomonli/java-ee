package com.javasl.javaee.json;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public record Person(String givennames, String surname) {

}
