package work;

public class WLU {
    private Long id;
    private String operatorName;
    private String listDate;
    private int dateYear;
    private int dateMonth;
    private int testTimeStart1;
    private int testTimeStart2;
    private int testTimeEnd1;
    private int testTimeEnd2;
    private String clientName;
    private String modeName;
    private String testFileName;
    private String remark;
    private String server;
    private String testItem;
    private String testItem2;
    private String unit;
    private Double TC;


    public WLU(String operatorName, String listDate, int dateYear, int dateMonth, int testTimeStart1, int testTimeStart2, int testTimeEnd1, int testTimeEnd2, String clientName, String modeName, String testFileName, String remark, String server, String testItem, String testItem2, String unit, Double TC) {
        this.operatorName = operatorName;
        this.listDate = listDate;
        this.dateYear = dateYear;
        this.dateMonth =dateMonth;
        this.testTimeStart1 = testTimeStart1;
        this.testTimeStart2 = testTimeStart2;
        this.testTimeEnd1 = testTimeEnd1;
        this.testTimeEnd2 = testTimeEnd2;
        this.clientName = clientName;
        this.modeName = modeName;
        this.testFileName = testFileName;
        this.remark = remark;
        this.server = server;
        this.testItem = testItem;
        this.testItem2 = testItem2;
        this.TC= TC;
        this.unit= unit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
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

    public int getTestTimeStart1() { return testTimeStart1; }

    public void setTestTimeStart1(int testTimeStart1) { this.testTimeStart1 = testTimeStart1; }

    public int getTestTimeStart2() { return testTimeStart2; }

    public void setTestTimeStart2(int testTimeStart2) { this.testTimeStart2 = testTimeStart2; }

    public int getTestTimeEnd1() { return testTimeEnd1; }

    public void setTestTimeEnd1(int testTimeEnd1) {this.testTimeEnd1 = testTimeEnd1; }

    public int getTestTimeEnd2() { return testTimeEnd2; }

    public void setTestTimeEnd2(int testTimeEnd2) { this.testTimeEnd2 = testTimeEnd2; }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public String getTestFileName() {
        return testFileName;
    }

    public void setTestFileName(String testFileName) {
        this.testFileName = testFileName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getTestItem() {
        return testItem;
    }

    public void setTestItem(String testItem) {
        this.testItem = testItem;
    }

    public String getTestItem2() {
        return testItem2;
    }

    public void setTestItem2(String testItem2) {
        this.testItem2 = testItem2;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getTC() {
        return TC;
    }

    public void setTC(Double getTC) {
        this.TC = TC;
    }
}
