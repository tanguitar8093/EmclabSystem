package work;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/work/LinkTimeServlet")
public class LinkTimeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String testItem2check =request.getParameter("testItem2");
        String dateDaycheck =request.getParameter("dateDay");
        String whatYear =request.getParameter("whatYear");
        String whatMonth =request.getParameter("whatMonth");
        String whatString =request.getParameter("whatString");
        request.setAttribute("whatString",whatString );
        String listDate="A";
        int tt=0,te=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://128.199.212.17:3306/guitar";
            String username = "guitar";
            String password = "";
            Connection collection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = collection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM DateShowList WHERE dateDay='"+dateDaycheck+"' AND testItem2='"+testItem2check+"' AND dateYear='"+whatYear+"' AND dateMonth='"+whatMonth+"' ");
            List<DateParament> userList =new ArrayList<>();
            while (resultSet.next()){
                String dateDay = resultSet.getString("dateDay");
                String dateUser = resultSet.getString("dateUser");
                String dateCase = resultSet.getString("dateCase");
                int dateYear = Integer.parseInt(resultSet.getString("dateYear"));
                int dateMonth = Integer.parseInt(resultSet.getString("dateMonth"));
                listDate = resultSet.getString("listDate");
                String bookTime = resultSet.getString("bookTime");
                String unit = resultSet.getString("unit");
                String modeName = resultSet.getString("modeName");
                String clientName = resultSet.getString("clientName");
                String testItem2 = resultSet.getString("testItem2");
                Long userid = resultSet.getLong("USERID");

                DateParament dateParament =new DateParament(dateDay, dateUser, dateCase,dateYear,dateMonth,listDate,bookTime,unit,modeName,clientName,testItem2);
                dateParament.setId(userid);
                userList.add(dateParament);
            }
            System.out.println(listDate);
            if (dateDaycheck.substring(dateDaycheck.length()-1).equals("1")){
                tt=9;te=12;
            }
            if (dateDaycheck.substring(dateDaycheck.length()-1).equals("2")){
                tt=13;te=19;
            }
            if (dateDaycheck.substring(dateDaycheck.length()-1).equals("3")){
                tt=19;te=23;
            }
            System.out.println(tt);
            System.out.println(te);
            ResultSet resultSet2 = statement.executeQuery("SELECT * FROM WorklistData WHERE testItem2='"+testItem2check+"' AND listDate='"+listDate+ "' ");
            List<WLU> userList2 =new ArrayList<>();
            while (resultSet2.next()){
                String operatorName = resultSet2.getString("operatorName");
                listDate = resultSet2.getString("listDate");
                int dateYear = Integer.parseInt(resultSet2.getString("dateYear"));
                int dateMonth = Integer.parseInt(resultSet2.getString("dateMonth"));
                int testTimeStart1 = Integer.parseInt(resultSet2.getString("testTimeStart1"));
                int testTimeStart2 = Integer.parseInt(resultSet2.getString("testTimeStart2"));
                int testTimeEnd1 = Integer.parseInt(resultSet2.getString("testTimeEnd1"));
                int testTimeEnd2 = Integer.parseInt(resultSet2.getString("testTimeEnd2"));
                String clientName = resultSet2.getString("clientName");
                String modeName = resultSet2.getString("modeName");
                String testFileName = resultSet2.getString("testFileName");
                String remark = resultSet2.getString("remark");
                String server = resultSet2.getString("server");
                String testItem = resultSet2.getString("testItem");
                String testItem2 = resultSet2.getString("testItem2");
                String unit = resultSet2.getString("unit");
                Double TC = Double.valueOf(resultSet2.getString("TC"));

                WLU wlu = new WLU(operatorName, listDate, dateYear, dateMonth, testTimeStart1, testTimeStart2, testTimeEnd1, testTimeEnd2, clientName, modeName, testFileName, remark, server, testItem,testItem2,unit,TC);
                userList2.add(wlu);
            }
            request.setAttribute("result", userList);
            request.setAttribute("result2", userList2);
            getServletContext().getRequestDispatcher("/work/linktime.jsp").forward(request, response);
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
