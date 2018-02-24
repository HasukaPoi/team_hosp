package team.hosp.patient;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hasuka on 2017/7/13.
 * Last updated by hasuka on 2017/7/13.
 */
class Line extends JPanel {
    Component field;

    Line(String info, Component f) {
        setLayout(new FlowLayout());
        JLabel label1 = new JLabel(info);
        label1.setPreferredSize(new Dimension(100, 20));
        add(label1);
        field = f;
        field.setPreferredSize(new Dimension(120, 20));
        add(field);
    }
}