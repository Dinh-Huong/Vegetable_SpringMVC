<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<f:form action="" method="post" modelAttribute="customer"
	cssClass="forms-sample">
	<h3>Form register customer</h3>
	<f:input path="role" value="0" cssClass="d-none"/>
	<div class="form-group">
		<f:label path="name">Name</f:label>
		<f:input path="name" cssClass="form-control text-dark"
			placeholder="Name" />
		<f:errors path="name" cssClass="error text-danger"></f:errors>
		<c:if test="${ !empty errorName }">
			<span class="text-danger">${ errorName }</span>
		</c:if>
	</div>
	<div class="form-group">
		<f:label path="email">Email</f:label>
		<f:input path="email" cssClass="form-control text-dark"
			placeholder="Email" />
		<f:errors path="email" cssClass="error text-danger"></f:errors>
		<c:if test="${ !empty errorEmail }">
			<span class="text-danger">${ errorEmail }</span>
		</c:if>
	</div>
	<div class="form-group">
		<f:label path="password">Password</f:label>
		<f:input path="password" type="password" cssClass="form-control text-dark"
			placeholder="Password" />
		<f:errors path="password" cssClass="error text-danger"></f:errors>
		<c:if test="${ !empty errorPassword }">
			<span class="text-danger">${ errorPassword }</span>
		</c:if>
	</div>
	<f:button class="btn btn-primary btn-md">Submit</f:button>
</f:form>