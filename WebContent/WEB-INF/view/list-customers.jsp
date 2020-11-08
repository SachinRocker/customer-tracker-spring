<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Customers</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css ">
<!--<link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">-->

<style>
a {
	color: black;
}
</style>

</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>

		<div id="container">
			<div id="content">
				
				<!--button for adding the customer  -->
				<input type="button" value="Add Customer"
					onclick="window.location.href='addCustomerForm'; return false;"
					class="add-button" />
				
				<!-- Search customer -->
				<form:form action="search" method="GET">
				<label>Search Customer:</label>
				<input type="text" name="searchName"/>
				<input type="submit" value="Search" class="add-button"/>
				</form:form>
				
				<!-- show customer list -->
				<table>

					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Action</th>

					</tr>
					<c:forEach var="customer" items="${customers}">
						<c:url var="updateLink" value="/customer/showFormUpdate">
							<c:param name="customerId" value="${customer.id}"></c:param>
						</c:url>
						<c:url var="deleteLink" value="/customer/delete">
							<c:param name="customerId" value="${customer.id}"></c:param>
						</c:url>
						<tr>
							<td>${customer.firstName }</td>
							<td>${customer.lastName }</td>
							<td>${customer.email }</td>
							<td><a href="${updateLink}">Update</a>
								<a>|</a>
								<a  onclick="if(!(confirm('Are you sure you want to delete this customer?'))) return false"
								href="${deleteLink}"
								
								 >Delete</a></td>
							
						</tr>
						
					</c:forEach>


				</table>



			</div>



		</div>



	</div>





</body>
</html>