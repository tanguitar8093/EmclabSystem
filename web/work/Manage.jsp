<%@ page import="work.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: msiTek
  Date: 2018/5/2
  Time: 下午 02:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <meta name="viewport" content="width=device-width;   initial-scale=0.7"; >
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style/list.css" type="text/css"/>
    <title>成員資料表管理</title>
</head>
<body>
<h1><b>成員資料表管理</b></h1>
<hr><br>
<table border="2" CELLSPACING=0>
    <tr>
        <td><b>編號</b></td>
        <td><b>單位</b></td>
        <td><b>姓名</b></td>
        <td><b>職位</b></td>
        <td><b>帳號</b></td>
        <td><b>修改密碼</b></td>
        <td><b>Email</b></td>
        <td><b>更改權限</b></td>
        <td><b>刪除</b></td>
        <td><b>提交</b></td>
    </tr>
<%
    ArrayList<User> users = (ArrayList) request.getAttribute("result");
    for (User user :users){
        out.print("  <form action=\"ManageUpdateServlet\" method=\"post\">");
        out.print("<tr>");
        out.print("<td>&nbsp;"+user.getId()+"&nbsp;</td>");
        out.print("<td>&nbsp;"+"<input type=\"text\"style=\"width: 6em\"  name=\"unit\" value=\""+ user.getUnit()+"\""+">"+"</input>"+"&nbsp;</td>");
        out.print("<td>&nbsp;"+"<input type=\"text\"style=\"width: 6em\"  name=\"name\" value=\""+ user.getName()+"\""+">"+"</input>"+"&nbsp;</td>");
        out.print("<td>&nbsp;"+"<input type=\"text\"style=\"width: 6em\"  name=\"job\" value=\""+ user.getJob()+"\""+">"+"</input>"+"&nbsp;</td>");
        out.print("<td>&nbsp;"+"<input type=\"text\"style=\"width: 6em\"  name=\"account\" value=\""+ user.getAccount()+"\""+">"+"</input>"+"&nbsp;</td>");
        out.print("<td>&nbsp;"+"<input type=\"text\"style=\"width: 6em\"  name=\"passwordfix\" >"+"</input>"+"&nbsp;</td>");
        out.print("<td>&nbsp;"+"<input type=\"email\"  name=\"email\" value=\""+ user.getEmail()+"\""+">"+"</input>"+"&nbsp;</td>");
        out.print("<td>&nbsp;"+"<input type=\"text\"style=\"width: 6em\"  name=\"powerfix\" value=\""+ user.getPower()+"\""+">"+"</input>"+"&nbsp;</td>");
        out.print("<input type=\"hidden\"style=\"width: 6em\"  name=\"password\" value=\""+ user.getPassword()+"\""+">"+"</input>");
        out.print("<input type=\"hidden\"style=\"width: 6em\"  name=\"id\" value=\""+ user.getId()+"\""+">"+"</input>");
        out.print("<td>&nbsp;"+"<input type=\"checkbox\" name=\"del\" value=\"del\""+">"+"</input>"+"&nbsp;</td>");
        out.print("<td>&nbsp;"+"<input type=\"submit\" class=\"btn btn-outline-dark btn-sm\" value=\"送出\""+">"+"</input>"+"&nbsp;</td>");
        out.print("</tr>");
        out.print(" </form>");
    }
%>
</table>
<br>
<p><b>※修改密碼：保持空白為不修改，輸入即修改，建議忘記密碼再使用<br>
※更改權限：1：管理者，2：debug工程師，3：測試工程師，4：待審核會員
</b></p>
<hr>
<form action="MainServlet" method="post">
<input type="submit" value="   回後台管理  " class="btn btn-outline-dark btn-sm">
    <input type="hidden" name="master" value="master">
</form>
<%
    String Message = (String) request.getAttribute("Message");
%>
<%
    if (Message != null){
        out.print("<hr>");
        out.print(" <c:if test=\"${flag }\">");
        out.print("  <span class=\"alert alert-danger alert-dismissable\" role=\"alert\">");
        out.print(" <button class=\"close\" type=\"button\" data-dismiss=\"alert\">&times;</button>");
        out.print("訊息："+Message);
        out.print("</span>");
        out.print("</c:if>");
    }
%>
</body>
</html>
