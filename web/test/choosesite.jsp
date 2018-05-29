<%--
  Created by IntelliJ IDEA.
  User: msiTek
  Date: 2018/1/12
  Time: 上午 10:12
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="../work/style/list.css" type="text/css"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>預約場地</title>
</head>
<body>
<h1>RE10m 使用情況</h1>
<input type="submit" value="切換顯示 人名/專案名">&nbsp;&nbsp;
查詢日期：<INPUT style="width: 9em" type="text" name="searchdate"> Ex.201801<br>
<TABLE BORDER=1 CELLSPACING=0 >
<TR>
    <td>&nbsp;&nbsp;2018/01</td>
    <TD>星期日&nbsp;&nbsp;</TD><TD>星期一&nbsp;&nbsp;</TD><TD>星期二&nbsp;&nbsp;</TD><TD>星期三&nbsp;&nbsp;</TD><TD>星期四&nbsp;&nbsp;</TD><TD>星期五&nbsp;&nbsp;</TD><TD>星期六&nbsp;&nbsp;</TD>
</TR>
<TR class="datecolor" style="background-color: black">
    <TD>Week1&emsp;&emsp;&emsp;</TD ><td></td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td>
</TR>
    <TR>
        <TD>09:00-12:00</TD><td></td><td></td><td></td><td></td><td>Allen.tan</td><td>Allen.tan</td><td></td>
    </TR>
    <TR>
        <TD>13:00-19:00</TD><td></td><td>Intel NH3-666</td><td></td><td></td><td></td><td>Allen.tan</td><td></td>
    </TR>
    <TR>
        <TD>19:00-23:00</TD><td></td><td></td><td>Allen.tan</td><td></td><td>Allen.tan</td><td></td><td>Allen.tan</td>
    </TR>
    <TR class="datecolor" style="background-color: black">
        <TD>Week2</TD ><td>7</td><td>8</td><td>9</td><td>10</td><td>11</td><td>12</td><td>13</td>
    </TR>
    <TR>
        <TD>09:00-12:00</TD><td></td><td></td><td></td><td></td><td>Allen.tan</td><td>Mitac MX110HD2</td><td></td>
    </TR>
    <TR>
        <TD>13:00-19:00</TD><td></td><td>kevin.kung</td><td></td><td></td><td></td><td>Allen.tan</td><td></td>
    </TR>
    <TR>
        <TD>19:00-23:00</TD><td></td><td></td><td>Allen.tan</td><td></td><td>Allen.tan</td><td></td><td>Allen.tan</td>
    </TR>
    <TR class="datecolor" style="background-color: black">
        <TD>Week3</TD ><td>14</td><td>15</td><td>16</td><td>17</td><td>18</td><td>19</td><td>20</td>
    </TR>
    <TR>
        <TD>09:00-12:00</TD><td></td><td></td><td></td><td></td><td>Allen.tan</td><td>Allen.tan</td><td></td>
    </TR>
    <TR>
        <TD>13:00-19:00</TD><td></td><td>kevin.kung</td><td></td><td></td><td></td><td>Allen.tan</td><td></td>
    </TR>
    <TR>
        <TD>19:00-23:00</TD><td></td><td></td><td>Allen.tan</td><td></td><td>Allen.tan</td><td></td><td>Cooper.Liu</td>
    </TR>
    <TR class="datecolor" style="background-color: black">
        <TD>Week4</TD ><td>21</td><td>22</td><td>23</td><td>24</td><td>25</td><td>26</td><td>27</td>
    </TR>
    <TR>
        <TD>09:00-12:00</TD><td></td><td></td><td></td><td></td><td>Allen.tan</td><td>Allen.tan</td><td></td>
    </TR>
    <TR>
        <TD>13:00-19:00</TD><td></td><td>kevin.kung</td><td></td><td></td><td></td><td>Allen.tan</td><td></td>
    </TR>
    <TR>
        <TD>19:00-23:00</TD><td></td><td></td><td>Allen.tan</td><td></td><td>Allen.tan</td><td></td><td>Allen.tan</td>
    </TR>
    <TR class="datecolor" style="background-color: black">
        <TD>Week5</TD ><td>28</td><td>29</td><td>30</td><td>31</td><td></td><td></td><td></td>
    </TR>
    <TR>
        <TD>09:00-12:00</TD><td></td><td></td><td></td><td></td><td>Allen.tan</td><td>Allen.tan</td><td></td>
    </TR>
    <TR>
        <TD>13:00-19:00</TD><td></td><td>kevin.kung</td><td></td><td></td><td></td><td>Allen.tan</td><td></td>
    </TR>
    <TR>
        <TD>19:00-23:00</TD><td></td><td></td><td>Allen.tan</td><td></td><td>Allen.tan</td><td></td><td>Allen.tan</td>
    </TR>
</TABLE>
    <h1>預約使用</h1>
<p>輸入客戶名稱： <INPUT style="width: 12em" type="text" name="ClientName"> Ex.Intel,Mitac<br>
<p>輸入型號：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <INPUT style="width: 12em" type="text" name="ModeName"> Ex.NR3-123,S5555<br>
<p> 選擇日期&nbsp;<select name="unit">
        <option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">7</option>
        <option value="1">8</option><option value="2">9</option><option value="3">10</option><option value="1">11</option><option value="2">12</option><option value="8">13</option><option value="8">14</option>
        <option value="1">15</option><option value="2">16</option><option value="3">17</option><option value="1">18</option><option value="2">19</option><option value="3">20</option><option value="8">21</option>
        <option value="1">22</option><option value="2">23</option><option value="3">24</option><option value="1">25</option><option value="2">26</option><option value="3">27</option>
        <option value="8">28</option><option value="1">29</option><option value="2">30</option><option value="3">31</option>
        </select>&nbsp;
選擇時段&nbsp;<select name="unit">
        <option value="EMC1">09:00-12:00</option>
        <option value="EMC2">13:00-19:00</option>
        <option value="EMCLAB">19:00-23:00</option>
    </select>&nbsp;<br>
    <p><input type="submit" value="提交申請"></p>
</html>
