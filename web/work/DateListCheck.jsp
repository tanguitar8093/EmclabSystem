<%@ page import="work.DateParament" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: msiTek
  Date: 2018/2/18
  Time: 下午 10:11
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
    <title>場地審核列表</title>
    <%
        String inputType = (String) request.getAttribute("inputType");
        String DateWrong = (String) request.getAttribute("DateWrong");
        //紀錄預定表紀錄
        String whatString = (String) request.getAttribute("whatString");
        //紀錄搜尋參數
        String whitchForm = (String) request.getAttribute("whitchForm");
        String testItem= (String) request.getAttribute("testItem");
        String date1= (String) request.getAttribute("date1");
        String date2= (String) request.getAttribute("date2");
        String whitchType= (String) request.getAttribute("whitchType");
        String searchWord= (String) request.getAttribute("searchWord");
        int searchId=0;
        if (whitchForm !=null) {
            searchId = (int) request.getAttribute("searchId");
        }

    %>
</head>
<body>

<h1><b>場地審核列表</b></h1>
<hr>
<br>
<table border="2" CELLSPACING=0>
    <tr>
        <td><b>申請時間</b></td>
        <td><b>使用時段</b></td>
        <td><b>場地</b></td>
        <td><b>使用者</b></td>
        <td><b>單位</b></td>
        <td><b>客戶名</b></td>
        <td><b>型號</b></td>
        <td><b>案號</b></td>
        <%
            if (!inputType.equals("normal") ){
                out.print("<td><b>刪除</b></td>");
//                out.print("<td><b>傳送訊息</b></td>");
            }else {
                out.print("     <td><b>核准</b></td>\n" +
                        "        <td><b>拒絕</b></td>\n"
//                        + "        <td><b>傳送訊息</b></td>"
                );
            }
        %>
        <td><b>提交</b></td>
    </tr>
    <%
        String DateDay="";
        String Trans="";
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
            out.println("<form action=\"DateListOkServlet\" method=\"post\">");

            out.println("<tr>");
            out.print("<td>&nbsp;"+dateParament.getBookTime()+"&nbsp;</td>");
            out.print("<td>&nbsp;"+DateDay+"&nbsp;</td>");
            out.print("<td>&nbsp;"+dateParament.getTestItem2()+"&nbsp;</td>");
            out.print("<td>&nbsp;"+"<input type=\"text\"style=\"width: 6em\"  name=\"DateUser\" value=\""+ dateParament.getDateUser()+"\""+">"+"</input>"+"&nbsp;</td>");
            out.print("<td>&nbsp;"+"<input type=\"text\"style=\"width: 4em\" maxlength=\"4\"  name=\"Unit\" value=\""+ dateParament.getUnit()+"\""+">"+"</input>"+"&nbsp;</td>");
            out.print("<td>&nbsp;"+"<input type=\"text\"style=\"width: 6em\"   name=\"ClientName\" value=\""+ dateParament.getClientName()+"\""+">"+"</input>"+"&nbsp;</td>");
            out.print("<td>&nbsp;"+"<input type=\"text\"style=\"width: 6em\"   name=\"ModeName\" value=\""+ dateParament.getModeName()+"\""+">"+"</input>"+"&nbsp;</td>");
            out.print("<td>&nbsp;"+"<input type=\"text\"style=\"width: 6em\" maxlength=\"8\"  name=\"DateCase\" value=\""+ dateParament.getDateCase()+"\""+">"+"</input>"+"&nbsp;</td>");
            if (inputType.equals("normal") ) {
                out.print("<td>&nbsp;" + "<input type=\"checkbox\" name=\"checkaction\" value=\"ok\"" + ">" + "</input>" + "&nbsp;</td>");
            }else {
                out.print("<input type=\"hidden\" name=\"inputType\" value=\"inputDateShowList\""+">"+"</input>");
            }
            out.print("<td>&nbsp;"+"<input type=\"checkbox\" name=\"checkaction\" value=\"no\""+">"+"</input>"+"&nbsp;</td>");
