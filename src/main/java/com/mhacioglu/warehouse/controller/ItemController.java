package com.mhacioglu.warehouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mhacioglu.warehouse.domain.ItemAddForm;
import com.mhacioglu.warehouse.domain.ItemOwner;
import com.mhacioglu.warehouse.service.ItemService;
import com.mhacioglu.warehouse.service.UserService;

@Controller
public class ItemController {
	private final ItemService itemService;
	private final UserService userService;
	
	@Autowired
	public ItemController(ItemService itemService, UserService userService) {
		this.itemService = itemService;
		this.userService = userService;
	}
	
	
	
	
	@RequestMapping(value = "/items/add")
	public ModelAndView getItemAddingPage() { 
		return new ModelAndView("itemAdding", "itemAddForm", new ItemAddForm());
		/*
		 * item ekleme sayfasi icin controller
		 * itemAddForm'a yapilacak islem sonucu kac item eklenecegi bilgisi getirilir
		 */
	}
	
	@RequestMapping(value = "/items", method = RequestMethod.POST)
	public String addItem(@Valid @ModelAttribute("itemForm")ItemAddForm form,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return "itemAdding";
		itemService.addItem(form);
		return "redirect:/items";
		/*
		 * itemAddForm'dan alinan bilgi ile item ekler
		 * @Valid ve Binding result ile validasyon yapilir.
		 */
	}
	
	@RequestMapping("/items")
	public ModelAndView getItemsPage() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("items", itemService.getAllItems());
		model.put("usernames", userService.getUsernames());
		model.put("owner", new ItemOwner());
		return new ModelAndView("itemlist", model);
		/*
		 * item listesinin goruntulenmesi
		 * kullanicilar, itemlerin kullanici listesi ve kullanici bilgisinin degismesi durumunda 
		 * kullanici bilgisinin degismesi durumunda 
		 * transfer edilebilmesi icin dto olarak owner gonderilir
		 */
	}
	
	
	
	@RequestMapping(value = "/items/{id}/edit", method = RequestMethod.POST)
	public String changeOwner(@ModelAttribute("owner") ItemOwner owner, @PathVariable("id") long id) {
	    itemService.setOwner(owner.getUsername(), id);
	    return "redirect:/items";
	    /*
	     * item'a ait user bilgisi degistiginde dto'nun getUsername()
	     * metodu kullanilarak id ile bulunan iteme kullanicisi atanir.
	     */
	}
	
	@RequestMapping(value = "items/{id}/delete", method = RequestMethod.POST)
	public String deleteItem (@PathVariable("id") long id) {
		itemService.deleteItemById(id);
		return "redirect:/items";
		/*
		 * post request'i ile verilen id'ye sahip item silinir.
		 */
	}
	
	

}
