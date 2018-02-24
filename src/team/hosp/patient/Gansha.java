package team.hosp.patient;

import team.hosp.BackToEntrance;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Fan Yiqi on Buzhidao Shenme Shihou.
 * Last updated by hasuka on 2017/7/13.
 */
public class Gansha extends JFrame {

    public Gansha() {
        //TODO 返回主界面页面按钮

        setTitle("From Page");
        this.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setLayout(new FlowLayout());
        setSize(300, 200);
        setResizable(false);

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = size.width;
        int screenHeight = size.height;
        int x = (screenWidth - this.getWidth()) / 2;
        int y = (screenHeight - this.getHeight()) / 2;
        this.setLocation(x, y);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new BackToEntrance());
        JLabel greeting = new JLabel("Welcome ! What do you want to do ?");
        JButton regBtn = new JButton("Register");
        regBtn.setPreferredSize(new Dimension(150, 30));
        regBtn.addActionListener(e -> {
            new Laile();
            dispose();//选择按钮后关闭当前窗口
        });
        JButton cancelRegButton = new JButton("Cancel Register");
        cancelRegButton.setPreferredSize(new Dimension(150, 30));
        cancelRegButton.addActionListener(e -> {
            new Liule();
            dispose();
        });
        add(greeting);
        add(Box.createVerticalStrut(40));
        add(regBtn);
        add(cancelRegButton);

        setVisible(true);

    }

    public static void main(String[] args) {
        new Gansha();
    }
}
