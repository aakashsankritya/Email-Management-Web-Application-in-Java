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
public class Serv_Enter extends HttpServlet {

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
          Connection con;
          ServletContext scon;
          ResultSet rs,rs1;
          String uname,sub,date1,from;
          int m,sta;
          int col=1;
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
          ServletOutputStream sos=response.getOutputStream();
          String f=request.getParameter("se");
          sos.println("<html><head><title>Welcome To Intranet Mailing System</title></head>");
          sos.println("<script>function d()");
          sos.println("{document.f.action='http://localhost:8081/Email2/Serv_DeleteCkd';");    
          sos.println("document.f.submit();}");
          sos.println("function e()");
          sos.println("{document.f.action='http://localhost:8081/Email2/Serv_Enter';");
          sos.println("document.f.submit();}");
          sos.println("</script></head>");
         
          sos.println("<body bgcolor=cyan text=blue>");
          sos.println("<center><blink><h1><b>"+f+ "Screen</b></h1></blink></center><br>");
          sos.println("<h3><i>"+f+" of "+uname+" </i></h3>");
          
          sos.println("<form name=f action='http://localhost:8081/Email2/Serv_MoveMessage'>");
          sos.println("<input type=button name=delete value='Delete' onClick='d()' >");
          sos.println("<input type=reset name=deselect value='DeSelect'><br><br>");
                    
          sos.println("<h4><i>Messages in "+f+"</i></h4>");
          sos.println("<table border=2>");
          sos.println("<TR bgcolor=yellow><th>Status</th><TH>X</TH><TH>From</TH><TH>Subject</Th><TH>Date</TH></TR>");
          st1=con.createStatement();
          rs1=st1.executeQuery("select mailid,mailfrom,subject,maildate,status from newcompose where mailto='"+uname+"' and folder='"+f+"'"); 
          int count=0;
          while(rs1.next())
          {
                    count++;
                    m=rs1.getInt(1);
                    from=rs1.getString(2);
                    sub=rs1.getString(3);
                    date1=rs1.getString(4);
                    sta=rs1.getInt(5);
                    if(col%2==0)
                       color="pink";
                    else
                       color="violet";   
                    col++;
                    sos.println("<tr bgcolor="+color+"><td>"+sta+"</td><td><input type=checkbox name=b"+count+" value="+m+"></td><td><a href='http://localhost:8081/Email2/Serv_SeeMessage?id="+m+"'>"+from+"</td></a><td>"+sub+"</td><td>"+date1+"</td></tr>");
          }
             sos.println("<input type=hidden name=hid  value="+count+">");
          sos.println("</form></table></body></html>");
          }
          catch(Exception e){
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
