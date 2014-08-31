package com.koala.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "XUSER")
public class User {

	@Id
	@Getter
	@Setter
	@Column(name = "id")
	private String id;

	@Getter
	@Setter
	@Column(name = "name")
	private String name;

	@Getter
	@Setter
	@Column(name = "login")
	private String login;

	@Getter
	@Setter
	@Column(name = "email")
	private String email;
}
