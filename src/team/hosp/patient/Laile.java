package team.hosp.patient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import team.hosp.Entrance;
import team.hosp.info.DepartInfo;
import team.hosp.util.DBUtils;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

/**
 * Created by fan Yiqi on 2017/7/8.
 * Last updated by hasuka on 2017/7/13.
 */
public class Laile extends JFrame {

    public Laile() {

        new JFrame();
        setTitle("挂号");
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


        JTextField patientName = new JTextField();
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        JComboBox<String> departBox = new JComboBox<>();

        java.util.List<DepartInfo> dList;
        try {
            dList = DBUtils.findAllDepart();
            for (DepartInfo d : dList)
                departBox.addItem(d.getDname());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "SQL EXCEPTION:\n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                new Gansha();
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

        submitButton.addActionListener(e -> {
            if (!patientName.getText().trim().equals(""))
                if (JOptionPane.showConfirmDialog(this, "Information Confirm:\n" + patientName.getText() + "  " + departBox.getSelectedItem(), "CONFIRM", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        int a[] = DBUtils.newReg(patientName.getText(), (String) departBox.getSelectedItem());
                        //TODO 返回病人流水号和排队号
                        JOptionPane.showMessageDialog(this, "Succeed! Your number is " + a[0] + ". \nIn the " + getOrdinal(a[1]) + " place of " + departBox.getSelectedItem() + ".");
                        new Entrance();
                        this.dispose();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
        });
        cancelButton.addActionListener(e -> {
            new Gansha();
            dispose();
        });

        add(Box.createVerticalStrut(10));
        add(new Line("Your Name. ", patientName));
        add(new Line("Department: ", departBox));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel);
        this.getRootPane().setDefaultButton(submitButton);

        setVisible(true);
    }

    public static String getOrdinal(int number) {
        String tail;
        if (number <= 0) {
            return "number must > 0";
        } else if (1 == number) {
            return "1st";
        } else if (2 == number) {
            return "2nd";
        } else if (number >= 20) {
            int last = number % 10;
            if (1 == last) {
                tail = "st";
            } else if (2 == last) {
                tail = "nd";
            } else if (3 == last) {
                tail = "rd";
            } else {
                tail = "th";
            }
        } else {
            tail = "th";
        }
        return number + tail;
    }


}

