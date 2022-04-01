<%@ page import="com.jee.bartersup.entity.Post" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--        pst = request.getParameter("post");%>--%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>

</head>
<body>
<% Post pst = new Post();
    String link;
    if(!(request.getAttribute("pst") != null || session.getAttribute("pst") != "")){
    pst = (Post) request.getAttribute("pst");
    out.println(pst.toString());
    link = request.getParameter("img");
    out.println(link);}

%>
<h1><%= "Hello World!" %>
</h1>
<h2>${pst.title}</h2>
<h2>${pst.price}</h2>
<h2>${pst.description}</h2>
<h2> ${link} </h2>
<img src="picture/canard.png"/>


<br/>


</body>
</html>