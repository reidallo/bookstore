package project.bookstore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private Integer postalCode;
    private String city;
    private String state;
    private String phone;
}
