package team.hosp.doctor;

import team.hosp.BackToEntrance;
import team.hosp.info.DepartInfo;
import team.hosp.util.DBUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

/**
 * Created by hasuka on 2017/7/8.
 * Last updated by hasuka on 2017/7/13.
 */
public class DoctorLoginFrame extends JFrame {
    public DoctorLoginFrame() {
        setTitle("DOCTOR LOGIN");
        setSize(250, 150);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((d.width - this.getWidth()) / 2, (d.height - this.getHeight()) / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new BackToEntrance());
        this.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridLayout(2, 1));
        JPanel nameLine = new JPanel();
        nameLine.setBorder(new EmptyBorder(10, 10, 10, 10));
        nameLine.setLayout(new FlowLayout());
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setPreferredSize(new Dimension(60, 20));
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(100, 20));
        nameLine.add(nameLabel);
        nameLine.add(nameField);
        this.add(nameLine);
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.X_AXIS));
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String doctor_name = nameField.getText();
            if (!doctor_name.trim().equals("")) {
                try {
                    DepartInfo check = DBUtils.doctorLoginCheck(doctor_name);
                    if (check.getDname().equals("null") && check.getDoctor_name().equals("null")) {
                        JOptionPane.showMessageDialog(this, "Depart not found");
                    } else {
                        new DoctorMainFrame(check);
                        this.dispose();
                    }
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(this, "Excpetion:" + e1.getMessage(), "EXCEPTION", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        this.getRootPane().setDefaultButton(loginButton);
        loginButton.setPreferredSize(new Dimension(80, 40));
        loginPanel.add(Box.createHorizontalGlue());
        loginPanel.add(loginButton);
        loginPanel.add(Box.createHorizontalGlue());
        this.add(loginPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DoctorLoginFrame();
    }
}
