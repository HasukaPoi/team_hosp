package team.hosp.util;

import team.hosp.info.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by hasuka on 2017/7/7.
 * Last updated by hasuka on 2017/7/13.
 */
public class DBUtils {
    private static Connection getConnection(String server) throws SQLException {
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String password = "";
        if (server.equals("")) server = "127.0.0.1";
        String url = "jdbc:mysql://" + (!server.equals("") ? server : "127.0.0.1") + ":3306/Hosp";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "JDBC DRIVER NOT FOUND", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return DriverManager.getConnection(url, user, password);
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static int[] newReg(String pname, String dname) throws SQLException {

        int[] data = new int[2];


        Connection conn = getConnection("");
        String sql = "insert into P(pname,dname) values(?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, pname);
        pstmt.setString(2, dname);
        pstmt.executeUpdate();
        close(null, pstmt, conn);

        conn = getConnection("");
        sql = "select count(no) from p where dname=? and treated=0";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, dname);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) data[1] = rs.getInt(1);
        close(rs, pstmt, conn);


        conn = getConnection("");
        sql = "select no from p where pname=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, pname);
        System.out.println("\t\t\t\t" + sql);
        rs = pstmt.executeQuery();
        while (rs.next()) data[0] = rs.getInt(1);
        close(rs, pstmt, conn);
        return data;
    }

    public static PatientInfo checkReg(int no) throws SQLException {
        Connection conn = getConnection("");
        String sql = "select * from P where no=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, no);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new PatientInfo(no, rs.getString("pname"), rs.getString("dname"), rs.getInt("treated"), rs.getInt("medicine"));

        } else throw new SQLException("Record not found");

    }

    public static boolean cancelReg(int no,String pname) throws SQLException {
        Connection conn = getConnection("");
        String sql = "update P set treated=2 where no=? and pname=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, no);
        pstmt.setString(2,pname);
        return pstmt.executeUpdate() > 0;
    }

    public static java.util.List<DepartInfo> findAllDepart() throws SQLException {
        java.util.List<DepartInfo> dList = new ArrayList<>();
        Connection conn = getConnection("");
        String sql = "select * from D";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            dList.add(new DepartInfo(rs.getString("dname"), rs.getString("doctor")));
        }
        return dList;
    }

    public static java.util.List<PatientInfo> findAllPatient() throws SQLException {
        java.util.List<PatientInfo> pList = new ArrayList<>();
        Connection conn = getConnection("");
        String sql = "select * from P";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            pList.add(new PatientInfo(rs.getInt("no"), rs.getString("pname"), rs.getString("dname"), rs.getInt("treated"), rs.getInt("medicine")));
        }
        return pList;
    }

    public static java.util.List<PatientInfo> findUntreatedPatient(DepartInfo d) throws SQLException {
        java.util.List<PatientInfo> pList = new ArrayList<>();
        Connection conn = getConnection("");
        String sql = "select * from P where dname=? and treated=0";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, d.getDname());
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            pList.add(new PatientInfo(rs.getInt("no"), rs.getString("pname"), rs.getString("dname"), rs.getInt("treated"), rs.getInt("medicine")));
        }
        return pList;
    }

    public static DepartInfo doctorLoginCheck(String doctor_name) throws SQLException {
        Connection conn = getConnection("");
        String sql = "select * from D where doctor=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, doctor_name);
        ResultSet rs = pstmt.executeQuery();
        DepartInfo d = new DepartInfo("null", "null");
        while (rs.next()) {
            d = new DepartInfo(rs.getString("dname"), rs.getString("doctor"));
        }
        return d;

    }

    public static boolean adminLoginChek(String account, String password) throws SQLException {
        Connection conn = getConnection("");
        String sql = "select * from U where account=? and password=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, account);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        return rs.next();
    }

    public static PatientInfo getFirstPatient(DepartInfo d) throws SQLException {
        Connection conn = getConnection("");
        String sql = "select * from P where dname=? and treated=0";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, d.getDname());
        ResultSet rs = pstmt.executeQuery();
        PatientInfo p;
        while (rs.next()) {
            p = new PatientInfo(rs.getInt("no"), rs.getString("pname"), rs.getString("dname"), rs.getInt("treated"), rs.getInt("medicine"));
            return p;
        }
        return null;

    }

    public static java.util.List<MedicineInfo> findAllMedicine() throws SQLException {
        java.util.List<MedicineInfo> mList = new ArrayList<>();
        Connection conn = getConnection("");
        String sql = "select * from M";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            mList.add(new MedicineInfo(rs.getString("mname"), rs.getInt("price"), rs.getInt("amount")));
        }
        return mList;
    }

    public static boolean treat(int no) throws SQLException {
        Connection conn = getConnection("");
        String sql = "update P set treated=1 where no=?";
        //String sql = (mi == null ? "update P set treated=1 where no=?" : "update P set treated=1 medicine=1 where no=?");
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, no);
        return pstmt.executeUpdate() > 0;
    }

    public static void treat(int no, MedicineInfo mi) throws SQLException {
        Connection conn = getConnection("");
        String sql = "update P set treated=1,medicine=1 where no=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, no);
        pstmt.executeUpdate();
        close(null, pstmt, conn);
        conn = getConnection("");
        sql = "update M set amount=? where mname=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, mi.getAmount());
        pstmt.setString(2, mi.getName());
        pstmt.executeUpdate();
        close(null, pstmt, conn);
    }

    public static boolean addMedicine(MedicineInfo mi) throws SQLException {
        Connection conn = getConnection("");
        String sql = "insert into M(mname,price,amount) values(?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, mi.getName());
        pstmt.setInt(2, mi.getPrice());
        pstmt.setInt(3, mi.getAmount());
        return pstmt.executeUpdate() > 0;
    }

    public static boolean alterMedicine(MedicineInfo mi) throws SQLException {
        Connection conn = getConnection("");
        String sql = "update M set price=?,amount=? where mname=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, mi.getPrice());
        pstmt.setInt(2, mi.getAmount());
        pstmt.setString(3, mi.getName());
        return pstmt.executeUpdate() > 0;
    }

    public static boolean delMedicine(MedicineInfo mi) throws SQLException{
        Connection conn = getConnection("");
        String sql = "delete from M where mname=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, mi.getName());
        return pstmt.executeUpdate() > 0;
    }


}
