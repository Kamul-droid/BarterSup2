<%--
  Created by IntelliJ IDEA.
  User: kam
  Date: 23/03/2022
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Image</h1>

<form name="img" action="image.post" enctype="multipart/form-data" method="post">

    <table>
        <tr>
            <td> image </td>
            <td>  <input type="file" name="file" value="file" /> </td>
        </tr>
    </table>
    <input type="submit" name="button" value="submit">
</form>

</body>
</html>
