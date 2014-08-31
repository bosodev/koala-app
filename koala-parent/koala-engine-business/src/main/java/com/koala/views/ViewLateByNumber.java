package com.koala.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "total_late_by_number")
public class ViewLateByNumber {

	@Id
	@Column(name = "ball", updatable = false, insertable = false)
	private Integer ball;

	@Column(name = "total", updatable = false, insertable = false)
	private Integer total;
}
