package work;

public class DateParament {
    private long id;
    private String dateDay;
    private String dateUser;
    private String dateCase;
    private int dateYear;
    private int dateMonth;
    private String listDate;
    private String bookTime;
    private String unit;
    private String modeName;
    private String clientName;
    private String testItem2;

    public DateParament( String dateDay, String dateUser, String dateCase, int dateYear, int dateMonth, String listDate,  String bookTime,String unit, String modeName, String clientName, String testItem2) {
        this.dateDay = dateDay;
        this.dateUser = dateUser;
        this.dateCase = dateCase;
        this.dateYear = dateYear;
        this.dateMonth = dateMonth;
        this.listDate = listDate;
        this.bookTime =bookTime;
        this.unit = unit;
        this.modeName = modeName;
        this.clientName = clientName;
        this.testItem2 = testItem2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDateDay() {
        return dateDay;
    }

    public void setDateDay(String dateDay) {
        this.dateDay = dateDay;
    }

    public String getDateUser() {
        return dateUser;
    }

    public void setDateUser(String dateUser) {
        this.dateUser = dateUser;
    }

    public String getDateCase() {
        return dateCase;
    }

    public void setDateCase(String dateCase) {
        this.dateCase = dateCase;
    }

    public int getDateYear() {
        return dateYear;
    }

    public void setDateYear(int dateYear) {
        this.dateYear = dateYear;
    }

    public int getDateMonth() {
        return dateMonth;
    }

    public void setDateMonth(int dateMonth) {
        this.dateMonth = dateMonth;
    }

    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
    }

    public String getBookTime() {
        return bookTime;
    }

    public void setBookTime(String bookTime) {
        this.bookTime = bookTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getTestItem2() {
        return testItem2;
    }

    public void setTestItem2(String testItem2) {
        this.testItem2 = testItem2;
    }
}
