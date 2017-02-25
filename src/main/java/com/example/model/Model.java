package com.example.model;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.joda.time.DateTime;
import org.springframework.data.annotation.ReadOnlyProperty;

@Entity
@Table(name = "model")
public class Model {

	@Id
	@GeneratedValue
	@ReadOnlyProperty
	@Column(name = "model_id", nullable = false)
	private Long id;

	@Column(name = "field_one")
	private String fieldOne;

	@Column(name = "field_two")
	private String fieldTwo;

	@ReadOnlyProperty
	@Column(name = "created")
	private DateTime created;

	@ReadOnlyProperty
	@Column(name = "updated")
	private DateTime updated;

	public Model() {
		super();
	}

	public Model(Long id) {
		super();
		this.id = id;
	}

	@PrePersist
	protected void onCreate() {
		this.created = new DateTime();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updated = new DateTime();
	}

	public Model update(Model model) {
		this.fieldOne = Optional.ofNullable(model.getFieldOne()).orElse(this.fieldOne);
		this.fieldTwo = Optional.ofNullable(model.getFieldTwo()).orElse(this.fieldTwo);
		return this;
	}

	public Long getId() {
		return id;
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

	public DateTime getCreated() {
		return created;
	}

	public DateTime getUpdated() {
		return updated;
	}

	@Override
	public String toString() {
		return "Model [id=" + id + ", fieldOne=" + fieldOne + ", fieldTwo=" + fieldTwo + ", created=" + created
				+ ", updated=" + updated + "]";
	}

}
