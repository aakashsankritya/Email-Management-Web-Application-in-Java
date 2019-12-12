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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Serv_Address extends HttpServlet {

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
String uname;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","Akash@7979");
            st=con.createStatement();
            scon=getServletContext();

ServletOutputStream sos=response.getOutputStream();

HttpSession session = request.getSession(true);
uname=session.getValue("name").toString();
rs=st.executeQuery("select actname,nickname,emailid,address,phone from addresses where uname='"+uname+"'");
out.println("<html><body bgcolor=cyan text=blue><pre>");
out.println("<blink><h1>Address Screen</h1></blink><br><br>");
out.println("<h2><i>Directory of '"+uname+"' :</i></h2><br><br>");
out.println("<form name=f10 action='http://localhost:8081/Email2/Serv_NewAddress'>");
out.println("<input type=submit value='ADD'></form>");
out.println("<a href='http://localhost:8081/Email2/listoptions.html' target=_parent><b>BACK</b></a>");
out.println("<table border=2><tr bgcolor=cyan><th>Name</th><th>Nick Name</th>");
out.println("<th>Address</th><th>Phone</th><th>E-Mailid</th><th>Options</th></tr>");
while(rs.next())
{
String n=rs.getString(1);
String nn=rs.getString(2);
String em=rs.getString(3);
String ad=rs.getString(4);
int p=rs.getInt(5);
out.println("<tr><td>"+n+"</td><td>"+nn+"</td><td>"+ad+"</td><td>"+p+"</td><td>"+em+"</td><td>");
out.println("<a href='http://localhost:8081/Email2/Serv_EditAddress?name="+n+"&add="+ad+"&pno="+p+"&mid="+em+"'>EDIT</a>");
out.println("<a href='http://localhost:8081/Email2/Serv_DeleteAddress?name="+n+"'>DELETE</a></td></tr>");

}
out.println("</table></body></html>");
}
catch(  IOException | ClassNotFoundException | SQLException e)
{
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Serv_Address.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Serv_Address.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Serv_Address.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Serv_Address.class.getName()).log(Level.SEVERE, null, ex);
        }
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
