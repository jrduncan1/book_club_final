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
<title>Dashboard</title>
</head>
<body>
	<div class="container m-5">
		<div class="d-flex justify-content-between align-items-center">
			<div class="col-sm-8">
				<h1>Welcome, ${loggedUser.name}</h1>
			</div>
			<div class="d-flex align-items-center justify-content-end">
				<a href="/books/new" class="btn btn-primary">Add a book to my
					shelf!</a> <a href="/logout" class="btn btn-warning ms-3">Logout</a>
			</div>
		</div>

		<h6>Books from everyone's shelves:</h6>

		<div class="mt-4 mb-5">
			<table class="table table-light table-striped">
				<tr>
					<th class="text-center">Id</th>
					<th class="text-center">Title</th>
					<th class="text-center">Author</th>
					<th class="text-center">Posted By</th>
					<th class="text-center">Actions</th>
				</tr>
				<c:forEach var="book" items="${books}">
					<tr>
						<td class="text-center">${book.id}</td>
						<td class="text-center">${book.title}</td>
						<td class="text-center">${book.author}</td>
						<td class="text-center">${book.creator.name}</td>
						<td class="d-flex justify-content-evenly align-items-center">
							<a href="/books/${book.id}" class="btn btn-sm btn-success">View</a>
							<c:if test="${book.creator.id == loggedUser.id}">
								<a href="/books/${book.id}/edit" class="btn btn-sm btn-secondary">Edit</a>
								<form action="/books/${book.id}/delete" method="post">
									<input type="hidden" name="_method" value="delete" />
									<button class="btn btn-sm btn-danger">Delete</button>
								</form> 

							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>