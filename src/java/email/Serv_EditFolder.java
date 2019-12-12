/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author akashsankritya
 */
public class Serv_EditFolder extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Statement st;
Connection con;
ResultSet rs;
ServletContext scon;
String uname,of;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            
        Class.forName("com.mysql.cj.jdbc.Driver");  
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","Akash@7979");
        st=con.createStatement();
        scon=getServletContext();

        HttpSession session = request.getSession(true);
        uname=session.getValue("name").toString();            

        of=request.getParameter("fol");
        ServletOutputStream sos=response.getOutputStream();
          sos.println("<html><body bgcolor=cyan text=blue>");
          sos.println("<form name=for method=post action='http://localhost:8081/Email2/Serv_ModifyFolder'>");
          sos.println("<h3>Changing the name of '"+of+"'</h3><br>");
          sos.println("");
          sos.println("<b>Folders New Name</b>  :   <input type=text name=newfol value=''><br><br>");
          sos.println("<input type=submit name=su value='MODIFY-FOLDER'>");
          sos.println("<input type=hidden name=hid value="+of+">");
          sos.println("</form></body></HTML>");
}
catch(  IOException | ClassNotFoundException | SQLException e){
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
