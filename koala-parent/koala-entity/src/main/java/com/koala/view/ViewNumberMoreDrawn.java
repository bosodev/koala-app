package com.koala.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Builder;

@Data
@Entity
@Builder
@Table(name = "fifteen_more_drawn")
public class ViewNumberMoreDrawn {

	public ViewNumberMoreDrawn() {
	}

	public ViewNumberMoreDrawn(Integer ball, Integer total) {
		this.total = total;
		this.ball = ball;
	}
	
	
	@Id
	@Column(name = "ball", updatable = false, insertable = false)
	private Integer ball;

	@Column(name = "total", updatable = false, insertable = false)
	private Integer total;

}
