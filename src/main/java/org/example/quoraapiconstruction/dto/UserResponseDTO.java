package org.example.quoraapiconstruction.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

//    id username email bio

    private Long id;

    private String username;

    private String email;

    private String bio;
}
