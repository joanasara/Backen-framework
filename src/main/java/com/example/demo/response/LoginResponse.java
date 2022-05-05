package com.example.demo.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	public LoginResponse(String jwtToken, String type, String refreshToken, String username, Long id) {
        this.jwtToken = jwtToken;
        this.type = type;
        this.refreshToken = refreshToken;
        this.username = username;
        this.id = id;
    }

    private final String jwtToken;
    private String type = "Bearer";
    private String refreshToken;
    private String username;
    private Long id;


}