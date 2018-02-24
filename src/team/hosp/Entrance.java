package team.hosp;

import team.hosp.admin.Admin_Login_Menu;
import team.hosp.patient.*;
import team.hosp.doctor.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by hasuka on 2017/7/7.
 * Last updated by hasuka on 2017/7/13.
 */
public class Entrance extends JFrame {
    public Entrance() {
        setTitle("HOSPITAL - SELECT YOUR ROLE");
        setSize(300, 200);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((d.width - this.getWidth()) / 2, (d.height - this.getHeight()) / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridLayout(3, 1));
        JButton patientButton = new JButton("Patient");
        JButton doctorButton = new JButton("Doctor");
        JButton adminButton = new JButton("Administrator");
        //TODO Alter ActionListener
        patientButton.addActionListener(e -> {
            new Gansha();
            this.dispose();
        });
        doctorButton.addActionListener(e -> {
            new DoctorLoginFrame();
            this.dispose();
        });
        adminButton.addActionListener(e -> {
            new Admin_Login_Menu();
            this.dispose();
        });
        add(patientButton);
        add(doctorButton);
        add(adminButton);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Entrance();

    }
}


