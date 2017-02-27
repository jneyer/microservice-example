package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.model.Model;
import com.example.repository.ModelRepository;
import com.example.service.ModelService;
import com.giantrobotlabs.util.CollectionRequest;
import com.giantrobotlabs.util.CollectionResponse;
import com.giantrobotlabs.util.ResourceException;

@Service
public class ModelServiceImpl implements ModelService<Model> {

	@Autowired
	ModelRepository modelRepository;

	@Override
	public CollectionResponse<Model> getMany(CollectionRequest pageRequest) {

		PageRequest pageable = new PageRequest(pageRequest.getPage(), pageRequest.getLimit());
		Page<Model> result = modelRepository.findAll(pageable);

		return new CollectionResponse<Model>(result);
	}

	@Override
	public Model getOne(Long id) {

		Model response = modelRepository.findOne(id);

		if (response == null) {
			throw new ResourceException("Resource not found.", HttpStatus.NOT_FOUND,
					"Could not find resource for id " + id);
		}

		return response;
	}

	@Override
	public Model addOne(Model input) {

		// Create clean model to make sure id and dates are not set
		Model model = new Model();
		model.update(input);

		return modelRepository.save(model);
	}

	@Override
	public Model updateOne(Long id, Model input) {

		Model model = getOne(id);
		model.update(input);

		return modelRepository.save(model);
	}

	@Override
	public void deleteOne(Long id) {

		Model model = getOne(id);
		modelRepository.delete(model);

	}
}
