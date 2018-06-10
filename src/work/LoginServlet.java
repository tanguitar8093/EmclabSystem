package work;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/work/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //step1
        //載入cookie seesion
        //autoin >>session,remember >>cookie
        HttpSession session = request.getSession();
        String autoIn = (String) session.getAttribute("autoIn");
        Cookie[] cookies = request.getCookies();
        if (cookies !=null){
            for (Cookie cookie5 : cookies) {
                String name = cookie5.getName();
                String value = cookie5.getValue();
                if(name.equals("remember") && value.equals("autologin")){
                    getServletContext().getRequestDispatcher("/work/MainServlet").forward(request, response);
                }
                session.setAttribute(name, value);
            }
        }
        if (autoIn != null){
            getServletContext().getRequestDispatcher("/work/MainServlet").forward(request, response);
        }

        String accountcheck = request.getParameter("accountcheck");
        String passwordcheck = request.getParameter("passwordcheck");
        String remember = request.getParameter("remember");
        //連結資料庫 查詢帳號密碼是否吻合
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL ="jdbc:mysql://128.199.212.17:3306/guitar";
            String username="guitar";
            String password="";
            Connection collection = DriverManager.getConnection(dbURL, username, password);



            // Method 2...
            PreparedStatement preparedStatement = collection.prepareStatement("SELECT account,password,unit,power FROM MitacEmcMember " +
                    "WHERE account=? AND password=?");
            preparedStatement.setString(1,accountcheck );
            preparedStatement.setString(2,passwordcheck );
            ResultSet resultSet = preparedStatement.executeQuery();

            //resultSet 指向,特別的物件 next > false 離開迴圈
            List<User> userList =new ArrayList<>();
            while (resultSet.next()){

                String Account =resultSet.getString("account");
                String Password =resultSet.getString("Password");
                String unit =resultSet.getString("unit");
                int power = Integer.parseInt(resultSet.getString("power"));
                //Step2
                //帳密正確,紀錄session,勾選自動登入,紀錄cookie導頁面>>主畫面
                //帳密錯誤,傳遞錯誤訊息回到登入jsp
                if (Password.equals(passwordcheck) && Account.equals(accountcheck)){
                    if (power ==4){
                        String messege = "帳號尚未審核通過!!";
                        request.setAttribute("messege", messege);
                        getServletContext().getRequestDispatcher("/work/login.jsp").forward(request, response);
                        return;
                    }
                    autoIn ="ok";
                    session.setAttribute("autoIn", autoIn);
                    session.setAttribute("accountcheck", accountcheck);
                    session.setAttribute("unit", unit);
                    session.setAttribute("power", power+"");
                    if (remember != null){
                        Cookie cookie = getCookie("accountcheck",accountcheck);
                        Cookie cookie2 = getCookie("unit",unit);
                        Cookie cookie3 = getCookie("power", String.valueOf(power));
                        Cookie cookie4 = getCookie("remember",remember);
                        response.addCookie(cookie);
                        response.addCookie(cookie2);
                        response.addCookie(cookie3);
                        response.addCookie(cookie4);
                    }
                    getServletContext().getRequestDispatcher("/work/MainServlet").forward(request, response);
                    return;
                }
            }
            request.setAttribute("accountcheck", accountcheck);
            request.setAttribute("passwordcheck", passwordcheck);
            if (accountcheck !=null || passwordcheck !=null) {
                String messege = "帳號或密碼不正確!!";
                request.setAttribute("messege", messege);
            }
            getServletContext().getRequestDispatcher("/work/login.jsp").forward(request, response);
            return;
        } catch (SQLException e){
            for (Throwable t: e){
                t.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Cookie getCookie(String cookiename, String remember) {
        Cookie cookie= new Cookie(cookiename,remember);
        cookie.setMaxAge(60000*60*24);
        cookie.setPath("/");
        return cookie;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
