package com.koala.entity;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Builder;

@Data
@Builder
public class Login {

	private String name;

	private String email;

	private String pass;

	private String token;

	private String authToken;

	private Date lastLogin;

	private User user;
}
