package com.example.service.impl;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.model.Model;
import com.example.repository.ModelRepository;
import com.example.util.CollectionRequest;
import com.example.util.CollectionResponse;
import com.example.util.ResourceException;

@RunWith(MockitoJUnitRunner.class)
public class ModelServiceImplTest {

	@Mock
	private ModelRepository modelRepository;

	@InjectMocks
	private ModelServiceImpl modelServiceImpl;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetMany() throws Exception {

		PageImpl<Model> page = new PageImpl<Model>(Arrays.asList(new Model()));
		when(modelRepository.findAll(isA(Pageable.class))).thenReturn(page);

		CollectionRequest pageRequest = new CollectionRequest(1, 10);
		CollectionResponse<Model> response = modelServiceImpl.getMany(pageRequest);

		assertThat(response, notNullValue());

	}

	@Test
	public void testGetOne() throws Exception {

		when(modelRepository.findOne(anyLong())).thenReturn(new Model());

		Model response = modelServiceImpl.getOne(1L);

		assertThat(response, notNullValue());
		verify(modelRepository).findOne(anyLong());
	}

	@Test
	public void testGetOneNotFound() throws Exception {

		when(modelRepository.findOne(anyLong())).thenReturn(null);

		thrown.expect(ResourceException.class);
		thrown.expectMessage(containsString("Could not find resource"));

		Model response = modelServiceImpl.getOne(1L);

		assertThat(response, nullValue());
		verify(modelRepository).findOne(anyLong());
	}

	@Test
	public void testAddOne() throws Exception {

		Model model = new Model(999L);

		when(modelRepository.save(isA(Model.class))).thenReturn(model);

		Model response = modelServiceImpl.addOne(new Model());

		assertThat(response, notNullValue());
		verify(modelRepository).save(isA(Model.class));
	}

	@Test
	public void testUpdateOne() throws Exception {

		Long id = 5L;
		Model model = new Model(id);

		when(modelRepository.findOne(id)).thenReturn(model);
		when(modelRepository.save(isA(Model.class))).thenReturn(model);

		Model response = modelServiceImpl.updateOne(id, model);

		assertThat(response, notNullValue());
		verify(modelRepository).save(isA(Model.class));
	}

	@Test
	public void testDeleteOne() throws Exception {

		Long id = 999L;

		when(modelRepository.findOne(id)).thenReturn(new Model(id));

		modelServiceImpl.deleteOne(id);
		verify(modelRepository).delete(isA(Model.class));
	}

}
