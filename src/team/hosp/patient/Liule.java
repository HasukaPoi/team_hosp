package team.hosp.patient;

import team.hosp.BackToEntrance;
import team.hosp.Entrance;
import team.hosp.info.PatientInfo;
import team.hosp.util.DBUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

/**
 * Created by Fan Yiqi on 2017/7/11.
 * Last updated by hasuka on 2017/7/13.
 */
public class Liule extends JFrame {

    public Liule() {

        new JFrame();
        setTitle("Cancel Register");
        this.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
        setSize(300, 200);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = size.width;
        int screenHeight = size.height;
        int x = (screenWidth - this.getWidth()) / 2;
        int y = (screenHeight - this.getHeight()) / 2;
        this.setLocation(x, y);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextField numberField = new JTextField();
        JTextField nameField = new JTextField();
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        this.addWindowListener(new BackToEntrance());

        submitButton.addActionListener(e -> {
            if (!numberField.getText().equals("") && !nameField.getText().equals(""))
                if (JOptionPane.showConfirmDialog(this, "Please confirm your information:\n No." + Integer.parseInt(numberField.getText()) + "Name: " + nameField.getText(), "CONFIRM", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        if (DBUtils.cancelReg(Integer.parseInt(numberField.getText()), nameField.getText())) {
                            JOptionPane.showMessageDialog(this, "Cancelled.", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
                            new Entrance();
                            dispose();
                        } else
                            JOptionPane.showMessageDialog(this, "No match record.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(this, "SQL EXCEPTION:\n" + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
        });

        cancelButton.addActionListener(e -> {
            new Gansha();
            dispose();
        });

        add(Box.createVerticalStrut(10));
        add(new Line("Number:", numberField));
        add(new Line("Name:", nameField));
        JPanel buttonLine = new JPanel();
        buttonLine.setLayout(new FlowLayout());
        buttonLine.add(submitButton);
        buttonLine.add(cancelButton);
        add(buttonLine);
        this.getRootPane().setDefaultButton(submitButton);

        setVisible(true);
    }
}
