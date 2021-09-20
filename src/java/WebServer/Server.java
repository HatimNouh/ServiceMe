/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
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
public class Server extends Thread {

    private final ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);

    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket server = serverSocket.accept();
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                DataInputStream in = new DataInputStream(server.getInputStream());
                String getIn = in.readUTF();

                if ("basic_form".equals(getIn.split(";")[3])) {
                    String check = new String();
                    check = "false";
                    if ("on".equals(getIn.split(";")[2])) {

                        if (ServerMathot.checkAccounetLogIn("Select * from Provide_Signin where Email = '" + getIn.split(";")[0] + "'", getIn.split(";")[1])) {
                            check = "true";
                        }
                        out.writeUTF(check);

                    } else {

                        if (ServerMathot.checkAccounetLogIn("Select * from custom_signin where Email = '" + getIn.split(";")[0] + "'", getIn.split(";")[1])) {
                            check = "true";
                        }
                        out.writeUTF(check);

                    }

                }

                else {

                    if ("on".equals(getIn.split(";")[5])) {

                        if (ServerMathot.checkAccounetSignUp("Select * from Provide_Signin where Email = '" + getIn.split(";")[1] + "'")) {
                            out.writeUTF("false");
                        } else {
                            ServerMathot.WriteTableSql(" INSERT INTO Provide_Signin(Full_Name,Email,password,contact_number) values('" + getIn.split(";")[0] + "','" + getIn.split(";")[1] + "','" + getIn.split(";")[2] + "','" + getIn.split(";")[3] + "')");
                            out.writeUTF("true");
                        }
                    } else {
                        if (ServerMathot.checkAccounetSignUp("Select * from custom_signin where Email = '" + getIn.split(";")[1] + "'")) {
                            out.writeUTF("false");
                        } else {
                            ServerMathot.WriteTableSql(" INSERT INTO custom_signin(Full_Name,Email,password,contact_number)  values('" + getIn.split(";")[0] + "','" + getIn.split(";")[1] + "','" + getIn.split(";")[2] + "','" + getIn.split(";")[3] + "')");
                            out.writeUTF("true");
                        }
                    }

                }
            } catch (SocketTimeoutException s) {

                break;
            } catch (IOException e) {
                break;
            }
        }
    }

    public static void main(String[] args) {

        int port = 8585;
        try {
            Thread t = new Server(port);
            t.start();
        } catch (IOException e) {
        }
    }
}
