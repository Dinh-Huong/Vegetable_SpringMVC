<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${ pageContext.servletContext.contextPath }" scope="session"/>

<f:form action="${ contextPath }/admin/category/update" method="post" modelAttribute="category"
	cssClass="forms-sample">
	<h3>Form create new category</h3>
	<f:input type="number" path="id" cssClass="d-none"/>
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
	<div class="form-check form-check-flat form-check-primary">
		<f:label path="" cssClass="d-block">Status</f:label>
		<f:radiobutton path="status" value="0" label="Fruit" cssClass="mr-2"
			checked="checked" />
		<f:radiobutton path="status" value="1" label="Vegetable" cssClass="mx-2" />
	</div>
	<f:button class="btn btn-primary btn-md">Save</f:button>
</f:form>