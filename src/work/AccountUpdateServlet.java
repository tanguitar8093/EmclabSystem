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

@WebServlet("/work/AccountUpdateServlet")
public class AccountUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //讀取參數
        String unit =request.getParameter("unit");
        String name =request.getParameter("name");
        String job =request.getParameter("job");
        String account =request.getParameter("account");
        String email =request.getParameter("email");
        String password2 =request.getParameter("password");
        String newPassword =request.getParameter("newPassword");
        String passwordFix =request.getParameter("passwordFix");
        String passwordFix2 =request.getParameter("passwordFix2");
        //檢查有無修改密碼
        if (newPassword!=null && passwordFix!=null &&passwordFix2!=null){
            if ( (newPassword.equals(password2)) && (passwordFix.equals(passwordFix2)) ){
                password2 =passwordFix;
                request.setAttribute("WrongMessage","資料修改成功，密碼修改成功!!");
            }else {
                request.setAttribute("WrongMessage","資料修改成功，密碼尚未修改!!");
            }
        }else {
            request.setAttribute("WrongMessage","資料修改成功!!");
        }
        //更新資料
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://128.199.212.17:3306/guitar?useUnicode=yes&characterEncoding=UTF-8";
            String username="guitar";
            String password="";
            Connection collection = DriverManager.getConnection(dbURL, username, password);

            Statement statement = collection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MitacEmcMember WHERE account='"+account+"' " );

            String InsertStm=null;
            InsertStm =String.format("Update MitacEmcMember SET name='%s',job='%s',password='%s',email='%s'  Where account='%s'"
                    ,name,job,password2,email,account);
            statement.executeUpdate(InsertStm);
            System.out.println(InsertStm);

            getServletContext().getRequestDispatcher("/work/AccountServlet").forward(request, response);
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
