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
public class Serv_SentMessages extends HttpServlet {

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
    ServletContext scon;
    ResultSet rs;
    String uname,s,d,mt;
    int m,col=1;
    String color;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","Akash@7979");
            st=con.createStatement();
            scon=getServletContext();

            HttpSession session = request.getSession(true);
            uname=session.getValue("name").toString();            

// ServletOutputStream sos=response.getOutputStream();
out.println("<html><head><title>Welcome To Intranet Mailing System</title>");
out.println("<script>function d1(){");
out.println("document.ff.action='http://localhost:8081/Email2/Serv_DeleteMessage';");
out.println("document.ff.submit();");
out.println("}</script></head>");
out.println("<body bgcolor=cyan text=blue><h3><i>Welcome '"+uname+"' @Intranet Mailing System</i></h3>");
out.println("<form name=ff><input type=button name=del value='Delete' onClick='d1()'>        <input type=reset name=des value='DeSelect'>");
out.println("<h3><i>Messages sent by "+uname+" till now</i></h3><br><br>");
out.println("<table border=2><tr bgcolor=brown><th>X</th><th>To</th><th>Subject</th><th>Date</th></tr>");
rs=st.executeQuery("select mailid,mailto,subject,maildate from newcompose where mailfrom='"+uname+"'");
int count=0;
while(rs.next())
{
count++;
m=rs.getInt(1);
mt=rs.getString(2);
s=rs.getString(3);
d=rs.getString(4);
if(col%2==0)
   color="pink";
else
{
    color="violet";
    col++;
}   
   
out.println("<tr bgcolor="+color+"><td><input type=checkbox name=cb"+count+" value="+m+"></td><td><a href='http://localhost:8081/Email2/Serv_SeeMessage1?id="+m+"'>"+mt+"</a></td><td>"+s+"</td><td>"+d+"</td></tr>"); 
}
out.println("<input type=hidden name=h value="+count+">");
out.println("</table></form></body></html>");
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
