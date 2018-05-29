package test;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CT {
        private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        /**
         * @param args
         */
        public static void main(String[] args) {
            Calendar calendar = Calendar.getInstance();
            System.out.println("today="+sdf.format(calendar.getTime()));
            calendar.set(2018,0,1);
            calendar.add(Calendar.DATE,-30); //Calendar.MONTH 加上1個月(用add    Method)
            System.out.println("nextMonthDay="+sdf.format(calendar.getTime()    ));
            //第一天
            System.out.println("nextMonthFrist="+sdf.format(getFirstMonthDay(calendar)));
            //最後一天
            System.out.println("nextMonthLast="+sdf.format(getLastMonthDay(calendar)));
        }


        //每個月的第一天日期
        public static Date getFirstMonthDay(Calendar calendar) {
            calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
            return calendar.getTime();
        }


        //每個月的最後一天日期
        public static Date getLastMonthDay(Calendar calendar) {
            calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
            return calendar.getTime();
        }
 public static Date getFirstMonthDay(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        return calendar.getTime();
 }
 public static Date getLastMonthDay(Date date) {
       Calendar calendar = new GregorianCalendar();
       calendar.setTime(date);
       calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
       return calendar.getTime();
 }
    }
