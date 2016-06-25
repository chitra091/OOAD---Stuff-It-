/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {

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
            out.println("<title>Servlet login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
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
        // JDBC driver name and database URL
      
      final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
      final String DB_URL="jdbc:mysql://localhost/stuffit";

      //  Database credentials
      final String USER = "root";
      final String PASS = "";
      
      String username = request.getParameter("username");
      String pwd = request.getParameter("pwd");
      
      try{
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");

         // Open a connection
         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         //check if duplicate entry exists
         Statement stmt1 = conn.createStatement();
         String sql;
         sql = "SELECT * FROM user";
         ResultSet rs = stmt1.executeQuery(sql);
         Boolean exists = false, invalid_user=true, incorrect_pass = true;
         User reg_user = new User();
         while(rs.next()){
            //Retrieve by column name
            if(rs.getString("username").equals(username)){
                invalid_user = false;
                if(rs.getString("password").equals(pwd)){
                    incorrect_pass = false;
                    reg_user = new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"),rs.getString("email"),rs.getString("password"));
                    exists = true;
                    if(rs.getString("username").equals("admin") && rs.getString("password").equals("admin") )
                    {
                        HttpSession session = request.getSession();
                        session.setAttribute("message", "Valid");
                        session.setAttribute("user", reg_user);
                        System.out.println(reg_user.getFirstName());
                        RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
                        rd.forward(request, response);
                        exists=false;
                    }
                    
          
                    break;
                }
            }
         }
         
         if(invalid_user){
                HttpSession session = request.getSession();
                session.setAttribute("message", "Invalid");
                System.out.println("invalid user");
                RequestDispatcher rd = request.getRequestDispatcher("login_result.jsp");
                rd.forward(request, response);
                // Clean-up environment
                rs.close();
                stmt1.close();
                conn.close();
         }
         else if(incorrect_pass){
                HttpSession session = request.getSession();
                session.setAttribute("message", "Incorrect");
                System.out.println("invalid password");
                RequestDispatcher rd = request.getRequestDispatcher("login_result.jsp");
                rd.forward(request, response);
                // Clean-up environment
                rs.close();
                stmt1.close();
                conn.close();
         }
         else if(exists){
                HttpSession session = request.getSession();
                session.setAttribute("message", "Valid");
                session.setAttribute("user", reg_user);
                System.out.println(reg_user.getFirstName());
                RequestDispatcher rd = request.getRequestDispatcher("login_result.jsp");
                rd.forward(request, response);
                // Clean-up environment
                rs.close();
                stmt1.close();
                conn.close();
         }
         
      }catch(SQLException se){
         //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){
         //Handle errors for Class.forName
         e.printStackTrace();
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
