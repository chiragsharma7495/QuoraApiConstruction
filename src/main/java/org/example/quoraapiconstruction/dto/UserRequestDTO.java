package org.example.quoraapiconstruction.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

//    username email bio

    @NotBlank(message = "username is required")
    @Size(min = 3 , max = 20, message = "username must be between 3 and 30 character")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = " Invalid Email format")
    private String email;

    @Size(message = "Bio cannot exceed length of 200 characters")
    private String bio;
}
