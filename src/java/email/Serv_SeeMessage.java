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
public class Serv_SeeMessage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    Statement st,st1;
    ResultSet rs;
    Connection con;
    ServletContext scon;
    String uname;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try  {
            /* TODO output your page here. You may use following sample code. */
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","Akash@7979");
            scon=getServletContext();

        HttpSession session = request.getSession(true);
        uname=session.getValue("name").toString();            
        st=con.createStatement();
        // ServletOutputStream sos=response.getOutputStream();
        int mi=Integer.parseInt(request.getParameter("id"));
        rs=st.executeQuery("select mailfrom,mailto,subject,maildate,cc,bcc,maildata,status,mailid from newcompose where mailto='"+uname+"' and mailid="+mi);
while(rs.next())
{
String mf=rs.getString(1);
String mt=rs.getString(2);
String s=rs.getString(3);
String d=rs.getString(4);
String c=rs.getString(5);
String b=rs.getString(6);
String md=rs.getString(7);
int sta=rs.getInt(8);
int mid=rs.getInt(9);
out.println("<html><head><title>Welcome to Intranet Mailing System</title></head>");
out.println("<form name=fo>");
out.println("<body bgcolor=cyan text=Blue><pre>");
out.println("From             :<input type=text name=t1 value='"+mf+"'><br>");
out.println("<img src=http://localhost:8081\\adv2.gif>");
out.println("<table cellpadding=15 ><tr><td><a href=http://localhost:8081/Email2/Serv_Compose?from="+mf+">Reply</a>");
out.println("</td><td><a href=http://localhost:8081/Email2/Serv_Inbox>Inbox</a></td></tr></table><hr>"); 
out.println("<pre><br> "+mf+" wrote a mail on "+d+"<br>"); 
out.println("<p>"+md+"</pre>"); 
out.println("<hr><table cellpadding=25><tr><td><a href=http://localhost:8081/Email2/Serv_Compose?from="+mf+">Reply</a>");
out.println("</td><td><a href=http://localhost:8081/Email2/Serv_Inbox>Inbox</a></td></tr></table>"); 
out.println("<center><img src=http://localhost:8081\\adv1.gif>"); 
st1=con.createStatement();
String sql = "update newcompose set status=0 where mailid='"+mid+"'";
PreparedStatement pst = con.prepareStatement(sql);
int x = pst.executeUpdate();
// int x=st1.executeUpdate("update newcompose set status=0 where mailid="+mid+"");
st1.close();
out.println("</form></body></html>");
}
        }
catch(  ClassNotFoundException | NumberFormatException | SQLException e)
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
