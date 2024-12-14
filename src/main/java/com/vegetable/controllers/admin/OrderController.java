package com.vegetable.controllers.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vegetable.dao.OrderImpl;
import com.vegetable.entities.Order;
import com.vegetable.helpers.PaginationHelper;

@Controller
@RequestMapping("admin")
public class OrderController {
	@Autowired(required = true)
	OrderImpl orderImpl;
	
	@RequestMapping(value = "orders", method = RequestMethod.GET)
	public String index(Integer page, String q, Model model, HttpServletRequest request) {
		if(page == null || page ==0) page = 1;
		Map<String, String> dataMap = new HashMap<String, String>();
		if(q != "" && q != null) dataMap.put("q", q);
		model.addAttribute("paginate", orderImpl.pagination(page, 5, dataMap));
		String url = new PaginationHelper().getParamFromUrl(request);
		model.addAttribute("pageUrl", url);
		model.addAttribute("page", "Order/index");
		return "admin";
	}
	
	@RequestMapping(value = "change/{id}", method = RequestMethod.POST)
	public String changeStatus(@PathVariable("id") int id, @RequestParam("status") int status, Model model) {
		Order order = orderImpl.getById(id);
		order.setStatus(status);
		orderImpl.update(order);
		return "redirect:/admin/orders";
	}
}
