package com.koala.statistics;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.koala.service.BuildGameService;

public class BuildGameServiceTest {

	private BuildGameService lotoBuildGameService = new BuildGameService();

	@Test
	public void random() {
		assertThat(lotoBuildGameService.randomRaffle().getNumbers().size(), equalTo(15));
	}
}
