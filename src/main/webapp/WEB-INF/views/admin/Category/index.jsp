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
					<h5 class="font-14">List category</h5>
					<div class="input-group col-6">
						<form action="${ contextPath }/admin/category" method="get" class="d-flex align-items-center">
							<input type="text" name="q" value="${ param.q }" class="form-control text-dark" placeholder="Search name..." />
							<span class="input-group-append">
								<button type="submit" class="btn btn-outline-secondary">Search</button>
							</span> 
						</form>
						<a href="${ contextPath }/admin/category/add" class="btn btn-success ml-3">Add category</a>
					</div>
				</div>
				<p class="sub-header">Management list of all types of vegetable
					foods.</p>

				<table class="table table-bordered dt-responsive nowrap text-center"
					style="border-collapse: collapse; border-spacing: 0; width: 100%;">
					<thead>
						<tr>
							<th class="col-1">#</th>
							<th class="col-3">Name</th>
							<th class="col-2">Slug</th>
							<th class="col-2">Type</th>
							<th class="col-2">Created at</th>
							<th class="col-2">Action</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${ paginate.data }" var="cate" varStatus="loop">
							<tr>
								<td>${loop.count}</td>
								<td>${ cate.name }</td>
								<td>${ cate.slug }</td>
								<td>${ cate.status == 0 ? "Fruit" : "Vegetable" }</td>
								<td>${ cate.createdAt }</td>
								<td class="d-flex"><a
									href="${ contextPath }/admin/category/edit/${cate.id}"
									class="btn btn-info mr-3">Edit</a> <a
									href="${ contextPath }/admin/category/delete/${cate.id}"
									class="btn btn-danger"
									onclick="return confirm('Are you sure?')">Delete</a></td>
							</tr>

						</c:forEach>
					</tbody>
				</table>

				<c:if test="${ paginate.totalPages != 0 }">
					<div
						class="w-100 d-flex justify-content-center align-items-center mt-3">
						<nav aria-label="Page navigation example">
							<ul class="pagination">
								<li class="page-item ${ paginate.currentPage == 1 ? 'disabled' : '' }">
									<a class="page-link" href="${ pageUrl }page=${ paginate.currentPage -1}" aria-label="Previous">
									 	<span aria-hidden="true">&laquo;</span>
									</a>
								</li>
								<c:forEach var="pg" begin="1" end="${ paginate.totalPages }">
									<li class="page-item ${ pg == paginate.currentPage ? 'active' : '' }">
										<a class="page-link" href="${ pageUrl }page=${ pg }">${ pg }</a>
									</li>
								</c:forEach>
								<li class="page-item ${ paginate.currentPage == paginate.totalPages ? 'disabled' : '' }">
									<a class="page-link" href="${ pageUrl }page=${ paginate.currentPage +1}" aria-label="Next"> 
									 <span aria-hidden="true">&raquo;</span>
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
