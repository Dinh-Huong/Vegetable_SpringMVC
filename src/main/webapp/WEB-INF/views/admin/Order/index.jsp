<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"
	value="${ pageContext.servletContext.contextPath }" scope="session" />
<!-- Start container-fluid -->
<div class="container-fluid">

	<!-- start  -->
	<div class="row">
		<div class="col-12">
			<div>
				<h4 class="header-title mb-3">Data Tables</h4>
			</div>
		</div>
	</div>
	<!-- end row -->

	<div class="row">
		<div class="col-12">
			<div>
				<div class="d-flex justify-content-between align-items-center">
					<h5 class="font-14">List orders</h5>
					<div class="input-group col-6">
						<form action="${ contextPath }/admin/orders" method="get"
							class="d-flex align-items-center">
							<input type="text" name="q" value="${ param.q }"
								class="form-control text-dark" placeholder="Search name..." />
							<span class="input-group-append">
								<button type="submit" class="btn btn-outline-secondary">Search</button>
							</span>
						</form>
					</div>
				</div>
				<p class="sub-header">Manage all store orders</p>

				<table class="table table-bordered dt-responsive nowrap text-center"
					style="border-collapse: collapse; border-spacing: 0; width: 100%;">
					<thead>
						<tr>
							<th class="col-1">#</th>
							<th class="col-2">Name</th>
							<th class="col-2">Phone</th>
							<th class="col-2">Address</th>
							<th class="col-2">Price</th>
							<th class="col-2">Status</th>
							<th class="col-1">Action</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${ paginate.data }" var="order" varStatus="loop">
							<tr>
								<td>${loop.count}</td>
								<td>${ order.user.name }</td>
								<td>${ order.phone }</td>
								<td>${ order.address }</td>
								<td>${ order.totalPrice }</td>
								<td>${ order.status == 1 ? "Preparing" : order.status == 2 ? "Delivering" :order.status == 3 ? "Complete" : "Cancel" }</td>
								<td class="d-flex">	
									<!-- Button trigger modal -->
									<button type="button" class="btn btn-primary"
										data-toggle="modal" data-target="#exampleModalCenter${ order.id }">
										Edit</button> <!-- Modal -->
									<div class="modal fade" id="exampleModalCenter${ order.id }" tabindex="-1"
										role="dialog" aria-labelledby="exampleModalCenterTitle"
										aria-hidden="true">
										<div class="modal-dialog modal-dialog-centered"
											role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLongTitle">Status</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body">
													<form action="change/${ order.id }" method="post" class="p-3">
														<div class="d-flex flex-column align-items-start">
														<div class="form-check">
															<input type="radio" name='status' id="status-1" value="1" class="form-check-input d-inline mr-2" 
															${ order.status  == 1 ? 'checked' : ''} > <label for="status-1"
																class="d-inline-block">Preparing</label>
														</div>
														<div class="form-check">
															<input type="radio" name='status' id="status-2" value="2"
																class="form-check-input d-inline mr-2" ${ order.status  == 2 ?
															'checked' : ''}> <label for="status-2"
																class="d-inline-block">Delivering</label>
														</div>
														<div class="form-check">
															<input type="radio" name='status' id="status-3" value="3"
																class="form-check-input d-inline mr-2" ${ order.status  == 3 ?
															'checked' : ''}> <label for="status-3"
																class="d-inline-block">Complete</label>
														</div>

														<div class="form-check">
															<input type="radio" name='status' id="status-4" value="4"
																class="form-check-input d-inline mr-2" ${ order.status  == 4 ?
															'checked' : ''}> <label for="status-4"
																class="d-inline-block">Cancel</label>
														</div>
														</div>
														
														<div class="modal-footer">
															<button type="button" class="btn btn-secondary"
																data-dismiss="modal">Close</button>
															<button type="submit" class="btn btn-primary">Save
																changes</button>
														</div>
														
													</form>
												</div>
											</div>
										</div>
									</div>
								</td>
							</tr>

						</c:forEach>
					</tbody>
				</table>

				<c:if test="${ paginate.totalPages != 0 }">
					<div
						class="w-100 d-flex justify-content-center align-items-center mt-3">
						<nav aria-label="Page navigation example">
							<ul class="pagination">
								<li
									class="page-item ${ paginate.currentPage == 1 ? 'disabled' : '' }">
									<a class="page-link"
									href="${ pageUrl }page=${ paginate.currentPage -1}"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a>
								</li>
								<c:forEach var="pg" begin="1" end="${ paginate.totalPages }">
									<li
										class="page-item ${ pg == paginate.currentPage ? 'active' : '' }">
										<a class="page-link" href="${ pageUrl }page=${ pg }">${ pg }</a>
									</li>
								</c:forEach>
								<li
									class="page-item ${ paginate.currentPage == paginate.totalPages ? 'disabled' : '' }">
									<a class="page-link"
									href="${ pageUrl }page=${ paginate.currentPage +1}"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a>
								</li>
							</ul>
						</nav>
					</div>
				</c:if>
			</div>
			<!-- end -->

		</div>
	</div>
	<!-- end row -->

</div>
<!-- end container-fluid -->
