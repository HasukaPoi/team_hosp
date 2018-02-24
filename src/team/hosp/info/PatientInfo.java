package team.hosp.info;

/**
 * Created by He Sikai on 2017/7/7.
 * Last Updated by He Sikai on 2017/7/8.
 */
public class PatientInfo {
    private int no;
    private String pname;
    private String dname;

    public int getNo() {
        return no;
    }

    public String getPname() {
        return pname;
    }

    public String getDname() {
        return dname;
    }

    public int getTreated() {
        return treated;
    }

    public int getMedicine() {
        return medicine;
    }

    private int treated;
    private int medicine;

    public PatientInfo(int no, String pname, String dname, int treated, int medicine) {
        this.no = no;
        this.pname = pname;
        this.dname = dname;
        this.treated = treated;
        this.medicine = medicine;
    }
}
