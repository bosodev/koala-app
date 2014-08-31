package com.koala.bean;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import com.koala.entity.User;

@Stateless
public class UserBean implements Serializable {

	private static final long serialVersionUID = -8036464262424120328L;

	EntityManager em;

	public UserBean() {

	}

	public void save() {
		User user = new User();
		em.persist(user);
	}

	public void save(User user) {
		em.persist(user);
	}

}
