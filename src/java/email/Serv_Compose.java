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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author akashsankritya
 */
public class Serv_Compose extends HttpServlet {

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
        Connection con;
        Statement st;
        ServletContext scon;
        ResultSet rs;
        try {
            /* TODO output your page here. You may use following sample code. */
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","Akash@7979");
            st=con.createStatement();
            scon=getServletContext();
            String name;



	HttpSession session = request.getSession(true);   
	name=session.getValue("name").toString();            	
        String Mto=null;
        try
        {      
        //Mto=session.getValue("MFrom").toString();      
        Mto=request.getParameter("from");
        }catch(Exception e){}
   
        //ServletOutputStream sos= res.getOutputStream();
        out.println("<html><title>Intranet Mailing System</title>");
        out.println("<body bgcolor=cyan text=blue><center><img src=http://localhost:8081\\adv3.gif border=0></center>");
        out.println("<pre>");
        out.println("<img src='http://localhost:8081/MAIL.GIF'><img src='http://localhost:8081/MAIL.GIF'><img src='http://localhost:8081/MAIL.GIF'>");
        out.println("<form name=form4 method=post action=Serv_Send>");
        if(Mto!=null)
        {  
        out.println("To                  :<input type=text name=to value="+Mto+"><br/>");   
        Mto=null;
        }
        else
             out.println("To                  :<input type=text name=to value=''><br/>");   
   
//<br>");
        out.println("Subject             :<input type=text name=subject value=''><br>");
        out.println("Cc                  :<input type=text name=cc value=''><br>");
        out.println("Bcc                 :<input type=text name=bcc value=''><br>");
        out.println("Mail data to be sent:<br>");
        out.println("<textarea name=maildata rows=10 cols=60></textarea><br>");
        out.println("<input type=submit name=submit1 value='SEND'><br>");

   rs=st.executeQuery("select actname,emailid from addresses where uname='"+name+"'");  
   if (rs.next())
   {
      out.println("<SELECT name=se onclick=getName(value) style=\"LEFT: 400px; POSITION: absolute; TOP: 160px; BACKGROUND-COLOR:#b464ff;width:130px;FONT-WEIGHT:BOLD\"");      
      out.println("SIZE=5 name=List1 value=\"List1\">");   
      do
      {         
         out.println("<OPTION value="+rs.getString(2)+" >"+rs.getString(1)+"</OPTION>");               
      }while(rs.next());
      out.println("</SELECT>");    
    }
       
    out.println("</form></body></html>");
    out.println("<script language=javascript>");
    out.println("function getName(Myname){");
    out.println("document.form4.to.value=Myname");
    out.println("}</script>");
   

      
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
            Logger.getLogger(Serv_Compose.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Serv_Compose.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Serv_Compose.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Serv_Compose.class.getName()).log(Level.SEVERE, null, ex);
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
