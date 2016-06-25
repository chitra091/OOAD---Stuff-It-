/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chitra
 */
@WebServlet(urlPatterns = {"/delete"})
public class delete extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet delete</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet delete at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
      final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
      final String DB_URL="jdbc:mysql://localhost/stuffit";

      //  Database credentials
      final String USER = "root";
      final String PASS = "";
      
      String r = request.getParameter("r");
      System.out.println("r: " + r);
      try{
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");

         // Open a connection
         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

         // Execute SQL query
         Statement stmt = conn.createStatement();
         String sql;
         //if(!searchtext.isEmpty()){
        //    System.out.println(searchtext);
            sql = "SELECT * FROM recipe";
            ResultSet rs = stmt.executeQuery(sql);
            int rec_id=0;
         // Extract data from result set
         while(rs.next()){
            //Retrieve by column name
                if(rs.getString("name").equals(r)){
                    rec_id = rs.getInt("id");
                    break;
                }
            }
            rs.close();
         //}
            PreparedStatement stmt1=conn.prepareStatement
                  ("delete from recipe where id = ?");
            stmt1.setInt(1, rec_id);
            int i=stmt1.executeUpdate();
            if(i>0){
                System.out.println("recipe del");
                stmt1=conn.prepareStatement
                  ("delete from recipe_ingred where recipe_id = ?");
                stmt1.setInt(1, rec_id);
                int j=stmt1.executeUpdate();
                if(j>0){
                    System.out.println("recipe del");
                    HttpSession session = request.getSession();
                    session.setAttribute("delmsg", "Recipe " + r + " successfully deleted! ");
                    RequestDispatcher rd = request.getRequestDispatcher("delete_success.jsp");
                    rd.forward(request, response);
                }
                stmt1.close();
            }
           
         // Clean-up environment
         stmt.close();
         conn.close();
      }catch(SQLException se){
         //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){
         //Handle errors for Class.forName
         e.printStackTrace();
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
