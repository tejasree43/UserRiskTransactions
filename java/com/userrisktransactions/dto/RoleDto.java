package com.userrisktransactions.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RoleDto
{
    @NotNull
    private Long id; // Role ID

    // You can add other fields if needed
    private String name;
}
