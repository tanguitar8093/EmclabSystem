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

@WebServlet("/work/WorkListServlet")
public class WorkListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
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
System.out.println(TransPower);
        if (TransPower !=3){
            if (TransPower !=1) {
                request.setAttribute("Message", "只開放給測試工程師及管理員使用!!");
                getServletContext().getRequestDispatcher("/work/MainServlet").forward(request, response);
                return;
            }
        }

        String url = "/work/worklist.jsp";
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";
        }

        if (action.equals("join")) {
            url = "/work/worklist.jsp";
        } else if (action.equals("add")) {
            String operatorName = request.getParameter("operatorName");
            String listDate = request.getParameter("listDate");
            if (listDate.length() ==0){
                request.setAttribute("wrongMessege", "   時間格式錯誤!");
                getServletContext().getRequestDispatcher(url).forward(request, response);
                return;
            }
            int dateYear = Integer.parseInt(request.getParameter("dateYear"));
            int dateMonth = Integer.parseInt(listDate.substring(5,7));
            int testTimeStart1 = Integer.parseInt(request.getParameter("testTimeStart1"));
            int testTimeStart2 = Integer.parseInt(request.getParameter("testTimeStart2"));
            int testTimeEnd1 = Integer.parseInt(request.getParameter("testTimeEnd1"));
            int testTimeEnd2 = Integer.parseInt(request.getParameter("testTimeEnd2"));
            String clientName = request.getParameter("clientName");
            String modeName = request.getParameter("modeName");
            String testFileName = request.getParameter("testFileName");
            String remark = request.getParameter("remark");
            String server = request.getParameter("server");
            String testItem = request.getParameter("testItem");
            String unit = request.getParameter("unit");
            //測項場地分類
            String testItem2="";
            if (testItem.equals("RE-S0")){
                testItem2="S0-RE";
            }
            if (testItem.equals("CE-S0") || testItem.equals("GSM-S0")){
                testItem2="S0-CE";
            }
            if (testItem.equals("RS-S1") || testItem.equals("GSM-S1") ){
                testItem2="S1";
            }
            if (testItem.equals("CS-S2") || testItem.equals("Flick/Harmonic-S2") || testItem.equals("EFT/B-S2") || testItem.equals("Surge-S2")
                    || testItem.equals("Dips/Variations-S2") || testItem.equals("GSM-S2")){
                testItem2="S2";
            }
            if (testItem.equals("ESD-S3") || testItem.equals("EFT/B-S3") || testItem.equals("Surge-S3") || testItem.equals("Flick/Harmonic-S3")
                    || testItem.equals("Dips/Variations-S3") ||  testItem.equals("MF-S3")){
                testItem2="S3";
            }
            if (testItem.equals("ESD-S4")){
                testItem2="S4";
            }
            if (testItem.equals("RE-966") || testItem.equals("RS-966")){
                testItem2="966";
            }
            //計算時間
            Double TC =(Double)(testTimeEnd1-testTimeStart1+(testTimeEnd2-testTimeStart2)/60.0);
            if (testTimeStart1<12 && testTimeEnd1>12){
                TC=TC-1;
            }
            if (testTimeStart1<18 && testTimeEnd1>18){
                TC=TC-1;
            }
            //判斷時間格式
            if (testTimeStart1>testTimeEnd1 || (testTimeStart1==testTimeEnd1 && testTimeStart2>=testTimeEnd2)){
                request.setAttribute("wrongMessege", "   時間格式錯誤!");
                getServletContext().getRequestDispatcher(url).forward(request, response);
                return;
            }
            System.out.println(TC);
            //讀取資料庫
            WLU wlu = new WLU(operatorName, listDate, dateYear,dateMonth, testTimeStart1, testTimeStart2, testTimeEnd1, testTimeEnd2, clientName, modeName, testFileName, remark, server, testItem,testItem2,unit,TC);
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String dbURL = "jdbc:mysql://128.199.212.17:3306/guitar?useUnicode=yes&characterEncoding=UTF-8";
                String username = "guitar";
                String password = "";
                Connection collection = DriverManager.getConnection(dbURL, username, password);
                Statement statement = collection.createStatement();
                int wts1=wlu.getTestTimeStart1();
                int wts2=wlu.getTestTimeStart2();
                int wte1=wlu.getTestTimeEnd1();
                int wte2=wlu.getTestTimeEnd2();
                double wts=wts1+(wts2/60.0);
                double wte=wte1+(wte2/60.0);

                ResultSet resultSet = statement.executeQuery("SELECT listDate,testTimeStart1,testTimeStart2,testTimeEnd1,testTimeEnd2,operatorName,testItem2,ListID FROM WorklistData "+"WHERE listDate='"+listDate+"' AND testItem2='"+testItem2+"' ");
                while (resultSet.next()){
                    int ts1 = Integer.parseInt(resultSet.getString("testTimeStart1"));
                    int ts2 = Integer.parseInt(resultSet.getString("testTimeStart2"));
                    int te1 = Integer.parseInt(resultSet.getString("testTimeEnd1"));
                    int te2 = Integer.parseInt(resultSet.getString("testTimeEnd2"));
                    String user = resultSet.getString("operatorName");
                    int ListID = Integer.parseInt(resultSet.getString("ListID"));
                    double ts=ts1+(ts2/60.0);
                    double te=te1+(te2/60.0);
//判斷時間是否有人使用
                    if ((ts<=wts && te>=wte) || (wts<ts && wte>ts)||(wts<te && wte>te)){
                        String tsm1=ts2+"";
                        String tsm2=te2+"";
                        System.out.println(ts+" "+wts);
                        System.out.println(te+" "+wte);

                        if (ts2==0){tsm1=ts2+"0";}
                        if (te2==0){tsm2=te2+"0";}
                        String wrongMessege ="與使用者時間衝突:"+user+",時段"+ts1+":"+tsm1+"-"+te1+":"+tsm2;
                        if (TransPower ==1){
                            wrongMessege =wrongMessege+"，請刪除資料!!";
                        }
                        request.setAttribute("wrongMessege", wrongMessege);
                        request.setAttribute("run", "no");
                        request.setAttribute("ListID", ListID);
                        if (TransPower ==1){
                            getServletContext().getRequestDispatcher("/work/WorklistCheckServlet").forward(request, response);
                            return;
                        }

                        getServletContext().getRequestDispatcher(url).forward(request, response);
                        return;
                    }
                }
                insertToDb(wlu);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {

                e.printStackTrace();
            }
            request.setAttribute("wlu", wlu);
            request.setAttribute("Message", "表單填寫完成，可至搜尋系統修改!");
            getServletContext().getRequestDispatcher("/work/MainServlet").forward(request, response);
        }
        getServletContext().getRequestDispatcher("/work/worklist.jsp").forward(request, response);

    }

