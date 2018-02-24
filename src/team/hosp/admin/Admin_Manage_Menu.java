package team.hosp.admin;

import team.hosp.info.DepartInfo;
import team.hosp.info.MedicineInfo;
import team.hosp.info.PatientInfo;
import team.hosp.util.DBUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

/**
 * Created by sunsetwan on 10/07/2017.
 * 最後更新：２０１７・７・１３　作業者：ハスカ
 */
public class Admin_Manage_Menu {


    //public void action

    class ShowModule extends JPanel {

        JTable table;
        JScrollPane scrollPane;


        public ShowModule(String[] columnNames, Object[][] dataset)

        {
            super(new GridLayout(1, 0));
            //final JTable table;
            table = new JTable();
            updateTable(columnNames, dataset);
            table.setPreferredScrollableViewportSize(new Dimension(600, 600));
            table.setAutoCreateRowSorter(true);
            table.setFillsViewportHeight(true);
            scrollPane = new JScrollPane(table);
            add(scrollPane);
            //add(table);

        }

        void updateTable(String[] columnNames, Object[][] dataset) {
            table.setModel(new DefaultTableModel(dataset, columnNames));
            scrollPane = new JScrollPane(table);
        }


    }


    // Boolean DefaultJudge = false;
    Boolean Judge = false;

    String[] columnNamesPatient = {"No",
            "Name",
            "Depart",
            "Treated",
            "Medicine"};

    String[] columnNamesDepart = {"Depart Name",
            "Doctor Name"};

    String[] columnNamesMedicine = {"Name",
            "Price",
            "Amount"};

    private JFrame AMM;
    private java.util.List<PatientInfo> patientInfoList;
    private java.util.List<DepartInfo> departInfoList;
    private java.util.List<MedicineInfo> medicineInfoList;
    private ShowModule showPatient;
    private ShowModule showDepart;
    private ShowModule showMedicine;
    TabDemo tabDemo;


    private void refreshMedicine() {
        //SHOW MEDICINE
        int index;
        try {
            medicineInfoList = DBUtils.findAllMedicine();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(AMM, "Exception: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        Object[][] mdata = new Object[medicineInfoList.size()][3];
        index = 0;
        for (MedicineInfo mi : medicineInfoList) {
            mdata[index][0] = mi.getName();
            mdata[index][2] = mi.getAmount();
            mdata[index][1] = mi.getPrice();
            ++index;
        }
        try {
            if (showMedicine != null) {
                showMedicine.updateTable(columnNamesMedicine, mdata);
                //showMedicine.updateUI();

            } else {
                showMedicine = new ShowModule(columnNamesMedicine, mdata);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(AMM, "Exception: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        showMedicine.setVisible(false);
    }

    public Admin_Manage_Menu() {

        //JFrame
        AMM = new JFrame("ADMIN MAIN PAGE");
        AMM.setSize(500, 500);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        AMM.setLocation((d.width - AMM.getWidth()) / 2, (d.height - AMM.getHeight()) / 2);
        AMM.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        AMM.setLayout(new BoxLayout(AMM.getContentPane(), BoxLayout.Y_AXIS));

        //SHOW PATIENT
        try {
            patientInfoList = DBUtils.findAllPatient();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(AMM, "Exception: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        Object[][] pData = new Object[patientInfoList.size()][5];
        int index = 0;
        for (PatientInfo p : patientInfoList) {
            pData[index][0] = p.getNo();
            pData[index][1] = p.getPname();
            pData[index][2] = p.getDname();
            pData[index][3] = p.getTreated() == 0 ? "Untreated" : (p.getTreated() == 1 ? "Treated" : (p.getTreated() == 2 ? "Cancelled" : "-ERROR-"));
            pData[index][4] = p.getMedicine() == 0 ? "No" : (p.getMedicine() == 1 ? "Yes" : "-ERROR-");
            ++index;
        }
        showPatient = null;
        try {
            showPatient = new ShowModule(columnNamesPatient, pData);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(AMM, "Exception: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        showPatient.setVisible(false);
        AMM.add(showPatient);


        //SHOW DEPARTMENT
        try {
            departInfoList = DBUtils.findAllDepart();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(AMM, "Exception: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        Object[][] ddata = new Object[departInfoList.size()][2];
        index = 0;
        for (DepartInfo di : departInfoList) {
            ddata[index][0] = di.getDname();
            ddata[index][1] = di.getDoctor_name();
            ++index;
        }
        showDepart = null;
        try {
            showDepart = new ShowModule(columnNamesDepart, ddata);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(AMM, "Exception: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        showDepart.setVisible(false);

        refreshMedicine();

        //TabDemo
        tabDemo = new TabDemo();


        tabDemo.card_btnModify.addActionListener(e -> {
            try {
                System.out.println(showMedicine.table.getSelectedRow());
                new ModifyInfoMenu(medicineInfoList.get(showMedicine.table.getSelectedRow()));
                AMM.dispose();
            } catch (ArrayIndexOutOfBoundsException e1) {
                JOptionPane.showMessageDialog(AMM, "No row selected", "WARNING", JOptionPane.WARNING_MESSAGE);
            }
            //TODO Genggai Zheli de Method Diaoyong Yiji Zai DBUtils Limian Xie Fangfa
        });
        tabDemo.card_btnAdd.addActionListener(e -> {
            new AddInfoMenu();
            AMM.dispose();
        });
        tabDemo.card_btnDelete.addActionListener(e -> {
            try {
                if (JOptionPane.showConfirmDialog(AMM, "Confirm Delete?", "CONFIRM", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (DBUtils.delMedicine(medicineInfoList.get(showMedicine.table.getSelectedRow())))
                        JOptionPane.showMessageDialog(AMM, "Delete Succeed", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(AMM, "Delete Failed", "ERROR", JOptionPane.ERROR_MESSAGE);
                    new Admin_Manage_Menu();
                    AMM.dispose();
                }
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(AMM, "Exception: " + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
        tabDemo.addComponentToPane(showPatient, showDepart, showMedicine, AMM);

        AMM.setVisible(true);

    }


    public static void main(String[] Args) {
        new Admin_Manage_Menu();
    }


}

