<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <meta charset="UTF-8">
        <title>EMCLAB 登入</title>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="style/login.css" media="screen and (min-device-width: 500px)" type="text/css"/>
    <link rel="stylesheet" href="style/login2.css" media="screen and (max-device-width: 500px)" type="text/css"/>
    <meta name="viewport" content="width=device-width;  height=device-height;  initial-scale=0.75 "; >
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<div>
    <img src="photo/mitac_logo_.jpg" alt="mitac" />
    <br>
    <h1>
        EMCLab 場地預約系統
    </h1>
        <form action="LoginServlet" method="post">
            <label>帳號：</label>
            <%--<input type="text" name="accountcheck"  required ><br>--%>
            <input type="text" class="form-control" aria-describedby="basic-addon1" name="accountcheck" required><br>
            <label>密碼：</label>
            <%--<input type="password" name="passwordcheck"  required><br>--%>
                <input type="password" class="form-control" name="passwordcheck" aria-describedby="basic-addon1" required><br>

    <label>&nbsp;</label><span>
    <input type="submit" class="btn btn-sm btn-success"name="in" value="登入"required>
    <input type="button" class="btn btn-sm btn-success" value="註冊" onclick="location.href='index.jsp'">
    <input type=checkbox value="autologin" name="remember" >自動登入
    </span><br>
    </form>
    <br>
</div>
    <%
        String Messege = (String) request.getAttribute("messege");
        if (Messege != null){
            out.print(" <c:if test=\"${flag }\">");
            out.print("  <span class=\"alert alert-danger alert-dismissable\" role=\"alert\">");
            out.print(" <button class=\"close\" type=\"button\" data-dismiss=\"alert\">&times;</button>");
            out.print("訊息："+Messege);
            out.print("</span>");
            out.print("</c:if>");
        }
    %>
</body>
</html>