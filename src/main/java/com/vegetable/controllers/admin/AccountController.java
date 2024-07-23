package com.vegetable.controllers.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vegetable.dao.AccountImpl;
import com.vegetable.dao.OrderImpl;
import com.vegetable.entities.Category;
import com.vegetable.entities.Order;
import com.vegetable.entities.Users;
import com.vegetable.helpers.Cipher;
import com.vegetable.helpers.PaginationHelper;

@Controller
@RequestMapping("admin")
public class AccountController {
	@Autowired(required = true)
	AccountImpl accountImpl;
	@Autowired(required = true)
	OrderImpl orderImpl;
	
	@RequestMapping(value = {"accounts"}, method = RequestMethod.GET)
	public String index(Integer page ,String role,Model model, HttpServletRequest request ) {
//		Map<String, String> dataMap = new HashMap<String, String>();
//		if(page == null || page == 0) page = 1;
//		dataMap.put("role", role);
//		model.addAttribute("role", role);
//		model.addAttribute("paginate", accountImpl.pagination(page, 10, dataMap));
//		String url = new PaginationHelper().getParamFromUrl(request);
//		model.addAttribute("pageUrl", url);
		
		model.addAttribute("paginate", accountImpl.getAll());
		model.addAttribute("page", "Account/index");
		return "admin";
	}
	
	@RequestMapping(value = "account/register-admin", method = RequestMethod.GET)
	public String createAdmin(Model model) {
		model.addAttribute("admin", new Users());
		model.addAttribute("page", "Account/addAdmin");
		return "admin";
	}
	
	@RequestMapping(value = "account/register-admin", method = RequestMethod.POST)
	public String saveAdmin(@Valid @ModelAttribute("admin") Users admin, BindingResult result, Model model) {
		boolean error = false;
		if(accountImpl.getByEmail(admin.getEmail()) != null) {
			model.addAttribute("errorEmail", "Email already exists!");
			error = true;
		}
		if(result.hasErrors() || error) {
			model.addAttribute("admin", admin);
			model.addAttribute("page", "Account/addAdmin");
			return "admin";
		}
		try {
			String hashPass = new Cipher().GenerateMD5(admin.getPassword());
			admin.setPassword(hashPass);
			accountImpl.insert(admin);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("admin", admin);
			model.addAttribute("page", "Accounts/addAdmin");
			return "admin";
		}
		return "redirect:/admin/accounts";
	}
	
	@RequestMapping(value = "account/register-customner", method = RequestMethod.GET)
	public String createCustomer(Model model) {
		model.addAttribute("customer", new Users());
		model.addAttribute("page", "Account/addCustomer");
		return "admin";
	}
	
	@RequestMapping(value = "account/register-customner", method = RequestMethod.POST)
	public String saveCustomner(@Valid @ModelAttribute("customer") Users customer, BindingResult result, Model model) {
		boolean error = false;
		if(accountImpl.getByEmail(customer.getEmail()) != null) {
			model.addAttribute("errorEmail", "Email already exists!");
			error = true;
		}
		if(result.hasErrors() || error) {
			model.addAttribute("customer", customer);
			model.addAttribute("page", "Account/addCustomer");
			return "admin";
		}
		try {
			String hashPass = new Cipher().GenerateMD5(customer.getPassword());
			customer.setPassword(hashPass);
			accountImpl.insert(customer);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("customer", customer);
			model.addAttribute("page", "Accounts/addCustomer");
			return "admin";
		}
		return "redirect:/admin/accounts";
	}
//	chưa sửa đc T.T
	@RequestMapping(value = "account/delete/{id}", method = RequestMethod.GET)
	public String delete(Model model, @PathVariable("id") int id) {
		try {
			accountImpl.delete(id);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/admin/accounts";
	}
	
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model) {
		return "admin/login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String onLogin(String email, String password, Model model, HttpServletRequest request) {
		Users users = accountImpl.getByEmail(email);
		if(users == null || !users.getPassword().equals(Cipher.GenerateMD5(password)) || users.getRole() != 1) {
			model.addAttribute("error", "Email or password is invalid!");
			model.addAttribute("email", email);
			model.addAttribute("password", password);
			return "admin/login";
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
		return "redirect:/admin";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
//		huy session
		session.invalidate();
		return "redirect:/admin/login";
	}
}
