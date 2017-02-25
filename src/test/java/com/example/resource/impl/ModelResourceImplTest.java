package com.example.resource.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.model.Model;
import com.example.service.ModelService;
import com.example.util.CollectionRequest;
import com.example.util.CollectionResponse;

@RunWith(MockitoJUnitRunner.class)
public class ModelResourceImplTest {

	@Mock
	private ModelService<Model> modelService;
	@InjectMocks
	private ModelResourceImpl modelResourceImpl;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetMany() throws Exception {

		Collection<Model> models = Arrays.asList(new Model());
		CollectionResponse<Model> collection = new CollectionResponse<Model>(models);

		when(modelService.getMany(isA(CollectionRequest.class))).thenReturn(collection);

		CollectionRequest pageRequest = new CollectionRequest(1, 10);
		CollectionResponse<Model> response = modelResourceImpl.getMany(pageRequest);

		assertThat(response, notNullValue());
		assertThat(response.getSize(), equalTo(1));
		verify(modelService).getMany(isA(CollectionRequest.class));
	}

	@Test
	public void testGetOne() throws Exception {

		Long id = 999L;
		Model model = new Model();

		when(modelService.getOne(anyLong())).thenReturn(model);

		Model response = modelResourceImpl.getOne(id);

		assertThat(response, notNullValue());
		verify(modelService).getOne(anyLong());
	}

	@Test
	public void testAddOne() throws Exception {

		Model model = new Model();

		when(modelService.addOne(isA(Model.class))).thenReturn(model);

		Model response = modelResourceImpl.addOne(model);

		assertThat(response, notNullValue());
		verify(modelService).addOne(isA(Model.class));
	}

	@Test
	public void testUpdateOne() throws Exception {

		Long id = 999L;
		Model model = new Model();

		when(modelService.updateOne(anyLong(), isA(Model.class))).thenReturn(model);

		Model response = modelResourceImpl.updateOne(id, model);

		assertThat(response, notNullValue());
		verify(modelService).updateOne(anyLong(), isA(Model.class));
	}

	@Test
	public void testDeleteOne() throws Exception {

		Long id = 1L;

		doNothing().when(modelService).deleteOne(anyLong());

		modelResourceImpl.deleteOne(id);

		verify(modelService).deleteOne(anyLong());
	}

}
