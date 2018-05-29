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

@WebServlet("/work/ManageUpdateServlet")
public class ManageUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //防止亂碼
        request.setCharacterEncoding("UTF-8");
        //導入參數
        String id =request.getParameter("id");
        String unit =request.getParameter("unit");
        String name =request.getParameter("name");
        System.out.println(name);
        String job =request.getParameter("job");
        String account =request.getParameter("account");
        String password2 =request.getParameter("password");
        String email =request.getParameter("email");
        String power =request.getParameter("powerfix");
        String passwordfix=request.getParameter("passwordfix");
        String del=request.getParameter("del");
        if (del == null){
            del="no";
        }
        //更改密碼
        if (!passwordfix.equals("")){
            password2 =passwordfix;
            request.setAttribute("Message","更新資料成功，已更改密碼!!");
        }else {
            request.setAttribute("Message","更新資料成功!!");
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://128.199.212.17:3306/guitar?useUnicode=yes&characterEncoding=UTF-8";
            String username = "guitar";
            String password = "";
            Connection collection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = collection.createStatement();
            //更新資料
            String InsertStm=null;
            InsertStm =String.format("Update MitacEmcMember SET unit ='%s',name='%s',job='%s',account='%s',password='%s',email='%s',power='%s'  Where USERID='%s'"
                    ,unit,name,job,account,password2,email,power,id);
            statement.executeUpdate(InsertStm);
            //刪除資料
            if (del.equals("del")){
                int resultSet2= statement.executeUpdate("DELETE FROM MitacEmcMember WHERE USERID=" + id);
                request.setAttribute("Message","刪除成功!!");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/work/ManageServlet").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
