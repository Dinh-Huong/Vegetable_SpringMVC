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
							<th class="col-1">Price</th>
							<th class="col-2">Status</th>
							<th class="col-2">Action</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${ paginate.data }" var="order" varStatus="loop">
							<tr>
								<td>${loop.count}</td>
								<td>${ order.users.name }</td>
								<td>${ order.phone }</td>
								<td>${ order.address }</td>
								<td>${ order.totalPrice }</td>
								<td>${ order.status }</td>
								<td class="d-flex">
									 <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".${ order.id }bd-example-modal-sm">Sửa</button>
                                        <div class="modal fade ${ order.id }bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                                            <div class="modal-dialog modal-sm">
                                              <div class="modal-content">
                                                <form action="" method="post" class="p-3">

                                                    <h3 class="text-center">Trạng thái</h3>
                                                    <div class="form-group">
                                                        <div>
                                                            <input type="radio" name='status' id="status-0" value="0" class="d-inline mr-2" {{ $item->status == 0 ? 'checked' : '' }}>
                                                            <label for="status-0" class="d-inline-block">Đang chuẩn bị</label>
                                                        </div>

                                                        <div>
                                                            <input type="radio" name='status' id="status-1" value="1" class="d-inline mr-2" {{ $item->status == 1 ? 'checked' : '' }}>
                                                            <label for="status-1" class="d-inline-block">Vận chuyển</label>
                                                        </div>
                                                        <div>
                                                            <input type="radio" name='status' id="status-2" value="2" class="d-inline mr-2" {{ $item->status == 2 ? 'checked' : '' }}>
                                                            <label for="status-2" class="d-inline-block">Giao hàng</label>
                                                        </div>

                                                        <div>
                                                            <input type="radio" name='status' id="status-3" value="3" class="d-inline mr-2" {{ $item->status == 3 ? 'checked' : '' }}>
                                                            <label for="status-3" class="d-inline-block">Hoàn thành</label>
                                                        </div>
                                                        <div>
                                                            <input type="radio" name='status' id="status-4" value="4" class="d-inline mr-2" {{ $item->status == 4 ? 'checked' : '' }}>
                                                            <label for="status-4" class="d-inline-block">Đã hủy</label>
                                                        </div>
                                                        <div>
                                                            <input type="radio" name='status' id="status-5" value="5" class="d-inline mr-2" {{ $item->status == 5 ? 'checked' : '' }}>
                                                            <label for="status-5" class="d-inline-block">Trả hàng</label>
                                                        </div>
                                                    </div>
                                                    <button type="submit" class="btn btn-dark">Lưu</button>
                                                </form>
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
