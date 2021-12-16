<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<meta charset="UTF-8">
<title>Add Book</title>
</head>
<body>
	<div class="container mt-5">
		<div class="d-flex justify-content-between align-items-center mb-5">
			<div class="col-sm-8">
				<h1>Add a Book to Your Shelf, ${loggedUser.name}!</h1>
			</div>
			<div class="d-flex align-items-center justify-content-end">
				<a href="/dashboard" class="btn btn-primary">Dashboard</a>
				<a href="/logout" class="btn btn-warning ms-3">Logout</a>
			</div>
		</div>
   		<div class="form-control">
			    <form:form action="/books/new" method="post" modelAttribute="newBook">
			        <div class="form-group">
			            <label>Title</label>
			            <form:input path="title" class="form-control" />
			            <form:errors path="title" class="text-danger" />
			        </div>
			        <div class="form-group mt-3">
			            <label>Author</label>
			            <form:input path="author" class="form-control" />
			            <form:errors path="author" class="text-danger" />
			        </div>
			        <div class="form-group mt-3">
			            <label>My thoughts</label>
			            <div>
			            	<form:textarea path="thoughts" type="text" class="form-control"></form:textarea>
			            </div>
			            <form:errors path="thoughts" class="text-danger" />
			        </div>
			        <input type="submit" value="Create" class="btn btn-primary my-3" />
    			</form:form>
   		</div>
	</div>

</body>
</html>