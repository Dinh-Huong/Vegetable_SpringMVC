package com.vegetable.controllers.user;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vegetable.dao.AccountImpl;
import com.vegetable.dao.CategoryImpl;
import com.vegetable.dao.CommentImpl;
import com.vegetable.dao.OrderDetailImpl;
import com.vegetable.dao.OrderImpl;
import com.vegetable.dao.ProductImpl;
import com.vegetable.entities.Category;
import com.vegetable.entities.Comment;
import com.vegetable.entities.Order;
import com.vegetable.entities.OrderDetail;
import com.vegetable.entities.Product;
import com.vegetable.entities.Users;
import com.vegetable.helpers.PaginationHelper;

import net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition.Undefined;
import net.bytebuddy.implementation.bind.annotation.Morph;
import net.bytebuddy.matcher.ModifierMatcher.Mode;

@Controller
public class HomeController {
	@Autowired(required = true)
	ProductImpl productImpl;
	@Autowired(required = true)
	CategoryImpl categoryImpl;
	@Autowired(required = true)
	OrderImpl orderImpl;
	@Autowired(required = true)
	OrderDetailImpl orderDetailImpl;
	@Autowired(required = true)
	AccountImpl accountImpl;
	@Autowired(required = true)
	CommentImpl commentImpl;
	
	@RequestMapping(value = {"/", "home"}, method = RequestMethod.GET)
	public String index(String search, Integer page, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if(page == null || page ==0) page = 1;
		Map<String, String> dataMap = new HashMap<String, String>();
		if(search != "" && search != null) dataMap.put("q", search);
		else {	
			model.addAttribute("paginate", productImpl.pagination(page, 8, dataMap));
//			model.addAttribute("products", productImpl.getAll());
			model.addAttribute("fruits", productImpl.getByType(0, 8));
			model.addAttribute("theBest", productImpl.getByType(0, 6));
			model.addAttribute("vegetable", productImpl.getByType(1, 8));
			
			Integer userId =  (Integer) session.getAttribute("id");
			if(userId != null) {		
				Order cart = orderImpl.getByStatusAndUserId(0, userId);
				List<OrderDetail> listOrderDetail = orderDetailImpl.getByOrderId(cart.getId());
				int count = (int) listOrderDetail.stream().count();
				model.addAttribute("count", count);
			}			
			model.addAttribute("page", "index");
		}
		return "home";
	}
	
	@RequestMapping(value = "fruits", method = RequestMethod.GET)
	public String fruitShop(String cate, Integer page, String search, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId =  (Integer) session.getAttribute("id");
		if(userId != null) {		
			Order cart = orderImpl.getByStatusAndUserId(0, userId);
			List<OrderDetail> listOrderDetail = orderDetailImpl.getByOrderId(cart.getId());
			int count = (int) listOrderDetail.stream().count();
			model.addAttribute("count", count);
		}		
		
		if(page == null || page ==0) page = 1;
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("fruits", "fruits");
		if(search != "" && search != null) dataMap.put("q", search);
		if(cate != "" && cate != null) dataMap.put("cate", cate);
		model.addAttribute("paginate", productImpl.pagination(page, 9, dataMap));
		String url = new PaginationHelper().getParamFromUrl(request);
		model.addAttribute("pageUrl", url);
		List<Category> listFruit = categoryImpl.getByStatus(0);
		
		model.addAttribute("category", listFruit);
		model.addAttribute("discount", productImpl.getByStatus(1));
//		model.addAttribute("fruits", productImpl.getAll());
		model.addAttribute("page", "fruitsShop");
		return "home";
	}
	@RequestMapping(value = "vegetables", method = RequestMethod.GET)
	public String vegetableShop(Integer page,String cate, String search, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId =  (Integer) session.getAttribute("id");
		if(userId != null) {		
			Order cart = orderImpl.getByStatusAndUserId(0, userId);
			List<OrderDetail> listOrderDetail = orderDetailImpl.getByOrderId(cart.getId());
			int count = (int) listOrderDetail.stream().count();
			model.addAttribute("count", count);
		}
		
		if(page == null || page ==0) page = 1;
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("vegetables", "vegetables");
		if(search != "" && search != null) dataMap.put("q", search);
		if(cate != "" && cate != null) dataMap.put("cate", cate);
		model.addAttribute("paginate", productImpl.pagination(page, 9, dataMap));
		String url = new PaginationHelper().getParamFromUrl(request);
		model.addAttribute("pageUrl", url);

		
		model.addAttribute("category", categoryImpl.getByStatus(1));
		model.addAttribute("discount", productImpl.getByStatus(1));
		model.addAttribute("page", "vegetableShop");
		return "home";
	}
	
	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail( @PathVariable("id") int id, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Integer userId =  (Integer) session.getAttribute("id");
		if(userId != null) {		
			Order cart = orderImpl.getByStatusAndUserId(0, userId);
			List<OrderDetail> listOrderDetail = orderDetailImpl.getByOrderId(cart.getId());
			int count = (int) listOrderDetail.stream().count();
			model.addAttribute("count", count);
		}
		List<Comment> comments = commentImpl.getByProductId(id);
		model.addAttribute("comments", comments);
		
		model.addAttribute("category", categoryImpl.getByStatus(0));
		model.addAttribute("theBest", productImpl.getByType(0, 6));
		model.addAttribute("theLate", productImpl.getByType(0, 10));
		model.addAttribute("product",productImpl.getById(id));
		model.addAttribute("comment", new Comment());
		
		model.addAttribute("page", "detail");
		return "home";
		
	}
	
