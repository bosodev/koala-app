package com.koala.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "XRAFFLEDATA")
public class RaffleDataAnalytic implements Serializable {

	private static final long serialVersionUID = -8124221271481321210L;

	@Id
	@Getter
	@Setter
	@Column(name = "concurse")
	private Integer concurse;

	@Getter
	@Setter
	@Column(name = "average_numbers")
	private Integer average;

	@Getter
	@Setter
	@Column(name = "sum_numbers")
	private Integer sum;

	@Getter
	@Setter
	@Column(name = "pair")
	private Integer pair;

	@Getter
	@Setter
	@Column(name = "unpaired")
	private Integer unpaired;

	@Getter
	@Setter
	@Column(name = "greater_sequence")
	private Integer greaterSequence;

	@Getter
	@Setter
	@Column(name = "sum_first_row")
	private Integer sumFirstRow;

	@Getter
	@Setter
	@Column(name = "sum_second_row")
	private Integer sumSecondRow;

	@Getter
	@Setter
	@Column(name = "sum_third_row")
	private Integer sumThirdRow;

	@Getter
	@Setter
	@Column(name = "sum_fourth_row")
	private Integer sumFourthRow;

	@Getter
	@Setter
	@Column(name = "sum_fiveth_row")
	private Integer sumFivethRow;

	@Getter
	@Setter
	@Column(name = "first_row")
	private Integer firstRow;

	@Getter
	@Setter
	@Column(name = "second_row")
	private Integer secondRow;

	@Getter
	@Setter
	@Column(name = "third_row")
	private Integer thirdRow;

	@Getter
	@Setter
	@Column(name = "fourth_row")
	private Integer fourthRow;

	@Getter
	@Setter
	@Column(name = "fiveth_row")
	private Integer fivethRow;

}
