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
import javax.servlet.ServletConfig;
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
public class Serv_ChnagePwd extends HttpServlet {

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
    String un;
    @Override
    public void init(ServletConfig sc)
    {
    try{
        super.init(sc);
        Class.forName("com.mysql.cj.jdbc.Driver");  
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","Akash@7979");
        st=con.createStatement();
        scon=getServletContext();
        }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    
    }
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
    {
        try
        {

            HttpSession session = req.getSession(true);
            un=session.getValue("name").toString();            

            ServletOutputStream sos=res.getOutputStream();
            String newp=req.getParameter("np");
            int a=st.executeUpdate("Update signupdetails set pwd='"+newp+"' where uname='"+un+"'");
            sos.println("<html><body bgcolor=cyan text=blue><h2><i>password is changed</i></h2></body></html>");
            res.setHeader("Refresh","2;URL=http://localhost:8081/Email2/Serv_Inbox");
        }
        catch(IOException | SQLException e)
        {
        }
                    
    }

    

}
