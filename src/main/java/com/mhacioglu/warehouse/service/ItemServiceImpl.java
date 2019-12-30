package com.mhacioglu.warehouse.service;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.mhacioglu.warehouse.domain.Item;
import com.mhacioglu.warehouse.domain.ItemAddForm;
import com.mhacioglu.warehouse.domain.User;
import com.mhacioglu.warehouse.repository.ItemRepository;
import com.mhacioglu.warehouse.repository.UserRepository;

@Service
public class ItemServiceImpl implements ItemService{
	private final ItemRepository itemRepo;
	private final UserRepository userRepo;
	
	@Autowired
	public ItemServiceImpl(ItemRepository itemRepo, UserRepository userRepo) {
		this.itemRepo = itemRepo;
		this.userRepo = userRepo;
	}
	
	public void addItem(ItemAddForm itemAddForm) {
		for(int i=0; i < itemAddForm.getAmount(); i++) {
			String code = Long.toHexString(Double
					.doubleToLongBits(Math.random())).substring(10);
			Item item = new Item(code, itemAddForm.getItemType());
			itemRepo.save(item);
			
		}  //item ekleme formundan item sayisini alip o kadar itemi repository'e ekler
		
	}
	
	@Override
	public Iterable<Item> getAllItems(){
		return itemRepo.findAll();
	} // repository'deki tum itemlari dondurur
	
	@Override
	public void deleteItemById(long itemId) {
		itemRepo.deleteById(itemId);
	} // id'ye gore itemi siler

	@Override
	public Item getItemById(long id) {
		return itemRepo.findById(id).get();
			
	} // id'ye gore itemi getirir.
	

	@Override
	public void setOwner(String username, long itemId) {
		
		User user = userRepo.findByUsername(username);
		Item item = getItemById(itemId);
		
		Set<Item> items = user.getItems();
		items.add(item);
		user.setItems(items);
		
		item.setUser(user);
		itemRepo.save(item);
		
		
	} /* 
		item'in user'ini atama:
		once user'daki item listesine atanacak item eklenir
		sonra item'in user'i atanir.
	*/

	
	
}
