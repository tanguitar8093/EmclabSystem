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

@WebServlet("/work/WorklistCheckServlet")
public class WorklistCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //檢查session
        HttpSession session = request.getSession();
        String accountcheck = (String) session.getAttribute("accountcheck");
        if (accountcheck ==null){
            getServletContext().getRequestDispatcher("/work/LoginServlet").forward(request, response);
            return;
        }
        String power = (String) session.getAttribute("power");
        int TransPower= Integer.parseInt(power);
        //刪除重複參數
        String wrongMessege= (String) request.getAttribute("wrongMessege");
        System.out.println(wrongMessege);
        int ListID =0;
        if (wrongMessege!= null){
            ListID = (int) request.getAttribute("ListID");
            System.out.println(ListID);
            request.setAttribute("wrongMessege",wrongMessege);
        }
        String run = (String) request.getAttribute("run");
        if (run ==null){
            run="yes";
        }
        request.setAttribute("run",run);
        System.out.println(run);
        //紀錄搜尋參數
        int searchId =0;
        String inputType ="SearchEdit2";

        if (wrongMessege ==null) {
            searchId = Integer.parseInt(request.getParameter("searchId"));
            request.setAttribute("searchId",searchId);
        }
        String readtestItem=   request.getParameter("readtestItem");
        String readdate1=   request.getParameter("readdate1");
        String readdate2=  request.getParameter("readdate2");
        String readwhitchType=  request.getParameter("readwhitchType");
        String readsearchWord=  request.getParameter("readsearchWord");
        String whitchForm=  request.getParameter("whitchForm");
        if (readtestItem ==null ||readtestItem.length() ==0){
            readtestItem= (String) request.getAttribute("readtestItem");
            readdate1= (String) request.getAttribute("readdate1");
            readdate2= (String) request.getAttribute("readdate2");
            readwhitchType= (String) request.getAttribute("readwhitchType");
            readsearchWord= (String) request.getAttribute("readsearchWord");
            whitchForm= (String) request.getAttribute("whitchForm");
            System.out.println(readdate1+"a"+readdate2+"a"+readsearchWord+"a"+readtestItem+"a"+readwhitchType+"a"+whitchForm+"a"+inputType+"a");
        }
        request.setAttribute("whitchForm",whitchForm);
        request.setAttribute("testItem",readtestItem);
        request.setAttribute("date1",readdate1);
        request.setAttribute("date2",readdate2);
        request.setAttribute("whitchType",readwhitchType);
        request.setAttribute("searchWord",readsearchWord);
        request.setAttribute("inputType",inputType);
        //WorkList表
        int dateYear = 0;
        int dateMonth = 0;
        String listDate = null;
        String modeName = null;
        String clientName = null;
        String unit = null;
        String testItem = null;
        String testItem2 = null;
        String operatorName = null;
        int testTimeStart1 = 0;
        int testTimeStart2 = 0;
        int testTimeEnd1 = 0;
        int testTimeEnd2 =0;
        String testFileName = null;
        String remark = null;
        String server = null;
        Double TC = 0.0;
        //讀取資料
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL ="jdbc:mysql://128.199.212.17:3306/guitar";
            String username="guitar";
            String password="";
            Connection collection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = collection.createStatement();

            ResultSet resultSet = null;
            if (wrongMessege !=null){
                searchId =ListID;
                request.setAttribute("searchId",searchId);
            }
                resultSet = statement.executeQuery("SELECT * FROM WorklistData WHERE ListID='" + searchId + "' ");
            List<WLU> userList =new ArrayList<>();
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
                WLU wlu = new WLU(operatorName, listDate, dateYear, dateMonth, testTimeStart1, testTimeStart2, testTimeEnd1, testTimeEnd2, clientName, modeName, testFileName, remark, server, testItem,testItem2,unit,TC);
                userList.add(wlu);
            }
            request.setAttribute("result", userList);
            getServletContext().getRequestDispatcher("/work/WorklistCheck.jsp").forward(request, response);
        }catch (SQLException e){
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
