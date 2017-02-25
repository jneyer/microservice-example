package com.example.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.model.Model;

@RunWith(MockitoJUnitRunner.class)
public class ModelTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testUpdate() throws Exception {

		Model model1 = new Model(1L);
		model1.setFieldOne("value1");
		model1.onCreate();

		Model model2 = new Model(2L);
		model2.setFieldTwo("value2");
		model2.onCreate();

		model2.update(model1);
		model2.onUpdate();

		assertThat(model2.getId(), equalTo(2L));
		assertThat(model2.getCreated(), notNullValue());
		assertThat(model2.getUpdated(), notNullValue());
		assertThat(model2.getFieldOne(), equalTo("value1"));
		assertThat(model2.getFieldTwo(), equalTo("value2"));

	}

	@Test
	public void testToString() throws Exception {

		assertThat(new Model().toString(), notNullValue());
	}

}
