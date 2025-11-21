package org.example.userservice.app.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {

    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
}
