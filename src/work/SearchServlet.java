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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/work/SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String whitchForm = request.getParameter("whitchForm");
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
            request.setAttribute("power",TransPower);
            request.setAttribute("accountcheck",accountcheck);
        //檢查讀取
        if (whitchForm == null){
            getServletContext().getRequestDispatcher("/work/search.jsp").forward(request, response);
            return;
        }
        String testItem = request.getParameter("testItem");
        String testItemSearch =testItem;
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        String whitchType = request.getParameter("whitchType");
        String searchWord = request.getParameter("searchWord");
        //紀錄搜尋參數
        request.setAttribute("whtichForm",whitchForm);
        request.setAttribute("searchStatus","ok");
        request.setAttribute("readtestItem",testItem);
        request.setAttribute("readdate1",date1);
        request.setAttribute("readdate2",date2);
        request.setAttribute("readwhitchType",whitchType);
        request.setAttribute("readsearchWord",searchWord);

        int Year = Integer.parseInt(date1.substring(0, 4));
        int Month = Integer.parseInt(date1.substring(5, 7));
        int Day = Integer.parseInt(date1.substring(8, 10));

        //預訂表參數
        String dateDay = null;
        String dateUser = null;
        String dateCase = null;
        int dateYear = 0;
        int dateMonth = 0;
        String listDate = null;
        String bookTime = null;
        String modeName = null;
        String clientName = null;
        String testItem2 = null;
        Long userid = null;
        String unit = null;
        //WorkList表
        String operatorName = null;
        int testTimeStart1 = 0;
        int testTimeStart2 = 0;
        int testTimeEnd1 = 0;
        int testTimeEnd2 =0;
        String testFileName = null;
        String remark = null;
        String server = null;
        Double TC = 0.0;
        //檢查日期
        boolean result = false;
        try {
            result = new time().compare(date1, date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (result == false) {
            request.setAttribute("wrongMessage", "日期錯誤!");
            getServletContext().getRequestDispatcher("/work/search.jsp").forward(request, response);
            return;
        }

        if (searchWord.equals("") && !whitchType.equals("alltype")) {
            request.setAttribute("wrongMessage", "請輸入關鍵字!");
            getServletContext().getRequestDispatcher("/work/search.jsp").forward(request, response);
            return;
        }
        //取得時間長度
        SimpleDateFormat TimeFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date TimeStart = null;
        Date TimeEnd = null;
        try {
            TimeStart = TimeFormat.parse(date1);
            TimeEnd = TimeFormat.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int TimeLength = (int) ((TimeEnd.getTime() - TimeStart.getTime()) / (1000 * 60 * 60 * 24));
        //讀取時間
        Calendar calendar = Calendar.getInstance();
        calendar.set(Year, Month - 1, Day - 1);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://128.199.212.17:3306/guitar";
            String username = "guitar";
            String password = "";
            Connection collection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = collection.createStatement();
            ResultSet resultSet=null;
                List<DateParament> userList =new ArrayList<>();
                List<WLU> userList2 =new ArrayList<>();


            for (int i = 0; i <= TimeLength; i++) {
                calendar.add(Calendar.DATE, +1);
                String ListDate =TimeFormat.format(calendar.getTime());
                //DateShowList
                System.out.println(TimeLength+"a"+i+"b"+ListDate);
                if (whitchForm.equals("DateShowList") ) {
                    System.out.println(listDate);
                    if (whitchType.equals("alltype") && testItemSearch.equals("All-Site")) {
                        resultSet = statement.executeQuery("SELECT * FROM DateShowList " + "WHERE listDate='" + ListDate + "' ");
                        } else if (whitchType.equals("alltype") && !testItemSearch.equals("All-Site")){
                        resultSet = statement.executeQuery("SELECT * FROM DateShowList " + "WHERE listDate='" + ListDate+ "' AND testItem2='"+testItemSearch+"' ");
                    } else if (!whitchType.equals("alltype") && testItem.equals("All-Site")){
                resultSet = statement.executeQuery("SELECT * FROM DateShowList " + "WHERE listDate='" + ListDate+"' AND "+whitchType+" like '"+searchWord+"%' ");
                    } else if (!whitchType.equals("alltype") && !testItemSearch.equals("All-Site")) {
                        resultSet = statement.executeQuery("SELECT * FROM DateShowList " + "WHERE listDate='" + ListDate+ "' AND testItem2='"+testItemSearch+"' AND "+whitchType+" like '"+searchWord+"%' ");
                    }
                        while (resultSet.next()) {
                            dateDay = resultSet.getString("dateDay");
                            dateUser = resultSet.getString("dateUser");
                            dateCase = resultSet.getString("dateCase");
                            dateYear = Integer.parseInt(resultSet.getString("dateYear"));
                            dateMonth = Integer.parseInt(resultSet.getString("dateMonth"));
                            listDate = resultSet.getString("listDate");
                            bookTime = resultSet.getString("bookTime");
                            unit = resultSet.getString("unit");
                            modeName = resultSet.getString("modeName");
                            clientName = resultSet.getString("clientName");
                            testItem2 = resultSet.getString("testItem2");
                            userid = resultSet.getLong("USERID");

                            DateParament dateParament =new DateParament(dateDay, dateUser, dateCase,dateYear,dateMonth,listDate,bookTime,unit,modeName,clientName,testItem2);
                            dateParament.setId(userid);
                            userList.add(dateParament);
                        }

                }
                //WorkSheet
                if (whitchForm.equals("WorklistData")){
                    System.out.println(whitchType+"aa"+testItemSearch);
                    if (whitchType.equals("alltype") && testItemSearch.equals("All-Site")) {
                        System.out.println(TimeLength+"aa"+i+"b"+ListDate);
                        resultSet = statement.executeQuery("SELECT * FROM WorklistData " + "WHERE listDate='" + ListDate + "' ");
                    }else if ((whitchType.equals("alltype") && testItemSearch.equals("S0-RE")) ||(whitchType.equals("alltype") && testItemSearch.equals("S0-CE"))
                            ||(whitchType.equals("alltype") && testItemSearch.equals("S1")) || (whitchType.equals("alltype") && testItemSearch.equals("S2"))
                            ||(whitchType.equals("alltype") && testItemSearch.equals("S3")) || (whitchType.equals("alltype") && testItemSearch.equals("S4"))
                            ||(whitchType.equals("alltype") && testItemSearch.equals("966"))){
                        System.out.println(TimeLength+"bb"+i+"b"+ListDate+testItem);
                        resultSet = statement.executeQuery("SELECT * FROM WorklistData " + "WHERE listDate='" + ListDate + "' AND testItem2='"+testItemSearch+"' ");
                    }else if ((whitchType.equals("alltype") && !testItemSearch.equals("S0-RE")) ||(whitchType.equals("alltype") && !testItemSearch.equals("S0-CE"))
                            ||(whitchType.equals("alltype") && !testItemSearch.equals("S1")) || (whitchType.equals("alltype") && !testItemSearch.equals("S2"))
                            ||(whitchType.equals("alltype") && !testItemSearch.equals("S3")) || (whitchType.equals("alltype") && !testItemSearch.equals("S4"))
                            ||(whitchType.equals("alltype") && !testItemSearch.equals("966"))|| (whitchType.equals("alltype") && !testItemSearch.equals("All-Site"))){
                        System.out.println(TimeLength+"cc"+i+"b"+ListDate);
                        resultSet = statement.executeQuery("SELECT * FROM WorklistData " + "WHERE listDate='" + ListDate + "' AND testItem='"+testItemSearch+"' ");
                    }else  if (!whitchType.equals("alltype") && testItemSearch.equals("All-Site")) {
                        System.out.println(TimeLength+"dd"+i+"b"+ListDate);
                        resultSet = statement.executeQuery("SELECT * FROM WorklistData " + "WHERE listDate='" + ListDate+"' AND "+whitchType+" like '"+searchWord+"%' ");
                    }else if ((!whitchType.equals("alltype") && testItemSearch.equals("S0-RE")) ||(!whitchType.equals("alltype") && testItemSearch.equals("S0-CE"))
                            ||(!whitchType.equals("alltype") && testItemSearch.equals("S1")) || (!whitchType.equals("alltype") && testItemSearch.equals("S2"))
                            ||(!whitchType.equals("alltype") && testItemSearch.equals("S3")) || (!whitchType.equals("alltype") && testItemSearch.equals("S4"))
                            ||(!whitchType.equals("alltype") && testItemSearch.equals("966"))){
                        System.out.println(TimeLength+"ee"+i+"b"+ListDate);
                        resultSet = statement.executeQuery("SELECT * FROM WorklistData " + "WHERE listDate='" + ListDate+ "' AND testItem2='"+testItemSearch+"' AND "+whitchType+" like '"+searchWord+"%' ");
                    }else if ((!whitchType.equals("alltype") && !testItemSearch.equals("S0-RE")) ||(!whitchType.equals("alltype") && !testItemSearch.equals("S0-CE"))
                            ||(!whitchType.equals("alltype") && !testItemSearch.equals("S1")) || (!whitchType.equals("alltype") && !testItemSearch.equals("S2"))
                            ||(!whitchType.equals("alltype") && !testItemSearch.equals("S3")) || (!whitchType.equals("alltype") && !testItemSearch.equals("S4"))
                            ||(!whitchType.equals("alltype") && !testItemSearch.equals("966"))||(!whitchType.equals("alltype") && !testItemSearch.equals("All-Site")) ){
                        System.out.println(TimeLength+"ff"+i+"b"+ListDate);
                        resultSet = statement.executeQuery("SELECT * FROM WorklistData " + "WHERE listDate='" + ListDate+ "' AND testItem='"+testItem+"' AND "+whitchType+" like '"+searchWord+"%' ");
                    }
                    while (resultSet.next()){
                        operatorName = resultSet.getString("operatorName");
                        listDate = resultSet.getString("listDate");
                        dateYear = Integer.parseInt(resultSet.getString("dateYear"));
                        dateMonth = Integer.parseInt(resultSet.getString("dateMonth"));
                        testTimeStart1 = Integer.parseInt(resultSet.getString("testTimeStart1"));
                        testTimeStart2 = Integer.parseInt(resultSet.getString("testTimeStart2"));
                        testTimeEnd1 = Integer.parseInt(resultSet.getString("testTimeEnd1"));
                        testTimeEnd2 = Integer.parseInt(resultSet.getString("testTimeEnd2"));
                        clientName = resultSet.getString("clientName");
                        modeName = resultSet.getString("modeName");
                        testFileName = resultSet.getString("testFileName");
                        remark = resultSet.getString("remark");
                        server = resultSet.getString("server");
                        testItem = resultSet.getString("testItem");
                        testItem2 = resultSet.getString("testItem2");
                        unit = resultSet.getString("unit");
                        TC = Double.valueOf(resultSet.getString("TC"));
                        userid = resultSet.getLong("ListID");

                        WLU wlu = new WLU(operatorName, listDate, dateYear, dateMonth, testTimeStart1, testTimeStart2, testTimeEnd1, testTimeEnd2, clientName, modeName, testFileName, remark, server, testItem,testItem2,unit,TC);
                        wlu.setId(userid);
                        userList2.add(wlu);
                    }
                }
            }
            request.setAttribute("result", userList);
            request.setAttribute("result2", userList2);
            getServletContext().getRequestDispatcher("/work/search.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        //待修改
    }
    public int timeLength(String time1, String time2)throws ParseException{
        SimpleDateFormat TimeFormat =new SimpleDateFormat("yyyy-MM-dd");
        Date TimeStart= null;
        Date TimeEnd= null;
        try {
            TimeStart = TimeFormat.parse(time1);
            TimeEnd=TimeFormat.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int TimeLength =(int)((TimeEnd.getTime()-TimeStart.getTime())/(1000*60*60*24));
        return TimeLength;
    }
    private void SearchDb(DateParament dateParament) throws ClassNotFoundException, SQLException {
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
