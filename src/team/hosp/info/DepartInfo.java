package team.hosp.info;

/**
 * Created by sunsetwan on 07/07/2017.
 * Last Updated by hasuka on 2017/07/08.
 */
public class DepartInfo {
    public String dname;
    public String doctor_name;

    DepartInfo() {

    }

    public DepartInfo(String dname, String doctor_name) {
        this.dname = dname;
        this.doctor_name = doctor_name;
    }


    public String getDname() {
        return dname;
    }

    public String getDoctor_name() {
        return doctor_name;
    }


}
