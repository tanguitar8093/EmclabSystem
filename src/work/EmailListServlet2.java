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

@WebServlet( "/work/EmailListServlet2")
public class EmailListServlet2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/work/index.jsp";
        // action == add
        //week2.jsp =request
        //如果用 browser發請求 action ="null"
        request.setCharacterEncoding("UTF-8");
        boolean check =true;
        String action = request.getParameter("action");
        String test = request.getParameter("test");
        //Step1
        //表單action有值>>傳值到確認頁面,  無值>>註冊畫面
        if (action == null) {
            action = "join";
        }

        if (action.equals("join")) {
            url = "/work/index.jsp";
        } else if (action.equals("add")) {
            String unit = request.getParameter("unit");
            String name = request.getParameter("name");
            String job = request.getParameter("job");
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            int power = 4;

            User user2 = new User(unit, name, job, account, password, email, power);
            //確認帳號是否重複
            try {
                check=checkDB(user2);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(check);
            if (check ==false){
                request.setAttribute("wrongMessage","已有相同帳號!!");
                getServletContext().getRequestDispatcher("/work/index.jsp").forward(request, response);
                return;
            }
            //Step2.經過確認無誤即可傳值
            if (test.equals("ok")) {
                try {
                    insertToDb(user2);

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                request.setAttribute("user2", user2);
                url = "/work/login.jsp";
                String messege = "註冊成功,等待審核";
                request.setAttribute("messege", messege);
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
            request.setAttribute("user2", user2);
            url = "/work/thanks.jsp";

        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    //導向URL 帶著request   (user), response
    //Dispatch 分配 導向
    private void insertToDb(User user2) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String dbURL = "jdbc:mysql://128.199.212.17:3306/guitar?useUnicode=yes&characterEncoding=UTF-8";
        String username = "guitar";
        String password = "";
        Connection collection = DriverManager.getConnection(dbURL, username, password);
        Statement statement = collection.createStatement();

        String insertStm = String.format("INSERT INTO MitacEmcMember (unit,name,job,account,password,email,power) " +
                        "Values('%s','%s','%s','%s','%s','%s','%s')",
                user2.getUnit(), user2.getName(), user2.getJob(), user2.getAccount(), user2.getPassword(), user2.getEmail(), user2.getPower());
        System.out.println(insertStm);
        statement.executeUpdate(insertStm);
    }

    private boolean checkDB(User user2) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        String dbURL = "jdbc:mysql://128.199.212.17:3306/guitar?useUnicode=yes&characterEncoding=UTF-8";
        String username = "guitar";
        String password = "";
        Connection collection = DriverManager.getConnection(dbURL, username, password);
        Statement statement = collection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT account FROM MitacEmcMember ");
        //resultSet 指向,特別的物件 next > false 離開迴圈
        while (resultSet.next()) {
            String Account = resultSet.getString("account");
            if (Account.equals(user2.getAccount())){
                return false;
            }
        }
        return true;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
