package work;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/work/WorklistOkServlet")
public class WorklistOkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String run = request.getParameter("run");
        if (run ==null){
            run="yes";
        }
        request.setAttribute("run",run);
        String id = request.getParameter("id");
        String wrongMessege = request.getParameter("wrongMessege");
        System.out.println(wrongMessege);
        String operatorName = request.getParameter("operatorName");
        String unit = request.getParameter("unit");
        String clientName = request.getParameter("clientName");
        String modeName = request.getParameter("modeName");
        String testFileName = request.getParameter("testFileName");
        String server = request.getParameter("server");
        String remark = request.getParameter("remark");
        String checkaction = request.getParameter("checkaction");
        //紀錄搜尋參數
        int searchId = Integer.parseInt(id);
        String testItem=   request.getParameter("testItem");
        String date1=   request.getParameter("date1");
        String date2=  request.getParameter("date2");
        String whitchType=  request.getParameter("whitchType");
        String searchWord=  request.getParameter("searchWord");
        String whitchForm=  request.getParameter("whitchForm");
        String inputType=  request.getParameter("inputType");
        System.out.println(testItem+"a"+date1+date2+"b"+whitchType+"c"+searchWord+"d"+whitchForm+"e"+inputType+"f");
        request.setAttribute("whitchForm",whitchForm);
        request.setAttribute("readtestItem",testItem);
        request.setAttribute("testItem",testItem);
        request.setAttribute("readdate1",date1);
        request.setAttribute("date1",date1);
        request.setAttribute("readdate2",date2);
        request.setAttribute("date2",date2);
        request.setAttribute("readwhitchType",whitchType);
        request.setAttribute("readsearchWord",searchWord);
        request.setAttribute("whitchType",whitchType);
        request.setAttribute("searchWord",searchWord);
        request.setAttribute("inputType",inputType);
        request.setAttribute("searchId",searchId);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://128.199.212.17:3306/guitar?useUnicode=yes&characterEncoding=UTF-8";
            String username = "guitar";
            String password = "";
            Connection collection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = collection.createStatement();
            //更新資料
            String InsertStm = null;
            InsertStm =String.format("Update WorklistData  SET operatorName ='%s',unit='%s',clientName='%s',modeName='%s',testFileName='%s',server='%s', remark='%s'  Where ListID='%s' "
                    ,operatorName,unit,clientName,modeName,testFileName,server,remark,id);
            statement.executeUpdate(InsertStm);
            request.setAttribute("wrongMessege","資料更新成功!!");
            //刪除資料
            if (checkaction!=null){
                int resultSet = statement.executeUpdate("DELETE FROM WorklistData WHERE ListID=" + id);
                if (run.equals("yes")) {
                    getServletContext().getRequestDispatcher("/work/SearchServlet").forward(request, response);
                    return;
                }else{
                    request.setAttribute("wrongMessege","刪除成功，請重新輸入資料");
                    getServletContext().getRequestDispatcher("/work/WorkListServlet").forward(request, response);
                    return;
                }
            }
            //回傳
            request.setAttribute("ListID",searchId);
            getServletContext().getRequestDispatcher("/work/WorklistCheckServlet").forward(request, response);
        }catch (SQLException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
