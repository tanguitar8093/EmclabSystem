<%--
  Created by IntelliJ IDEA.
  User: msiTek
  Date: 2018/2/21
  Time: 下午 03:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>場地預約</title>
    <link rel="stylesheet" href="../work/style/main.css" type="text/css"/>
</head>
<body>
<h1>選擇場地</h1>
<form action="/work/CheckStringServlet" method="post">
    <label><b>Site S0：</b></label>
    <input type="radio"name="testItem2" value="S0-RE" checked>S0: RE</input>
    <input type="radio"name="testItem2" value="S0-CE" >S0: CE</input><br>
    <label><b>Site S1-S4：</b></label>
    <input type="radio"name="testItem2" value="S1" >S1</input>
    <input type="radio"name="testItem2" value="S2" >S2</input>
    <input type="radio"name="testItem2" value="S3" >S3</input>
    <input type="radio"name="testItem2" value="S4" >S4</input><br>
    <label><b>Site 966：</b></label>
    <input type="radio"name="testItem2" value="966">966 </input><br><br>
    <input type="submit" value="進入查詢/預約">
</form>
</body>
</html>
