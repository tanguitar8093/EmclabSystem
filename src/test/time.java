package test;

import java.text.DateFormat;
import  java.util.*;
import  java.text.ParseException;
import  java.text.SimpleDateFormat;
import java.util.Date;
import  java.util.Calendar;

public class time {
    public boolean compare(String time1,String time2) throws ParseException
    {
        //如果想比较日期则写成"yyyy-MM-dd"就可以了
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        //将字符串形式的时间转化为Date类型的时间
        Date a=sdf.parse(time1);
        Date b=sdf.parse(time2);
        //Date类的一个方法，如果a早于b返回true，否则返回false
        if(a.before(b) || a.equals(b))
            return true;
        else
            return false;
        /*
         * 如果你不喜欢用上面这个太流氓的方法，也可以根据将Date转换成毫秒
        if(a.getTime()-b.getTime()<0)
            return true;
        else
            return false;
        */
    }
    public static void main(String[] args) throws Exception
    {
        boolean result=new time().compare("2013-1-30", "2012-12-30");
        System.out.println(result);
        //日期相減
        String date1="2018-01-01";
        String date2="2018-01-31";
        SimpleDateFormat vs =new SimpleDateFormat("yyyy-MM-dd");
        Date start=vs.parse(date1);
        Date end=vs.parse(date2);
        Date dd=vs.parse(date1);
        long vsvs =((end.getTime()-start.getTime())/(1000*60*60*24));
        System.out.println(vsvs);
        System.out.println(dd);

        Calendar now = Calendar.getInstance();
        now.set(2018,0,1); //日期為2001/3/1
        now.add(now.DATE,-30);
        System.out.println(DateFormat.getDateInstance().format(now.getTime()));





    }
}
