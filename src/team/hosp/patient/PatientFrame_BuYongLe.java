package team.hosp.patient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by hasuka on 2017/7/8.
 * Last updated by hasuka on 2017/7/8.
 */
public class PatientFrame_BuYongLe extends JFrame {
    public PatientFrame_BuYongLe() {
        setTitle("Patient");
        setSize(300, 200);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((d.width - this.getWidth()) / 2, (d.height - this.getHeight()) / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
        //上面是一个边界
        //setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setLayout(new GridLayout(2, 1));
        JButton regButton = new JButton("Register");
        JButton cancelButton = new JButton("Cancel Register");
        regButton.addActionListener(e -> System.out.println("应有新窗口"));
        //TODO Ying You Xin Chuang Kou
        add(regButton);
        add(cancelButton);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PatientFrame_BuYongLe();
    }

}
