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
public class Serv_SeeMessage1 extends HttpServlet {

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
int x;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","Akash@7979");
            scon=getServletContext();
            HttpSession session = request.getSession(true);
            uname=session.getValue("name").toString();            


            st=con.createStatement();
            // ServletOutputStream sos=response.getOutputStream();
            int mi=Integer.parseInt(request.getParameter("id"));
            rs=st.executeQuery("select mailfrom,mailto,subject,maildate,cc,bcc,maildata,status from newcompose where mailfrom='"+uname+"' and mailid="+mi);
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
out.println("<html><head><title>Welcome to Intranet Mailing System</title></head>");
out.println("<form name=fo>");
out.println("<body bgcolor=cyan text=blue><pre>");

out.println("From             :  "+mf+"<br>"); 
out.println("To               :  "+mt+"<br>"); 
out.println("Cc               :  "+c+"<br>"); 
out.println("Bcc              :  "+b+"<br>"); 
out.println("Subject          :  "+s+"<br><br>");
out.println("<textarea rows=10 cols=60 name=t6 readonly>"+md+"</textarea>");

st1=con.createStatement();
x=st1.executeUpdate("update newcompose set status=0");
st1.close();

out.println("</form></body></html>");
}
        
 }
catch(Exception e)
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
