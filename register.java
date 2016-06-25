/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
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
@WebServlet(urlPatterns = {"/register"})
public class register extends HttpServlet {

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
            out.println("<title>Servlet register</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet register at " + request.getContextPath() + "</h1>");
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
      
      String fname = request.getParameter("firstname");
      String lname = request.getParameter("lastname");
      String username = request.getParameter("username");
      String pwd = request.getParameter("pwd");
      String emailid = request.getParameter("emailid");
      String address1 = request.getParameter("address1");
      String address2 = request.getParameter("address2");
      String street = request.getParameter("street");
      String apt = request.getParameter("apt");
      String zipcode = request.getParameter("zipcode");
      String city = request.getParameter("city");
      String state = request.getParameter("state");
      String country = request.getParameter("country");
      String phno = request.getParameter("phno");
    
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
         Boolean duplicate = false;
         while(rs.next()){
            //Retrieve by column name
            if(rs.getString("username").equals(username)){
                duplicate = true;
                break;
            }
         }
         
         if(duplicate){
                HttpSession session = request.getSession();
                session.setAttribute("message", "Duplicate user!");
                RequestDispatcher rd = request.getRequestDispatcher("register_success.jsp");
                rd.forward(request, response);
                // Clean-up environment
                rs.close();
                stmt1.close();
                conn.close();
         }
         else {
         // Execute SQL query
         PreparedStatement stmt=conn.prepareStatement
                  ("insert into user values(?,?,?,?,?,?)");
        stmt.setInt(1, 0);
        stmt.setString(2, fname);
        stmt.setString(3, lname);
        stmt.setString(4, username);
        stmt.setString(5, emailid);
        stmt.setString(6, pwd);
        int i=stmt.executeUpdate();
        
          if(i>0)
          {
            System.out.println("You are sucessfully registered in user");
            stmt=conn.prepareStatement
                  ("insert into address values(?,?,?,?,?,?,?,?,?,?)");

            stmt.setInt(1, 0);
            stmt.setString(2, username);
            stmt.setString(3, address1);
            stmt.setString(4, address2);
            stmt.setString(5, street);
            stmt.setString(6, apt);
            stmt.setString(7, zipcode);
            stmt.setString(8, city);
            stmt.setString(9, state);
            stmt.setString(10, country);
            int j=stmt.executeUpdate();
            
            if(j>0)
          {
            System.out.println("You are sucessfully registered in address");
            stmt=conn.prepareStatement
                  ("insert into phone values(?,?,?,?)");

            stmt.setInt(1, 0);
            stmt.setString(2, username);
            stmt.setString(3, phno);
            stmt.setString(4, phno);            
            
            int k=stmt.executeUpdate();
            
            if(k>0){
                System.out.println("You are sucessfully registered in phone");
                HttpSession session = request.getSession();
                session.setAttribute("message", "Successfully registered!");
                RequestDispatcher rd = request.getRequestDispatcher("register_success.jsp");
                rd.forward(request, response);
                // Clean-up environment
                stmt.close();
                conn.close();
            }
          }
          }
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
