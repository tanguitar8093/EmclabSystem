<%--
  Created by IntelliJ IDEA.
  User: msiTek
  Date: 2018/1/23
  Time: 下午 04:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    import java.time.LocalDate;
    LocalDate localDate = LocalDate.now();

    System.out.println("--Month--");
    System.out.println(localDate.getMonth().getValue());

    System.out.println("--Year--");
    System.out.println(localDate.getYear());

    System.out.println("--Day Of Week--");
    LocalDate firstDayOfMonth =
    LocalDate.of(2016,12, 1);
    System.out.println(firstDayOfMonth.getDayOfWeek().getValue());
    }
    }
%>
</body>
</html>
