package work;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/work/MathServlet")
public class MathServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        LocalDate localDate = LocalDate.now();
        int  dateYear = localDate.getYear();
        int  dateMonth = localDate.getMonth().getValue();
        int  workMonth = localDate.getMonth().getValue();
        //讀取場地
        String testItem2 =request.getParameter("testItem2");
        if (testItem2 ==null){
            testItem2="S0-RE";
        }
        //判讀時間:單月or年度
        String  whichTime =request.getParameter("whichTime");
        request.setAttribute("whichTime",whichTime );
        String str =request.getParameter("whatString");
        if (whichTime == null){
            whichTime ="month";
        }

        //無讀取狀態set  格式設定2種情形:EX.201801 or 201811
        if (str ==null && dateMonth <10){
            str =dateYear+"0"+dateMonth;
        }
        if (str ==null && dateMonth >10){
            str =dateYear+""+dateMonth;
        }

        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()){ //這段必須放前面,判讀是否為數字
            request.setAttribute("dateYear", dateYear);
            request.setAttribute("dateMonth",dateMonth);
            request.setAttribute("whatString","格式錯誤!");
            whichTime ="month";
            if (dateMonth <10){
                str =dateYear+"0"+dateMonth;
            }
            if (dateMonth >10){
                str =dateYear+""+dateMonth;
            }
        }
        if (whichTime.equals("month") && str.length() ==6 && //判讀是否為6位數:單月
                Integer.parseInt(str.substring(0,4)) >2000 &&
                Integer.parseInt(str.substring(4,6)) <=12 && Integer.parseInt(str.substring(4,6)) >=1 && isNum.matches() ){
            request.setAttribute("whatString",str);
            dateYear = Integer.parseInt(str.substring(0,4));
            dateMonth = Integer.parseInt(str.substring(4,6));
            workMonth = Integer.parseInt(str.substring(4,6));
            request.setAttribute("dateYear", dateYear);
            request.setAttribute("dateMonth",dateMonth);
        }else if (!whichTime.equals("month") && str.length() ==4 && //判讀是否為4位數:年度
                Integer.parseInt(str) >2000  && isNum.matches() ){
            request.setAttribute("whatString",str);
            dateYear = Integer.parseInt(str);
            request.setAttribute("dateYear", dateYear);
            request.setAttribute("dateMonth",00);
        } else {
            request.setAttribute("dateYear", dateYear);
            request.setAttribute("dateMonth",dateMonth);
            request.setAttribute("whatString","格式錯誤!");
            whichTime ="month";
        }

        Double[] math; // x is a reference to int[]
        math = new Double[12]; // 利用new指令產生物件
        Double TC=0.0;
        math[0]=0.0;
        math[3]=0.0;
        math[6]=0.0;
        math[9]=0.0;
        int workday=0;
        int workdaysum=0,firsthalfsum=0,lasthalfsum=0,season1sum=0,season2sum=0,season3sum=0,season4sum=0;
        //天數>>後台設置參數
        int [] MonthDay = new int[12];
        int i=0;
        int ms=0,me=0,mu=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL ="jdbc:mysql://128.199.212.17:3306/guitar";
            String username="guitar";
            String password="";
            Connection collection = DriverManager.getConnection(dbURL, username, password);

            Statement statement = collection.createStatement();
