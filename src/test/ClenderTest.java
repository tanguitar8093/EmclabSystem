package test;

import java.time.*;

class CalenderTest {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();//构造一个表示当前日期的对象
        int month = date.getMonthValue();//获取当前月份
        int today = date.getDayOfMonth();//获取当前日期

        date = date.minusDays(today-1); //设置date为这个月的第一天,并且得到这一天为星期几
        DayOfWeek weekday = date.getDayOfWeek();
        int value = weekday.getValue();

        //打印日历的主体
        System.out.println(" Mon Tue Wed Thu Fri Sat Sun");

        for(int i = 1; i < value; i++)
            System.out.print("    ");


        while(date.getMonthValue() == month) {

            System.out.printf("%3d",date.getDayOfMonth());

            if(date.getDayOfMonth() == today) {
                System.out.print("*");
            }else {
                System.out.print(" ");
            }

            date = date.plusDays(1);
//            System.out.println(date);

            if(date.getDayOfWeek().getValue() == 1) {//如果是星期一,则换行输出
                System.out.println();
            }
        }

        if(date.getDayOfWeek().getValue() != 1)
            System.out.println();
    }
}