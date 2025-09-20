package com.project1.Book.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotEmpty(message = "First name is Mandatory")
    @NotBlank(message = "First name is Mandatory")
    private String firstName;
    @NotEmpty(message = "Last name is Mandatory")
    @NotBlank(message = "Last name is Mandatory")
    private String lastName;
    @NotEmpty(message = "Email is Mandatory")
    @NotBlank(message = "Email is Mandatory")
    @Email(message ="Email is not well formatted")
    private String email;
    @NotEmpty(message = "password is Mandatory")
    @NotBlank(message = "password is Mandatory")
    @Size(min = 8 , message = "password should be minimum 8 characters")
    private String password;
}
