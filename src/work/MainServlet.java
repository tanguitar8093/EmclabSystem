package work;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/work/MainServlet")
public class MainServlet extends HttpServlet {
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

        //後台登入權限篩選
        String master =request.getParameter("master");
        if (TransPower ==1 ){
            if (master !=null){
                getServletContext().getRequestDispatcher("/MasterMode.jsp").forward(request, response);
                return;
        }
        }else if (master !=null){
            request.setAttribute("Message","權限不足!!");
        }


        getServletContext().getRequestDispatcher("/work/site.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
