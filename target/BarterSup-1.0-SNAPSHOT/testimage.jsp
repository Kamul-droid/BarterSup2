<%--
  Created by IntelliJ IDEA.
  User: kam
  Date: 22/03/2022
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.jee.bartersup.entity.User"  %>


<% User umodel = (User) request.getAttribute("usermodel");

%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form  action="do.register" method="POST" >

    <div class="form-input">
        <table class="table">
            <tr>
                <td> Nom d'utilisateur </td>
                <td> <input type="text" name="usrnme" value="<%= umodel.getUsername()%>"/> </td>
            </tr>
            <tr>
                <td> Nom </td>
                <td> <input type="text" name="ftname" value=" <%= umodel.getFirstname()%>"/> </td>
            </tr>
            <tr>
                <td> prenom </td>
                <td> <input type="text" name="ltname" value=" <%= umodel.getLastname()%>"/> </td>
            </tr>
            <tr>
                <td> mot de passe </td>
                <td> <input type="password" name="pword" value="<%= umodel.getPassword()%>"/> </td>
            </tr>
            <tr>
                <td> adresse </td>
                <td> <input type="text" name="dress" value="<%= umodel.getAddress()%>"/> </td>
            </tr>
            <tr>
                <td> email </td>
                <td> <input type="text" name="eml" value="<%= umodel.getEmail()%>"/> </td>
            </tr>

            <tr>
                <td> Code postal </td>
                <td> <input type="text" name="cdpst" value="<%= umodel.getZipcode()%>"/> </td>
            </tr>
            <br>
            <tr>
                <td> Rgpd </td>
                <td> <input type="checkbox" name="rd" /> </td>
            </tr>


        </table>
    </div>

    <button type="submit"   >Submit</button>

<%--    <input type="submit" value="submit"/>--%>
</form>

</body>
</html>
