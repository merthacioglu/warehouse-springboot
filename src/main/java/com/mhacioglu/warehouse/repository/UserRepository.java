package com.mhacioglu.warehouse.repository;

import org.springframework.data.repository.CrudRepository;

import com.mhacioglu.warehouse.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username); // CrudRepository'de bulunan hazir fonksiyon
}
