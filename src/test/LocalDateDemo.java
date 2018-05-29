package test;
    import java.time.LocalDate;
    import java.time.temporal.TemporalAdjuster;
    import java.time.temporal.TemporalAdjusters;
    import java.util.*;
    import java.util.regex.Matcher;
    import java.util.regex.Pattern;


public class LocalDateDemo {
    public static void main(String[] args) {

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        System.out.println("--TODAY--");
        System.out.println(LocalDate.now().getDayOfMonth());
        System.out.println("--Month--");
        System.out.println(localDate.getMonth().getValue());

        System.out.println("--Year--");
        System.out.println(localDate.getYear());

        System.out.println("--Day Of Week--");
        LocalDate firstDayOfMonth =
                LocalDate.of(2018, 2, 1);
        System.out.println(firstDayOfMonth.getDayOfWeek().getValue());


        LocalDate lastday = LocalDate.of(2018, 2, 1).with(TemporalAdjusters.lastDayOfMonth());
        int gg = lastday.getDayOfMonth();
        System.out.println(gg);


        //判斷數字
        isNumeric("");
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            System.out.println("no");
            return false;
        }
        System.out.println("yes");
        return true;
    }
}