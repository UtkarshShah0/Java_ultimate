<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%! 
	public int square(int n){
		return n*n;
	}
%>
</head>
<body>
	
	<c:forEach var="sal" items="${pay}">
		<p>${sal}</p>
	</c:forEach>

	<%= square(10) %>
		
		<%
			for(int i=1; i<= 10; i++){
				out.println("<h1>Hello from JSP View</h1>");
			}

		 %>
		 
	
		 
		 
		 
</body>
</html>  