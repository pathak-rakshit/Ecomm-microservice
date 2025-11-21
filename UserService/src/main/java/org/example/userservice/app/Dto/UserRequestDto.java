package org.example.userservice.app.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.userservice.app.Model.Address;

@Data
@NoArgsConstructor
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private Address address;

}
