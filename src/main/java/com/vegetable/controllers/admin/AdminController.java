package com.vegetable.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vegetable.dao.AccountImpl;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	AccountImpl accountImpl;
	@RequestMapping("")
	public String index(Model model) {
		model.addAttribute("paginate", accountImpl.getAll());
		model.addAttribute("page", "Account/index");
		return "admin";
	}
}
