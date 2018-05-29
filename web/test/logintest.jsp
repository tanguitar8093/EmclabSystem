<%@ page import="work.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: msiTek
  Date: 2018/1/22
  Time: 下午 04:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>
        <td>id</td>
        <td>ACCOUNT</td>
        <td>PASSWORD</td>
        <td></td>
    </tr>
<%
    ArrayList<User> users = (ArrayList) request.getAttribute("result");
    String accountcheck = (String) request.getAttribute("accountcheck");
    String passwordcheck = (String) request.getAttribute("passwordcheck");
    String ac ="dfddf";
    String ps ="ddf";
    for (User user :users){
        out.println("<tr>");
        out.print("<td>"+user.getId()+"</td>");
        out.print("<td>"+user.getAccount()+"</td>");
        out.print("<td>"+user.getPassword()+"</td>");
        out.print("<td><a href=\"EmailEditServlet?id="+ user.getId()+ "\">Edit</a></td>");
        out.println("</tr>");
        if (user.getAccount().equals(accountcheck) && user.getPassword().equals(passwordcheck)){
            out.println("ok");
        }
    }
%>
</table>
</body>
</html>
