package com.vegetable.controllers.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vegetable.dao.OrderImpl;
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
		model.addAttribute("paginate", orderImpl.pagination(page, 10, dataMap));
		String url = new PaginationHelper().getParamFromUrl(request);
		model.addAttribute("pageUrl", url);
		
		model.addAttribute("page", "Order/index");
		return "admin";
	}
}
