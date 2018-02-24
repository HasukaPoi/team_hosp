package team.hosp.info;

import team.hosp.util.DBUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by hasuka on 2017/7/10.
 * Last updated by hasuka on 2017/7/10.
 */
public class PatientTable extends JTable {
    private String[] columnName;

    public PatientTable() {

        columnName = new String[]{"No.", "Name", "Depart", "Treated", "Medicine"};
        refresh_administrator();

    }

    public PatientTable(DepartInfo d) {
        columnName = new String[]{"No.", "Name"};
        refresh_doctor(d);
    }

    private void refresh_administrator() {
        try {
            java.util.List<PatientInfo> pList = DBUtils.findAllPatient();
            int num = pList.size();
            Object[][] data = new Object[num][5];
            int index = 0;
            for (PatientInfo p : pList) {
                data[index][0] = p.getNo();
                data[index][1] = p.getPname();
                data[index][2] = p.getDname();
                switch (p.getTreated()) {
                    case 0:
                        data[index][3] = "No";
                        break;
                    case 1:
                        data[index][3] = "Yes";
                        break;
                    case 2:
                        data[index][3] = "Cancelled";
                        break;
                }
                data[index][4] = p.getMedicine() == 0 ? "No" : "Yes";
                ++index;
            }
            setModel(new DefaultTableModel(data, columnName));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "发生异常：" + e.getMessage() + "\n请联系管理员处理");
            e.printStackTrace();
        }

    }

    private void refresh_doctor(DepartInfo d) {
        try {
            java.util.List<PatientInfo> pList = DBUtils.findUntreatedPatient(d);
            int num = pList.size();
            Object[][] data = new Object[num][2];
            int index = 0;
            for (PatientInfo p : pList) {
                data[index][0] = p.getNo();
                data[index][1] = p.getPname();
                ++index;
            }
            setModel(new DefaultTableModel(data, columnName));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "发生异常：" + e.getMessage() + "\n请联系管理员处理");
            e.printStackTrace();
        }
    }

    public void history_doctor(){}

}
