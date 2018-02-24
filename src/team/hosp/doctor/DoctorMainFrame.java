package team.hosp.doctor;

import team.hosp.BackToEntrance;
import team.hosp.info.DepartInfo;
import team.hosp.info.PatientTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by hasuka on 2017/7/10.
 * Last Updated by hasuka on 2017/7/10.
 */
public class DoctorMainFrame extends JFrame {
    public DoctorMainFrame(DepartInfo currentDepart) {
        setTitle("DEPARTMENT: " + currentDepart.getDname());
        setSize(300, 300);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((d.width - this.getWidth()) / 2, (d.height - this.getHeight()) / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.addWindowListener(new BackToEntrance());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        JButton treatButton = new JButton("Treat");
        this.getRootPane().setDefaultButton(treatButton);
        JButton historyButton = new JButton("History");
        //TODO
        treatButton.addActionListener(e -> {
            new DoctorTreatFrame(currentDepart);
            this.dispose();
        });
        historyButton.addActionListener(e ->{
            new DoctorHistoryFrame(currentDepart);
            this.dispose();
        });
        buttonPanel.add(treatButton);
        buttonPanel.add(historyButton);
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel("Current Queue"));
        this.add(buttonPanel);
        this.add(jPanel);
        this.add(new JScrollPane(new PatientTable(currentDepart)));
        setVisible(true);
    }

    public static void main(String[] args) {
        new DoctorMainFrame(new DepartInfo("内科", ""));
    }


}
