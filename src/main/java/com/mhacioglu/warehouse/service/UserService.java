package com.mhacioglu.warehouse.service;

import com.mhacioglu.warehouse.domain.Item;
import com.mhacioglu.warehouse.domain.User;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
	void addUser(User user);
	Iterable<User> getUsers();
	List<String> getUsernames();
	User getUserByUsername(String username);
	Optional<User> getUserById(long id);
	Map<String, List<Item>>itemNumberByName(long userId);
}
