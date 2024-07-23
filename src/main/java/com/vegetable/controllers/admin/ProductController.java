package com.vegetable.controllers.admin;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vegetable.dao.CategoryImpl;
import com.vegetable.dao.ProductImpl;
import com.vegetable.entities.Category;
import com.vegetable.entities.Product;
import com.vegetable.helpers.PaginationHelper;

@Controller
@RequestMapping("admin")
public class ProductController {
	@Autowired(required = true)
	ProductImpl productImpl;
	@Autowired(required = true)
	CategoryImpl categoryImpl;
	
	@RequestMapping(value = "products", method = RequestMethod.GET)
	public String index(Integer page, String q, Model model, HttpServletRequest request) {
		if(page == null || page ==0) page = 1;
		Map<String, String> dataMap = new HashMap<String, String>();
		if(q != "" && q != null) dataMap.put("q", q);
		model.addAttribute("paginate", productImpl.pagination(page, 10, dataMap));
		String url = new PaginationHelper().getParamFromUrl(request);
		model.addAttribute("pageUrl", url);
		
		model.addAttribute("page", "Product/index");
		return "admin";
	}
	
	@RequestMapping(value = "product/add", method = RequestMethod.GET)
	public String create(Model model) {
		List<Category> listCategory = categoryImpl.getAll();
		model.addAttribute("category", listCategory);
		model.addAttribute("page", "Product/add");
		model.addAttribute("product", new Product());
		return "admin";
	}
	
	@RequestMapping(value = "product/add", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("product") Product product, BindingResult result, @RequestParam("upload-file") MultipartFile file, Model model,  HttpServletRequest request ) {
		Boolean error = false;
		if(productImpl.getByName(product.getName()) != null) {
			model.addAttribute("errorName", "Name already exists!");
			error = true;
		}
		if(productImpl.getBySlug(product.getSlug()) != null) {
			model.addAttribute("errorSlug", "Name already exists!");
			error = true;
		}
		if (file.isEmpty()) {
	        model.addAttribute("errorImage", "Please select an image file!");
	        error = true;
	    }
		if(result.hasErrors() || error) {
			model.addAttribute("product", product);
			model.addAttribute("category", categoryImpl.getAll());
			model.addAttribute("page", "Product/add");	
			return "admin";
		}

		try {
//			lay duowng dan luu tru tep thu muc
			String uploadDir = request.getServletContext().getRealPath("resources/images");
			String fileName = System.currentTimeMillis() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
			File uploadDirFile = new File(uploadDir + "/" + fileName );
			if(!uploadDirFile.exists()) {
				uploadDirFile.mkdirs();
			}
//			chuyen duong dan nay vao thu muc da tao
			file.transferTo(uploadDirFile);
			product.setThumbnail("images/"+ fileName);
			
			productImpl.insert(product);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("product", product);
			model.addAttribute("page", "Product/add");
		}
		return "redirect:/admin/products";
	}
	
	@RequestMapping(value = "product/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("product", productImpl.getById(id));
		model.addAttribute("category", categoryImpl.getAll());
		model.addAttribute("page", "Product/edit");
		return "admin";
	}
	
	@RequestMapping(value = "product/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("product") Product product, BindingResult result, @RequestParam("upload-file") MultipartFile file, Model model,  HttpServletRequest request ) {
		Boolean error = false;
		if(productImpl.getByName(product.getName()) != null && !productImpl.getById(product.getId()).getName().equals(product.getName())) {
			model.addAttribute("errorName", "Name already exists!");
			error = true;
		}
		if(productImpl.getBySlug(product.getSlug()) != null && !productImpl.getById(product.getId()).getSlug().equals(product.getSlug())) {
			model.addAttribute("errorSlug", "Name already exists!");
			error = true;
		}
		if(result.hasErrors() || error) {
			System.out.print(product.getCategoryId());
			model.addAttribute("product", product);
			model.addAttribute("category", categoryImpl.getAll());
			model.addAttribute("page", "Product/edit");	
			return "admin";
		}

		try {
//			lay duowng dan luu tru tep thu muc
			String uploadDir = request.getServletContext().getRealPath("resources/images");
			if(!file.isEmpty()) {
				String fileName = System.currentTimeMillis() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
				File uploadDirFile = new File(uploadDir + "/" + fileName );
				if(!uploadDirFile.exists()) {
					uploadDirFile.mkdirs();
				}
//			chuyen duong dan nay vao thu muc da tao
				file.transferTo(uploadDirFile);
				product.setThumbnail("images/"+ fileName);				
			}
			
			productImpl.update(product);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("product", product);
			model.addAttribute("page", "Product/edit");
		}
		return "redirect:/admin/products";
	}
	
	@RequestMapping(value = "product/delete/{id}", method = RequestMethod.GET)
	public String delete(Model model, @PathVariable("id") int id) {
		
			productImpl.delete(id);
		
		return "redirect:/admin/products";
	}
}
