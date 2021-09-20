/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dj sean
 */
public class ServerMathot {

    public static void WriteTableSql(String insertInto) {
        try {
            String fqurl = "jdbc:mysql://localhost:3306/basic_signin";
            String user = "root";
            String password = "Hati@0793";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection mcon = DriverManager.getConnection(fqurl, user, password);
            Statement stmnt = (Statement) mcon.createStatement();
            stmnt.executeUpdate(insertInto);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServerMathot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean checkAccounetSignUp(String Sql) {
        boolean warning = false;
        String fqurl = "jdbc:mysql://localhost:3306/basic_signin";
        String user = "root";
        String password = "Hati@0793";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection mcon = DriverManager.getConnection(fqurl, user, password);
            Statement stmnt = mcon.createStatement();
            ResultSet result = stmnt.executeQuery(Sql);
            while (result.next()) {
                warning = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);

        }

        return warning;

    }

    public static boolean checkAccounetLogIn(String Sql, String paw) {
        boolean check = false;

        String fqurl = "jdbc:mysql://localhost:3306/basic_signin";
        String user = "root";
        String password = "Hati@0793";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection mcon = DriverManager.getConnection(fqurl, user, password);
            Statement stmnt = mcon.createStatement();
            ResultSet result = stmnt.executeQuery(Sql);
            result.next();

            if (paw.equals(result.getString("password"))) {
                check = true;
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);

        }
        return check;
    }

}
