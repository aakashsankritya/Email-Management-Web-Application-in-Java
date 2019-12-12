/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author akashsankritya
 */
public class description extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
            /* TODO output your page here. You may use following sample code. */
            PrintWriter pw=response.getWriter();
            response.setContentType("Text/Html");
            HttpSession session = request.getSession(true);
      
            String name=session.getValue("name").toString();      
            pw.println("<html>");
            pw.println("<body bgcolor=cyan text=blue>");
            pw.println("<center><img src=http://localhost:8081\\wel1.gif  height=200 width=800</center>");      
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<br>");
            pw.println("<center><h1><font color=brown> Hi "+name+ "<h2><font color=blue>");
            pw.println("<font color=red>GoTo <a href=http://localhost:8081/Email2/Serv_Inbox> Inbox</a>");
            pw.println("<h2><b><i></i></b></h2><br><br><br><br>");
            pw.println("<img src=http://localhost:8081\\moved.gif border=0>");
            pw.println("</body></html>");
            
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
