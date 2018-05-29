<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page  import="java.util.*" %>
<%@ page  import="java.time.LocalDate" %>
<%@ page  import="java.time.temporal.TemporalAdjusters" %>
<%@ page import="work.DateUse" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="style/list.css" type="text/css"/>
    <style>
        hr{
            border:none;
            border-top:dashed 1px #CCC;
            height:1px;
            color:#FFFFFF;
            margin: 0 6 0 6;
        }
        h4{
            color:teal;
        }
        h1{
            color:black;
        }
    </style>
    <meta name="viewport" content="width=device-width;   initial-scale=0.6 "; >

    <%
       String showType = (String) request.getAttribute("showType");
        if (showType==null) {showType ="showname";}
    %>
    <%
        //顯示分類
        HashMap<String,String> subUser = new HashMap<String,String>();
        ArrayList<DateUse> show = (ArrayList) request.getAttribute("result");
        for (DateUse shows :show){
            if (showType.equals("showname")) {
                subUser.put(shows.getDateDay(), shows.getDateUser());
            }else if (showType.equals("showcase")){
                subUser.put(shows.getDateDay(), shows.getDateCase());
            }else if (showType.equals("showmode")){
                subUser.put(shows.getDateDay(), shows.getModeName());
            }else{
                subUser.put(shows.getDateDay(), shows.getClientName());
            }
        }
    %>
    <%
        LocalDate localDate = LocalDate.now();
        int whatYear = (int) request.getAttribute("whatYear");
        int whatMonth = (int) request.getAttribute("whatMonth");
        //getParament:jsp>>jsp,jsp>>servlet , getAttribute:Servlet>>jsp,Servlet>>Servlet
        String whatString = (String) request.getAttribute("whatString");
        LocalDate firstDayOfMonth = LocalDate.of(whatYear,whatMonth, 1);
        LocalDate lastday = LocalDate.of(whatYear,whatMonth,1).with(TemporalAdjusters.lastDayOfMonth());
        int gg =lastday.getDayOfMonth();
        int DayRun =1;
        String today =" ";
        int firstday=(firstDayOfMonth.getDayOfWeek().getValue());
        int time1 =878,time2 = 878,time3 =878;
        String showtime1 =" ",showtime2=" ",showtime3 =" ";
        %>
    <title>場地預約</title>
</head>
<body>
<h1>
    <%
        String testItem2 = (String) request.getAttribute("testItem2");
        out.print(testItem2+" 使用情況");
        %>
