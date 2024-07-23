<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${ pageContext.servletContext.contextPath }" scope="session" />

	 <!-- Single Page Header start -->
        <div class="container-fluid page-header py-5">
            <h1 class="text-center text-white display-6">Profile</h1>
            <ol class="breadcrumb justify-content-center mb-0">
                <li class="breadcrumb-item"><a href="#">Home</a></li>
                <li class="breadcrumb-item"><a href="#">Pages</a></li>
                <li class="breadcrumb-item active text-white">Information</li>
            </ol>
        </div>
        <!-- Single Page Header End -->
       <c:if test="${ not empty info }">
		 <div class="jumbotron mx-auto">
		     <h1 class="display-3">Name: ${ info.name }</h1>
		     <p class="lead">Email: ${ info.name }</p>
		     <hr class="my-2">
		     <p>Information about orders</p>
		     <p class="lead">
		      
		     </p>
		   </div>
		</c:if>
		<c:if test="${ empty info }">
			<h1 class="display-6 text-danger text-center">${ notexist }</h1>
		</c:if>
		