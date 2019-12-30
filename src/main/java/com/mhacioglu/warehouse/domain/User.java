package com.mhacioglu.warehouse.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private long id;

	@Size(min = 3, max = 30, message = "Kullanıcı adı 3 ile 30 karakter arasında olmalıdır")
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Size(min = 3, message = "Kullanıcı en az 3 karakterden oluşmalıdır")
	@Column(name = "password", nullable = false)
	private String password;

	@NotEmpty( message = "İsim boş olamaz")
	@Column(name = "name", nullable = false)
	private String name;

	@NotEmpty( message = "Soyisim boş olamaz")
	@Column(name = "lastname", nullable = false)
	private String lastName;

	@OneToMany(mappedBy = "user")
	private Set<Item> items;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, String name, String lastName) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
	}

	public User() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return username;
	}

	 @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("USER");
	        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
	        list.add(simpleGrantedAuthority);
	        return list; // userlara 'user' yetkisinin atanmasi
	    }

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
