<%--
  Created by IntelliJ IDEA.
  User: msiTek
  Date: 2017/12/11
  Time: 下午 01:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<link rel="stylesheet" href="style/main.css" type="text/css"/>
<style>
    div {
        /*width: 800px;*/
        /*height: 400px;*/
        position: absolute;
        top: 25%;
        left: 55%;
        margin: -150px 0 0 -300px;
    }
    hr{
        border:none;
        border-top:dashed 1px #CCC;
        height:1px;
        color:#FFFFFF;
        margin: 0 6 0 6;
    }
</style>
<meta name="viewport" content="width=device-width;   initial-scale=0.5 "; >
<html>
<head>

    <meta charset="UTF-8">
    <title>註冊資訊</title>

</head>
<body>
<div>
    <h1>Mitac EMCLab </h1>
    <hr>
    <p><h4>註冊資訊 :</h4></p>

    <label>單位</label>
    <span>
         <!--user.getFirstName-->
        ${user2.unit}
    </span><br/>
            <!--user.getlastName-->
    <label>姓名</label>
    <span>
        ${user2.name}
    </span><br/>

    <label>職稱</label>
    <span>
        ${user2.job}
    </span><br/>

    <label>帳號</label>
    <span>
        ${user2.account}
    </span><br/>

    <label>密碼</label>
    <span>
        ${user2.password}
    </span><br/>

    <label>Email</label>
    <span>
        ${user2.email}
    </span><br/>
<br><hr><br>
    <form action="EmailListServlet2" method="post">
        <input type="hidden" name="test" value="ok">
        <input type="hidden" name="action" value="add">
        <input type="hidden" name="unit" value="${user2.unit}">
        <input type="hidden" name="name" value="${user2.name}">
        <input type="hidden" name="job" value="${user2.job}">
        <input type="hidden" name="account" value="${user2.account}">
        <input type="hidden" name="password" value="${user2.password}">
        <input type="hidden" name="email" value="${user2.email}">
        <input type="submit"  class="btn btn-sm btn-success" value="確定註冊">
        <input type="button"  class="btn btn-sm btn-success" value="重新填寫" onclick="location.href='index.jsp'">
    </form>
</div>
</body>
</html>
