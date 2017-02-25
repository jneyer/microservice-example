package com.example.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.model.Model;

public interface ModelRepository extends PagingAndSortingRepository<Model, Long> {

}
