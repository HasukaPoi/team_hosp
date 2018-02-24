package team.hosp.admin;

import java.awt.*;
import javax.swing.*;


/**
 * Created by sunsetwan on 11/07/2017.
 * Last updated by hasuka on 2017/7/13.
 */
public class TabDemo {
    final static String PATIENT_PANEL = "Patient Info";
    final static String DEPART_PANEL = "Department Info";
    final static String MEDICINE_PANEL = "Medicine Info";
    final static int extraWindowWidth = 100;

    //Create Button
    //JButton card_btnRefresh=new JButton("Refresh");
    JButton card_btnAdd = new JButton("Add Info");
    JButton card_btnDelete = new JButton("Delete Info");
    JButton card_btnModify = new JButton("Modify Info");


    JButton card_btnAdd_fake = new JButton("Add Info");
    JButton card_btnDelete_fake = new JButton("Delete Info");
    JButton card_btnModify_fake = new JButton("Modify Info");

    public void addComponentToPane(Component component1, Component component2, Component component3, Container pane) {
        JTabbedPane tabbedPane = new JTabbedPane();


        //Create JPanel With Buttons
        JPanel card_BtnLine = new JPanel();
        card_BtnLine.setLayout(new BoxLayout(card_BtnLine, BoxLayout.X_AXIS));
        //card_BtnLine.add(card_btnRefresh);
        card_BtnLine.add(card_btnAdd);
        card_BtnLine.add(card_btnDelete);
        card_BtnLine.add(card_btnModify);

        //Create NullJPanel
        JPanel card_FakeBtnLine = new JPanel();
        card_FakeBtnLine.setLayout(new BoxLayout(card_FakeBtnLine, BoxLayout.X_AXIS));
        card_FakeBtnLine.add(card_btnAdd_fake);
        card_FakeBtnLine.add(card_btnDelete_fake);
        card_FakeBtnLine.add(card_btnModify_fake);
        //This is a fake ButtonLine, use for Zhan Wei (However, Kan Shang Qu it doesn't work )


        //Create the "cards".
        JPanel card_InfoOfPatient = new JPanel() {
            //Make the panel wider than it really needs, so
            //the window's wide enough for the tabs to stay
            //in one row.
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += extraWindowWidth;
                return size;
            }
        };
        //card_InfoOfPatient.setPreferredSize(new Dimension(100,100));

        card_InfoOfPatient.setLayout(new BoxLayout(card_InfoOfPatient, BoxLayout.Y_AXIS));
        card_InfoOfPatient.add(component1);
        card_InfoOfPatient.add(Box.createVerticalStrut(40));
        card_FakeBtnLine.setVisible(false);
        card_InfoOfPatient.add(card_FakeBtnLine);

        component1.setVisible(true);

        JPanel card_InfoOfDepartment = new JPanel();
        card_InfoOfDepartment.setLayout(new BoxLayout(card_InfoOfDepartment, BoxLayout.Y_AXIS));
        card_InfoOfDepartment.add(component2);
        card_InfoOfDepartment.add(Box.createVerticalStrut(40));
        card_InfoOfPatient.add(card_FakeBtnLine);

        /*card_InfoOfDepartment.add(card_btnAdd);

        card_InfoOfDepartment.add(card_btnDelete);

        card_InfoOfDepartment.add(card_btnModify);*/

        component2.setVisible(true);

        JPanel card_InfoOfMedicine = new JPanel();
        card_InfoOfMedicine.setLayout(new BoxLayout(card_InfoOfMedicine, BoxLayout.Y_AXIS));
        card_InfoOfMedicine.add(component3);
        card_InfoOfMedicine.add(Box.createVerticalStrut(10));
        card_InfoOfMedicine.add(card_BtnLine);

        /*card_InfoOfDepartment.add(card_btnAdd);

        card_InfoOfDepartment.add(card_btnDelete);

        card_InfoOfDepartment.add(card_btnModify);*/

        component3.setVisible(true);


        tabbedPane.addTab(PATIENT_PANEL, card_InfoOfPatient);
        tabbedPane.addTab(DEPART_PANEL, card_InfoOfDepartment);
        tabbedPane.addTab(MEDICINE_PANEL, card_InfoOfMedicine);

        pane.add(tabbedPane, BorderLayout.CENTER);


    }

    public static void creatAndShowGUI() {

    }


}