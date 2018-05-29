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

@WebServlet("/work/ExcelServlet")
public class ExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=testxls.xls");

        Workbook wb = new HSSFWorkbook();

        Sheet sheet = wb.createSheet("jijiji");
        Row row = sheet.createRow(0);
        for (int i=0;i<=12;i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue("123");
        }
        row = sheet.createRow(1);
        for (int i=0;i<=12;i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue("123");
        }
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);

        byte[] outArray = outByteStream.toByteArray();
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
