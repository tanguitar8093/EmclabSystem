<%@ page import="work.WLU" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: msiTek
  Date: 2018/4/11
  Time: 上午 10:18
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
    <link rel="stylesheet" href="style/main.css" type="text/css"/>
    <%
        //刪除重複
        String wrongMessege = (String) request.getAttribute("wrongMessege");
    //紀錄搜尋參數
    String whitchForm = (String) request.getAttribute("whitchForm");
    String testItem= (String) request.getAttribute("testItem");
    String date1= (String) request.getAttribute("date1");
    String date2= (String) request.getAttribute("date2");
    String whitchType= (String) request.getAttribute("whitchType");
    String run= (String) request.getAttribute("run");
    System.out.println(run);
    if (run == null){
        run="yes";
    }
    System.out.println(run);
    String searchWord= (String) request.getAttribute("searchWord");
    String inputType= (String) request.getAttribute("inputType");
    System.out.println(whitchForm+testItem+date1+date2+whitchType+searchWord);
    //searchId有兩種可能
    String searchId= String.valueOf((int) request.getAttribute("searchId"));
    %>
    <title>Worklist審核列表</title>
</head>
<body>
<table>
<h1>Worklist審核列表</h1>
<hr><br>
<table border="2" CELLSPACING=0>
    <tr>
        <td><b>日期</b></td>
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
        <td><b>刪除</b></td>
        <td><b>提交</b></td>
    </tr>
<%
out.println("<form action=\"WorklistOkServlet\" method=\"post\">");
   String tts1="",tts2="",tte1="",tte2="";
    String testItem2 = null;
    ArrayList<WLU> wlus = (ArrayList) request.getAttribute("result");
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
        out.print("<td>&nbsp;"+wlu.getListDate()+"&nbsp;</td>");
        out.print("<td>&nbsp;"+time+"&nbsp;</td>");
        out.print("<td>&nbsp;"+wlu.getTC()+"/hr"+"&nbsp;</td>");
        out.print("<td>&nbsp;"+"<input type=\"text\"style=\"width: 6em\"  name=\"operatorName\" value=\""+ wlu.getOperatorName()+"\""+">"+"</input>"+"&nbsp;</td>");
        out.print("<td>&nbsp;"+wlu.getTestItem()+"&nbsp;</td>");
        out.print("<td>&nbsp;"+"<input type=\"text\"style=\"width: 6em\"  name=\"unit\" value=\""+ wlu.getUnit()+"\""+">"+"</input>"+"&nbsp;</td>");
        out.print("<td>&nbsp;"+"<input type=\"text\"style=\"width: 6em\"  name=\"clientName\" value=\""+ wlu.getClientName()+"\""+">"+"</input>"+"&nbsp;</td>");
        out.print("<td>&nbsp;"+"<input type=\"text\"style=\"width: 8em\"  name=\"modeName\" value=\""+ wlu.getModeName()+"\""+">"+"</input>"+"&nbsp;</td>");
        out.print("<td>&nbsp;"+"<input type=\"text\"style=\"width: 10em\"  name=\"testFileName\" value=\""+ wlu.getTestFileName()+"\""+">"+"</input>"+"&nbsp;</td>");
        out.print("<td>&nbsp;"+"<input type=\"text\"style=\"width: 6em\"  name=\"server\" value=\""+ wlu.getServer()+"\""+">"+"</input>"+"&nbsp;</td>");
        out.print("<td>&nbsp;"+"<input type=\"text\"style=\"width: 6em\"  name=\"remark\" value=\""+ wlu.getRemark()+"\""+">"+"</input>"+"&nbsp;</td>");
        out.print("<td>&nbsp;"+"<input type=\"checkbox\" name=\"checkaction\" value=\"no\""+">"+"</input>"+"&nbsp;</td>");
        out.print("<input type=\"hidden\"style=\"width: 6em\"  name=\"id\" value=\""+searchId+"\""+">"+"</input>");
        out.print("<input type=\"hidden\"  name=\"wrongMessege\" value=\""+wrongMessege+"\">");
        //傳送搜尋參數
        out.print("<input type=\"hidden\"  name=\"whitchForm\" value=\""+whitchForm+"\">");
        out.print("<input type=\"hidden\"  name=\"testItem\" value=\""+testItem+"\">");
        out.print("<input type=\"hidden\"  name=\"date1\" value=\""+date1+"\">");
        out.print("<input type=\"hidden\"  name=\"date2\" value=\""+date2+"\">");
        out.print("<input type=\"hidden\"  name=\"whitchType\" value=\""+whitchType+"\">");
        out.print("<input type=\"hidden\"  name=\"searchWord\" value=\""+searchWord+"\">");
        out.print("<input type=\"hidden\"  name=\"inputType\" value=\""+inputType+"\">");
        out.print("<input type=\"hidden\"  name=\"searchId\" value=\""+searchId+"\">");
        out.print("<input type=\"hidden\"  name=\"run\" value=\""+run+"\">");
        out.print("<td>&nbsp;"+"<input type=\"submit\"  class=\"btn btn-outline-dark btn-sm\" value=\"送出\""+">"+"</input>"+"&nbsp;</td>");
        out.println("</tr>");
        out.print("</form>");
    }
%>
</table>
    <br><hr><br>
    <%

        out.println("<form action=\"SearchServlet\" method=\"post\">");
        out.print("<input type=\"hidden\"  name=\"whitchForm\" value=\""+whitchForm+"\">");
        out.print("<input type=\"hidden\"  name=\"testItem\" value=\""+testItem+"\">");
        out.print("<input type=\"hidden\"  name=\"date1\" value=\""+date1+"\">");
        out.print("<input type=\"hidden\"  name=\"date2\" value=\""+date2+"\">");
        out.print("<input type=\"hidden\"  name=\"whitchType\" value=\""+whitchType+"\">");
        out.print("<input type=\"hidden\"  name=\"searchWord\" value=\""+searchWord+"\">");
        out.print("<input type=\"hidden\"  name=\"readinputType\" value=\""+inputType+"\">");
        out.print("<input type=\"hidden\"  name=\"searchId\" value=\""+searchId+"\">");
        if (run.equals("yes")){
        out.print("<input type=\"submit\" class=\"btn btn-outline-dark btn-sm\" value=\"回搜尋列表\""+">"+"</input>");
        }
        out.print("<input type=\"button\" value=\"  回主選單  \"  class=\"btn btn-outline-dark btn-sm\" onclick=\"location.href='MainServlet'\">");
        out.print("</form>");
    %>
        <%
        if (wrongMessege != null){
            out.print("<hr><br>");
            out.print(" <c:if test=\"${flag }\">");
            out.print("  <span class=\"alert alert-danger alert-dismissable\" role=\"alert\">");
            out.print(" <button class=\"close\" type=\"button\" data-dismiss=\"alert\">&times;</button>");
            out.print("訊息："+wrongMessege);
            out.print("</span>");
            out.print("</c:if>");
        }
    %>
</body>
</html>
