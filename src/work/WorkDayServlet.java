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
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/work/WorkDayServlet")
public class WorkDayServlet extends HttpServlet {
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
        //檢查權限
        if (TransPower !=1){
            request.setAttribute("wrongMessage","權限不足！");
            getServletContext().getRequestDispatcher("/work/MainServlet").forward(request, response);
            return;
        }
        //載入時間參數
        LocalDate localDate = LocalDate.now();
        int  dateYear = localDate.getYear();

        //檢查輸入格式/初始化
        String workDays =request.getParameter("workDays");
        //if 更新
        String update =request.getParameter("update");
        int [] MonthDay = new int[12];
        int  workDaysCheck=0;

        if (workDays ==null){
            workDays=dateYear+"";
        }

        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(workDays);
        if (!isNum.matches()){ //這段必須放前面,判讀是否為數字
          request.setAttribute("wrongMessage","格式錯誤!");
          workDays=dateYear+"";
          request.setAttribute("workDays",workDays);
        }else {
                workDaysCheck = Integer.parseInt(workDays);
            if (workDaysCheck <2000 || workDaysCheck>9999) {
                request.setAttribute("wrongMessage","時間錯誤!");
                workDays=dateYear+"";
                request.setAttribute("workDays",workDays);
            }
        }
        workDaysCheck = Integer.parseInt(workDays);

        //讀取工作天資料:
        int workday=0;
        int workdaysum=0;
        int i=0;
        String  NullCheck=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL ="jdbc:mysql://128.199.212.17:3306/guitar";
            String username="guitar";
            String password="";
            Connection collection = DriverManager.getConnection(dbURL, username, password);

            Statement statement = collection.createStatement();
            ResultSet WorkDaySet = statement.executeQuery("SELECT * FROM WorkDay " +
                    "WHERE Year='"+workDaysCheck+"'");
            while (WorkDaySet.next()){
                workday = Integer.valueOf(WorkDaySet.getString("workday"));
                MonthDay[i] =Integer.valueOf(WorkDaySet.getString("workday"));
                workdaysum= workdaysum+workday;
                i++;
            }
            //檢查月份是否為0
            int checkNull=0;
            for (i=0;i<12;i++){
                if (MonthDay[i] ==0){
                    checkNull++;
                }
            }
            // 新增工作天
            if (checkNull == 12){
                for (i=1;i<=12;i++) {
                    String insertStm = String.format("INSERT INTO WorkDay(Year,month,workday)" +
                            "Values('%s','%s','%s')", workDaysCheck,i,1 );
                    statement.executeUpdate(insertStm);
                }
            }else if (update !=null){
                String [] checkname= new String[12];
                boolean result=false;
                try {
                    for (int k=0; k<12; k++) {
                        checkname[k] =request.getParameter("Month"+k);
                        result =  check(checkname[k]);
                        if (result == false){
                            request.setAttribute("wrongMessage","格式錯誤!");
                            workDays=dateYear+"";
                            request.setAttribute("workDays",workDays);
                        }else {
                            MonthDay[k] = Integer.parseInt(request.getParameter("Month" + k));
                        }
                    }
                for (i=0;i<12;i++) {
                    String insertStm = String.format("Update WorkDay SET workday='%s' WHERE Year='%s' AND month='%s' "
                            ,MonthDay[i],workDaysCheck,i+1);
                    statement.executeUpdate(insertStm);
                }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        } catch (SQLException e){
            for (Throwable t: e){
                t.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("workDaysCheck",workDaysCheck);
        request.setAttribute("MonthDay",MonthDay);

        getServletContext().getRequestDispatcher("/work/workday.jsp").forward(request, response);

    }
    public boolean check (String check) throws ParseException
    {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(check);
        if (!isNum.matches()) { //這段必須放前面,判讀是否為數字
            return false;
        }else {
            return true;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
