package com.vegetable.controllers.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vegetable.dao.CategoryImpl;
import com.vegetable.entities.Category;
import com.vegetable.helpers.PaginationHelper;

@Controller
@RequestMapping("admin")
public class CategoryController {
	@Autowired(required=true)
	CategoryImpl categoryImpl;
	
	@RequestMapping(value = "category", method = RequestMethod.GET)
	public String index(Integer page, String q, Model model, HttpServletRequest request) {
		if(page == null || page ==0) page = 1;
		Map<String, String> dataMap = new HashMap<String, String>();
		if(q != "" && q != null) dataMap.put("q", q);
		model.addAttribute("paginate", categoryImpl.pagination(page, 10, dataMap));
		String url = new PaginationHelper().getParamFromUrl(request);
		model.addAttribute("pageUrl", url);
		
//		model.addAttribute("data", categoryImpl.getAll());
		model.addAttribute("page", "Category/index");
		return "admin";
	}
	
	@RequestMapping(value = "category/add", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("page", "Category/add");
		model.addAttribute("category", new Category());
		return "admin";
	}
	
	@RequestMapping(value = "category/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
		Boolean error = false;
		
		if(categoryImpl.getByName(category.getName()) != null) {
			model.addAttribute("errorName", "This name already exists!");
			error = true;
		}
		if(categoryImpl.getBySlug(category.getSlug()) != null) {
			model.addAttribute("errorSlug", "This name already exists!");
			error = true;
		}
		if(result.hasErrors()||error) {
			model.addAttribute("category", category);
			model.addAttribute("page", "Category/add");
			return "admin";
		}
		try {
			categoryImpl.insert(category);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("category", category);
			model.addAttribute("page", "Category/add");
			return "admin";
		}
		return "redirect:/admin/category";
	}
	
	@RequestMapping(value = "category/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("category", categoryImpl.getById(id));
		model.addAttribute("page", "Category/edit");
		return "admin";
	}
	
	@RequestMapping(value = "category/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
		Boolean error = false;
		
		if(categoryImpl.getByName(category.getName()) != null && !categoryImpl.getById(category.getId()).getName().equals(category.getName())) {
			model.addAttribute("errorName", "This name already exists!");
			error = true;
		}
		if(categoryImpl.getBySlug(category.getSlug()) != null && !categoryImpl.getById(category.getId()).getSlug().equals(category.getSlug())) {
			model.addAttribute("errorSlug", "This name already exists!");
			error = true;
		}
		if(result.hasErrors() || error) {
			model.addAttribute("category", category);
			model.addAttribute("page", "Category/edit");
			return "admin";
		}
		try {
			categoryImpl.update(category);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("categoy", category);
			model.addAttribute("page", "Category/edit");
			return "admin";
		}
		return "redirect:/admin/category";
	}
	
	@RequestMapping(value = "category/delete/{id}", method = RequestMethod.GET)
	public String delete(Model model, @PathVariable("id") int id) {
		try {
			categoryImpl.delete(id);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/admin/category";
	}
}
