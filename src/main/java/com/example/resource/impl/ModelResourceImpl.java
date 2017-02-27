package com.example.resource.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Model;
import com.example.resource.ModelResource;
import com.example.service.ModelService;
import com.giantrobotlabs.util.CollectionRequest;
import com.giantrobotlabs.util.CollectionResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "models")
public class ModelResourceImpl implements ModelResource<Model> {

	@Autowired
	ModelService<Model> modelService;

	@Override
	@ApiOperation(value = "Get models", notes = "Return a list of all models.", response = CollectionResponse.class)
	@RequestMapping(method = RequestMethod.GET, path = "/models", produces = "application/json")
	public CollectionResponse<Model> getMany(@ModelAttribute CollectionRequest pageRequest) {
		return modelService.getMany(pageRequest);
	}

	@Override
	@ApiOperation(value = "Get one model", notes = "Return a single model.", response = Model.class)
	@RequestMapping(method = RequestMethod.GET, path = "/models/{id}", produces = "application/json")
	public Model getOne(@PathVariable(value = "id") Long id) {
		return modelService.getOne(id);
	}

	@Override
	@ApiOperation(value = "Add a model", notes = "Add a model.", response = Model.class)
	@RequestMapping(method = RequestMethod.POST, path = "/models", produces = "application/json")
	public Model addOne(@RequestBody Model model) {
		return modelService.addOne(model);
	}

	@Override
	@ApiOperation(value = "Update a model", notes = "Update a model.", response = Model.class)
	@RequestMapping(method = RequestMethod.PUT, path = "/models/{id}", produces = "application/json")
	public Model updateOne(@PathVariable(value = "id") Long id, @RequestBody Model model) {
		return modelService.updateOne(id, model);
	}

	@Override
	@ApiOperation(value = "Delete a model", notes = "Delete a model.")
	@RequestMapping(method = RequestMethod.DELETE, path = "/models/{id}", produces = "application/json")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteOne(@PathVariable(value = "id") Long id) {
		modelService.deleteOne(id);
	}

}
