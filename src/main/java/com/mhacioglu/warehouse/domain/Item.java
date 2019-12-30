package com.mhacioglu.warehouse.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private long id;
	
	@Column(name ="code", nullable = false, updatable = false, unique = true)
	private String code;
	
	@Column(name = "itemType", nullable = false)
	private String itemType;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Item() {
		
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Item(String code, String itemType) {
		this.code = code;
		this.itemType = itemType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", code=" + code + ", itemType=" + itemType + "]";
	}
	
	
}
