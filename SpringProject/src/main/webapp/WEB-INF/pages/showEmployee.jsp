<%@page import="java.util.List"%>
<%@page import="com.cov.beans.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All employee page</title>
</head>
<body>
	<h2>Employee Details</h2><br><br><br>
	<label for="department">Search for Department:</label>

<select name="showdepartment" id="department">
  <option value="Sales">Sales</option>
  <option value="Production">Production</option>
  <option value="Marketing">Marketing</option>
</select>
	<table border="3" bgcolor="#FFFF00" style="text-align: center;">
		<tr>
			<th>ID</th>
			<th>Name</th>
			
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%
		List<Employee> emps = (List<Employee>) request.getAttribute("emps");
		for (Employee emp : emps) {
		%>
		<tr>
			<td><%=emp.getId()%></td>
			<td><%=emp.getName()%></td>

			<td><a href="editEmp?id=<%=emp.getId()%>">Edit</a></td>
			<td><a href="deleteEmp?id=<%=emp.getId()%>">Delete</a></td>
		</tr>
		<%
		}
		%>
	</table>
	<br>
	<br>
	<a href="/">Home</a>
	<br>
	<br>
	<a href="regemp">Register Employees</a>
	<br>
	<br>



</body>
</html>