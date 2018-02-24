package team.hosp.admin;

import team.hosp.admin.AddInfoMenu.*;
import team.hosp.info.MedicineInfo;
import team.hosp.util.DBUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Created by sunsetwan on 11/07/2017.
 * Last updated by hasuka on 2017/7/13.
 */
public class ModifyInfoMenu extends JFrame {

    private JTextField nameField;
    private JTextField priceField;
    private JTextField amountField;

    public ModifyInfoMenu(MedicineInfo mi) {
        //System.out.println(mi.getName());
        setTitle("MODIFY MEDICINE INFO");
        setSize(290, 200);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((d.width - this.getWidth()) / 2, (d.height - this.getHeight()) / 2);
        //  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setAlwaysOnTop(true);

        SetComponents set_Components = new SetComponents();


        JButton submit_ModifyInfo = new JButton("Submit");
        JButton cancel_ModifyInfo = new JButton("Cancel");
        JPanel submit_cancel_Line_ModifyInfo = new JPanel();
        submit_cancel_Line_ModifyInfo.setLayout(new BoxLayout(submit_cancel_Line_ModifyInfo, BoxLayout.X_AXIS));
        submit_cancel_Line_ModifyInfo.add(submit_ModifyInfo);
        submit_cancel_Line_ModifyInfo.add(cancel_ModifyInfo);


        nameField = new JTextField(mi.getName());
        priceField = new JTextField(Integer.toString(mi.getPrice()));
        amountField = new JTextField(Integer.toString(mi.getAmount()));
        add(set_Components.CreatePanelLine(set_Components.CreateLabel("Medicine name"), nameField, this.getContentPane()));
        add(set_Components.CreatePanelLine(set_Components.CreateLabel("Medicine Price"), priceField, this.getContentPane()));
        add(set_Components.CreatePanelLine(set_Components.CreateLabel("Medicine Amount"), amountField, this.getContentPane()));
        add(submit_cancel_Line_ModifyInfo);
        this.getRootPane().setDefaultButton(submit_ModifyInfo);
        setVisible(true);

        submit_ModifyInfo.addActionListener(e -> {
            try {
                if (DBUtils.alterMedicine(new MedicineInfo(nameField.getText(), Integer.parseInt(priceField.getText()), Integer.parseInt(amountField.getText())))) {
                    JOptionPane.showMessageDialog(this, "Edit Accepted", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
                    new Admin_Manage_Menu();
                    this.dispose();
                } else JOptionPane.showMessageDialog(this, "Edit Denied", "ERROR", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(this, "SQL EXCEPTION:\n" + e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancel_ModifyInfo.addActionListener(e -> {

            new Admin_Manage_Menu();
            this.dispose();
            //new Admin_Manage_Menu();

        });
    }

    public static void main(String[] Args) {
        ModifyInfoMenu modifyInfoMenu = new ModifyInfoMenu(new MedicineInfo("", 0, 0));
        modifyInfoMenu.setTitle("Modify the Info of the Medicine");
    }


}
