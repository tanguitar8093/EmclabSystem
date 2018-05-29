<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.temporal.TemporalAdjusters" %><%--
  Created by IntelliJ IDEA.
  User: msiTek
  Date: 2018/1/11
  Time: 下午 01:16
  To change this template use File | Settings | File Templates.
--%>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<meta name="viewport" content="width=device-width;   initial-scale=0.75 "; >
<link rel="stylesheet" href="style/list2.css" media="screen and (min-device-width: 500px)" type="text/css"/>
<link rel="stylesheet" href="style/list22.css" media="screen and (max-device-width: 500px)" type="text/css"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        LocalDate localDate = LocalDate.now();
        int today =LocalDate.now().getDayOfMonth();
        int whatYear = localDate.getYear();
        int whatMonth = localDate.getMonth().getValue();
        int power = (int) request.getAttribute("power");
    %>
    <title>表單填寫</title>
</head>
<body>
<div>
<h1>Test Log Sheet</h1>
<hr><br>
<form action="WorkListServlet" method="post">
    <label> Operator Name：</label>
    <input type="text" name="operatorName" value="${accountcheck}" readonly><br>
    <label> Date：</label>
    <%
        if (power ==1){
            out.print("<input type=\"date\" name=\"listDate\">");
        }else {
            out.println("<input type=\"text\" name=\"listDate\" value=\"" + localDate + "\"readonly>");
        }
        out.println("<input type=\"hidden\" name=\"dateYear\" value=\""+whatYear+"\"readonly>");
        out.println("<input type=\"hidden\" name=\"dateMonth\" value=\""+whatMonth+"\"readonly>");
    %>
    <br>
    <label> Test Time：</label>
    <select name="testTimeStart1">
        <option value=9>09</option>
        <option value=10>10</option>
        <option value=11>11</option>
        <option value=13>13</option>
        <option value=14>14</option>
        <option value=15>15</option>
        <option value=16>16</option>
        <option value=17>17</option>
        <option value=19>19</option>
        <option value=20>20</option>
        <option value=21>21</option>
        <option value=22>22</option>
        <option value=23>23</option>
    </select>:
    <select name="testTimeStart2">
        <option value=0>00</option>
        <option value=30>30</option>
    </select>-
    <select name="testTimeEnd1">
        <option value=9>09</option>
        <option value=10>10</option>
        <option value=11>11</option>
        <option value=12>12</option>
        <option value=13>13</option>
        <option value=14>14</option>
        <option value=15>15</option>
        <option value=16>16</option>
        <option value=17>17</option>
        <option value=18>18</option>
        <option value=19>19</option>
        <option value=20>20</option>
        <option value=21>21</option>
        <option value=22>22</option>
        <option value=23>23</option>
    </select>:
    <select name="testTimeEnd2">
        <option value=0>00</option>
        <option value=30>30</option>
    </select>

    <br>
    <label> Client Name：</label>
    <input type="text" name="clientName" required><br>

    <label> Mode Name：</label>
    <input type="text" name="modeName" required><br>

    <label> Test File Name：</label>
    <input type="text" name="testFileName" required><br>

    <label> Remark：</label>
    <input type="text" name="remark"><br>
        <label> 測試項目/場地：</label>
    <select name="testItem">
        <option value="RE-S0">RE: S0</option>
        <option value="RE-966">RE: 966</option>
        <option value="CE-S0">CE: S0</option>
        <option value="RS-S1">RS: S1</option>
        <option value="RS-966">RS: 966</option>
        <option value="CS-S2">CE: S0</option>
        <option value="ESD-S3">ESD: S3</option>
        <option value="ESD-S4">ESD: S4</option>
        <option value="Flick/Harmonic-S2">Flick/Harmonic: S2</option>
        <option value="Flick/Harmonic-S3">Flick/Harmonic: S3</option>
        <option value="EFT/B-S2">EFT/B: S2</option>
        <option value="EFT/B-S3">EFT/B: S3</option>
        <option value="Surge-S2">Surge: S2</option>
        <option value="Surge-S3">Surge: S3</option>
        <option value="MF-S3">Magnetic Field: S3</option>
        <option value="Dips/Variations-S2">Dips/Variations: S2</option>
        <option value="Dips/Variations-S3">Dips/Variations: S3</option>
        <option value="GSM-S0">GSM: S0</option>
        <option value="GSM-S1">GSM: S1</option>
        <option value="GSM-S2">GSM: S2</option>
    </select><br>
        <label>服務類別：</label>
        <select name="server">
            <option value="test">測試</option>
            <option value="report">報告</option>
        </select><br>
        <label>負責單位：</label>
        <select name="unit">
            <option value="EBU">EBU</option>
            <option value="TBU">TBU</option>
            <option value="CBU">CBU</option>
            <option value="EMCLab">EMCLab</option>
        </select><br>
        <input type="hidden" name="action" value="add">
        <label>&nbsp;</label>
        <input type="submit" value="確認" class="btn btn-outline-dark btn-sm" id="WorklistOk">
        <input type="reset" class="btn btn-outline-dark btn-sm" value="重新填寫"><br><br>
    ※IE瀏覽器 日期格式：Ex.2018-05-04 (共10碼，含"-")



</form>
<hr><br>
<input type="submit" value="  回主選單  " class="btn btn-outline-dark btn-sm" onclick="location.href='MainServlet'">
</div>
<%
    String wrongMessege = (String) request.getAttribute("wrongMessege");
%>
<%
    if (wrongMessege != null){
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
