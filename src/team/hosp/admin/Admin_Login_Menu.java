package team.hosp.admin;

import team.hosp.BackToEntrance;
import team.hosp.Entrance;
import team.hosp.util.DBUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


/**
 * Created by sunsetwan on 08/07/2017.
 * Last updated by hasuka on 2017/7/13.
 */
public class Admin_Login_Menu {

    class PanelLine extends JPanel {
        Component component;

        PanelLine(String title, Component comp) {
            setLayout(new FlowLayout());
            JLabel jlabel = new JLabel(title);
            jlabel.setPreferredSize(new Dimension(80, 20));
            add(jlabel);
            component = comp;
            component.setPreferredSize(new Dimension(120, 20));
            add(component);


        }
    }

    public Admin_Login_Menu() {
        //JFrame
        JFrame jfAMenu = new JFrame("ADMINISTRATOR LOGIN");
        jfAMenu.setSize(300, 200);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        jfAMenu.setLocation((d.width - jfAMenu.getWidth()) / 2, (d.height - jfAMenu.getHeight()) / 2);

        //It's important.
        jfAMenu.setLayout(new BoxLayout(jfAMenu.getContentPane(), BoxLayout.Y_AXIS));
        jfAMenu.add(Box.createVerticalStrut(10));
        jfAMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Components
        JButton btn_Confirm = new JButton("Confirm");
        JButton btn_Cancel = new JButton("Cancel");
        JTextField jtx_Account = new JTextField();
        JTextField jtx_Password = new JPasswordField();
        //JPanel_one
        PanelLine jp_Account = new PanelLine("Account", jtx_Account);

        //JPanel_Two
        PanelLine jp_Password = new PanelLine("Password", jtx_Password);

        //JPanel_Three
        JPanel jp_BtnConfirm = new JPanel();

        jp_BtnConfirm.add(btn_Confirm);
        jp_BtnConfirm.add(btn_Cancel);
        jp_BtnConfirm.setLayout(new BoxLayout(jp_BtnConfirm, BoxLayout.X_AXIS));

        jfAMenu.addWindowListener(new BackToEntrance());
        //Add Components
        jfAMenu.add(jp_Account);

        jfAMenu.add(Box.createVerticalStrut(10));
        jfAMenu.add(jp_Password);
        jfAMenu.add(jp_BtnConfirm);
        jfAMenu.add(Box.createVerticalGlue());
        jfAMenu.getRootPane().setDefaultButton(btn_Confirm);

        jfAMenu.setVisible(true);

        //EventHandler
        btn_Confirm.addActionListener(e -> {
            try {
                if (!jtx_Account.getText().equals("") && !jtx_Password.getText().equals(""))
                    if (DBUtils.adminLoginChek(jtx_Account.getText(), jtx_Password.getText())) {
                        new Admin_Manage_Menu();
                        jfAMenu.dispose();
                    } else JOptionPane.showMessageDialog(jfAMenu, "Wrong ID or PW", "ERROR", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(jfAMenu, "Exception:" + e1.getMessage(), "EXCEPTION", JOptionPane.ERROR_MESSAGE);
            }
        });

        btn_Cancel.addActionListener(e -> {
            new Entrance();
            jfAMenu.dispose();
        });
    }

    public static void main(String[] Args) {
        new Admin_Login_Menu();
    }
}
