<%--
  Created by IntelliJ IDEA.
  User: msiTek
  Date: 2018/1/4
  Time: 下午 01:46
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
    <meta name="viewport" content="width=device-width;   initial-scale=0.75 "; >
    <link rel="stylesheet" href="style/site2.css" media="screen and (max-device-width: 500px)" type="text/css"/>
    <link rel="stylesheet" href="style/site.css" media="screen and (min-device-width: 500px)" type="text/css"/>

    <title>Mitac EMCLab</title>
    <style>
        hr{
            border:none;
            border-top:dashed 1px #CCC;
            height:1px;
            color:#FFFFFF;
            margin: 0 6 0 6;
        }
    </style>
</head>
<body>
<div>
<h1>E M C 場 地 預 約 系 統</h1>
<hr>
    <br>
    <h5>預約場地</h5>
    <br>
     
     <input type="button" value="  預約場地  " class="btn  btn-outline-dark" onclick="location.href='CheckStringServlet'">
    <input type="button" value="  表單填寫  "class="btn  btn-outline-dark" onclick="location.href='WorkListServlet'">
    <br> <br>

    <h5>場地資料</h5>
    <br>
     
    <input type="submit" value="  使用統計  " class="btn  btn-outline-dark"  onclick="location.href='MathServlet'">
    <input type="submit" value="  搜尋系統  "  class="btn  btn-outline-dark" onclick="location.href='SearchServlet'">
    <br><br>

        <b><h5>帳戶管理</h5></b>
    <br>
        <form action="MainServlet">
             
                <input type="button" value="  帳戶資料  "  class="btn  btn-outline-dark" onclick="location.href='AccountServlet'">
                <input type="submit"class="btn  btn-outline-dark"  value="  後台管理  ">
                <input type="hidden" name="master" value="master">
            </form>
        <br>
<hr>
    <br>
<input type="submit" value="  登出系統  "  class="btn btn-outline-dark" onclick="location.href='LogoutServlet'">
</div>
<%
    String Message = (String) request.getAttribute("Message");
%>
    <%
        if (Message != null){
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
