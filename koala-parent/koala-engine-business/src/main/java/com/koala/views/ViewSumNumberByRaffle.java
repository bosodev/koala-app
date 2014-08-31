package com.koala.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "fifteen_more_drawn")
public class ViewSumNumberByRaffle {

	@Id
	@Column(name = "sum_numbers", updatable = false, insertable = false)
	private Integer count;

	@Column(name = "total", updatable = false, insertable = false)
	private Integer total;
}
