package com.vegetable.controllers.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vegetable.dao.AccountImpl;
import com.vegetable.dao.OrderImpl;
import com.vegetable.entities.Order;
import com.vegetable.entities.Users;
import com.vegetable.helpers.Cipher;

@Controller
public class UserController {
	@Autowired
	AccountImpl accountImpl;
	@Autowired(required = true)
	OrderImpl orderImpl;
	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("customer", new Users());
		return "register";
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String saveCustomner(@Valid @ModelAttribute("customer") Users customer, BindingResult result, Model model) {
		boolean error = false;
		if(accountImpl.getByEmail(customer.getEmail()) != null) {
			model.addAttribute("errorEmail", "Email already exists!");
			error = true;
		}
		if(result.hasErrors() || error) {
			model.addAttribute("customer", customer);
			return "register";
		}
		try {
			String hashPass = new Cipher().GenerateMD5(customer.getPassword());
			customer.setPassword(hashPass);
			accountImpl.insert(customer);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("customer", customer);
			return "register";
		}
		return "redirect:/login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String onLogin(String email, String password, Model model, HttpServletRequest request) {
		Users users = accountImpl.getByEmail(email);
		if(users == null || !users.getPassword().equals(Cipher.GenerateMD5(password)) || users.getRole() != 0 ) {
			model.addAttribute("error", "Email or password is invalid!");
			model.addAttribute("email", email);
			model.addAttribute("password", password);
			return "login";
		}
//		lay session || tao ra session
		HttpSession httpSession = request.getSession();
//		khoi tao time ton tai cho session
		httpSession.setMaxInactiveInterval(24*60*60*30);
		httpSession.setAttribute("id", users.getId());
		httpSession.setAttribute("name", users.getName());
		httpSession.setAttribute("email", users.getEmail());
		httpSession.setAttribute("password", users.getPassword());
		httpSession.setAttribute("role", users.getRole());
		
		Order cart = orderImpl.getByStatusAndUserId(0, users.getId());
		if(cart == null) {
			 Order cart1 = new Order();
			 cart1.setStatus(0);
			 cart1.setUserId(users.getId());
			 
			 orderImpl.insert(cart1);
			 
		} 
		
		return "redirect:/home";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
//		huy session
		session.invalidate();
		return "redirect:/home";
	}
	
}
