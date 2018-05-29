package work;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/work/CheckStringServlet")
public class CheckStringServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //檢查session
        HttpSession session = request.getSession();
        String accountcheck = (String) session.getAttribute("accountcheck");
        if (accountcheck ==null){
            getServletContext().getRequestDispatcher("/work/LoginServlet").forward(request, response);
            return;
        }

        //讀取場地資訊
        String testItem2 =request.getParameter("testItem2");
        if (testItem2 ==null){
            testItem2="S0-RE";
        }
        request.setAttribute("testItem2",testItem2);
        //讀取+傳送錯誤訊息
        String DateWrong = (String) request.getAttribute( "DateWrong");
        request.setAttribute("DateWrong",DateWrong);
        //載入系統時間參數，檢查有無讀取jsp
        LocalDate localDate = LocalDate.now();
       int  whatYear = localDate.getYear();
       int whatMonth = localDate.getMonth().getValue();

       String str =request.getParameter("whatString");
       //無讀取狀態set  格式設定2種情形:EX.201801 or 201811
       if (str ==null && whatMonth <10){
           str =whatYear+"0"+whatMonth;
       }
       if (str ==null && whatMonth >10){
           str =whatYear+""+whatMonth;
        }
        //檢查讀取姓名或案號,無讀取狀態set
       String showType =request.getParameter("showType");

       if (showType == null){
           showType ="showname";
       }
       request.setAttribute("showType", showType);

       //字串檢查:日曆表
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()){ //這段必須放前面
            request.setAttribute("whatYear", whatYear);
            request.setAttribute("whatMonth",whatMonth);
            request.setAttribute("whatString","格式錯誤!");
            getServletContext().getRequestDispatcher("/work/Hashmaptest").forward(request, response);
            return;
        }
            if (str.length() ==6 &&
                    Integer.parseInt(str.substring(4,6)) <=12 && Integer.parseInt(str.substring(4,6)) >=1 && isNum.matches() ){
                request.setAttribute("whatString",str);
                whatYear = Integer.parseInt(str.substring(0,4));
                whatMonth = Integer.parseInt(str.substring(4,6));
                request.setAttribute("whatYear", whatYear);
                request.setAttribute("whatMonth",whatMonth);
                getServletContext().getRequestDispatcher("/work/Hashmaptest").forward(request, response);
                return;
            }else {
                request.setAttribute("whatYear", whatYear);
                request.setAttribute("whatMonth",whatMonth);
                request.setAttribute("whatString","格式錯誤!");
                getServletContext().getRequestDispatcher("/work/Hashmaptest").forward(request, response);
                return;
            }
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
