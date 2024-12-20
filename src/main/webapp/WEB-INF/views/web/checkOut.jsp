<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${ pageContext.servletContext.contextPath }" scope="session" />
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<!-- Single Page Header start -->
<div class="container-fluid page-header py-5">
	<h1 class="text-center text-white display-6">Checkout</h1>
	<ol class="breadcrumb justify-content-center mb-0">
		<li class="breadcrumb-item"><a href="#">Home</a></li>
		<li class="breadcrumb-item"><a href="#">Pages</a></li>
		<li class="breadcrumb-item active text-white">Checkout</li>
	</ol>
</div>
<!-- Single Page Header End -->


<!-- Checkout Page Start -->
<div class="container-fluid py-5">
	<div class="container py-5">
		<h1 class="mb-4">Billing details</h1>
		<f:form action="" method="post" modelAttribute="checkout">
			<f:hidden path="id"/>
			<f:hidden path="userId"/>
			<div class="row g-5">
				<div class ="col-md-12 col-lg-6 col-xl-7">
						<div class="form-item w-100">
							<label class="form-label my-3">Name<sup>*</sup></label> 
							<f:input path="name" type="text" class="form-control"/>
							<c:if test="${ !empty errorName }">
								<span class="text-danger">${ errorName }</span>
							</c:if>
						</div>
						<div class="form-item">
							<label class="form-label my-3">Company Name<sup>*</sup></label> 
							<f:input path="company" type="text" class="form-control"/>
						</div>
						<div class="form-item">
							<label class="form-label my-3">Address <sup>*</sup></label> 
							<f:input path="address" type="text" class="form-control" placeholder="House Number Street Name"/>
							<c:if test="${ !empty errorAddress }">
								<span class="text-danger">${ errorAddress }</span>
							</c:if>
						</div>
						<div class="form-item">
							<label class="form-label my-3">Town/City<sup>*</sup></label> 
							<f:input path="city" type="text" class="form-control"/>
							<c:if test="${ !empty errorCity }">
								<span class="text-danger">${ errorCity }</span>
							</c:if>
						</div>
						<div class="form-item">
							<label class="form-label my-3">Mobile<sup>*</sup></label> 
							<f:input path="phone" type="number" class="form-control"/>
							<f:errors path="phone" cssClass="text-danger"></f:errors>
							<c:if test="${ !empty errorPhone }">
								<span class="text-danger">${ errorPhone }</span>
							</c:if>
						</div>
						<div class="form-item">
							<label class="form-label my-3">Email Address<sup>*</sup></label> 
							<f:input path="email" type="email" class="form-control"/>
							<f:errors path="email" cssClass="text-danger"></f:errors>
						</div>
						<div class="form-item">
							<label class="form-label my-3">Note</label> 
							<textarea name="note" class="form-control" spellcheck="false"
								cols="30" rows="11" placeholder="Oreder Notes (Optional)"></textarea>
						</div>
				</div>
				<div class="col-md-12 col-lg-6 col-xl-5">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Products</th>
									<th scope="col">Name</th>
									<th scope="col">Price</th>
									<th scope="col">Quantity</th>
									<th scope="col">Total</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ listCart }" var="detail">
									<tr>
										<th scope="row">
											<div class="d-flex align-items-center mt-2">
												<img src="${ contextPath }/${ detail.product.thumbnail }"
													class="img-fluid rounded-circle"
													style="width: 90px; height: 90px;" alt="">
											</div>
										</th>
										<td class="py-5"> ${ detail.product.name } </td>
										<td class="py-5">$ ${ detail.product.price }</td>
										<td class="py-5"> ${ detail.quantity } </td>
										<td class="py-5">$ ${ detail.totalPrice }</td>
									</tr>
								</c:forEach>
								<tr>
									<th scope="row"></th>
									<td class="py-5"></td>
									<td class="py-5"></td>
									<td class="py-5">
										<p class="mb-0 text-dark py-3">Subtotal</p>
									</td>
									<td class="py-5">
										<div class="py-3 border-bottom border-top">
											<p class="mb-0 text-dark">$ ${subTotal} </p>
										</div>
									</td>
								</tr>
								<tr>
									<th scope="row"></th>
									<td class="py-5">
										<p class="mb-0 text-dark py-4">Shipping</p>
									</td>
									<td colspan="3" class="py-5">
										<div class="form-check text-start">
											<input type="checkbox"
												class="form-check-input bg-primary border-0" id="Shipping-1"
												name="Shipping-1" value="Shipping"> <label
												class="form-check-label" for="Shipping-1">Free
												Shipping</label>
										</div>
										<div class="form-check text-start">
											<input type="checkbox"
												class="form-check-input bg-primary border-0" id="Shipping-2"
												name="Shipping-1" value="Shipping"> <label
												class="form-check-label" for="Shipping-2">Flat rate:
												$15.00</label>
										</div>
										<div class="form-check text-start">
											<input type="checkbox"
												class="form-check-input bg-primary border-0" id="Shipping-3"
												name="Shipping-1" value="Shipping"> <label
												class="form-check-label" for="Shipping-3">Local
												Pickup: $8.00</label>
										</div>
									</td>
								</tr>
								<tr>
									<th scope="row"></th>
									<td class="py-5">
										<p class="mb-0 text-dark text-uppercase py-3">TOTAL</p>
									</td>
									<td class="py-5"></td>
									<td class="py-5"></td>
									<td class="py-5">
										<div class="py-3 border-bottom border-top">
											<p class="mb-0 text-dark">$ ${ subTotal }</p>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div
						class="row g-4 text-center align-items-center justify-content-center border-bottom py-3">
						<div class="col-12">
							<div class="form-check text-start my-3">
								<input type="checkbox"
									class="form-check-input bg-primary border-0" id="Transfer-1"
									name="Transfer" value="Transfer"> <label
									class="form-check-label" for="Transfer-1">Direct Bank
									Transfer</label>
							</div>
							<p class="text-start text-dark">Make your payment directly
								into our bank account. Please use your Order ID as the payment
								reference. Your order will not be shipped until the funds have
								cleared in our account.</p>
						</div>
					</div>
					<div
						class="row g-4 text-center align-items-center justify-content-center border-bottom py-3">
						<div class="col-12">
							<div class="form-check text-start my-3">
								<input type="checkbox"
									class="form-check-input bg-primary border-0" id="Payments-1"
									name="Payments" value="Payments"> <label
									class="form-check-label" for="Payments-1">Check
									Payments</label>
							</div>
						</div>
					</div>
					<div
						class="row g-4 text-center align-items-center justify-content-center border-bottom py-3">
						<div class="col-12">
							<div class="form-check text-start my-3">
								<input type="checkbox"
									class="form-check-input bg-primary border-0" id="Delivery-1"
									name="Delivery" value="Delivery"> <label
									class="form-check-label" for="Delivery-1">Cash On
									Delivery</label>
							</div>
						</div>
					</div>
					<div
						class="row g-4 text-center align-items-center justify-content-center border-bottom py-3">
						<div class="col-12">
							<div class="form-check text-start my-3">
								<input type="checkbox"
									class="form-check-input bg-primary border-0" id="Paypal-1"
									name="Paypal" value="Paypal"> <label
									class="form-check-label" for="Paypal-1">Paypal</label>
							</div>
						</div>
					</div>
					<div
						class="row g-4 text-center align-items-center justify-content-center pt-4">
						<button type="submit"
							class="btn border-secondary py-3 px-4 text-uppercase w-100 text-primary">Place
							Order</button>
					</div>
				</div>
			</div>
		</f:form>
	</div>
</div>
<!-- Checkout Page End -->