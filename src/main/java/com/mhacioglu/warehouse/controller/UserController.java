package com.mhacioglu.warehouse.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.NoSuchElementException;
import com.mhacioglu.warehouse.domain.User;
import com.mhacioglu.warehouse.domain.validator.SignupValidator;
import com.mhacioglu.warehouse.service.UserService;

@Controller
public class UserController {
	private final UserService userService;
	private final SignupValidator signupValidator;
	
	@Autowired
	public UserController(UserService userService, SignupValidator signupValidator) {
		this.userService = userService;
		this.signupValidator = signupValidator;
	}
	
	@RequestMapping(value = "/signup")
	public ModelAndView getSignUpPage() {
		return new ModelAndView("signup", "user", new User());
		/*
		 * kayit olma sayfasinin gonderilmesi
		 * kayit olacak kisiyi user nesnesinde tutulur
		 */
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUp(@Valid @ModelAttribute("user")User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return "signup";
		
		userService.addUser(user);
		return "redirect:/";
		/*
		 * kayit olurken degerler valide edilir.
		 * hata varsa sayfada hatalar gosterilir kullanici devam edemez
		 * yoksa ana sayfaya yonlendirilir.
		 */
	}
	
	@RequestMapping(value = "/users")
	public ModelAndView getUsersPage() {
		return new ModelAndView("userlist", "users", userService.getUsers());
		/*
		 * kullanici listesi sayfasinin goruntulenmesi
		 * view'da kullanilacak model olarak userlarin listesi verilir
		 */
	}
	
	@RequestMapping(value = "/users/{id}/items")
	public ModelAndView getUsersItemListPage(@PathVariable("id") long id) {
		userService.getUserById(id).orElseThrow(NoSuchElementException::new); // id'deki user yoksa exception firlatilir
		return new ModelAndView("useritemlist", "items", userService.itemNumberByName(id));
		/*
		 * verilen id'deki userin itemlerinin goruntulenmesi
		 * items modelinde itemname - itemlist hashmap'i saklanir.
		 */
		
	}
	
	@InitBinder()
	public void initBinder(WebDataBinder binder) {
	    binder.addValidators(signupValidator); // binder nesnesine validator'un eklenmesi
	}
}