</h1>
<hr><br>
<table BORDER=1 CELLSPACING=0>
  <%   out.println("<td>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+whatYear+"/"+whatMonth+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
          +"</td>"); %>
    <TD>星期日&nbsp;&nbsp;</TD>
    <TD>星期一&nbsp;&nbsp;</TD>
    <TD>星期二&nbsp;&nbsp;</TD>
    <TD>星期三&nbsp;&nbsp;</TD>
    <TD>星期四&nbsp;&nbsp;</TD>
    <TD>星期五&nbsp;&nbsp;</TD>
    <TD>星期六&nbsp;&nbsp;</TD>
    </TR>
    <%
        String dateDay ="";
        int runweek =6;
        if (firstday>6){
            firstday =0;
        }
        if (firstday>4){
            runweek =7;
        }
        int firstrun=0;
        for (int j=1;j<runweek;j++) {
            for (int i = 0; i < 4; i++) {

                out.println("<tr>");
                switch(i) {
                    case 0:
                        out.print("<td class=\"datecolor\" style=\"background-color: black\">" + "WEEK" + j +"</td>");
                        for (int k=0;k<7;k++) {
                            if (firstrun ==0 && k >=firstday) {
                                if (DayRun == LocalDate.now().getDayOfMonth() && whatYear == localDate.getYear() &&
                                        whatMonth == localDate.getMonth().getValue()){
                                    today ="**";
                                }
                                out.print("<td class=\"datecolor\" style=\"background-color: black\">"+today+ DayRun +today+"</td>");
                                DayRun++;
                                firstrun++;
                                today =" ";
                            }else if (firstrun >0 && gg>=DayRun){
                                if (DayRun == LocalDate.now().getDayOfMonth() && whatYear == localDate.getYear() &&
                                        whatMonth == localDate.getMonth().getValue()){
                                    today ="**";
                                }
                                out.print("<td class=\"datecolor\" style=\"background-color: black\">" +today+ DayRun +today+ "</td>");
                                DayRun++;
                                today =" ";
                            }else{
                                out.print("<td class=\"datecolor\" style=\"background-color: black\">" + " " + "</td>");
                            }
                            if (subUser.get(DayRun+"-1") ==null ){

                            }
                        }
                        break;
                    case 1:
                        if (time1 ==878) {
                            time1 = DayRun - 7;
                        }
                        out.print("<td>" + "09:00-12:00"+"</td>");
                        for (int k=0;k<7;k++) {
                            if (time1 <= gg) {
                                showtime1=subUser.get(time1+"-1");
                                dateDay =time1+"-1";
                                if (showtime1 ==null){
                                    showtime1 =" ";
                                    out.print("<td>" + showtime1 + "</td>");
                                }else {
                                    out.print("<form action=\"LinkTimeServlet\" method=\"post\">");
                                    out.print("<td>"+"&nbsp;&nbsp;"+"<input type=\"submit\"  class=\"btn btn-outline-dark btn-sm\" value=\""+showtime1+"\" >"+"&nbsp;&nbsp;"+"</td>");
                                    out.print("<input type=\"hidden\" name=\"testItem2\" value=\""+testItem2+"\">");
                                    out.print("<input type=\"hidden\" name=\"dateDay\" value=\""+dateDay+"\">");
                                    out.print("<input type=\"hidden\" name=\"whatYear\" value=\""+whatYear+"\">");
                                    out.print("<input type=\"hidden\" name=\"whatMonth\" value=\""+whatMonth+"\">");
                                    out.print("<input type=\"hidden\" name=\"whatString\" value=\""+ whatString+ "\""+">"+"</input>");
                                    out.print("</form>");
                                }
                                time1++;
                            }else {
                                out.print("<td>" + " " + "</td>");
                            }
                        }
                        break;
                    case 2:
                        if (time2 ==878) {
                            time2 = DayRun - 7;
                        }
                        out.print("<td>" + "13:00-19:00"+"</td>");
                        for (int k=0;k<7;k++) {
                            if (time2 <= gg) {
                                showtime2=subUser.get(time2+"-2");
                                dateDay =time2+"-2";
                                if (showtime2 ==null){
                                    showtime2 =" ";
                                    out.print("<td>" + showtime2 + "</td>");
                                }else {
                                    out.print("<form action=\"LinkTimeServlet\" method=\"post\">");
                                    out.print("<td>"+"&nbsp;&nbsp;"+"<input type=\"submit\" class=\"btn btn-outline-dark btn-sm\" value=\""+showtime2+"\" >"+"&nbsp;&nbsp;"+"</td>");
                                    out.print("<input type=\"hidden\" name=\"testItem2\" value=\""+testItem2+"\">");
                                    out.print("<input type=\"hidden\" name=\"dateDay\" value=\""+dateDay+"\">");
                                    out.print("<input type=\"hidden\" name=\"whatYear\" value=\""+whatYear+"\">");
                                    out.print("<input type=\"hidden\" name=\"whatMonth\" value=\""+whatMonth+"\">");
                                    out.print("<input type=\"hidden\" name=\"whatString\" value=\""+ whatString+ "\""+">"+"</input>");
                                    out.print("</form>");
                                }
                                time2++;
                            }else {
                                out.print("<td>" + " " + "</td>");
                            }
                        }
                        break;
                    case 3:
                        if (time3 ==878) {
                            time3 = DayRun - 7;
                        }
                        out.print("<td>" + "19:00-23:00"+"</td>");
                        for (int k=0;k<7;k++) {
                            if (time3 <= gg){
                                showtime3=subUser.get(time3+"-3");
                                dateDay =time3+"-3";
                                if (showtime3 ==null){
                                    showtime3 =" ";
                                    out.print("<td>" + showtime3 + "</td>");
                                }else {
                                    out.print("<form action=\"LinkTimeServlet\" method=\"post\">");
                                    out.print("<td>"+"&nbsp;&nbsp;"+"<input type=\"submit\" class=\"btn btn-outline-dark btn-sm\" value=\""+showtime3+"\" >"+"&nbsp;&nbsp;"+"</td>");
                                    out.print("<input type=\"hidden\" name=\"testItem2\" value=\""+testItem2+"\">");
                                    out.print("<input type=\"hidden\" name=\"dateDay\" value=\""+dateDay+"\">");
                                    out.print("<input type=\"hidden\" name=\"whatYear\" value=\""+whatYear+"\">");
                                    out.print("<input type=\"hidden\" name=\"whatMonth\" value=\""+whatMonth+"\">");
                                    out.print("<input type=\"hidden\" name=\"whatString\" value=\""+ whatString+ "\""+">"+"</input>");
                                    out.print("</form>");
                                }
                                time3++;
                            }else {
                                out.print("<td>" + " " + "</td>");
                            }
                        }
                        break;
                }
                    out.println("</tr>");
            }
        }

    %>
    </form>
    <form action="CheckStringServlet" method="post">
        場地： <select name="testItem2">
            <option value="S0-RE">S0-RE</option>
            <option value="S0-CE">S0-CE</option>
            <option value="S1">S1</option>
            <option value="S2">S2</option>
            <option value="S3">S3</option>
            <option value="S4">S4</option>
            <option value="966">966</option>
        </select>
        &nbsp; <input type="submit" value="送出" class="btn btn-outline-dark btn-sm" ><br>
    </form>&nbsp;&nbsp;
    <form action="CheckStringServlet" method="post">
        顯示：<input type="radio"name="showType" value="showname" checked>負責人</input>
        <input type="radio" name="showType" value="showcase">案號</input>
        <input type="radio" name="showType" value="showclient">客戶</input>
        <input type="radio" name="showType" value="showmode">型號</input>
        &nbsp;&nbsp;查詢時間：
        <input type="text"style="width: 6em" maxlength="6" value="${whatString}" name="whatString" >
        <input type="hidden" name="testItem2" value="${testItem2}"> &nbsp;
        <input type="submit" class="btn btn-outline-dark btn-sm" value="送出" > Ex.201801<br><br>
    </form>
    <hr><br>
