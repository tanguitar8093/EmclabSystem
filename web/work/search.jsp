<%@ page import="java.time.LocalDate" %>
<%@ page import="work.DateParament" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="work.WLU" %><%--
  Created by IntelliJ IDEA.
  User: msiTek
  Date: 2018/1/10
  Time: 下午 04:40
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <meta name="viewport" content="width=device-width;   initial-scale=0.6 "; >
    <link rel="stylesheet" href="style/main.css" type="text/css"/>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%
        String whitchForm =request.getParameter("whitchForm");
        String date1=request.getParameter("date1");
        String wrongMessage = (String) request.getAttribute("wrongMessage");
        String searchStatus = (String) request.getAttribute("searchStatus");
    %>
    <title>搜尋系統</title>
    <style>
        h1{
            color:black;
        }
    </style>
</head>
<body>
<h1><b>搜尋系統</b></h1>
<hr><br>
<form action="search.jsp" method="post">
    查詢表單：
    <select name="whitchForm">
        <option value="DateShowList">場地預訂表</option>
        <option value="WorklistData">TestLogSheet</option>
    </select>
    <input type="submit" class="btn btn-outline-dark btn-sm" value="選擇" >
</form>
<form action="SearchServlet" method="post">
    <%
        if (whitchForm!=null) {
            out.print("<hr><br>");
            if (whitchForm.equals("DateShowList")){
            out.print("<h4><b>"+"場地預訂表"+"</b></h4>");
            }else {
                out.print("<h4><b>"+"TestLogSheet"+"</b></h4>");
            }
            out.print("<br>");
            out.print("場地測項：");
            if (whitchForm.equals("DateShowList")) {
                out.print("<select name=\"testItem\">\n" +
                        "    <option value=\"All-Site\">所有場地</option>\n" +
                        "    <option value=\"S0-RE\">S0-RE</option>\n" +
                        "    <option value=\"S0-CE\">S0-CE</option>\n" +
                        "    <option value=\"S1\">S1</option>\n" +
                        "    <option value=\"S2\">S2</option>\n" +
                        "    <option value=\"S3\">S3</option>\n" +
                        "    <option value=\"S4\">S4</option>\n" +
                        "    <option value=\"966\">966</option>\n" +
                        "</select>");
            } else if (whitchForm.equals("WorklistData")) {
                out.print("<select name=\"testItem\">\n" +
                        "    <option value=\"All-Site\">所有場地</option>\n" +
                        "    <option value=\"S0-RE\">S0-RE</option>\n" +
                        "    <option value=\"S0-CE\">S0-CE</option>\n" +
                        "    <option value=\"S1\">S1</option>\n" +
                        "    <option value=\"S2\">S2</option>\n" +
                        "    <option value=\"S3\">S3</option>\n" +
                        "    <option value=\"S4\">S4</option>\n" +
                        "    <option value=\"966\">966</option>\n" +
                        "    <option value=\"RE-S0\">RE: S0</option>\n" +
                        "    <option value=\"RE-966\">RE: 966</option>\n" +
                        "    <option value=\"CE-S0\">CE: S0</option>\n" +
                        "    <option value=\"RS-S1\">RS: S1</option>\n" +
                        "    <option value=\"RS-966\">RS: 966</option>\n" +
                        "    <option value=\"CS-S2\">CE: S0</option>\n" +
                        "    <option value=\"ESD-S3\">ESD: S3</option>\n" +
                        "    <option value=\"ESD-S4\">ESD: S4</option>\n" +
                        "    <option value=\"Flick/Harmonic-S2\">Flick/Harmonic: S2</option>\n" +
                        "    <option value=\"Flick/Harmonic-S3\">Flick/Harmonic: S3</option>\n" +
                        "    <option value=\"EFT/B-S2\">EFT/B: S2</option>\n" +
                        "    <option value=\"EFT/B-S3\">EFT/B: S3</option>\n" +
                        "    <option value=\"Surge-S2\">Surge: S2</option>\n" +
                        "    <option value=\"Surge-S3\">Surge: S3</option>\n" +
                        "    <option value=\"MF-S3\"> Magnetic Field: S3</option>\n" +
                        "    <option value=\"Dips/Variations-S2\">Dips/Variations: S2</option>\n" +
                        "    <option value=\"Dips/Variations-S3\">Dips/Variations: S3</option>\n" +
                        "    <option value=\"GSM-S0\">GSM: S0</option>\n" +
                        "    <option value=\"GSM-S1\">GSM: S1</option>\n" +
                        "    <option value=\"GSM-S2\">GSM: S2</option>\n" +
                        "</select>");
            }
            out.print("  <br><br>\n" +
                    "    查詢時間&nbsp;: &nbsp;<input type=\"date\" name=\"date1\"required >至<input type=\"date\" name=\"date2\" required>\n ");
            if (whitchForm.equals("DateShowList")) {
                out.print("<br><br>\n" +
                        "    查詢類別：\n" +
                        "    <select name=\"whitchType\">\n" +
                        "        <option value=\"alltype\">所有資料</option>\n" +
                        "        <option value=\"dateUser\">負責人</option>\n" +
                        "        <option value=\"unit\">負責單位</option>\n" +
                        "        <option value=\"clientName\">客戶名</option>\n" +
                        "        <option value=\"modeName\">型號</option>\n" +
                        "        <option value=\"dateCase\">案號</option>\n" +
                        "    </select>\n");
            }else{
                out.print("<br><br>\n" +
                        "    查詢類別：\n" +
                        "    <select name=\"whitchType\">\n" +
                        "        <option value=\"alltype\">所有資料</option>\n" +
                        "        <option value=\"operatorName\">測試者</option>\n" +
                        "        <option value=\"unit\">負責單位</option>\n" +
                        "        <option value=\"clientName\">客戶名</option>\n" +
                        "        <option value=\"modeName\">型號</option>\n" +
                        "        <option value=\"testFileName\">案號/檔名</option>\n" +
                        "    </select>\n");
            }

            out.print("<input type=\"hidden\" name=\"whitchForm\" value=\""+whitchForm+"\">");
            out.print("<input type=\"text\" name=\"searchWord\"> "+"    <input type=\"submit\"  class=\"btn btn-outline-dark btn-sm\" value=\"搜尋\">");
            out.print("<br><br>"+" ※IE瀏覽器 日期格式：Ex.2018-05-04 (共10碼，含\"-\")");
        }

    %>
