<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"
	value="${ pageContext.servletContext.contextPath }" scope="session" />
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<!-- Single Page Header start -->
<div class="container-fluid page-header py-5">
	<h1 class="text-center text-white display-6">Shop Detail</h1>
	<ol class="breadcrumb justify-content-center mb-0">
		<li class="breadcrumb-item"><a href="#">Home</a></li>
		<li class="breadcrumb-item"><a href="#">Pages</a></li>
		<li class="breadcrumb-item active text-white">Shop Detail</li>
	</ol>
</div>
<!-- Single Page Header End -->


<!-- Single Product Start -->
<div class="container-fluid py-5 mt-5">
	<div class="container py-5">
		<div class="row g-4 mb-5">
			<div class="col-lg-8 col-xl-9">
				<div class="row g-4">
					<div class="col-lg-6">
						<div class="border rounded">
							<a href="#"> <img src="${ contextPath }/${product.thumbnail}"
								class="img-fluid rounded" alt="Image">
							</a>
						</div>
					</div>
					<div class="col-lg-6">
						<h4 class="fw-bold mb-3">${ product.name }</h4>
						<p class="mb-3">Category: ${ product.category.name }</p>
						<h5 class="fw-bold mb-3"> ${ product.price } $</h5>
						<div class="d-flex mb-4">
							<i class="fa fa-star text-secondary"></i> <i
								class="fa fa-star text-secondary"></i> <i
								class="fa fa-star text-secondary"></i> <i
								class="fa fa-star text-secondary"></i> <i class="fa fa-star"></i>
						</div>
						<p class="mb-4">  </p>
						<form action="${ contextPath }/addCart/${ product.id }" method="post">
							<div class="input-group quantity mb-5" style="width: 100px;">
								<div class="input-group-btn">
									<button onclick="hanldeQuantity(this, null)" type="button"
										class="btn btn-sm btn-minus rounded-circle bg-light border">
										<i class="fa fa-minus"></i>
									</button>
								</div>
								<input type="text" id="quantity-inp_null" name="quantity"
									class="form-control form-control-sm text-center border-0"
									value="1" min="1">
								<div class="input-group-btn">
									<button onclick="hanldeQuantity(this, null)" type="button"
										class="btn btn-sm btn-plus rounded-circle bg-light border">
										<i class="fa fa-plus"></i>
									</button>
								</div>
							</div>
							<button type="submit"
								class="btn border border-secondary rounded-pill px-4 py-2 mb-4 text-primary"><i
								class="fa fa-shopping-bag me-2 text-primary" ></i> Add to cart</a>
						</form>
					</div>
					<div class="col-lg-12">
						<nav>
							<div class="nav nav-tabs mb-3">
								<button class="nav-link active border-white border-bottom-0"
									type="button" role="tab" id="nav-about-tab"
									data-bs-toggle="tab" data-bs-target="#nav-about"
									aria-controls="nav-about" aria-selected="true">Description</button>
								<button class="nav-link border-white border-bottom-0"
									type="button" role="tab" id="nav-mission-tab"
									data-bs-toggle="tab" data-bs-target="#nav-mission"
									aria-controls="nav-mission" aria-selected="false">Reviews</button>
							</div>
						</nav>
						<div class="tab-content mb-5">
							<div class="tab-pane active" id="nav-about" role="tabpanel"
								aria-labelledby="nav-about-tab">
								<p>${ product.description }</p>
							</div>
							<div class="tab-pane" id="nav-mission" role="tabpanel"
								aria-labelledby="nav-mission-tab">
								<c:forEach items="${ comments }" var="cmt">
									<div class="d-flex">
										<img src="${ contextPath }/user/img/avatar.jpg" class="img-fluid rounded-circle p-3"
											style="width: 100px; height: 100px;" alt="">
										<div class="">
											<p class="mb-2" style="font-size: 14px;"> ${ cmt.createdAt } </p>
											<div class="d-flex justify-content-between">
												<h5>${ cmt.users.name }</h5>
												<div class="d-flex mb-3">
													<i class="fa fa-star text-secondary"></i> <i
														class="fa fa-star text-secondary"></i> <i
														class="fa fa-star text-secondary"></i> <i
														class="fa fa-star text-secondary"></i> <i
														class="fa fa-star"></i>
												</div>
											</div>
											<p>${ cmt.content }</p>
										</div>
									</div>
								</c:forEach>
								
							</div>
							<div class="tab-pane" id="nav-vision" role="tabpanel">
								<p class="text-dark">Tempor erat elitr rebum at clita. Diam
									dolor diam ipsum et tempor sit. Aliqu diam amet diam et eos
									labore. 3</p>
								<p class="mb-0">Diam dolor diam ipsum et tempor sit. Aliqu
									diam amet diam et eos labore. Clita erat ipsum et lorem et sit</p>
							</div>
						</div>
					</div>
					<f:form action="${ contextPath }/postComment/${ product.id } " method="post" modelAttribute="comment">
						<h4 class="mb-5 fw-bold">Leave a Reply</h4>
						<!-- <div class="row g-4">
							<div class="col-lg-6">
								<div class="border-bottom rounded">
									<input type="text" class="form-control border-0 me-4"
										placeholder="Yur Name *">
								</div>
							</div>
							<div class="col-lg-6">
								<div class="border-bottom rounded">
									<input type="email" class="form-control border-0"
										placeholder="Your Email *">
								</div>
							</div> -->
							<div class="col-lg-12">
								<div class="border-bottom rounded my-4">
										<f:textarea path="content" placeholder="Your Review *" class="form-control border-0" cols="30"
										rows="8" spellcheck="false"/>
								</div>
								<span class="text-danger"> ${ error } </span>
								<f:errors path="content" cssClass="error text-danger"></f:errors>
								
							</div>
							<div class="col-lg-12">
								<div class="d-flex justify-content-between py-3 mb-5">
									<div class="d-flex align-items-center">
										<p class="mb-0 me-3">Please rate:</p>
										<div class="d-flex align-items-center"
											style="font-size: 12px;">
											<i class="fa fa-star text-muted"></i> <i class="fa fa-star"></i>
											<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
												class="fa fa-star"></i>
										</div>
									</div>
									<button type="submit"
										class="btn border border-secondary text-primary rounded-pill px-4 py-3">
										Post Comment</button>
								</div>
							</div>
						</div>
					</f:form>
				</div>
				<div class="col-lg-4 col-xl-3">
					<div class="row g-4 fruite">
						<div class="col-lg-12">
						<!-- 	<div class="input-group w-100 mx-auto d-flex mb-4">
								<input type="search" class="form-control p-3"
									placeholder="keywords" aria-describedby="search-icon-1">
								<span id="search-icon-1" class="input-group-text p-3"><i
									class="fa fa-search"></i></span>
							</div> -->
							<div class="mb-4">
								<h4>Categories</h4>
								<ul class="list-unstyled fruite-categorie">
									<c:forEach items="${ category }" var="cate">
										<li>
											<div class="d-flex justify-content-between fruite-name">
												<a href="/Vegetable/fruits?cate=${ cate.id }"><i class="fas fa-apple-alt me-2"></i>${ cate.name }</a> <span>(3)</span>
											</div>
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
						<div class="col-lg-12">
							<h4 class="mb-4">Featured products</h4>
							<c:forEach items="${ theBest }" var="best">
							<a href="${ contextPath }/detail/${best.id}">
								<div class="d-flex align-items-center justify-content-start">
									<div class="rounded" style="width: 100px; height: 100px;">
										<img src="${ contextPath }/${ best.thumbnail }" class="img-fluid rounded"
											alt="Image">
									</div>
									<div class="ml-3">
										<h6 class="mb-2">${ best.name }</h6>
										<div class="d-flex mb-2">
											<i class="fa fa-star text-secondary"></i> <i
												class="fa fa-star text-secondary"></i> <i
												class="fa fa-star text-secondary"></i> <i
												class="fa fa-star text-secondary"></i> <i class="fa fa-star"></i>
										</div>
										<div class="d-flex mb-2">
											<h5 class="fw-bold me-2"> ${ best.price } $</h5>
											<!-- <h5 class="text-danger text-decoration-line-through">4.11
												$</h5> -->
										</div>
									</div>
								</div>
							</a>
							</c:forEach>
							<div class="d-flex justify-content-center my-4">
								<a href="#"
									class="btn border border-secondary px-4 py-3 rounded-pill text-primary w-100">Vew
									More</a>
							</div>
						</div>
						<div class="col-lg-12">
							<div class="position-relative">
								<img src="img/banner-fruits.jpg" class="img-fluid w-100 rounded"
									alt="">
								<div class="position-absolute"
									style="top: 50%; right: 10px; transform: translateY(-50%);">
									<h3 class="text-secondary fw-bold">
										Fresh <br> Fruits <br> Banner
									</h3>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<h1 class="fw-bold mb-0">Related products</h1>
		<div class="vesitable">
			<div class="owl-carousel vegetable-carousel justify-content-center">
				<c:forEach items="${ theLate }" var="late">
					<div
						class="border border-primary rounded position-relative vesitable-item">
						<div class="vesitable-img">
							<img src="${ contextPath }/${ late.thumbnail }"
								class="img-fluid w-100 rounded-top" alt="">
						</div>
						<div
							class="text-white bg-primary px-3 py-1 rounded position-absolute"
							style="top: 10px; right: 10px;">Vegetable</div>
						<div class="p-4 pb-0 rounded-bottom">
							<h4>${ late.name }</h4>
							<!-- <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed
								do eiusmod te incididunt</p> -->
							<div class="d-flex justify-content-between flex-lg-wrap">
								<p class="text-dark fs-5 fw-bold">$${ late.price } / kg</p>
								<a href="${ contextPath }/detail/${ late.id }"
									class="btn border border-secondary rounded-pill px-3 py-1 mb-4 text-primary"><i
									class="fa fa-shopping-bag me-2 text-primary"></i> Detail</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
<!-- Single Product End -->

