package com.group10.bookingtravel.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class UserRegisterRequestDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String fullname;

    @NotBlank
    private String email;

    @NotBlank
    private String address;

    @NotBlank
    private String dob;

    @NotBlank
    private String gender;

}
