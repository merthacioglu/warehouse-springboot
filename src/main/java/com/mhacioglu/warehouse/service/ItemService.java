package com.mhacioglu.warehouse.service;



import com.mhacioglu.warehouse.domain.Item;
import com.mhacioglu.warehouse.domain.ItemAddForm;

public interface ItemService {
	void addItem(ItemAddForm itemAddForm);
	Iterable<Item> getAllItems();
	void deleteItemById(long id);
	
	Item getItemById(long id);
	void setOwner(String username, long itemId);
}