//            out.print("<td>&nbsp;"+"</input>"+"<input type=\"text\" name=\"actionreason\""+">"+"</input>"+"&nbsp;</td>");
           //傳送搜尋參數
            if (whitchForm !=null){
                out.print("<input type=\"hidden\"  name=\"whitchForm\" value=\""+whitchForm+"\">");
                out.print("<input type=\"hidden\"  name=\"testItem\" value=\""+testItem+"\">");
                out.print("<input type=\"hidden\"  name=\"date1\" value=\""+date1+"\">");
                out.print("<input type=\"hidden\"  name=\"date2\" value=\""+date2+"\">");
                out.print("<input type=\"hidden\"  name=\"whitchType\" value=\""+whitchType+"\">");
                out.print("<input type=\"hidden\"  name=\"searchWord\" value=\""+searchWord+"\">");
                out.print("<input type=\"hidden\"  name=\"readinputType\" value=\""+inputType+"\">");
                out.print("<input type=\"hidden\"  name=\"searchId\" value=\""+searchId+"\">");
            }
            if (whatString !=null){//場地列表
                out.print("<input type=\"hidden\"  name=\"whatString\" value=\""+whatString+"\">");
            }
            out.print("<td>&nbsp;"+"<input type=\"submit\" class=\"btn btn-outline-dark btn-sm\" value=\"送出\""+">"+"</input>"+"&nbsp;</td>");
            out.println("</tr>");
            out.print("<input type=\"hidden\" name=\"id\" value=\""+ dateParament.getId()+ "\""+">"+"</input>");
            out.print("</form>");
        }


    %>
</table>
<br><hr><br>
<%
    if (whitchForm !=null){
        out.println("<form action=\"SearchServlet\" method=\"post\">");
        out.print("<input type=\"hidden\"  name=\"whitchForm\" value=\""+whitchForm+"\">");
        out.print("<input type=\"hidden\"  name=\"testItem\" value=\""+testItem+"\">");
        out.print("<input type=\"hidden\"  name=\"date1\" value=\""+date1+"\">");
        out.print("<input type=\"hidden\"  name=\"date2\" value=\""+date2+"\">");
        out.print("<input type=\"hidden\"  name=\"whitchType\" value=\""+whitchType+"\">");
        out.print("<input type=\"hidden\"  name=\"searchWord\" value=\""+searchWord+"\">");
        out.print("<input type=\"hidden\"  name=\"readinputType\" value=\""+inputType+"\">");
        out.print("<input type=\"hidden\"  name=\"searchId\" value=\""+searchId+"\">");
        out.print("<input type=\"submit\" class=\"btn btn-outline-dark btn-sm\" value=\"回搜尋列表\""+">"+"</input>");
        out.print("<input type=\"button\" value=\"  回主選單  \"  class=\"btn btn-outline-dark btn-sm\" onclick=\"location.href='MainServlet'\">");
        out.print("</form>");
    }
    if (whatString !=null){
        out.println("<form action=\"CheckStringServlet\" method=\"post\">");
        out.print("<input type=\"hidden\"  name=\"whatString\" value=\""+whatString+"\">");
        out.print("<input type=\"submit\" class=\"btn btn-outline-dark btn-sm\" value=\"回場地列表\""+">"+"</input>");
        out.print("<input type=\"button\" value=\"  回主選單  \"  class=\"btn btn-outline-dark btn-sm\" onclick=\"location.href='MainServlet'\">");
        out.print("</form>");
    }
    if (whatString ==null && whitchForm==null ){
        out.print("<form action=\"MainServlet\" method=\"post\">");
        out.print(" <input type=\"hidden\" name=\"master\" value=\"master\">");
        out.print("<input type=\"submit\" value=\"  回後台管理  \"  class=\"btn btn-outline-dark btn-sm\">");
        out.print("</form>");
    }
%>

<%
    if (DateWrong != null){
        out.print("<table></table>");
        out.print("<hr>");
        out.print(" <c:if test=\"${flag }\">");
        out.print("  <span class=\"alert alert-danger alert-dismissable\" role=\"alert\">");
        out.print(" <button class=\"close\" type=\"button\" data-dismiss=\"alert\">&times;</button>");
        out.print("訊息："+DateWrong);
        out.print("</span>");
        out.print("</c:if>");
    }
%>
</body>
</html>
