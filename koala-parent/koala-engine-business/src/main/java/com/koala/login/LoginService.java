package com.koala.login;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mysema.query.jpa.impl.JPAQuery;

@Stateless
public class LoginService {

	@PersistenceContext(unitName = "primary")
	private EntityManager entityManager;

	public String recoverPass(String mail) {
		JPAQuery query = new JPAQuery(entityManager);
		QLogin login = QLogin.login;
		Login loginResult = query.from(login).where(login.email.eq(mail))
				.singleResult(QLogin.create(login.pass));
		return loginResult.getPass();

	}

}