//讀取工作天
            ResultSet WorkDaySet = statement.executeQuery("SELECT * FROM WorkDay " +
                    "WHERE Year='"+dateYear+"'");
            while (WorkDaySet.next()){
               workday = Integer.valueOf(WorkDaySet.getString("workday"));
               MonthDay[i] =Integer.valueOf(WorkDaySet.getString("workday"));
                if (i<6){
                    firsthalfsum=firsthalfsum+workday;
                }
                if (i>5 && i<12){
                    lasthalfsum=lasthalfsum+workday;
                }
                if (i>=0 && i<=2){
                    season1sum=season1sum+workday;
                }
                if(i>2 && i<=5){
                    season2sum=season2sum+workday;
                }
                if(i>5 && i<=8){
                    season3sum=season3sum+workday;
                }
                if(i>8 && i<=11){
                    season4sum=season4sum+workday;
                }
               i++;
               workdaysum= workdaysum+workday;
            }
            if (workdaysum <=120){
                request.setAttribute("dateYear", dateYear);
                request.setAttribute("dateMonth",dateMonth);
                request.setAttribute("whatString","工作日異常!");
                whichTime ="month";
            }
            //讀取單月
            if (whichTime.equals("month")) {
                ResultSet resultSet = statement.executeQuery("SELECT TC FROM WorklistData " +
                        "WHERE dateYear='" + dateYear + "'AND dateMonth='" + dateMonth + "' AND testItem2='" + testItem2 + "'");
                while (resultSet.next()) {
                    TC = Double.valueOf(resultSet.getString("TC"));
                    math[0] = math[0] + TC;
                }
                math[1]=math[0]/(MonthDay[workMonth-1]*12.0);
                math[2]=math[0]/(MonthDay[workMonth-1]*8.0);
                System.out.println(MonthDay[workMonth-1]);
            }
            //讀取整年
            if (whichTime.equals("All-Year")) {
                ResultSet resultSet = statement.executeQuery("SELECT TC FROM WorklistData " +
                        "WHERE dateYear='" + dateYear + "' AND testItem2='" + testItem2 + "'");
                while (resultSet.next()) {
                    TC = Double.valueOf(resultSet.getString("TC"));
                    math[0] = math[0] + TC;
                }
                math[1]=math[0]/(workdaysum*12.0);
                math[2]=math[0]/(workdaysum*8.0);
            }
            //讀取前後半年 1234季
            if (whichTime.equals("First-Half-Year")){
                ms=1;me=6;mu=firsthalfsum;
            }else if (whichTime.equals("Last-Half-Year")){
                ms=7;me=12;mu=lasthalfsum;
            }else if (whichTime.equals("Season1")){
                ms=1;me=3;mu=season1sum;
            }else if (whichTime.equals("Season2")){
                ms=4;me=6;mu=season2sum;
            }else if (whichTime.equals("Season3")){
                ms=7;me=9;mu=season3sum;
            }else if (whichTime.equals("Season4")){
                ms=10;me=12;mu=season4sum;
            }
            if (!whichTime.equals("All-Year") && !whichTime.equals("month")) {
                for (i=ms;i<=me;i++) {
                    ResultSet resultSet = statement.executeQuery("SELECT TC FROM WorklistData " +
                            "WHERE dateYear='" + dateYear + "' AND testItem2='" + testItem2 + "' AND dateMonth='"+i+"' ");
                    while (resultSet.next()) {
                        TC = Double.valueOf(resultSet.getString("TC"));
                        math[0] = math[0] + TC;
                    }
                }
                math[1]=math[0]/(mu*12.0);
                math[2]=math[0]/(mu*8.0);
            }
//單月 ETC BU
            if (whichTime.equals("month")) {
                ResultSet resultSet2 = statement.executeQuery("SELECT TC FROM WorklistData " +
                        "WHERE dateYear='" + dateYear + "'AND dateMonth='" + dateMonth + "' AND testItem2='" + testItem2 + "' AND unit='" + "EBU" + "' ");
                while (resultSet2.next()) {
                    TC = Double.valueOf(resultSet2.getString("TC"));
                    math[3] = math[3] + TC;
                }
                math[4] = math[3] / (MonthDay[workMonth - 1] * 12.0);
                math[5] = math[3] / (MonthDay[workMonth - 1] * 8.0);

                ResultSet resultSet3 = statement.executeQuery("SELECT TC FROM WorklistData " +
                        "WHERE dateYear='" + dateYear + "'AND dateMonth='" + dateMonth + "' AND testItem2='" + testItem2 + "' AND unit='" + "TBU" + "' ");
                while (resultSet3.next()) {
                    TC = Double.valueOf(resultSet3.getString("TC"));
                    math[6] = math[6] + TC;
                }
                math[7] = math[6] / (MonthDay[workMonth - 1] * 12.0);
                math[8] = math[6] / (MonthDay[workMonth - 1] * 8.0);

                ResultSet resultSet4 = statement.executeQuery("SELECT TC FROM WorklistData " +
                        "WHERE dateYear='" + dateYear + "'AND dateMonth='" + dateMonth + "' AND testItem2='" + testItem2 + "' AND unit='" + "CBU" + "' ");
                while (resultSet4.next()) {
                    TC = Double.valueOf(resultSet4.getString("TC"));
                    math[9] = math[9] + TC;
                }
                math[10] = math[9] / (MonthDay[workMonth - 1] * 12.0);
                math[11] = math[9] / (MonthDay[workMonth - 1] * 8.0);
            }
