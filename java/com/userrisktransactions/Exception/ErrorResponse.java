package com.userrisktransactions.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    String message;
    int status;
    ErrorResponse(String message, int status)
    {
      this.message = message;
      this.status = status;

    }

}
