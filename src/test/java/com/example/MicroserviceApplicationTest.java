package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MicroserviceApplicationTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testRun() throws Exception {

		String[] args = new String[0];
		MicroserviceApplication.main(args);
	}

}
