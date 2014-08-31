package com.koala.views;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "raffle_with_numbers_aunt")
public class ViewRaffleNumberAunt {

	@Id
	@Column(name = "concurse", updatable = false, insertable = false)
	private Integer concurse;

	@Column(name = "date", updatable = false, insertable = false)
	private Date date;

}