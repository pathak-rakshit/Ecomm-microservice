package org.example.userservice.app.Model;

import lombok.Data;

@Data
public class Address {

  private long id;
  private String street;
  private String city;
  private String state;
  private String zip;
  private String country;

}