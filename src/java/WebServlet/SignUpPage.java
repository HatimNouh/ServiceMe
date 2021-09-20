package WebServlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dj sean
 */
@WebServlet(urlPatterns = {"/basic_form"})
public class SignUpPage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
 response.setContentType("text/html");          
        
        if (request.getParameter("logIn") != null) {
            String getIn = new String();
            response.setContentType("text/html;charset=UTF-8");
            getIn = request.getParameter("email");
            getIn += ";" + request.getParameter("Newpassword");
            getIn += ";" + request.getParameter("LogInPro");
            getIn += ";basic_form";
            String serverName = "localhost";
            int port = 8585;
            try {
                Socket admine = new Socket(serverName, port);
                OutputStream outToServer = admine.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToServer);
                out.writeUTF(getIn);
                InputStream inFromServer = admine.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                getIn = in.readUTF();
                admine.close();
            } catch (IOException e) {
            }

            try (PrintWriter out = response.getWriter()) {
                if ("true".equals(getIn)) {
                    response.sendRedirect("successfulPege.html");
                } else {
                    response.sendRedirect("NotsuccessfulPage.html");

                }
            }
        } else if (request.getParameter("Create_account") != null)  {
            String getIn = new String();
            getIn = request.getParameter("fname");
            getIn += ";" + request.getParameter("NewEmail");
            getIn += ";" + request.getParameter("password");
            getIn += ";" + request.getParameter("Contact_number");
            getIn += ";" + request.getParameter("ConPassword");
            getIn += ";" + request.getParameter("Sign_UpPro");
            getIn += ";basic_form";
            String serverName = "localhost";
            int port = 8585;
            try {
                Socket admine = new Socket(serverName, port);
                OutputStream outToServer = admine.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToServer);
                out.writeUTF(getIn);
                InputStream inFromServer = admine.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                getIn = in.readUTF();
                admine.close();
            } catch (IOException e) {
            }
            try(PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                
                if ("true".equals(getIn)) {
                    HttpSession session=request.getSession();  
                    session.setAttribute("NewEmail",request.getParameter("NewEmail"));  
                   request.getRequestDispatcher("ProviderDescription.html").include(request, response);
                   
                }else{
                response.sendRedirect("NotsuccessfulPage.html");
                }

            }

        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
