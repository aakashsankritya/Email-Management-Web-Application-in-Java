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
public class Serv_DeleteMessage extends HttpServlet {

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
ResultSet rs,rs1;
Connection con;
ServletContext scon;
String uname;
int y;
int rcnt=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","Akash@7979");
            st=con.createStatement();
            st1=con.createStatement();
            scon=getServletContext();
            HttpSession session = request.getSession(true);
            uname=session.getValue("name").toString();            
      
            ServletOutputStream sos= response.getOutputStream();
            int cnt=Integer.parseInt(request.getParameter("h"));

            for (int i=1;i<=cnt;i++)
            {
                String chk=request.getParameter("cb"+i);
                if(chk!=null)
                {
                    int mid=Integer.parseInt(chk);
                    y=st1.executeUpdate("delete from newcompose where mailid="+mid);
                    if(y > 0)
                    {
                        rcnt++;
                        sos.println("<html><body bgcolor=cyan text=blue>");
                        sos.println("</body></html>");
                        response.setHeader("Refresh","2;URL=http://localhost:8081/Email2/Serv_SentMessages");
                    }

                }
            }
                  sos.println("<h3><i>"+rcnt+ "Record(s) deleted </i></h3>");
                  rcnt=0;
            }
            catch(IOException | ClassNotFoundException | NumberFormatException | SQLException e)
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
