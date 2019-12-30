package com.mhacioglu.warehouse;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.mhacioglu.warehouse.domain.*;
import com.mhacioglu.warehouse.repository.*;
import com.mhacioglu.warehouse.service.UserServiceImpl;

@SpringBootApplication
public class WarehouseApplication {

	@Autowired
    UserServiceImpl userService;
	
    @Autowired
    ItemRepository itemRepository;

    
    
	public static void main(String[] args) {
	
		SpringApplication.run(WarehouseApplication.class, args);
		
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		Item item1 = new Item("236dsh374", "Kol Saati");
        Item item2 = new Item("382udjs", "Sandalye");
        Item item3 =  new Item("djf73hr", "Kol Saati");
        Item item4 = new Item("skfu3", "Cep Telefonu");

        Set set1 = new HashSet<Item>();
        set1.add(item1);
        set1.add(item3);
        set1.add(item4);

        Set set2 = new HashSet<Item>();
        set2.add(item2);

        User user1 = new User("abcdef", "123456");
        user1.setName("Mert");
        user1.setLastName("Hacioglu");
        user1.setItems(set1);

        User user2 = new User("xyz", "000");
        user2.setName("Alan");
        user2.setLastName("Turing");
        user2.setItems(set2);

        item1.setUser(user1);
        item3.setUser(user1);
        item4.setUser(user1);

        item2.setUser(user2);

        userService.addUser(user1);
        userService.addUser(user2);

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
       
      
	}
}
