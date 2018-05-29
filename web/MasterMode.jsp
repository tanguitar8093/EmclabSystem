<%--
  Created by IntelliJ IDEA.
  User: msiTek
  Date: 2018/5/1
  Time: 上午 01:32
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
    <meta name="viewport" content="width=device-width;   initial-scale=0.8 "; >
    <link rel="stylesheet" href="style/master.css" media="screen and (min-device-width: 500px)" type="text/css"/>
    <link rel="stylesheet" href="style/list22.css" media="screen and (max-device-width: 500px)" type="text/css"/>
    <title>後台管理</title>
</head>
<body>
<div>
<h1>後台管理</h1>
<hr><br>
<TABLE>
<input type="button" value="  場地審核表管理  " class="btn btn-outline-dark" onclick="location.href='DateListCheckServlet'"><br><br>
<input type="button" value="  成員資料表管理  " class="btn btn-outline-dark" onclick="location.href='ManageServlet'"><br><br>
<input type="button" value="  已成立表單管理  " class="btn btn-outline-dark" onclick="location.href='SearchServlet'"><br><br>
<input type="button" value="  工作日參數設定  " class="btn btn-outline-dark" onclick="location.href='WorkDayServlet'"><br><br>
</TABLE>
<hr><br>
<input type="button" value="  回主選單  "  class="btn btn-outline-dark" onclick="location.href='MainServlet'">
</div>
</body>
</html>
