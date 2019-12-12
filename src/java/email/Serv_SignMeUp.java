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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author akashsankritya
 */
public class Serv_SignMeUp extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
                
        try {
            
            /* TODO output your page here. You may use following sample code. */
            Class.forName("com.mysql.cj.jdbc.Driver");  
               Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","Akash@7979");                 
               Statement stm=con.createStatement(); 
               
                
                String a=request.getParameter("age");
                int age1=Integer.parseInt(a);
                
                
                
                String pincode=request.getParameter("pin");
                int r=Integer.parseInt(pincode);
                
                ResultSet rs=stm.executeQuery("select * from signupdetails where uname='"+request.getParameter("uname")+"'");
                
                if(rs.next())
                {
                out.println("<html><body><h2>Username already exits. Try giving another name</h2></body></html>");
                response.setHeader("Refresh","2;URL=http://localhost:8081\\signmeup.html");
                stm.close();
                }
                else
                {
                

                out.println("<h1> hello </h1>");
                out.println("<html><body bgcolor=cyan text=blue><h1>Congrats ! <br>for sucessfully registering urself with the Intranet Mailing System </h1></body></html>");
                PreparedStatement pst=con.prepareStatement("insert into signupdetails values(?,?,?,?,?,?,?,?)");
                pst.setString(1,request.getParameter("uname"));
                pst.setString(2,request.getParameter("pwd"));
                pst.setInt(3,age1);
                pst.setString(4,request.getParameter("r1"));
                pst.setString(5,request.getParameter("city"));
                pst.setString(6,request.getParameter("state"));
                pst.setInt(7,r);
                pst.setString(8,request.getParameter("nation"));
                pst.execute();
                pst=con.prepareStatement("insert into folders values(?,?)");
                pst.setString(1,request.getParameter("uname"));
                pst.setString(2,"Trash");
                pst.execute();
                out.println("<html><body bgcolor=cyan text=blue ><h1>Congrats ! <br>for sucessfully registering urself with the Intranet Mailing System </h1></body></html>");
                response.setHeader("Refresh","2;URL=http://localhost:8081\\loginsc.html");
               

                }
        }
        catch(ClassNotFoundException | SQLException p)
            {
                System.out.println(p);
               
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
            Logger.getLogger(Serv_SignMeUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Serv_SignMeUp.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Serv_SignMeUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Serv_SignMeUp.class.getName()).log(Level.SEVERE, null, ex);
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
