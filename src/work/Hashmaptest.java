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

@WebServlet("/work/Hashmaptest")
//讀取jsp參數 再用參數載入資料庫搜尋讀取要用的資料

public class Hashmaptest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //項目分類
     String testItem2 = (String) request.getAttribute( "testItem2");
     //讀取時間參數
       int whatYear = (int) request.getAttribute("whatYear");
       int whatMonth = (int) request.getAttribute("whatMonth");
//        DateUse dateUse = new DateUse(dateDay,dateUser,dateCase);
        try {
//          insertToDb(dateUse);
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL ="jdbc:mysql://128.199.212.17:3306/guitar";
            String username="guitar";
            String password="";
            Connection collection = DriverManager.getConnection(dbURL, username, password);

            Statement statement = collection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT dateDay,dateUser,dateCase,testItem2,modeName,clientName FROM DateShowList "+"WHERE dateYear='"+whatYear+"' AND dateMonth='"+whatMonth+"'" +
                    "AND testItem2='"+testItem2+"' ");
            //resultSet 指向 特別的物件 next > false 離開迴圈
            List<DateUse> userList =new ArrayList<>();
            while (resultSet.next()){
                String dateDayin=resultSet.getString("dateDay");
                String dateUserin =resultSet.getString("dateUser");
                String dateCasein =resultSet.getString("dateCase");
                String modeNamein =resultSet.getString("modeName");
                String clientNamein =resultSet.getString("clientName");
                DateUse listin =new DateUse(dateDayin,dateUserin,dateCasein,modeNamein,clientNamein);
                userList.add(listin);
            }

            request.setAttribute("result", userList);
            getServletContext().getRequestDispatcher("/work/TABLETEST.jsp").forward(request, response);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    private void insertToDb(DateUse dateUse) throws ClassNotFoundException,SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String dbURL ="jdbc:mysql://128.199.212.17:3306/guitar?useUnicode=yes&characterEncoding=UTF-8";
        String username="guitar";
        String password="";
        Connection collection = DriverManager.getConnection(dbURL, username, password);
        Statement statement = collection.createStatement();

        String insertStm =String.format("INSERT INTO Date201801 (dateDay,dateUser,dateCase) " +
                        "Values('%s','%s','%s')",
                dateUse.getDateDay(),dateUse.getDateUser(),dateUse.getDateCase());
        statement.executeUpdate(insertStm);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}

