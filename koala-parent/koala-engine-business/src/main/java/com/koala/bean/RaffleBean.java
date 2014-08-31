package com.koala.bean;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import com.koala.entity.Raffle;

@Stateless
public class RaffleBean implements Serializable {

	private static final long serialVersionUID = -5699419768453474921L;

	EntityManager em;

	public RaffleBean() {

	}

	public void save(Raffle raffle) {
		em.persist(raffle);
	}

	public void delete(Raffle raffle) {
		em.remove(raffle);
	}
}