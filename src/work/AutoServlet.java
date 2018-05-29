package work;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/work/AutoServlet")
public class AutoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String remember = (String) session.getAttribute("remember");
        System.out.println(remember);
        if(remember != null){
            getServletContext().getRequestDispatcher("/work/MainServlet").forward(request, response);
        }
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                if(name.equals("remember") && value.equals("autologin")){
                    getServletContext().getRequestDispatcher("/work/MainServlet").forward(request, response);
                }
            }
        }



        System.out.println(remember);

//        if ( remember != null) {
//            getServletContext().getRequestDispatcher("/work/MainServlet").forward(request, response);
//        }
        if (remember == null){
            getServletContext().getRequestDispatcher("/work/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
