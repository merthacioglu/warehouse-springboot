package com.mhacioglu.warehouse.repository;

import org.springframework.data.repository.CrudRepository;

import com.mhacioglu.warehouse.domain.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
