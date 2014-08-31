package com.koala.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "fifteen_less_drawn")
public class ViewNumberLessDrawn {

	@Id
	@Column(name = "ball", updatable = false, insertable = false)
	private Integer ball;

	@Column(name = "total", updatable = false, insertable = false)
	private Integer total;
}
