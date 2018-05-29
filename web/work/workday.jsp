<%--
  Created by IntelliJ IDEA.
  User: msiTek
  Date: 2018/4/18
  Time: 下午 02:01
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
    <meta name="viewport" content="width=device-width;   initial-scale=0.7 "; >
    <link rel="stylesheet" href="style/workday.css" media="screen and (min-device-width: 500px)" type="text/css"/>
    <link rel="stylesheet" href="style/list22.css" media="screen and (max-device-width: 500px)" type="text/css"/>
    <title>工作天設定</title>
    <%
        int workDaysCheck = (int) request.getAttribute("workDaysCheck");
        int [] MonthDay;
        MonthDay = (int[]) request.getAttribute("MonthDay");
    %>
</head>
<body>
<div>
<h1><b>工作天設定</b></h1>
<hr>
    <p>輸入年分：
        <form action="WorkDayServlet">
            <input type="text" name="workDays" value="${workDaysCheck}">
            <input type="submit" class="btn btn-outline-dark btn-sm" class="btn btn-outline-dark btn-sm" value="查詢/新增">
        </form>
    </p>
<hr><br>
<form action="WorkDayServlet">
<%
   out.print("<table BORDER=1 CELLSPACING=0>");
   out.print(" <tr><th colspan=\"2\">"+workDaysCheck+"年工作日"+"</th></tr>");
    int i;
    for (i=0; i<12; i++){
        out.print("<tr>");
        out.print(" <td>"+(i+1)+"月"+"</td>");
        out.print("<td> <input type=\"text\"  style:\"text-align:center\" name=\"Month"+i+"\"  value=\""+MonthDay[i]+"\"  ></td>");
    }
    out.print(" <tr><th colspan=\"2\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
            "<input type=\"submit\" class=\"btn btn-outline-dark btn-sm\" value=\"新增/修改\"></th></tr>");
    out.print("<input type=\"hidden\" name=\"update\" value=\"update\">");
    out.print("<input type=\"hidden\" name=\"workDays\" value=\""+workDaysCheck+"\">");
    out.print("</table>");
%>
</form>
<hr>
    <form action="MainServlet" method="post">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="submit" value="   回後台管理  " class="btn btn-outline-dark btn-sm">
        <input type="hidden" name="master" value="master">
    </form>
<%
 String wrongMessage = (String) request.getAttribute("wrongMessage");

%>
</div>
<%
    if (wrongMessage != null){
        out.print(" <c:if test=\"${flag }\">");
        out.print("  <span class=\"alert alert-danger alert-dismissable\" role=\"alert\">");
        out.print(" <button class=\"close\" type=\"button\" data-dismiss=\"alert\">&times;</button>");
        out.print("訊息："+wrongMessage);
        out.print("</span>");
        out.print("</c:if>");
    }
%>
</body>
</html>