package work;

import test.time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/work/DateSubmitServlet")
public class DateSubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String listDate=request.getParameter("listDate");
        LocalDate localDate = LocalDate.now();
        int  dateMonth = Integer.parseInt(listDate.substring(5,7));
        int  dateYear = localDate.getYear();
        LocalDate lastday = LocalDate.of(dateYear,dateMonth,1).with(TemporalAdjusters.lastDayOfMonth());
        int gg =lastday.getDayOfMonth();
        int today =LocalDate.now().getDayOfMonth();
        int date1 =Integer.parseInt(listDate.substring(8,10));
        int year=Integer.parseInt(listDate.substring(0,4));
        int date2 =Integer.parseInt(request.getParameter("date2"));
        String now=localDate+"";
        String dateDay=date1+"-"+date2;
        String dateUser =request.getParameter("dateUser");
        String dateCase =request.getParameter("dateCase");
        String unit =request.getParameter("unit");
        String modeName =request.getParameter("modeName");
        String clientName =request.getParameter("clientName");
        String testItem2 =request.getParameter("testItem2");
        //產生申請時間
        Date current=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String bookTime=sdf.format(current);
        //判斷日期合法
//            if ( (dateYear==year &&  (date1 > gg || date1 < today)) || dateYear>year || (dateYear ==year && dateMonth> ) {
//                request.setAttribute("DateWrong", "日期錯誤！");
//                getServletContext().getRequestDispatcher("/work/CheckStringServlet").forward(request, response);
//                return;
//            }
        //讀取權限session
        //檢查session
        HttpSession session = request.getSession();
        String accountcheck = (String) session.getAttribute("accountcheck");
        if (accountcheck ==null){
            getServletContext().getRequestDispatcher("/work/LoginServlet").forward(request, response);
            return;
        }
        String power = (String) session.getAttribute("power");
        int TransPower= Integer.parseInt(power);
        //讀取場地紀錄
        String whatString =request.getParameter("whatString");
        //檢查日期
        boolean result = false;
        try {
            result = new time().compare(now,listDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (TransPower !=1 && result == false) {
            request.setAttribute("DateWrong", "日期錯誤！");
            getServletContext().getRequestDispatcher("/work/CheckStringServlet").forward(request, response);
            return;
        }
        //判斷時段是否已使用
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL ="jdbc:mysql://128.199.212.17:3306/guitar";
            String username="guitar";
            String password="";
            Connection collection = DriverManager.getConnection(dbURL, username, password);

            Statement statement = collection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT dateDay,dateUser,dateCase,testItem2 FROM DateShowList "+"WHERE testItem2='"+testItem2+"' AND " +
                    "listDate='"+listDate+"' ");
            while (resultSet.next()){
                String dateDayCheck=resultSet.getString("dateDay");
                String dateUserCheck =resultSet.getString("dateUser");
                if (TransPower!=1 && dateDay.equals(dateDayCheck)){
                    request.setAttribute("DateWrong","使用者:"+dateUserCheck+"已預定該時段！");
                   getServletContext().getRequestDispatcher("/work/CheckStringServlet").forward(request, response);
                    return;
                }else if (TransPower ==1 && dateDay.equals(dateDayCheck)){
                    request.setAttribute("inputType","replace");
                    request.setAttribute("dateDayCheck",dateDayCheck);
                    request.setAttribute("dateUserCheck",dateUserCheck);
                    request.setAttribute("listDateCheck",listDate);
                    request.setAttribute("whatString",whatString);
                    request.setAttribute("DateWrong","使用者:"+dateUserCheck+"已於"+listDate+"預定該時段，請先刪除資料");
                    getServletContext().getRequestDispatcher("/work/DateListCheckServlet").forward(request, response);
                    return;
                }
            }
            dateYear= Integer.parseInt(listDate.substring(0,4));
            System.out.println(dateYear);
            DateParament dateParament = new DateParament(dateDay,dateUser,dateCase,dateYear,dateMonth,listDate,bookTime,unit,modeName,clientName,testItem2);

            //過濾管理者OR一般使用者
            if (TransPower !=1){
                request.setAttribute("inputType","normal");
            insertToDb(dateParament);
            }else {
                GMToDb(dateParament);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        申請完成訊息設定
        request.setAttribute("Message","場地申請完成，請等待審核!!");
        if (TransPower ==1){
            request.setAttribute("Message","場地預約成功!!");
        }


        getServletContext().getRequestDispatcher("/work/MainServlet").forward(request, response);
    }
    //檢查日期大小
    public boolean compare(String time1,String time2) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date a=sdf.parse(time1);
        Date b=sdf.parse(time2);
        if(a.before(b) || a.equals(b))
            return true;
        else
            return false;
    }

    //寫入資料庫
    private void insertToDb(DateParament dateParament) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String dbURL = "jdbc:mysql://128.199.212.17:3306/guitar";
        String username = "guitar";
        String password = "";
        Connection collection = DriverManager.getConnection(dbURL, username, password);
        Statement statement = collection.createStatement();

        String insertStm = String.format("INSERT INTO DateShowListCheck(dateDay,dateUser,dateCase,dateYear,dateMonth,listDate,bookTime,unit,modeName,clientName,testItem2)" +
                        "Values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                dateParament.getDateDay(),dateParament.getDateUser(),dateParament.getDateCase(),dateParament.getDateYear(),dateParament.getDateMonth(),
                dateParament.getListDate(),dateParament.getBookTime(),dateParament.getUnit(),dateParament.getModeName(),dateParament.getClientName(),
                dateParament.getTestItem2());
        statement.executeUpdate(insertStm);
    }
    //管理者直接寫入
    private void GMToDb(DateParament dateParament) throws ClassNotFoundException, SQLException {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
