package com.example.resource.impl;

import static com.example.util.TestUtils.asJsonString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.model.Model;
import com.example.service.ModelService;
import com.example.util.CollectionResponse;
import com.example.util.ResourceException;

@RunWith(SpringRunner.class)
@WebMvcTest(ModelResourceImpl.class)
public class ModelResourceImplTestIt {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ModelService<Model> modelService;

	@InjectMocks
	private ModelResourceImpl modelResourceImpl;

	@Before
	public void setUp() throws Exception {

		Collection<Model> models = new ArrayList<Model>();
		when(this.modelService.getMany(null)).thenReturn(new CollectionResponse<Model>(models));
		when(this.modelService.getOne(1L)).thenReturn(new Model(1L));
		when(this.modelService.getOne(2L))
				.thenThrow(new ResourceException("Not found", HttpStatus.NOT_FOUND, "Error message"));
		when(this.modelService.addOne(isA(Model.class))).thenReturn(new Model(1L));
		when(this.modelService.updateOne(anyLong(), isA(Model.class))).thenReturn(new Model(1L));

	}

	@Test
	public void testGetModels() throws Exception {

		mvc.perform(get("/models").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void testGetOneModels() throws Exception {

		mvc.perform(get("/models/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)));

		mvc.perform(get("/models/2").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

	}

	@Test
	public void testPostModels() throws Exception {

		Model model = new Model();
		model.setFieldOne("test one");
		model.setFieldTwo("test two");

		mvc.perform(post("/models").contentType(MediaType.APPLICATION_JSON).content(asJsonString(model)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)));

	}

	@Test
	public void testPutModels() throws Exception {

		Model model = new Model(99L);
		model.setFieldOne("test one");
		model.setFieldTwo("test two");

		mvc.perform(put("/models/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(model)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)));

	}

	@Test
	public void tesDeleteModels() throws Exception {

		mvc.perform(delete("/models/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

	}

}