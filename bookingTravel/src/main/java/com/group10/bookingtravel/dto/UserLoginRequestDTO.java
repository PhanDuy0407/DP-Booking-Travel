package com.group10.bookingtravel.dto;
import javax.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class UserLoginRequestDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