//整年ETC
            if (whichTime.equals("All-Year")) {
                ResultSet resultSet2 = statement.executeQuery("SELECT TC FROM WorklistData " +
                        "WHERE dateYear='" + dateYear + "' AND testItem2='" + testItem2 + "' AND unit='" + "EBU" + "' ");
                while (resultSet2.next()) {
                    TC = Double.valueOf(resultSet2.getString("TC"));
                    math[3] = math[3] + TC;
                }
                math[4] = math[3] / (workdaysum * 12.0);
                math[5] = math[3] / (workdaysum * 8.0);

                ResultSet resultSet3 = statement.executeQuery("SELECT TC FROM WorklistData " +
                        "WHERE dateYear='" + dateYear + "' AND testItem2='" + testItem2 + "' AND unit='" + "TBU" + "' ");
                while (resultSet3.next()) {
                    TC = Double.valueOf(resultSet3.getString("TC"));
                    math[6] = math[6] + TC;
                }
                math[7] = math[6] / (workdaysum * 12.0);
                math[8] = math[6] / (workdaysum * 8.0);

                ResultSet resultSet4 = statement.executeQuery("SELECT TC FROM WorklistData " +
                        "WHERE dateYear='" + dateYear + "' AND testItem2='" + testItem2 + "' AND unit='" + "CBU" + "' ");
                while (resultSet4.next()) {
                    TC = Double.valueOf(resultSet4.getString("TC"));
                    math[9] = math[9] + TC;
                }
                math[10] = math[9] / (workdaysum * 12.0);
                math[11] = math[9] / (workdaysum * 8.0);
            }
//1234季,前後年ETC
            if (!whichTime.equals("All-Year") && !whichTime.equals("month")) {
                for (i=ms;i<=me;i++) {
                    ResultSet resultSet2 = statement.executeQuery("SELECT TC FROM WorklistData " +
                            "WHERE dateYear='" + dateYear + "' AND testItem2='" + testItem2 + "' AND unit='" + "EBU" + "' AND dateMonth='" + i + "' ");
                    while (resultSet2.next()) {
                        TC = Double.valueOf(resultSet2.getString("TC"));
                        math[3] = math[3] + TC;
                    }

                    ResultSet resultSet3 = statement.executeQuery("SELECT TC FROM WorklistData " +
                            "WHERE dateYear='" + dateYear + "' AND testItem2='" + testItem2 + "' AND unit='" + "TBU" + "' AND dateMonth='" + i + "' ");
                    while (resultSet3.next()) {
                        TC = Double.valueOf(resultSet3.getString("TC"));
                        math[6] = math[6] + TC;
                    }

                    ResultSet resultSet4 = statement.executeQuery("SELECT TC FROM WorklistData " +
                            "WHERE dateYear='" + dateYear + "' AND testItem2='" + testItem2 + "' AND unit='" + "CBU" + "' AND dateMonth='" + i + "' ");
                    while (resultSet4.next()) {
                        TC = Double.valueOf(resultSet4.getString("TC"));
                        math[9] = math[9] + TC;
                    }
                }
                    math[4] = math[3] / (mu * 12.0);
                    math[5] = math[3] / (mu * 8.0);
                    math[7] = math[6] / (mu * 12.0);
                    math[8] = math[6] / (mu * 8.0);
                    math[10] = math[9] / (mu * 12.0);
                    math[11] = math[9] / (mu * 8.0);

            }
            request.setAttribute("math",math);
            } catch (SQLException e){
            for (Throwable t: e){
                t.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("testItem2",testItem2);
        getServletContext().getRequestDispatcher("/work/math.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
