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
public class Serv_Folder extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    Connection con;
ResultSet rs;
ServletContext scon;
String uname,f1;
String fol[]=new String[25];
int t[]=new int[25];
int n[]=new int[25];
int col=1;
String color;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","Akash@7979");
            ServletOutputStream sos=response.getOutputStream();
Statement st=con.createStatement();
scon=getServletContext();

   HttpSession session = request.getSession(true);
         uname=session.getValue("name").toString();            
rs=st.executeQuery("select fname from folders where uname='"+uname+"'");
System.out.println("select fname from folders where uname='"+uname+"'");

    int i=0;
          while(rs.next())
          {
           f1=rs.getString(1);
          fol[i]=f1;
          System.out.println(fol[i]);
           i++;

          }
          st.close();

sos.println("<html><head><title>Welcome To Intranet Mailing System</title></head>");
sos.println("<body bgcolor=cyan text=blue>");
sos.println("<center><blink><h1><b>Folder Screen</b></h1></blink></center><br>");
sos.println("<form name=fm method=post action='http://localhost:8081/Email2/Serv_NewFolder'><input type=submit value='ADD-FOLDER'></form>");
sos.print("<a href='http://localhost:8081/Email2/listoptions.html' target=_parent><b>BACK</b></a>");
sos.println("<table border=2>");
sos.println("<tr bgcolor=brown><th>Folder Name</th><th>New Mails</th><th>Total Mails</th><th>Options</th></tr>");

for(int j=0;j<i;j++)
{

int tmails=0,nmails=0;
Statement st1=con.createStatement();
ResultSet rs1=st1.executeQuery("select status from newcompose where mailto='"+uname+"' and folder='"+fol[j]+"'");

System.out.println("select status from newcompose where mailto='"+uname+"' and folder='"+fol[j]+"'");
if(col%2==0)
   color="pink";
else
{
   color="violet";   
   col++;
}


while(rs1.next())
   {
   int s=rs1.getInt(1);
       tmails++;
   if(s==1)
       nmails++;

   }
 t[j]=tmails;
 n[j]=nmails;

sos.println("<tr bgcolor="+color+"><td>"+fol[j]+"</td><td>"+n[j]+"</td><td>"+t[j]+"</td>");
sos.println("<td><a href='http://localhost:8081/Email2/Serv_EditFolder?fol="+fol[j]+"'>EDIT</a>");
sos.println("<a href='http://localhost:8081/Email2/Serv_DeleteFolder?fol="+fol[j]+"'>DELETE</a></td></tr>");
st1.close();

}
 sos.println("</table></form></body></html>");
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