</table>
<br><hr><br>
<h4><b>預約使用</b></h4><br>
<form action="DateSubmitServlet" method="post">
<p>輸入客戶名稱： <INPUT style="width: 12em" type="text" name="clientName" required> Ex.Intel,Mitac<br>
<p>輸入型號：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <INPUT style="width: 12em" type="text" name="modeName" required> Ex.NR3-123,S5555<br>
<p>輸入案號：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <INPUT style="width: 12em" type="text" name="dateCase"  maxlength="8" > Ex.20180002<br>
<p>選擇日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="date" name="listDate" >&nbsp;
<%--<p> 選擇日期&nbsp;<select name="date1">--%>
    <%--<option value=1>1</option><option value=2>2</option><option value=3>3</option><option value=4>4</option><option value=5>5</option><option value=6>6</option><option value=7>7</option>--%>
    <%--<option value=8>8</option><option value=9>9</option><option value=10>10</option><option value=11>11</option><option value=12>12</option><option value=13>13</option><option value=14>14</option>--%>
    <%--<option value=15>15</option><option value=16>16</option><option value=17>17</option><option value=18>18</option><option value=19>19</option><option value=20>20</option><option value=21>21</option>--%>
    <%--<option value=22>22</option><option value=23>23</option><option value=24>24</option><option value=25>25</option><option value=26>26</option><option value=27>27</option>--%>
    <%--<option value=28>28</option><option value=29>29</option><option value=30>30</option><option value=31>31</option>--%>
<%--</select>&nbsp;--%>
    選擇時段&nbsp;<select name="date2">
        <option value=1>09:00-12:00</option>
        <option value=2>13:00-19:00</option>
        <option value=3>19:00-23:00</option>
    </select>&nbsp;
    <input type="hidden" name="dateUser" value="${accountcheck}" >
    <input type="hidden" name="unit" value="${unit}" >
    <input type="hidden" name="testItem2" value="${testItem2}">
    <input type="hidden" name="checkorlist" value="check">
    <input type="hidden" name="whatString" value="${whatString}">
    <input type="submit" class="btn btn-outline-dark btn-sm" value="提交申請"><br><br>
    ※IE瀏覽器 輸入日期格式：年(4碼)-月(2碼)-日(2碼) 含"-" Ex.2018-05-02
    </p>

        <%
        String DateWrong = (String) request.getAttribute("DateWrong");
    %>
    <%
        if (DateWrong != null){
            out.print("<br>");
            out.print(" <c:if test=\"${flag }\">");
            out.print("  <span class=\"alert alert-danger alert-dismissable\" role=\"alert\">");
            out.print(" <button class=\"close\" type=\"button\" data-dismiss=\"alert\">&times;</button>");
            out.print("訊息："+DateWrong);
            out.print("</span>");
            out.print("</c:if>");
        }
    %>
    <hr><br>
    <input type="button" class="btn btn-outline-dark btn-sm" value="  回主選單  "  class="btn btn-outline-dark btn-sm" onclick="location.href='MainServlet'">
</form>
</body>
</html>
