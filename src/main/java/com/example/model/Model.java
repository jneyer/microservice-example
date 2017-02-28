package com.example.model;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.giantrobotlabs.model.ModelTemplate;

@Entity
@Table(name = "model")
public class Model extends ModelTemplate {

	@Column(name = "field_one")
	private String fieldOne;

	@Column(name = "field_two")
	private String fieldTwo;

	public Model() {
		super();
	}

	public Model(Long modelId) {
		super();
		this.modelId = modelId;
	}

	public Model update(Model model) {
		this.fieldOne = Optional.ofNullable(model.getFieldOne()).orElse(this.fieldOne);
		this.fieldTwo = Optional.ofNullable(model.getFieldTwo()).orElse(this.fieldTwo);
		return this;
	}

	public String getFieldOne() {
		return fieldOne;
	}

	public void setFieldOne(String value) {
		this.fieldOne = value;
	}

	public String getFieldTwo() {
		return fieldTwo;
	}

	public void setFieldTwo(String value) {
		this.fieldTwo = value;
	}

	@Override
	protected void onCreate() {
		super.onCreate();
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
	}

	@Override
	public String toString() {
		return "Model [modelId=" + this.getModelId() + ", fieldOne=" + this.getFieldOne() + ", fieldTwo="
				+ this.getFieldTwo() + ", created=" + this.getCreated() + ", updated=" + this.getUpdated() + "]";
	}

}
