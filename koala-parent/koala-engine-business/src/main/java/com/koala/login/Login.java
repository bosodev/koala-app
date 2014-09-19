package com.koala.login;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.mysema.query.annotations.QueryProjection;

@Data
@Entity
@Table(name = "XLOGIN")
@AllArgsConstructor
@NoArgsConstructor
public class Login {

	@QueryProjection
	public Login(String pass) {
		this.pass = pass;
	}
	
	@QueryProjection
	public Login(String email, String pass) {
		this.email = email;
		this.pass = pass;
	}

	@Id
	private Long id;

	private String email;

	private String userName;

	private String pass;

	private String oldPass;

	private Date lastLogin;

	private String qrCode;

}
