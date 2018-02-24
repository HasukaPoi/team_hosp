package team.hosp.admin;

import team.hosp.info.MedicineInfo;
import team.hosp.util.DBUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Created by sunsetwan on 11/07/2017.
 * Last updated by hasuka on 2017/7/13.
 */
public class AddInfoMenu extends JFrame {
    private JTextField nameField;
    private JTextField priceField;
    private JTextField amountField;

    public static class SetComponents {

        public JLabel CreateLabel(String title) {
            JLabel label = new JLabel(title);
            label.setPreferredSize(new Dimension(120, 20));
            return label;
        }

        public Component CreatePanelLine(Component label, Component textfield, Component pane) {
            JPanel panelLine = new JPanel();
            panelLine.setLayout(new BoxLayout(panelLine, BoxLayout.X_AXIS));
            panelLine.add(Box.createHorizontalStrut(10));
            panelLine.add(label);
            panelLine.add(textfield);
            return panelLine;
        }

    }

    public AddInfoMenu() {

        setSize(290, 200);
        setTitle("ADD MEDICINE INFO");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((d.width - this.getWidth()) / 2, (d.height - this.getHeight()) / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));


        SetComponents setComponents = new SetComponents();

        JButton submit_AddInfo = new JButton("Submit");
        JButton cancel_AddInfo = new JButton("Cancel");
        JPanel submit_cancel_Line = new JPanel();
        submit_cancel_Line.setLayout(new BoxLayout(submit_cancel_Line, BoxLayout.X_AXIS));
        submit_cancel_Line.add(submit_AddInfo);
        submit_cancel_Line.add(cancel_AddInfo);


        nameField = new JTextField();
        priceField = new JTextField();
        amountField = new JTextField();
        add(setComponents.CreatePanelLine(setComponents.CreateLabel("Medicine name"), nameField, this.getContentPane()));
        add(setComponents.CreatePanelLine(setComponents.CreateLabel("Medicine Price"), priceField, this.getContentPane()));
        add(setComponents.CreatePanelLine(setComponents.CreateLabel("Medicine Amount"), amountField, this.getContentPane()));
        add(submit_cancel_Line);


        //EventHandler
        submit_AddInfo.addActionListener(e -> {
            //TODO
            try {
                if (DBUtils.addMedicine(new MedicineInfo(nameField.getText(), Integer.parseInt(priceField.getText()), Integer.parseInt(amountField.getText())))) {
                    JOptionPane.showMessageDialog(this, "Add Accepted", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
                    new Admin_Manage_Menu();
                    this.dispose();
                } else JOptionPane.showMessageDialog(this, "Add Denied", "ERROR", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(this, "SQL EXCEPTION:\n" + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }


        });


        cancel_AddInfo.addActionListener(e -> {
            new Admin_Manage_Menu();
            this.dispose();

        });
        this.getRootPane().setDefaultButton(submit_AddInfo);

        setVisible(true);
    }


    public static void main(String[] Args) {
        AddInfoMenu addInfoMenu = new AddInfoMenu();

    }


}