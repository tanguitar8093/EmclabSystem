package work;

public class User {
    private  Long id;
    private  String unit;
    private  String name;
    private  String job;
    private  String account;
    private  String password;
    private  String email;
    private  int power;

    public User(String unit, String name, String job, String account, String password, String email,int power){
        this.unit =unit;
        this.name=name;
        this.job=job;
        this.account=account;
        this.password=password;
        this.email=email;
        this.power=power;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

}