	@RequestMapping(value = "postComment/{id}", method = RequestMethod.POST)
	public String postComment(@PathVariable("id") int proId, @Valid @ModelAttribute("comment") Comment cmt, Model model, RedirectAttributes attributes, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("id");
		if(cmt.getContent().isEmpty()) {
			attributes.addFlashAttribute("error", "Please fill out this field!");
			System.out.println("aaaaaaaaaaa");
			return "redirect:/detail/"+proId;
		}
		if(userId != null) {
			cmt.setUserId(userId);
			cmt.setProductId(proId);
			commentImpl.insert(cmt);
			
		} else {
			return "redirect:/login";
		}
		return "redirect:/detail/"+proId;
	}
	
	@RequestMapping(value = "addCart/{id}", method = RequestMethod.POST)
	public String addCart(@PathVariable("id") Integer proId,Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("id");
		if(userId != null ) {
			Product product = productImpl.getById(proId);
			Order cart = orderImpl.getByStatusAndUserId(0, userId);
			List<OrderDetail> listOrderDetail = orderDetailImpl.getByOrderId(cart.getId());
			
			boolean ProExistInCart = false;
			for (OrderDetail detail : listOrderDetail ) {
				if(detail.getProductId() == proId) {
					int quantity = detail.getQuantity() + Integer.parseInt( request.getParameter("quantity"));
					detail.setQuantity(quantity);
					detail.setTotalPrice(product.getPrice()*quantity);
					orderDetailImpl.update(detail);
					ProExistInCart = true;
				}
				break;
			}
			if(ProExistInCart != true) {
				OrderDetail orderDetail = new OrderDetail();
				
				orderDetail.setOrderId(cart.getId());
				orderDetail.setProductId(product.getId());
				orderDetail.setQuantity(Integer.parseInt( request.getParameter("quantity")));
				orderDetail.setTotalPrice(product.getPrice()*Integer.parseInt( request.getParameter("quantity")));
				orderDetailImpl.insert(orderDetail);
			}
			
		} else {
			return "redirect:/login";
		}
		
		return "redirect:/cart";
	}
	
	@RequestMapping(value = "cart", method = RequestMethod.GET)
	public String cart(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId =  (Integer) session.getAttribute("id");
		if(userId != null) {		
			Order cart = orderImpl.getByStatusAndUserId(0, userId);
			List<OrderDetail> listOrderDetail = orderDetailImpl.getByOrderId(cart.getId());
			int count = (int) listOrderDetail.stream().count();
			model.addAttribute("count", count);			
			model.addAttribute("listCart", listOrderDetail);
			float subTotal = 0;
			for (OrderDetail orderDetail : listOrderDetail) {
				subTotal += orderDetail.getTotalPrice();
				
			}
			System.out.print(subTotal);
			model.addAttribute("subTotal", subTotal);
		}
		model.addAttribute("page", "cart");
		return "home";
	}
	
