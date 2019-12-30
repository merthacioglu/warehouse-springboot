package com.mhacioglu.warehouse.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mhacioglu.warehouse.domain.Item;
import com.mhacioglu.warehouse.domain.User;
import com.mhacioglu.warehouse.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	
	private final UserRepository userRepo;
	
    private BCryptPasswordEncoder encoder;
    
    
	@Autowired
	
	public UserServiceImpl(UserRepository userRepo,  BCryptPasswordEncoder encoder) {
		this.userRepo = userRepo;
		this.encoder = encoder;
	}
	

	
	@Override
	public void addUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
		/*
		 * user repo ile user eklenmesi
		 * ekleme yapilirken password bilgisi spring security
		 * kapsaminda encrypt ediliir
		 */
	}

	@Override
	public Iterable<User> getUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
		/*
		 * repository'deki tum user bilgileri getilir.
		 */
	}
	
	@Override
	public List<String> getUsernames(){
		List<String> usernames = new ArrayList<String>();
		Iterator iterator = getUsers().iterator();
		
		while(iterator.hasNext()) {
			User user = (User) iterator.next();
			usernames.add(user.getUsername());
		}
		
		return usernames;
		/*
		 * Userlar uzerinde iterate edilerek username bilgilerinin
		 * alinmasi ve return edilmesi
		 */
		
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub

		return userRepo.findByUsername(username);
		/*
		 * username bilgisi ile username'in return edilmesi
		 */
	}

	@Override
	public Optional<User> getUserById(long id) {
		// TODO Auto-generated method stub

		return userRepo.findById(id);
		/*
		 * id bilgisi ile username'in return edilmesi
		 */
	}

	@Override
	public Map<String, List<Item>> itemNumberByName(long userId) {
		// TODO Auto-generated method stub
		Map<String, List<Item>> itemMap = new HashMap<String, List<Item>>();
		Set<Item> usersItems = getUserById(userId).get().getItems();
		for(Item item : usersItems) {
			List<Item> itemList = new ArrayList<Item>();
			String itemName = item.getItemType();
			
			if(itemMap.containsKey(itemName))
				itemList = itemMap.get(itemName);
			
			itemList.add(item);
			itemMap.put(itemName, itemList);
			
		}
		return itemMap;
		/*
		 * bir user'in itemlerinin listesini dondurur
		 * user'in itemleri dondurulur
		 * user'in her bir itemi uzerinde gezilerek 
		 * itemname-itemlist seklinde key-value ikilileri tasiyan bir map 
		 * olusturulur.
		 */
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = getUserByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException(username + " adında kullanıcı bulunamadı.");
		else
			return user; 
		/*
		 * username'e sahip kullanicinin var olup olmadiginin kontrolu
		 */
	}
	
	
	
	
	
	
}
