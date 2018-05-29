<%@ page import="work.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  user: msiTek
  Date: 2017/12/11
  Time: 下午 01:36
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <title>帳號資料</title>
    <link rel="stylesheet" href="style/main.css" media="screen and (min-device-width: 500px)" type="text/css"/>
    <link rel="stylesheet" href="style/list22.css" media="screen and (max-device-width: 500px)" type="text/css"/>
    <meta name="viewport" content="width=device-width;   initial-scale=0.7 "; >
    <meta charset="UTF-8">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<div>
<h1>帳號資訊</h1>
<hr>
<form action="AccountUpdateServlet " method="post">
    <br>
<%
    ArrayList<User> users = (ArrayList) request.getAttribute("result");
    for (User user :users){
        out.print("<label>單位</label>");
        out.print("<input type=\"text\" name=\"unit\" value=\""+user.getUnit()+" \" readonly>");
        out.print("<span>");
        out.print("</span><br/>");
        out.print("<label>帳號</label>");
        out.print("<input type=\"text\" name=\"account\" value=\""+user.getAccount()+" \"  readonly>");
        out.print("<span>");
        out.print("</span><br/>");
        out.print("<label>姓名</label>");
        out.print("<input type=\"text\" name=\"name\" value=\""+user.getName()+"\" >");
        out.print("<span>");
        out.print("</span><br/>");
        out.print("<label>職稱</label>");
        out.print("<input type=\"text\" name=\"job\" value=\""+user.getJob()+"\">");
        out.print("<span>");
        out.print("</span><br/>");
        out.print("<label>E-mail</label>");
        out.print("<input type=\"email\" name=\"email\" value=\""+user.getEmail()+"\">");
        out.print("<span>");
        out.print("</span><br/>");
        out.print("<label>修改密碼</label>\n" +
                "<span>\n" +
                "   <input type=\"password\"  name=\"newPassword\">" +"[輸入原密碼]"+
                "</span><br/>\n" +
                "<label>&nbsp;</label>\n" +
                "<input type=\"password\" name=\"passwordFix\">" +"[輸入新密碼]"+
                "</span><br/>\n" +
                "<label>&nbsp;</label>\n" +
                "<input type=\"password\" name=\"passwordFix2\">" +"[輸入新密碼]"+
                "</span><br/>");
        out.print("<input type=\"hidden\" name=\"password\" value=\""+user.getPassword()+"\">");
    }
%>
    <p><b>※單位、帳號：只提供後台修改<br>
    ※修改密碼：不輸入即維持原狀，修改需輸入1次原密碼及2次新密碼
    </b>
    </p>
<hr><br>
<input type="submit" class="btn btn-outline-dark btn-sm" value="  送出資料  ">
    <input type="button" value="  回主選單  " class="btn btn-outline-dark btn-sm"  onclick="location.href='MainServlet'">
</form>
</div>
<%
    String WrongMessage = (String) request.getAttribute("WrongMessage");
%>
<%
    if ( WrongMessage != null){
        out.print(" <c:if test=\"${flag }\">");
        out.print("  <span class=\"alert alert-danger alert-dismissable\" role=\"alert\">");
        out.print(" <button class=\"close\" type=\"button\" data-dismiss=\"alert\">&times;</button>");
        out.print("訊息："+ WrongMessage);
        out.print("</span>");
        out.print("</c:if>");
    }
%>
</body>
</html>
