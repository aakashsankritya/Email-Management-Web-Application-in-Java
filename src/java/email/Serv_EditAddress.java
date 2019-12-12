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
public class Serv_EditAddress extends HttpServlet {

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
    ServletContext scon1;
    String fraddress,frmid,uname,str;
    int frphone;
    ResultSet rs;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
           
        Class.forName("com.mysql.cj.jdbc.Driver");  
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","Akash@7979");
        st=con.createStatement();
        scon1=getServletContext();


        HttpSession session = request.getSession(true);
        uname=session.getValue("name").toString();            
   
        ServletOutputStream sos=response.getOutputStream();
        str=request.getParameter("name");
        fraddress=request.getParameter("add");
        frphone=Integer.parseInt(request.getParameter("pno"));
        frmid=request.getParameter("mid");
        System.out.println(str);
        sos.println("<html><body bgcolor=cyan text=blue><h2><b><i><center>Modify Address,phone,mail_id of "+str+"</center></i></b></h2><br><br>");
        sos.println("<form name=form7 method=post action='http://localhost:8081/Email2/Serv_ModifyAddress'><pre>");

        sos.println("<h3><i>For editing the fields overwrite that particular field</i></h3><br>");
        sos.println("<b>Frd-Name </b>:   <input type=text name=nam value="+str+" readonly><br>");
        sos.println("<b>Address  </b>:   <input type=text name=add1 value="+fraddress+"><br>");
        sos.println("<b>Phone No.</b>:   <input type=text name=pno1 value="+frphone+"><br>");
        sos.println("<b>Mail Id  </b>:   <input type=text name=mid1 value="+frmid+"><br>");
        sos.println("<input type=submit name=s6 value='MODIFY-ADDRESS'>");
        sos.println("</form></body></HTML>");

        }
catch(  IOException | ClassNotFoundException | NumberFormatException | SQLException e){
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
