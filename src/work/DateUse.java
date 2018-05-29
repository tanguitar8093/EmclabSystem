package work;

public class DateUse {
    private long id;
    private String dateDay;
    private String dateUser;
    private String dateCase;
    private String modeName;
    private String clientName;




    public DateUse(String dateDay, String dateUser, String dateCase, String modeName, String clientName) {
        this.dateDay = dateDay;
        this.dateUser = dateUser;
        this.dateCase = dateCase;
        this.modeName = modeName;
        this.clientName = clientName;
    }
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getDateDay() { return dateDay; }

    public void setDateDay(String dateDay) { this.dateDay = dateDay; }

    public String getDateUser() { return dateUser; }

    public void setDateUser(String dateUser) { this.dateUser = dateUser; }

    public String getDateCase() { return dateCase; }

    public void setDateCase(String dateCase) { this.dateCase = dateCase; }

    public String getModeName() { return modeName; }

    public void setModeName(String modeName) { this.modeName = modeName;}

    public String getClientName() { return clientName; }

    public void setClientName(String clientName) { this.clientName = clientName; }
}
