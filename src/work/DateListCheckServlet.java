package work;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/work/DateListCheckServlet")
public class DateListCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateDayCheck = (String) request.getAttribute("dateDayCheck");
        String listDateCheck = (String) request.getAttribute("listDateCheck");
        String dateUserCheck = (String) request.getAttribute("dateUserCheck");
        //檢查session
        HttpSession session = request.getSession();
        String accountcheck = (String) session.getAttribute("accountcheck");
        if (accountcheck ==null){
            getServletContext().getRequestDispatcher("/work/LoginServlet").forward(request, response);
            return;
        }
        String power = (String) session.getAttribute("power");
        int TransPower= Integer.parseInt(power);


        //讀取類別
        String inputType = (String) request.getAttribute("inputType");
        if (inputType ==null){
            inputType =request.getParameter("inputType");
        }
        if (inputType==null){
            inputType="normal";
            if (TransPower !=1){
                request.setAttribute("Message","權限不足!!");
                getServletContext().getRequestDispatcher("/work/MainServlet").forward(request, response);
                return;
            }
        }
        int searchId =0;
        if (inputType.equals("SearchEdit")) {
            searchId = Integer.parseInt(request.getParameter("searchId"));
            //紀錄搜尋參數
            String readtestItem=   request.getParameter("readtestItem");
            String readdate1=   request.getParameter("readdate1");
            String readdate2=  request.getParameter("readdate2");
            String readwhitchType=  request.getParameter("readwhitchType");
            String readsearchWord=  request.getParameter("readsearchWord");
            String whitchForm=  request.getParameter("whitchForm");

            if (readtestItem ==null){
                readtestItem= (String) request.getAttribute("readtestItem");
                readdate1= (String) request.getAttribute("readdate1");
                readdate2= (String) request.getAttribute("readdate2");
                readwhitchType= (String) request.getAttribute("readwhitchType");
                readsearchWord= (String) request.getAttribute("readsearchWord");
                whitchForm= (String) request.getAttribute("whitchForm");
            }
            request.setAttribute("whitchForm",whitchForm);
            request.setAttribute("testItem",readtestItem);
            request.setAttribute("date1",readdate1);
            request.setAttribute("date2",readdate2);
            request.setAttribute("whitchType",readwhitchType);
            request.setAttribute("searchWord",readsearchWord);

            request.setAttribute("inputType",inputType);
            request.setAttribute("searchId",searchId);
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL ="jdbc:mysql://128.199.212.17:3306/guitar";
            String username="guitar";
            String password="";
            Connection collection = DriverManager.getConnection(dbURL, username, password);

            Statement statement = collection.createStatement();
            ResultSet resultSet = null;
            if (listDateCheck !=null || searchId !=0){
                if (inputType.equals("replace")) {
                    resultSet = statement.executeQuery("SELECT * FROM DateShowList WHERE listDate='" + listDateCheck + "' AND dateDay='" +
                            dateDayCheck + "' AND dateUser='" + dateUserCheck + "' ");
                }
                if (inputType.equals("SearchEdit")){
                    resultSet = statement.executeQuery("SELECT * FROM DateShowList WHERE USERID='" + searchId + "' ");
                }
            }else {
                resultSet = statement.executeQuery("SELECT * FROM DateShowListCheck");
            }

            //resultSet 指向 特別的物件 next > false 離開迴圈
                List<DateParament> userList =new ArrayList<>();
            while (resultSet.next()){
            String dateDay = resultSet.getString("dateDay");
            String dateUser = resultSet.getString("dateUser");
            String dateCase = resultSet.getString("dateCase");
            int dateYear = Integer.parseInt(resultSet.getString("dateYear"));
            int dateMonth = Integer.parseInt(resultSet.getString("dateMonth"));
            String listDate = resultSet.getString("listDate");
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

            request.setAttribute("result", userList);
            request.setAttribute("inputType",inputType);
            request.setAttribute("dateDayCheck",dateDayCheck);
            request.setAttribute("listDateCheck",listDateCheck);
            request.setAttribute("dateUserCheck",dateUserCheck);
            getServletContext().getRequestDispatcher("/work/DateListCheck.jsp").forward(request, response);

        } catch (SQLException e){
            for (Throwable t: e){
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
