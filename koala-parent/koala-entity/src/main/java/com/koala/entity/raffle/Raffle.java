package com.koala.entity.raffle;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

@Data
@Entity
@Builder
@Table(name = "XRAFFLE")
@AllArgsConstructor
@NoArgsConstructor
public class Raffle implements Serializable {

	private static final long serialVersionUID = 3245609328405966657L;

	@Id
	@Column(name = "concurse")
	private Integer concurse;
	@Column(name = "date")
	private Date date;
	@Column(name = "ball_a")
	private Integer ball1;
	@Column(name = "ball_b")
	private Integer ball2;
	@Column(name = "ball_c")
	private Integer ball3;
	@Column(name = "ball_d")
	private Integer ball4;
	@Column(name = "ball_e")
	private Integer ball5;
	@Column(name = "ball_f")
	private Integer ball6;
	@Column(name = "ball_g")
	private Integer ball7;
	@Column(name = "ball_h")
	private Integer ball8;
	@Column(name = "ball_i")
	private Integer ball9;
	@Column(name = "ball_j")
	private Integer ball10;
	@Column(name = "ball_k")
	private Integer ball11;
	@Column(name = "ball_l")
	private Integer ball12;
	@Column(name = "ball_m")
	private Integer ball13;
	@Column(name = "ball_n")
	private Integer ball14;
	@Column(name = "ball_o")
	private Integer ball15;
}
