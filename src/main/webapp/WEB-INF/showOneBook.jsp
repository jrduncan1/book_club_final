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
<title>View Book</title>
</head>
<body>
	<div class="container m-5">
		<div class="d-flex justify-content-between align-items-center mb-5">
			<div class="col-sm-8">
				<h1>${book.title}</h1>
			</div>
			<div class="d-flex align-items-center justify-content-end">
				<a href="/dashboard" class="btn btn-primary">Dashboard</a>
				<a href="/logout" class="btn btn-warning ms-3">Logout</a>
			</div>
		</div>
		<div>
			<h4 class="mb-3">${book.creator.name} read ${book.title} by ${book.author}</h4>
			<h6>Here are ${book.creator.name}'s thoughts:</h6>
		</div>
		<div>
			<p>${book.thoughts}</p>
		</div>

	</div>
</body>
</html>