</form>
<HR>
<table>


<%
    if (searchStatus !=null && wrongMessage==null) {
        out.println("<br>");
        int power = (int) request.getAttribute("power");
        String accountcheck = (String) request.getAttribute("accountcheck");
        String readtestItem= (String) request.getAttribute("readtestItem");
        String readdate1= (String) request.getAttribute("readdate1");
        String readdate2= (String) request.getAttribute("readdate2");
        String readwhitchType= (String) request.getAttribute("readwhitchType");
        String readsearchWord= (String) request.getAttribute("readsearchWord");

        if (whitchForm.equals("DateShowList")) {
            out.print("<form action=\"DateListCheckServlet\" method=\"post\">");
            out.print("<table border=\"2\" CELLSPACING=0>\n" +
                    "    <tr>\n" +
                    "        <td><b>使用時段</b></td>\n" +
                    "        <td><b>場地</b></td>\n" +
                    "        <td><b>使用者</b></td>\n" +
                    "        <td><b>單位</b></td>\n" +
                    "        <td><b>客戶名</b></td>\n" +
                    "        <td><b>型號</b></td>\n" +
                    "        <td><b>案號</b></td>\n");
                out.print("<td><b>資料更動</b></td>");
            out.print("<tr>");

            String DateDay = "";
            String Trans = "";
            String testItem2 = null;
            ArrayList<DateParament> dateParaments = (ArrayList) request.getAttribute("result");
            for (DateParament dateParament : dateParaments) {
                Trans = (dateParament.getDateDay()).substring(dateParament.getDateDay().length() - 1);
                if (Trans.equals("1")) {
                    DateDay = dateParament.getListDate() + " 09:00-12:00";
                } else if (Trans.equals("2")) {
                    DateDay = dateParament.getListDate() + " 13:00-19:00";
                } else {
                    DateDay = dateParament.getListDate() + " 19:00-23:00";
                }
                out.println("<tr>");
                out.print("<td>&nbsp;" + DateDay + "&nbsp;</td>");
                out.print("<td>&nbsp;" + dateParament.getTestItem2() + "&nbsp;</td>");
                out.print("<td>&nbsp;" + dateParament.getDateUser() + "&nbsp;</td>");
                out.print("<td>&nbsp;" + dateParament.getUnit() + "&nbsp;</td>");
                out.print("<td>&nbsp;" + dateParament.getClientName() + "&nbsp;</td>");
                out.print("<td>&nbsp;" + dateParament.getModeName() + "&nbsp;</td>");
                out.print("<td>&nbsp;" + dateParament.getDateCase() + "&nbsp;</td>");
                //預設為同姓名可更改
//                if (power ==1 || (accountcheck.equals(dateParament.getDateUser()))){
//                    out.print("<form action=\"DateListCheckServlet\" method=\"post\">");
//                    out.print("<input type=\"hidden\"  name=\"searchId\" value=\""+dateParament.getId()+"\">");
//                    out.print("<input type=\"hidden\"  name=\"inputType\" value=\"SearchEdit\">");
//                    out.print("<input type=\"hidden\"  name=\"whitchForm\" value=\""+whitchForm+"\">");
//                    out.print("<input type=\"hidden\"  name=\"readtestItem\" value=\""+readtestItem+"\">");
//                    out.print("<input type=\"hidden\"  name=\"readdate1\" value=\""+readdate1+"\">");
//                    out.print("<input type=\"hidden\"  name=\"readdate2\" value=\""+readdate2+"\">");
//                    out.print("<input type=\"hidden\"  name=\"readwhitchType\" value=\""+readwhitchType+"\">");
//                    out.print("<input type=\"hidden\"  name=\"readsearchWord\" value=\""+readsearchWord+"\">");
//                    out.print("<td><b>&nbsp;&nbsp;&nbsp;<input type=\"submit\" class=\"btn btn-outline-dark btn-sm\" value=\"編輯\"></b></td>");
//                    out.print("</form>");
//                }else {
//                    out.print("<td>"+"權限不足"+"</td>");
//                }
                if (power ==1 ){
                    out.print("<form action=\"DateListCheckServlet\" method=\"post\">");
                    out.print("<input type=\"hidden\"  name=\"searchId\" value=\""+dateParament.getId()+"\">");
                    out.print("<input type=\"hidden\"  name=\"inputType\" value=\"SearchEdit\">");
                    out.print("<input type=\"hidden\"  name=\"whitchForm\" value=\""+whitchForm+"\">");
                    out.print("<input type=\"hidden\"  name=\"readtestItem\" value=\""+readtestItem+"\">");
                    out.print("<input type=\"hidden\"  name=\"readdate1\" value=\""+readdate1+"\">");
                    out.print("<input type=\"hidden\"  name=\"readdate2\" value=\""+readdate2+"\">");
                    out.print("<input type=\"hidden\"  name=\"readwhitchType\" value=\""+readwhitchType+"\">");
                    out.print("<input type=\"hidden\"  name=\"readsearchWord\" value=\""+readsearchWord+"\">");
                    out.print("<td><b>&nbsp;&nbsp;&nbsp;<input type=\"submit\" class=\"btn btn-outline-dark btn-sm\" value=\"編輯\"></b></td>");
                    out.print("</form>");
                }else {
                    out.print("<td>"+"權限不足"+"</td>");
                }
                out.println("</tr>");
                testItem2 = dateParament.getTestItem2();
            }
        }else {
            out.print("<form action=\"WorklistCheckServlet\" method=\"post\">");
            out.print("<table border=\"2\" CELLSPACING=0>\n" +
                    "    <tr>\n" +
                    "        <td><b>日期</b></td>\n" +
                    "        <td><b>使用時段</b></td>\n" +
                    "        <td><b>使用時間</b></td>\n" +
                    "        <td><b>測試人員</b></td>\n" +
                    "        <td><b>測試項目</b></td>\n" +
                    "        <td><b>負責單位</b></td>\n" +
                    "        <td><b>客戶名</b></td>\n" +
                    "        <td><b>型號</b></td>\n" +
                    "        <td><b>案號</b></td>\n" +
                    "        <td><b>測試/報告</b></td>\n" +
                    "        <td><b>備註</b></td>\n");
            out.print("<td><b>資料更動</b></td>");
            out.print("</tr>");
            String tts1="",tts2="",tte1="",tte2="";
            ArrayList<WLU> wlus = (ArrayList) request.getAttribute("result2");
            for (WLU wlu:wlus){
                tts1=""+wlu.getTestTimeStart1();
                tts2=""+wlu.getTestTimeStart2();
                tte1=""+wlu.getTestTimeEnd1();
                tte2=""+wlu.getTestTimeEnd2();
                String testItem2 = wlu.getTestItem2();
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
                out.print("<td>&nbsp;"+wlu.getListDate()+"&nbsp;</td>");
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
                if (power ==1 || (accountcheck.equals(wlu.getOperatorName()))){
                    out.print("<form action=\"WorklistCheckServlet\" method=\"post\">");
                    out.print("<input type=\"hidden\"  name=\"searchId\" value=\""+wlu.getId()+"\">");
                    out.print("<input type=\"hidden\"  name=\"whitchForm\" value=\""+whitchForm+"\">");
                    out.print("<input type=\"hidden\"  name=\"readtestItem\"    value=\""+readtestItem+"\">");
                    out.print("<input type=\"hidden\"  name=\"readdate1\" value=\""+readdate1+"\">");
                    out.print("<input type=\"hidden\"  name=\"readdate2\" value=\""+readdate2+"\">");
                    out.print("<input type=\"hidden\"  name=\"readwhitchType\" value=\""+readwhitchType+"\">");
                    out.print("<input type=\"hidden\"  name=\"readsearchWord\" value=\""+readsearchWord+"\">");
                    out.print("<td><b>&nbsp;&nbsp;&nbsp;<input type=\"submit\" class=\"btn btn-outline-dark btn-sm\" value=\"編輯\"></b></td>");
                    out.print("</form>");
                }else {
                    out.print("<td>&nbsp;"+"權限不足"+"</td>");
                }
                out.println("</tr>");
            }
        }
        out.print("<table>");
        out.print("<br><hr>");
        out.print("</table>");
    }
    %>
</form>
</table>
<input type="button" value="  回主選單  " class="btn btn-outline-dark btn-sm"  onclick="location.href='MainServlet'">
<%
    if (wrongMessage != null){
        out.print("<br><hr>");
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