<%--
  Created by IntelliJ IDEA.
  User: kam
  Date: 22/03/2022
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<%@ page import="com.jee.bartersup.entity.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jee.bartersup.entity.Category"  %>
<%@ page import="java.util.ArrayList" %>
<%--<%@ taglib prefix="gurutag" uri=""http://xmlns.jcp.org/jsf/core %>--%>
<%--<%@ taglib prefix="core" uri="http://xmlns.jcp.org/jsf/core" %>--%>




<% Post umodel = new Post() ;
    List<Category> typelist = new ArrayList<>();
    if ( request.getAttribute("postmodel") != null){
        umodel = (Post) request.getAttribute("postmodel");
        out.println(typelist.size()+" item");
    }
    typelist = (List<Category>) request.getAttribute("categorylist");


%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1> Créer un post</h1>
<form  action="create.post" method="POST" >

    <div class="form-input">
        <table class="table">
            <tr>
                <td> Titre </td>
                <td> <input type="text" name="title" value="<%= umodel.getTitle()%>"/> </td>
            </tr>
            <tr>
                <td> Type </td>
                <td>


                <select name="type">
                <% for (Category cat : typelist ) {
                    out.println( "<option value="+cat.getId()+">"+cat.getType()+"</option>");
                }
                %>

                </select>
                </td>
            </tr>
            <tr>
                <td> Prix </td>
                <td> <input type="text" name="price" value=" <%= umodel.getPrice()%>"/> </td>
            </tr>
            <tr>
                <td> Description </td>
                <td> <input type="text" name="descript" value="<%= umodel.getDescription()%>"/> </td>
            </tr>

        </table>
    </div>

    <button type="submit"   >Submit</button>

    <%--    <input type="submit" value="submit"/>--%>
</form>

</body>
</html>
