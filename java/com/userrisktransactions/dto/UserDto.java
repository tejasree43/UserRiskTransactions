package com.userrisktransactions.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
public class UserDto {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]([a-zA-Z0-9])*$")
    private String username;

    @NotBlank
    @Size(min = 8) // Or other criteria
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private LocalDate dateOfBirth;

    private Set<RoleDto> roles; // Initialize to an empty set
 // Assuming you want to pass role IDs

}