//寫入資料庫
    private void insertToDb(WLU wlu) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String dbURL = "jdbc:mysql://128.199.212.17:3306/guitar?useUnicode=yes&characterEncoding=UTF-8";
        String username = "guitar";
        String password = "";
        Connection collection = DriverManager.getConnection(dbURL, username, password);
        Statement statement = collection.createStatement();

        String insertStm = String.format("INSERT INTO WorklistData(operatorName,listDate,dateYear,dateMonth,testTimeStart1,testTimeStart2,testTimeEnd1,testTimeEnd2,clientName,modeName,testFileName,remark,server, testItem,testItem2,unit,TC)" +
                        "Values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                wlu.getOperatorName(), wlu.getListDate(), wlu.getDateYear(),wlu.getDateMonth(),wlu.getTestTimeStart1(), wlu.getTestTimeStart2(), wlu.getTestTimeEnd1(), wlu.getTestTimeEnd2(), wlu.getClientName(), wlu.getModeName()
                , wlu.getTestFileName(), wlu.getRemark(), wlu.getServer(), wlu.getTestItem(),wlu.getTestItem2(),wlu.getUnit(),wlu.getTC());

        System.out.println(insertStm);
        statement.executeUpdate(insertStm);
    }
    //測試用尚未成功:新的方法
    private void SearchToDb(WLU wlu) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String dbURL = "jdbc:mysql://128.199.212.17:3306/guitar?useUnicode=yes&characterEncoding=UTF-8";
        String username = "guitar";
        String password = "";
        Connection collection = DriverManager.getConnection(dbURL, username, password);
        Statement statement = collection.createStatement();
        int wts1=wlu.getTestTimeStart1();
        int wts2=wlu.getTestTimeStart2();
        int wte1=wlu.getTestTimeEnd1();
        int wte2=wlu.getTestTimeEnd2();

        ResultSet resultSet = statement.executeQuery("SELECT listDate,testTimeStart1,testTimeStart2,testTimeEnd1,testTimeEnd2 FROM WorklistData "+"WHERE listDate='"+wlu.getListDate()+"'");
        while (resultSet.next()){
            int testTimeStart1 = Integer.parseInt(resultSet.getString("testTimeStart1"));
            int testTimeStart2 = Integer.parseInt(resultSet.getString("testTimeStart2"));
            int testTimeEnd1 = Integer.parseInt(resultSet.getString("testTimeEnd1"));
            int testTimeEnd2 = Integer.parseInt(resultSet.getString("testTimeEnd2"));
            if ((wts1<testTimeStart1 && wte1<testTimeEnd1) || (wts1>testTimeStart1 && wte1>testTimeEnd1)
                    || (wts1 >= testTimeStart1 && wte1 <=testTimeEnd1)|| (wts1<testTimeStart1 && wte1>testTimeEnd1)){

            }


        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}