package work;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/work/ExcelServlet")
public class ExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //項目
        String[] dateDay;
        dateDay = new String[1000]; // 利用new指令產生物件
        String[] dateUser;
        dateUser = new String[1000];
        String[] dateCase;
        dateCase =new String[1000];
        int[] dateYear;
        dateYear =new int[1000];
        int[] dateMonth;
        dateMonth =new int[1000];
        String[] listDate;
        listDate =new String[1000];
        String[] bookTime;
        bookTime =new  String[1000];
        String[] unit;
        unit= new String[1000];
        String[] modeName;
        modeName =new String[1000];
        String[] clientName;
        clientName =new String[1000];
        String[] testItem2;
        testItem2 =new  String[1000];
        Long[] userid;
        userid =new  Long[1000];

        String [] name;
        name =new String[7];
        name[0]="編號";
        name[1]="預約日期 ";
        name[2]="申請時間";
        name[3]="申請人";
        name[4]="場地";
        name[5]="型號";
        name[6]="客戶";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://128.199.212.17:3306/guitar";
            String username = "guitar";
            String password = "";
            Connection collection = DriverManager.getConnection(dbURL, username, password);
            Statement statement = collection.createStatement();
            ResultSet resultSet = null;




            resultSet = statement.executeQuery("SELECT * FROM DateShowList");
            List<DateParament> userList =new ArrayList<>();
            int i=0;
            response.setContentType("application/ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=DataOutput.xls");
            Workbook wb = new HSSFWorkbook();

            while (resultSet.next()) {

                dateDay[i] = resultSet.getString("dateDay");
                dateUser[i] = resultSet.getString("dateUser");
                dateCase[i] = resultSet.getString("dateCase");
                dateYear[i] = Integer.parseInt(resultSet.getString("dateYear"));
                dateMonth[i] = Integer.parseInt(resultSet.getString("dateMonth"));
                listDate[i] = resultSet.getString("listDate");
                bookTime[i] = resultSet.getString("bookTime");
                unit[i] = resultSet.getString("unit");
                modeName[i] = resultSet.getString("modeName");
                clientName[i] = resultSet.getString("clientName");
                testItem2[i] = resultSet.getString("testItem2");
                userid[i] = resultSet.getLong("USERID");
                i++;
            }
            int end =i;
            //Excel處理
            Sheet sheet = wb.createSheet("WorkLoad");

            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            for (i=0;i<=end+1;i++) {
                row = sheet.createRow(i);
                if (i==0){
                    for (int k=0;k<=6;k++) {
                        cell = row.createCell(k);
                        cell.setCellValue(name[k]);
                    }
                }
                else {
                    cell = row.createCell(0);
                    if (i<=end) {
                        cell.setCellValue(i);
                    }
                    cell = row.createCell(1);
                    cell.setCellValue(listDate[i-1]);
                    cell = row.createCell(2);
                    cell.setCellValue(bookTime[i-1]);
                    cell = row.createCell(3);
                    cell.setCellValue(dateUser[i-1]);
                    cell = row.createCell(4);
                    cell.setCellValue(testItem2[i-1]);
                    cell = row.createCell(5);
                    cell.setCellValue(modeName[i-1]);
                    cell = row.createCell(6);
                    cell.setCellValue(clientName[i-1]);
                }

            }


            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            wb.write(outByteStream);

            byte[] outArray = outByteStream.toByteArray();
            OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);
            outStream.flush();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
