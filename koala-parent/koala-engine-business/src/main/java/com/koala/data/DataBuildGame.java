package com.koala.data;

import java.util.Set;
import java.util.TreeSet;

import lombok.Data;

@Data
public class DataBuildGame {

	private Set<Integer> numbers;

	public DataBuildGame() {
		numbers = new TreeSet<Integer>();
	}

}
