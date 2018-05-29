<%@ page import="java.time.LocalDate" %><%--
  Created by IntelliJ IDEA.
  User: msiTek
  Date: 2018/1/10
  Time: 下午 04:40
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width;   initial-scale=0.75 "; >
    <link rel="stylesheet" href="style/list2.css" media="screen and (min-device-width: 500px)" type="text/css"/>
    <link rel="stylesheet" href="style/list22.css" media="screen and (max-device-width: 500px)" type="text/css"/>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%
        Double[] math;
            math= (Double[]) request.getAttribute("math");
    %>
    <%
        LocalDate localDate = LocalDate.now();
    %>
    <%
        String whatString = (String) request.getAttribute("whatString");
        int dateYear = (int) request.getAttribute("dateYear");
        int dateMonth = (int) request.getAttribute("dateMonth");
    %>
    <title>數據統計</title>
</head>
<body>
<div>
<%
    String testItem2= (String) request.getAttribute("testItem2");
    String whichTime= (String) request.getAttribute("whichTime");
    if (dateMonth !=0){
        out.print("<h1>"+dateYear+"年"+dateMonth+"月"+testItem2+"統計"+"</h1>");
    }else {
        out.print("<h1>"+dateYear+"年 "+whichTime+" "+testItem2+"統計"+"</h1>");
    }
%>
    <hr><br>
<form action="MathServlet" method="post">
場地：
<select name="testItem2">
    <option value="S0-RE">S0-RE</option>
    <option value="S0-CE">S0-CE</option>
    <option value="S1">S1</option>
    <option value="S2">S2</option>
    <option value="S3">S3</option>
    <option value="S4">S4</option>
    <option value="966">966</option>
</select>
    &nbsp;&nbsp;查詢類別：
    <select name="whichTime">
        <option value="month">單月</option>
        <option value="All-Year">整年</option>
        <option value="Season1">第一季</option>
        <option value="Season2">第二季</option>
        <option value="Season3">第三季</option>
        <option value="Season4">第四季</option>
        <option value="First-Half-Year">前半年</option>
        <option value="Last-Half-Year">後半年</option>
    </select>
<br><br>時間：
<input type="text"style="width: 6em" maxlength="6" value="${whatString}" name="whatString" >
<input type="submit" class="btn btn-outline-dark btn-sm" value="送出" ><br><br>
    輸入格式Ex.單月:201802&nbsp;&nbsp;季/年:2018<br>
</form>
<hr><br>
<b>總使用統計</b><br><br>
<TABLE BORDER=1 CELLSPACING=0>
    <TR>
        <TD>12hr/day</TD>
        <TD>8hr/day</TD>
        <TD>Total/month</TD>
    </TR>
        <%
          out.print(" <TR>");
          out.print("<td>"+(Math.rint(math[1]*100*100)/100)+"%"+"</td>");
          out.print("<td>"+(Math.rint(math[2]*100*100)/100)+"%"+"</td>");
          out.print("<td>"+(Math.rint(math[0]*100)/100)+"/hr"+"</td>");
          out.print(" </TR>");
        %>

</TABLE>
※總使用率=實際時數/工作天時數<br><br>
<b>部門使用統計</b><br><br>
<TABLE BORDER=1 CELLSPACING=0>
    <TR>
        <TD></TD>
        <TD>12hr/day</TD>
        <TD>8hr/day</TD>
        <TD>Total/month</TD>
    </TR>
    <TR>
        <TD>EBU</TD>
        <%
            out.print("<td>"+(Math.rint(math[4]*100*100)/100)+"%"+"</td>");
            out.print("<td>"+(Math.rint(math[5]*100*100)/100)+"%"+"</td>");
            out.print("<td>"+(Math.rint(math[3]*100)/100)+"/hr"+"</td>");
        %>
    </TR>
    <TR>
        <TD>TBU</TD>
        <%
            out.print("<td>"+(Math.rint(math[7]*100*100)/100)+"%"+"</td>");
            out.print("<td>"+(Math.rint(math[8]*100*100)/100)+"%"+"</td>");
            out.print("<td>"+(Math.rint(math[6]*100)/100)+"/hr"+"</td>");
        %>
    </TR>
    <TR>
        <TD>CBU</TD>
        <%
            out.print("<td>"+(Math.rint(math[10]*100*100)/100)+"%"+"</td>");
            out.print("<td>"+(Math.rint(math[11]*100*100)/100)+"%"+"</td>");
            out.print("<td>"+(Math.rint(math[9]*100)/100)+"/hr"+"</td>");
        %>
    </TR>
</TABLE>
<br>
    <hr><br>
<input type="button" value="  回主選單  " class="btn btn-outline-dark btn-sm" onclick="location.href='MainServlet'">
</div>
</body>
</html>
<%--<h1>查詢</h1>--%>
<%--欲查詢年/月份：<br>--%>
<%--<input type="text" name="recode" maxlength="6"><br>--%>
<%--Ex.201802<br><br>--%>
<%--欲查詢案號或型號：<br>--%>
<%--<input type="text" name="mode" maxlength="8"><br>--%>
<%--Ex.20180002<br><br>--%>
<%--<input type="submit" value="  月份查詢  ">--%>
<%--<input type="submit" value="  專案查詢  "><br>--%>