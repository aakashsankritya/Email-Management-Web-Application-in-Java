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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author akashsankritya
 */
public class Serv_Inbox extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // String cssTag="<link rel='stylesheet' type='text/css' href='css/style.css'>";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String f1,sub,from,date1;
        int sta,m,col=1;
        String color;
        try {
            /* TODO output your page here. You may use following sample code. */
             Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","Akash@7979");   
            Statement stm=con.createStatement(); 
         HttpSession session = request.getSession(true);
         String uname=session.getValue("name").toString();
         out.println("<html><head><title>Welcome To Intranet Mailing System</title><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
         out.println("<script>function d()");
         out.println("{document.f.action='http://localhost:8081/Email2/Serv_DeleteCkd';");    
         out.println("document.f.submit();}");
         out.println("function e()");
         out.println("{document.f.action='http://localhost:8081/Email2/Serv_Enter';");
         out.println("document.f.submit();}");
         out.println("</script></head>");
         out.println("<body class=main>");
         out.println("<center><br><b>Inbox Screen of "+uname+"</b></center><br>");
         out.println("<form class=form1 name=f action='http://localhost:8081/Email2/Serv_MoveMessage'>");
         out.println("<input type=button name=delete value='Delete' onClick='d()' style=\"width:100\">");
         out.println("<input type=reset name=deselect value='DeSelect' style=\"width:100\"><br><br>");
         out.println("<input type=submit name=move value='MOVE' style=\"width:100\">");
         out.println("<input type=button name=enter value='ENTER' onClick='e()' style=\"width:100\">");
         ResultSet rs=stm.executeQuery("select fname from folders where uname='"+uname+"'");
         out.println("<select name=se style=\"width:150\">");
         while(rs.next())
         {
         f1=rs.getString(1);
         out.println("<option value="+f1+">"+f1+"</option>");
         }
         out.println("</select>");
         
         
         
         out.println("<br/><br/><hr/><table class=form1 cellspacing=3>"); 
         out.println("<TR><th style=\"width:10\"><font color=red>Status</th><TH><input type=checkbox name=chkall onclick=chkit()></TH><TH  style=\"width:380\"><font color=red>From</TH><TH  style=\"width:250\"><font color=red>Subject</Th><TH  style=\"width:150\"><font color=red>Date</TH></TR>"); 
         Statement st1 = con.createStatement();
         ResultSet rs1=st1.executeQuery("select mailid,mailfrom,subject,maildate,status from newcompose where mailto='"+uname+"' and folder='inbox'"); 
         int count=0;
         while(rs1.next())
         {
                  if (col%2==0)
                     color="violet";
                  else
                     color="pink";
                  col++;
                  count++;
                  m=rs1.getInt(1);
                  from=rs1.getString(2);
                  sub=rs1.getString(3);
                  
                  date1=rs1.getDate(4).toString();
                  sta=rs1.getInt(5);
                  if (sub==null)
                     sub="[NONE]";
                  
                  if(sta==1)
                  {          
                     out.println("<tr bgcolor="+color+"><td><center><img src='http://localhost:8081//button.gif' height=20 width=25 ></td><td>");
                     out.println("<input type=checkbox name=b"+count+" value="+m+"></td><td><h3><a href='http://localhost:8081/Email2/Serv_SeeMessage?id="+m+"'>"+from+"</td></a><td><h3>"+sub+"</td><td><h3>"+date1+"</td></tr>");
                  }
                  else 
                     out.println("<tr bgcolor="+color+"><td><center></td><td><input type=checkbox name=b"+count+" value="+m+"></td><td><h3><a class=sign href='http://localhost:8081/Email2/Serv_SeeMessage?id="+m+"'>"+from+"</td></a><td><h3>"+sub+"</td><td><h4>"+date1+"</td></tr>");                  
         }
         out.println("<script language=JavaScript>");
         out.println("function chkit(){");
         for (int i=1;i<=count;i++)
            out.println("document.f.b"+i+".checked=document.f.chkall.checked;");
         out.println("}</script>");         
         out.println("<input type=hidden name=hid  value="+count+">");
         out.println("</form></table><hr/></body></html>");
         }
      catch(ClassNotFoundException | SQLException e){
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
