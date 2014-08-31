package com.koala.bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.koala.entity.UserRaffle;

public class UserRaflleBean {

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	public void save(UserRaffle userRaffle) {
		em.persist(userRaffle);
	}
}