	@RequestMapping(value = "cart/delete/{id}", method = RequestMethod.GET)
	public String delete(Model model, @PathVariable("id") int id) {
		try {
			System.out.print(id);
			orderDetailImpl.delete(id);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/cart";
	}
	
	@RequestMapping(value = "/update/order-detail/{id}/{quantity}", method = RequestMethod.GET)
    public String updateOrderDetail(@PathVariable("id") int id, @PathVariable("quantity") int quantity, Model model, HttpServletRequest request) {
		float subTotal = 0;

		OrderDetail detail = orderDetailImpl.getById(id);
		Product product = productImpl.getById(detail.getProductId());
		Float totalPrice = product.getPrice()*quantity;

		detail.setQuantity(quantity);
		detail.setTotalPrice(totalPrice);
		orderDetailImpl.update(detail);
		
		List<OrderDetail> listOrderDetail = orderDetailImpl.getByOrderId(detail.getOrderId());
		for (OrderDetail orderDetail : listOrderDetail) {
			subTotal += orderDetail.getTotalPrice();
			
		}
		System.out.print(subTotal);

        return "OKI";
    }
	
	@RequestMapping(value = "check-out", method = RequestMethod.GET)
	public String checkOut( Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId =  (Integer) session.getAttribute("id");
		if(userId != null) {		
			Order cart = orderImpl.getByStatusAndUserId(0, userId);
			List<OrderDetail> listOrderDetail = orderDetailImpl.getByOrderId(cart.getId());
			int count = (int) listOrderDetail.stream().count();
			model.addAttribute("count", count);
			
			float subTotal = 0;
			for (OrderDetail orderDetail : listOrderDetail) {
				subTotal += orderDetail.getTotalPrice();
				
			}
//			Order orderByUserId = orderImpl.getByUserId(userId);
			System.out.print("aaaaaaaaaaaaaaaaaaaa"+cart.getId());
			model.addAttribute("checkout", cart);
			model.addAttribute("subTotal", subTotal);
			model.addAttribute("listCart", listOrderDetail);
		}	
		
		model.addAttribute("page", "checkOut");
		return "home";
	}

	@RequestMapping(value = "check-out", method = RequestMethod.POST)
	public String placeOrder(@Valid @ModelAttribute("checkout") Order order, BindingResult result, Model model, HttpServletRequest request) {
		System.out.print(order.getName() + order.getId());
		HttpSession session = request.getSession();
		Integer userId =  (Integer) session.getAttribute("id");
		Order cart = orderImpl.getByStatusAndUserId(0, userId);
		List<OrderDetail> listOrderDetail = orderDetailImpl.getByOrderId(cart.getId());
		float subTotal = 0;
			for (OrderDetail orderDetail : listOrderDetail) {
				subTotal += orderDetail.getTotalPrice();
				
			}
		Boolean error = false;
		if(order.getName() == "") {
			model.addAttribute("errorName", "This field cannot be left blank!");
			error = true;
		}
		if(order.getCity() == "") {
			model.addAttribute("errorCity", "This field cannot be left blank!");
			error = true;
		}
		if(order.getAddress() == "") {
			model.addAttribute("errorAddress", "This field cannot be left blank!");
			error = true;
		}
		if(order.getPhone() == "") {
			model.addAttribute("errorPhone", "This field cannot be left blank!");
			error = true;
		}
		if(result.hasErrors() || error) {
			model.addAttribute("subTotal", subTotal);
			model.addAttribute("listCart", listOrderDetail);
			model.addAttribute("page", "checkOut");
			return "home";
		}
		try {
			System.out.print("cccccccccccccc");
			order.setTotalPrice(subTotal);
			order.setStatus(1);
			orderImpl.update(order);
			
			Order cartNew = new Order();
			cartNew.setStatus(0);
			cartNew.setUserId(userId); 
			orderImpl.insert(cartNew);
		} catch (Exception e) {
			System.out.print("gggg");
			model.addAttribute("subTotal", subTotal);
			model.addAttribute("listCart", listOrderDetail);
			model.addAttribute("page", "checkOut");
			return "home";
		}
		return "redirect:/home";
	}
	
	@RequestMapping(value = "contact", method = RequestMethod.GET)
	public String contact(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId =  (Integer) session.getAttribute("id");
		if(userId != null) {		
			Order cart = orderImpl.getByStatusAndUserId(0, userId);
			List<OrderDetail> listOrderDetail = orderDetailImpl.getByOrderId(cart.getId());
			int count = (int) listOrderDetail.stream().count();
			model.addAttribute("count", count);
		}	
		model.addAttribute("page", "contact");
		return "home";
	}
	
	@RequestMapping(value = "information", method = RequestMethod.GET)
	public String info(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("id");	
		if(userId != null) {
			Users acc = accountImpl.getById(userId);
			Order cart = orderImpl.getByStatusAndUserId(0, userId);
			List<OrderDetail> listOrderDetail = orderDetailImpl.getByOrderId(userId);
			int count = (int) listOrderDetail.stream().count();
			model.addAttribute("count", count);
			model.addAttribute("info", acc);			
		}
		model.addAttribute("notexist", "You are not logged in!");
		model.addAttribute("page", "information");
		return "home";
		
	}
	

}
