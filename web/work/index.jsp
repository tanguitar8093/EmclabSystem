<!DOCTYPE html>
<html lang="en">
    <meta charset="UTF-8">
    <title>Mitac EMCLab</title>
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
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    </head>
<body>
<div>
    <h1>Mitac EMCLab</h1>
    <p><h4>場地預約註冊</h4></p>
    <hr>
    <form action="EmailListServlet2" method="post" >
        <span class="form-group">
        <input type="hidden" name="action" value="add">
        <input type="hidden" name="test" value="no">
        <label>所屬單位：</label>
        <select name="unit">
            <option value="EBU">EBU</option>
            <option value="TBU">TBU</option>
            <option value="CBU">CBU</option>
            <option value="EMCLab">EMCLab</option>
        </select><br>
        <label>姓名：</label>
        <input type="text" name="name" required ><br>
        <label>職稱：</label>
        <input type="text" name="job" required><br>
        <label>設定帳號：</label>
        <input type="text" name="account" required><br>
        <label>設定密碼：</label>
        <input type="password" name="password" required><br>

        <label>Email:</label>
        <input type="email" name="email" required><br>
            <hr>
        <label>&nbsp;</label>
        <input type="submit" class="btn btn-sm btn-success" value="註冊" id="submit">
        <input type="reset" class="btn btn-sm btn-success" value="重新填寫">
        </span>
    </form>
</div>
    <%--//訊息欄效果--%>
    <c:if test="${flag } ">
        <span class="alert alert-danger alert-dismissable" role="alert" >
            <button class="close" type="button" data-dismiss="alert">&times;</button>
           <%
               String wrongMessage = (String) request.getAttribute("wrongMessage");
               out.print("訊息：");
              if (wrongMessage!=null) {
                  out.println(wrongMessage);
              }
                  out.println("已有帳號?<a href=\"LoginServlet\">由此登入</a>");

           %>
            <%
                session.removeAttribute("flag");
            %>
        </span>
    </c:if>
</body>
</html>