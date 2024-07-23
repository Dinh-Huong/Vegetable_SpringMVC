<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<f:form action="" method="post" modelAttribute="product" cssClass="forms-sample" enctype="multipart/form-data">
	<h3>Form create new category</h3>
	<div class="form-group">
		<f:label path="name">Name</f:label>
		<f:input path="name" cssClass="form-control text-dark"
			placeholder="Name" oninput="ChangeToSlug(this, 'slug')" />
		<f:errors path="name" cssClass="error text-danger"></f:errors>
		<c:if test="${ !empty errorName }">
			<span class="text-danger">${ errorName }</span>
		</c:if>
	</div>
	<div class="form-group">
		<f:label path="slug">Slug</f:label>
		<f:input path="slug" id="slug" cssClass="form-control text-dark"
			placeholder="Slug" />
		<f:errors path="slug" cssClass="error text-danger"></f:errors>
		<c:if test="${ !empty errorSlug }">
			<span class="text-danger">${ errorSlug }</span>
		</c:if>
	</div>
	<div class="form-group">
		<f:label path="categoryId">Category</f:label>
		<f:select path="categoryId" cssClass="form-control">
			<c:forEach items="${ category }" var="cate">
				<f:option value="${ cate.id }">${ cate.name }</f:option>
			</c:forEach>
		</f:select>
		<f:errors path="categoryId" cssClass="error text-danger"></f:errors>
	</div>
	<div class="form-group">
		<f:label path="price">Price</f:label>
		<f:input path="price" id="price" type="number" cssClass="form-control text-dark"
			placeholder="Price" />
		<f:errors path="price" cssClass="error text-danger"></f:errors>
		<c:if test="${ !empty errorPrice }">
			<span class="text-danger">${ errorPrice }</span>
		</c:if>
	</div> 
	<div class="form-check form-check-flat form-check-primary">
		<f:label path="" cssClass="d-block">Status</f:label>
		<f:radiobutton path="status" value="0" label="No discount" cssClass="mr-2" checked="checked" />
		<f:radiobutton path="status" value="1" label="Discount" cssClass="mx-2" />
	</div> 
	
	<div class="form-group">
		<label for="thumbnail">Thumbnail</label> 
		<input type="file" name="upload-file" id="thumbnail" class="form-control" />
		<c:if test="${ !empty errorImage }">
			<span class="text-danger">${ errorImage }</span>
		</c:if>
		<div class="w-100 mt-2 text-center">
			<img alt="" id="preview-img" class="w-50" src="">
		</div>
		<div class="form-group">
			<f:label path="description">Description</f:label>
			<f:textarea language="en" path="description" id="description" cssClass="form-control text-white" />
		</div>
	</div>
	<f:button class="btn btn-primary btn-md">Submit</f:button>
</f:form>

<script> CKEDITOR.replace('description') </script>