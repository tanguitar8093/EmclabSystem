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

@WebServlet("/work/DateListOkServlet")
public class DateListOkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String DateUser = request.getParameter("DateUser");
        String DateCase = request.getParameter("DateCase");
        String ClientName = request.getParameter("ClientName");
        String ModeName = request.getParameter("ModeName");
        String Unit = request.getParameter("Unit");
        String actionreason = request.getParameter("actionreason");
        String checkaction = request.getParameter("checkaction");
        String inputType = request.getParameter("inputType");
        //讀取場地紀錄
        String whatString =request.getParameter("whatString");
        //讀取搜尋參數
        String whitchForm =request.getParameter("whitchForm");
        String testItem=request.getParameter("testItem");
        String date1=request.getParameter("date1");
        String date2=request.getParameter("date2");
        String whitchType=request.getParameter("whitchType");
        String searchWord=request.getParameter("searchWord");
        String readinputType=request.getParameter("readinputType");
        int searchId=0;
        if (whitchForm !=null) {
            searchId = Integer.parseInt(request.getParameter("searchId"));
        }
        if (checkaction ==null){
            checkaction="nothing";
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://128.199.212.17:3306/guitar";
            String username = "guitar";
            String password = "";
            Connection collection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = collection.createStatement();
            //更新資料
            String InsertStm=null;
            if (inputType ==null){
            InsertStm =String.format("Update DateShowListCheck SET dateUser ='%s',dateCase='%s',clientName='%s',modeName='%s',unit='%s'  Where USERID=%s"
                    ,DateUser,DateCase,ClientName,ModeName,Unit,id);
                request.setAttribute("DateWrong","資料更新成功!!");
            }else if (inputType.equals("inputDateShowList")){
                InsertStm =String.format("Update DateShowList  SET dateUser ='%s',dateCase='%s',clientName='%s',modeName='%s',unit='%s'  Where USERID=%s"
                        ,DateUser,DateCase,ClientName,ModeName,Unit,id);
                request.setAttribute("DateWrong","資料更新成功!!");
            }

            statement.executeUpdate(InsertStm);
            //寫入資料
            DateParament dateParament = null;
            if (checkaction.equals("ok")) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM DateShowListCheck WHERE USERID=" + id);
                while (resultSet.next()) {
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

                    dateParament = new DateParament(dateDay, dateUser, dateCase, dateYear, dateMonth, listDate, bookTime, unit, modeName, clientName, testItem2);
                    dateParament.setId(userid);
                }
                request.setAttribute("dateParament", dateParament);
                insertToDb(dateParament);
                int resultSet2 = statement.executeUpdate("DELETE FROM DateShowListCheck WHERE USERID=" + id);
                //由此插入傳送被拒絕使用者訊息
                int resultSet3 = statement.executeUpdate("DELETE FROM DateShowListCheck WHERE listDate='" + dateParament.getListDate() + "' AND dateDay='" + dateParament.getDateDay()
                        + "' AND testItem2='" + dateParament.getTestItem2() + "' ");
                request.setAttribute("DateWrong", dateParament.getDateUser()+"成功通過審核，系統自動刪除同時段申請人!!");
            }
            if (whatString != null && checkaction.equals("nothing") ){
                request.setAttribute("whatString",whatString);
                request.setAttribute("DateWrong","資料更新成功!!");
                getServletContext().getRequestDispatcher("/work/CheckStringServlet").forward(request, response);
                return;
            }
        if (whatString != null && checkaction.equals("no") && inputType.equals("inputDateShowList")){
            int resultSet4 = statement.executeUpdate("DELETE FROM DateShowList WHERE USERID=" + id);
            request.setAttribute("whatString",whatString);
            request.setAttribute("DateWrong","刪除成功!!");
            getServletContext().getRequestDispatcher("/work/CheckStringServlet").forward(request, response);
            return;
        }
        if (inputType ==null && checkaction.equals("no")){
            int resultSet5 = statement.executeUpdate("DELETE FROM DateShowListCheck WHERE USERID=" + id);
            request.setAttribute("DateWrong","刪除成功!!");
        }
        if ((whatString == null && checkaction.equals("no")) && inputType!=null){
            int resultSet6 = statement.executeUpdate("DELETE FROM DateShowList WHERE USERID=" + id);
            request.setAttribute("DateWrong","刪除成功!!");
        }
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (whitchForm !=null){
            request.setAttribute("whitchForm",whitchForm);
            request.setAttribute("readtestItem",testItem);
            request.setAttribute("readdate1",date1);
            request.setAttribute("readdate2",date2);
            request.setAttribute("readwhitchType",whitchType);
            request.setAttribute("readsearchWord",searchWord);
            request.setAttribute("inputType",readinputType);
            request.setAttribute("searchId",searchId);
        }

        getServletContext().getRequestDispatcher("/work/DateListCheckServlet").forward(request, response);
    }
    private void insertToDb(DateParament dateParament) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String dbURL = "jdbc:mysql://128.199.212.17:3306/guitar";
        String username = "guitar";
        String password = "";
        Connection collection = DriverManager.getConnection(dbURL, username, password);
        Statement statement = collection.createStatement();

        String insertStm = String.format("INSERT INTO DateShowList(dateDay,dateUser,dateCase,dateYear,dateMonth,listDate,bookTime,unit,modeName,clientName,testItem2)" +
                        "Values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                dateParament.getDateDay(),dateParament.getDateUser(),dateParament.getDateCase(),dateParament.getDateYear(),dateParament.getDateMonth(),
                dateParament.getListDate(),dateParament.getBookTime(),dateParament.getUnit(),dateParament.getModeName(),dateParament.getClientName(),
                dateParament.getTestItem2());

        statement.executeUpdate(insertStm);
    }
    private void modifyDb(DateParament dateParament) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String dbURL = "jdbc:mysql://128.199.212.17:3306/guitar";
        String username = "guitar";
        String password = "";
        Connection collection = DriverManager.getConnection(dbURL, username, password);
        Statement statement = collection.createStatement();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}