package com.koala.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "total_raffle_number_equal")
public class ViewRaffleNumberEqual {

	@Id()
	@Column(name = "total", updatable = false, insertable = false)
	private Integer total;
	@Column(name = "ball_a", updatable = false, insertable = false)
	private Integer ball1;
	@Column(name = "ball_b", updatable = false, insertable = false)
	private Integer ball2;
	@Column(name = "ball_c", updatable = false, insertable = false)
	private Integer ball3;
	@Column(name = "ball_d", updatable = false, insertable = false)
	private Integer ball4;
	@Column(name = "ball_e", updatable = false, insertable = false)
	private Integer ball5;
	@Column(name = "ball_f", updatable = false, insertable = false)
	private Integer ball6;
	@Column(name = "ball_g", updatable = false, insertable = false)
	private Integer ball7;
	@Column(name = "ball_h", updatable = false, insertable = false)
	private Integer ball8;
	@Column(name = "ball_i", updatable = false, insertable = false)
	private Integer ball9;
	@Column(name = "ball_j", updatable = false, insertable = false)
	private Integer ball10;
	@Column(name = "ball_k", updatable = false, insertable = false)
	private Integer ball11;
	@Column(name = "ball_l", updatable = false, insertable = false)
	private Integer ball12;
	@Column(name = "ball_m", updatable = false, insertable = false)
	private Integer ball13;
	@Column(name = "ball_n", updatable = false, insertable = false)
	private Integer ball14;
	@Column(name = "ball_o", updatable = false, insertable = false)
	private Integer ball15;

}
