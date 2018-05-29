<%@ page import="java.util.ArrayList" %>
<%@ page import="work.DateParament" %>
<%@ page import="work.WLU" %><%--
  Created by IntelliJ IDEA.
  User: msiTek
  Date: 2018/2/25
  Time: 上午 03:57
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
    <meta name="viewport" content="width=device-width;   initial-scale=0.5 "; >
    <%
        String whatString = (String) request.getAttribute("whatString");
    %>
    <link rel="stylesheet" href="style/main.css" type="text/css"/>
    <title>該時段資料</title>
    <style>
        h1{
            color:teal;
        }
    </style>
</head>
<body>
<h1><b>該時段資訊：</b></h1>
<table border="2" CELLSPACING=0>
    <tr>
        <td><b>使用時段</b></td>
        <td><b>場地</b></td>
        <td><b>使用者</b></td>
        <td><b>單位</b></td>
        <td><b>客戶名</b></td>
        <td><b>型號</b></td>
        <td><b>案號</b></td>
    </tr>
    <%
        String DateDay="";
        String Trans="";
        String testItem2 = null;
        ArrayList<DateParament> dateParaments = (ArrayList) request.getAttribute("result");
        for (DateParament dateParament :dateParaments){
            Trans=(dateParament.getDateDay()).substring(dateParament.getDateDay().length()-1);
            if (Trans.equals("1")){
                DateDay=dateParament.getListDate()+" 09:00-12:00";
            }else if (Trans.equals("2")){
                DateDay=dateParament.getListDate()+" 13:00-19:00";
            }else {
                DateDay=dateParament.getListDate()+" 19:00-23:00";
            }
            out.println("<tr>");
            out.print("<td>&nbsp;"+DateDay+"&nbsp;</td>");
            out.print("<td>&nbsp;"+dateParament.getTestItem2()+"&nbsp;</td>");
            out.print("<td>&nbsp;"+dateParament.getDateUser()+"&nbsp;</td>");
            out.print("<td>&nbsp;"+dateParament.getUnit()+"&nbsp;</td>");
            out.print("<td>&nbsp;"+dateParament.getClientName()+"&nbsp;</td>");
            out.print("<td>&nbsp;"+dateParament.getModeName()+"&nbsp;</td>");
            out.print("<td>&nbsp;"+dateParament.getDateCase()+"&nbsp;</td>");
            out.println("</tr>");
            testItem2=dateParament.getTestItem2();
        }

    %>
</table>
<br>
<h1><b>當天實際情況：</b></h1>
<table border="2" CELLSPACING=0>
    <tr>
        <td><b>使用時段</b></td>
        <td><b>使用時間</b></td>
        <td><b>測試人員</b></td>
        <td><b>測試項目</b></td>
        <td><b>負責單位</b></td>
        <td><b>客戶名</b></td>
        <td><b>型號</b></td>
        <td><b>案號</b></td>
        <td><b>測試/報告</b></td>
        <td><b>備註</b></td>
    </tr>
<%
    String tts1="",tts2="",tte1="",tte2="";
    ArrayList<WLU> wlus = (ArrayList) request.getAttribute("result2");
    for (WLU wlu:wlus){
        tts1=""+wlu.getTestTimeStart1();
        tts2=""+wlu.getTestTimeStart2();
        tte1=""+wlu.getTestTimeEnd1();
        tte2=""+wlu.getTestTimeEnd2();
        testItem2 =wlu.getTestItem2();
        if (wlu.getTestTimeStart1()<10){
            tts1="0"+wlu.getTestTimeStart1();
        }
        if (wlu.getTestTimeStart2()<10){
            tts2="0"+wlu.getTestTimeStart2();
        }
        if (wlu.getTestTimeEnd1()<10){
            tte1="0"+wlu.getTestTimeEnd1();
        }
        if (wlu.getTestTimeEnd2()<10){
            tte2="0"+wlu.getTestTimeEnd2();
        }
        String time=tts1+":"+tts2+"-"+tte1+":"+tte2;
        out.println("<tr>");
        out.print("<td>&nbsp;"+time+"&nbsp;</td>");
        out.print("<td>&nbsp;"+wlu.getTC()+"/hr"+"&nbsp;</td>");
        out.print("<td>&nbsp;"+wlu.getOperatorName()+"&nbsp;</td>");
        out.print("<td>&nbsp;"+wlu.getTestItem()+"&nbsp;</td>");
        out.print("<td>&nbsp;"+wlu.getUnit()+"&nbsp;</td>");
        out.print("<td>&nbsp;"+wlu.getClientName()+"&nbsp;</td>");
        out.print("<td>&nbsp;"+wlu.getModeName()+"&nbsp;</td>");
        out.print("<td>&nbsp;"+wlu.getTestFileName()+"&nbsp;</td>");
        out.print("<td>&nbsp;"+wlu.getServer()+"&nbsp;</td>");
        out.print("<td>&nbsp;"+wlu.getRemark()+"&nbsp;</td>");
        out.println("</tr>");
        
    }
%>
</table>
<br>
    <%
        out.print("<form action=\"CheckStringServlet\" method=\"post\">");
        out.print("<input type=\"hidden\" name=\"testItem2\" value=\""+ testItem2+ "\""+">"+"</input>");
        out.print("<input type=\"hidden\" name=\"whatString\" value=\""+ whatString+ "\""+">"+"</input>");
        out.print("<input type=\"submit\"  class=\"btn btn-outline-dark btn-sm\" value=\"回場地表單\" >");
        out.print("</form>");
    %>
</body>
</html>
