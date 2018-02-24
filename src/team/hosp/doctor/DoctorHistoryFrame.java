package team.hosp.doctor;

import team.hosp.BackToEntrance;
import team.hosp.info.DepartInfo;
import team.hosp.info.PatientInfo;
import team.hosp.util.DBUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by hasuka on 2017/7/13.
 */
public class DoctorHistoryFrame extends JFrame {
    String[] columnNamesPatient = {"No",
            "Name",
            "Depart",
            "Treated",
            "Medicine"};

    public DoctorHistoryFrame(DepartInfo currentDepart) {
        setTitle("DEPARTMENT: " + currentDepart.getDname());
        setSize(500, 500);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((d.width - this.getWidth()) / 2, (d.height - this.getHeight()) / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                new DoctorMainFrame(currentDepart);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        JTable patientTable = new JTable();
        patientTable.setModel(new DefaultTableModel(getPatientInfo(), columnNamesPatient));
        this.add(new JScrollPane(patientTable));
        setVisible(true);

    }

    private Object[][] getPatientInfo() {
        java.util.List<PatientInfo> patientInfoList;

        Object[][] pData = new Object[0][];
        try {
            patientInfoList = DBUtils.findAllPatient();
            pData = new Object[patientInfoList.size()][5];
            int index = 0;
            for (PatientInfo p : patientInfoList) {
                pData[index][0] = p.getNo();
                pData[index][1] = p.getPname();
                pData[index][2] = p.getDname();
                pData[index][3] = p.getTreated() == 0 ? "Untreated" : (p.getTreated() == 1 ? "Treated" : (p.getTreated() == 2 ? "Cancelled" : "-ERROR-"));
                pData[index][4] = p.getMedicine() == 0 ? "No" : (p.getMedicine() == 1 ? "Yes" : "-ERROR-");
                ++index;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Exception: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return pData;

    }
}
