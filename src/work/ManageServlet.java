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

@WebServlet("/work/ManageServlet")
public class ManageServlet extends HttpServlet {
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
        request.setAttribute("power",TransPower);
        request.setAttribute("accountcheck",accountcheck);
            //權限檢查
        if (TransPower !=1){
            request.setAttribute("WrongMessage","權限不足!!");
            getServletContext().getRequestDispatcher("/work/site.jsp").forward(request, response);
            return;
        }
        //載入資料
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL ="jdbc:mysql://128.199.212.17:3306/guitar";
            String username="guitar";
            String password="";
            Connection collection = DriverManager.getConnection(dbURL, username, password);

            Statement statement = collection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MitacEmcMember");
            List<User> userList =new ArrayList<>();
            while (resultSet.next()){
                String unit =resultSet.getString("unit");
                String name =resultSet.getString("name");
                String job =resultSet.getString("job");
                String account =resultSet.getString("account");
                String password2 =resultSet.getString("password");
                String email =resultSet.getString("email");
                int power2 = Integer.parseInt(resultSet.getString("power"));
                Long id = resultSet.getLong("USERID");
                User user=new User(unit,name,job,account,password2,email,power2);
                user.setId(id);
                userList.add(user);
            }
            request.setAttribute("result", userList);
            getServletContext().getRequestDispatcher("/work/Manage.jsp").forward(request, response);
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
