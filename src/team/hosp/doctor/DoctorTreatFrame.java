package team.hosp.doctor;

import team.hosp.info.*;
import team.hosp.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

/**
 * Created by hasuka on 2017/7/10.
 * Last Updated by hasuka on 2017/7/11.
 */
public class DoctorTreatFrame extends JFrame {
    private MedicineInfo currentMedicine;
    private java.util.List<MedicineInfo> medicineList;
    private PatientInfo currentPatient;

    public DoctorTreatFrame(DepartInfo currentDepart) {
        setTitle("Department: " + currentDepart.getDname());
        setSize(250, 200);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((d.width - this.getWidth()) / 2, (d.height - this.getHeight()) / 2);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        currentPatient = null;
        try {
            currentPatient = DBUtils.getFirstPatient(currentDepart);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Excpetion:" + e.getMessage(), "EXCEPTION", JOptionPane.ERROR_MESSAGE);
        }
        try {
            medicineList = DBUtils.findAllMedicine();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Excpetion:" + e.getMessage(), "EXCEPTION", JOptionPane.ERROR_MESSAGE);
        }
        JComboBox<String> mNameBox = new JComboBox<>();
        mNameBox.addItem("--NONE--");
        currentMedicine = new MedicineInfo("", 0, 0);
        JSpinner mAmountSpinner = new JSpinner(new SpinnerNumberModel(0, 0, currentMedicine.getAmount(), 1));
        ((JSpinner.DefaultEditor) mAmountSpinner.getEditor()).getTextField().setEditable(false);
        for (MedicineInfo m : medicineList) {
            mNameBox.addItem(m.getName());
        }
        mNameBox.addItemListener(e -> {
            if (mNameBox.getSelectedItem().equals("--NONE--")) {
                currentMedicine = null;
            } else
                for (MedicineInfo m : medicineList) {
                    if (m.getName().equals(mNameBox.getSelectedItem())) {
                        currentMedicine = m;
                        break;
                    }
                }
            mAmountSpinner.setModel(new SpinnerNumberModel(0, 0, (currentMedicine != null) ? currentMedicine.getAmount() : 0, 1));
            ((JSpinner.DefaultEditor) mAmountSpinner.getEditor()).getTextField().addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int keyChar = e.getKeyChar();
                    if (keyChar < KeyEvent.VK_0 || keyChar > KeyEvent.VK_9) e.consume();
                    if ((int) mAmountSpinner.getValue() > currentMedicine.getAmount())
                        mAmountSpinner.setValue(currentMedicine.getAmount());
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });

        });

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        JLabel label1 = new JLabel("当前病人：" + currentPatient.getPname());
        panel1.add(label1);
        this.add(panel1);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        JLabel label2 = new JLabel("Medicine: ");
        label2.setPreferredSize(new Dimension(80, 20));
        mNameBox.setPreferredSize(new Dimension(120, 20));
        panel2.add(label2);
        panel2.add(mNameBox);
        this.add(panel2);
        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        JLabel label3 = new JLabel("Amount:");
        label3.setPreferredSize(new Dimension(80, 20));
        mAmountSpinner.setPreferredSize(new Dimension(120, 20));
        panel3.add(label3);
        panel3.add(mAmountSpinner);
        this.add(panel3);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout());
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            boolean flag = true;
            try {
                if (currentMedicine == null)
                    DBUtils.treat(currentPatient.getNo());
                else if ((int) mAmountSpinner.getValue() == 0 && !mNameBox.getSelectedItem().equals("--NONE--"))
                    if (JOptionPane.showConfirmDialog(this, "Medicine chosen with an amount of 0. Continue?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                        DBUtils.treat(currentPatient.getNo());
                    else return;
                else if (currentMedicine.getAmount() - (int) mAmountSpinner.getValue() >= 0)
                    DBUtils.treat(currentPatient.getNo(), new MedicineInfo(currentMedicine.getName(), currentMedicine.getPrice(), currentMedicine.getAmount() - (int) mAmountSpinner.getValue()));
                else {
                    flag = false;
                    JOptionPane.showMessageDialog(this, "Medicine amount over upper limit.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                if (flag) {
                    JOptionPane.showMessageDialog(this, "Entry Succeed", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    new DoctorMainFrame(currentDepart);
                    this.dispose();
                }

            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(this, "Excpetion:" + e1.getMessage(), "EXCEPTION", JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            new DoctorMainFrame(currentDepart);
            dispose();
        });
        panel4.add(submitButton);
        panel4.add(cancelButton);
        add(panel4);
        this.getRootPane().setDefaultButton(submitButton);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DoctorTreatFrame(new DepartInfo("内科", "王"));
    }
}
