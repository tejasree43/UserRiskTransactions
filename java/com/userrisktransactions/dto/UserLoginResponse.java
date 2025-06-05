package com.userrisktransactions.dto;

import com.userrisktransactions.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginResponse {
    private User user;
    private String token;

}
