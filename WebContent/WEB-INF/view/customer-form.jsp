<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css ">
	<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-customer-style.css ">
<title>Customer Form</title>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
		</div>
 <div id="container">
	<form:form action="saveCustomer" method="POST" modelAttribute="customer" >
	
	<!-- This is required to track the cutomerId based on the customer data in the form -->
				<form:hidden path="id"/>
		<table>
		<tbody>
		
			<tr>
				<td><label>First Name:</label></td>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<td><label>Last Name:</label></td>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<td><label>Email:</label></td>
				<td><form:input path="email" /></td>
			</tr>
			
			<tr>
				<td colspan="2"><input type="submit" value="Save" class="save"/></td>
			</tr>
			</tbody>
		</table>
		

	</form:form>
	<div style="clear; both;"></div>
	
	<div>
		<p>
		<a href="${pageContext.request.contextPath}/customer/list">Go to list</a>
		</p>
	</div>
	
	
	</div>
</body>
</html>