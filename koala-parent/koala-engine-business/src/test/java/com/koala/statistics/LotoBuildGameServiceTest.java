package com.koala.statistics;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.koala.service.LotoBuildGameService;

public class LotoBuildGameServiceTest {

	private LotoBuildGameService lotoBuildGameService = new LotoBuildGameService();

	@Test
	public void random() {
		assertThat(lotoBuildGameService.randomRaffle().getNumbers().size(), equalTo(15));
	}

}
