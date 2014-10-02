package com.koala.entity.raffle;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import com.mysema.query.annotations.QueryProjection;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "XRAFFLEDATA")
public class RaffleDataAnalytic implements Serializable {

	private static final long serialVersionUID = -8124221271481321210L;

	@QueryProjection
	public RaffleDataAnalytic(Double avgPair, Double avgUnPaired) {
		this.avgPair = avgPair;
		this.avgUnPaired = avgUnPaired;
	}

	private Double avgPair;

	private Double avgUnPaired;

	@Id
	@Column(name = "concurse")
	private Integer concurse;

	@Column(name = "average_numbers")
	private Integer average;

	@Column(name = "sum_numbers")
	private Integer sum;

	@Column(name = "pair")
	private Integer pair;

	@Column(name = "unpaired")
	private Integer unpaired;

	@Column(name = "greater_sequence")
	private Integer greaterSequence;

	@Column(name = "sum_first_row")
	private Integer sumFirstRow;

	@Column(name = "sum_second_row")
	private Integer sumSecondRow;

	@Column(name = "sum_third_row")
	private Integer sumThirdRow;

	@Column(name = "sum_fourth_row")
	private Integer sumFourthRow;

	@Column(name = "sum_fiveth_row")
	private Integer sumFivethRow;

	@Column(name = "first_row")
	private Integer firstRow;

	@Column(name = "second_row")
	private Integer secondRow;

	@Column(name = "third_row")
	private Integer thirdRow;

	@Column(name = "fourth_row")
	private Integer fourthRow;

	@Column(name = "fiveth_row")
	private Integer fivethRow;

}
