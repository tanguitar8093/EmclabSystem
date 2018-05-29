package work;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/work/LogoutServlet")
public class logoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("accountcheck");
        session.removeAttribute("autoIn");
        session.removeAttribute("unit");
        session.removeAttribute("power");

        Cookie[] cookies =request.getCookies();
        for (Cookie cookie4 : cookies){
            cookie4.setValue("");
            cookie4.setPath("/");
            cookie4.setMaxAge(0);
            response.addCookie(cookie4);
        }
        String messege ="登出成功!!";
        request.setAttribute("messege", messege);
        getServletContext().getRequestDispatcher("/work/login.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
