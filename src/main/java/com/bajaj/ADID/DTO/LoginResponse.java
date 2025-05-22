package com.bajaj.ADID.DTO;

import lombok.Data;

@Data
public class LoginResponse {

public LoginResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
private boolean success;
    private String message;
}
