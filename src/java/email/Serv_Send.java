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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author akashsankritya
 */
public class Serv_Send extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Statement st,st1,st2,st3;
        ResultSet rs,rs1,rs2;
        int i;
        ServletContext scon;
        boolean bool;
        String mfrom;
        try{
            /* TODO output your page here. You may use following sample code. */
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","Akash@7979");
            scon=getServletContext();
            


            HttpSession session = request.getSession(true);
            mfrom=session.getValue("name").toString();            


st=con.createStatement();

rs = st.executeQuery("Select max(mailid) from newcompose");
rs.next();
if(rs == null)
{
i=1;

}
else
{
i=rs.getInt(1);
i++;
}
String mto=request.getParameter("to");
String msub=request.getParameter("subject");
String mcc=request.getParameter("cc");
String mbcc=request.getParameter("bcc");
String mexc=request.getParameter("s1");
String mdata=request.getParameter("maildata");

rs1=st.executeQuery("select * from signupdetails where uname='"+mto+"'");
if(rs1.next())
{
bool=true;

}
else
{
bool=false;
out.println("<html><head><script>{alert('Invalid Mail-to address - He is an unregistered user');window.history.go(-1);}</script></head></html>");

}
st.close();
if(bool==true)
{
// st2=con.createStatement();
    // "Insert into newcompose values('"+i+"''"+mfrom+"''"+mto+"''"+msub+"''"+mcc+"''"+mbcc+"''"+mexc+"''"+mdata+"''inbox',sysdate,"+1+")"
    String sql = "insert into newcompose values(?,?,?,?,?,?,?,?,?,?,?)";
    PreparedStatement pst = con.prepareStatement(sql);
    pst.setInt(1,i);
    pst.setString(2,mfrom);
    pst.setString(3,mto);
    pst.setString(4,msub);
    pst.setString(5,mcc);
    pst.setString(6,mbcc);
    pst.setString(7,mexc);
    pst.setString(8,mdata);
    pst.setString(9,"inbox");
    java.sql.Date sysdate = new java.sql.Date(new java.util.Date().getTime());
    pst.setDate(10,sysdate);
    pst.setString(11,"1");
    int x = pst.executeUpdate();
    
    
    
// int x=st2.executeUpdate();
// st2.close();
out.println("<html><body bgcolor=cyan text=blue ><font color=blue><h3><i>Your message has been sent to "+mto+"</i></h3></font>");
out.println("<form action=http://localhost:8081/Email2/Serv_NewAddress><center>");
out.println("<h3><a href=http://localhost:8081/Email2/Serv_Compose>Compose</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href=http://localhost:8081/Email2/Serv_Inbox>Goto Inbox</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href=http://localhost:8081/Email2/Serv_NewAddress>Add Address</a></body></html>");

}
}
catch(  ClassNotFoundException | SQLException e)
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Serv_Send.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Serv_Send.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Serv_Send.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Serv_Send.class.getName()).log(Level.SEVERE, null, ex);
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
