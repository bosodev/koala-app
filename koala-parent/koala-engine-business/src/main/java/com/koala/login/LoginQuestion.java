package com.koala.login;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "XLOGINQUESTION")
@AllArgsConstructor
@NoArgsConstructor
public class LoginQuestion {

	@Id
	private String id;
	
	private String answer;

	private String question;

}
