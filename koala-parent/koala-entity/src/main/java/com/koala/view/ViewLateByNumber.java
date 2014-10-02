package com.koala.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Builder;

@Builder
@Data
@Entity
@Table(name = "total_late_by_number")
public class ViewLateByNumber {

	public ViewLateByNumber() {
	}

	public ViewLateByNumber(Integer ball, Integer total) {
		this.total = total;
		this.ball = ball;
	}

	@Id
	@Column(name = "ball", updatable = false, insertable = false)
	private Integer ball;

	@Column(name = "total", updatable = false, insertable = false)
	private Integer total;
}
