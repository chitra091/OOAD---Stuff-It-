/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Ingredients;
import entity.User;
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
@WebServlet(urlPatterns = {"/addrecipe"})
public class addrecipe extends HttpServlet {

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
            out.println("<title>Servlet addrecipe</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addrecipe at " + request.getContextPath() + "</h1>");
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
      HttpSession session = request.getSession();
      User u = (User)session.getAttribute("user");
      String recipe_name = request.getParameter("recipename");
      String recipe_type = request.getParameter("type");
      int recipe_rating = Integer.parseInt(request.getParameter("rating"));
      String recipe_process = request.getParameter("process");
      List<String> ilist  = new ArrayList<String>();
      String[] iarray = request.getParameterValues("ing");
      for(int i=0;i<iarray.length;i++){
          //System.out.println("ing: " + iarray.length);
          ilist.add(iarray[i]);
      }
      List<String> itemp = ilist;
      if(ilist.isEmpty()){
                session.setAttribute("message", "Enter ingredients!");
                RequestDispatcher rd = request.getRequestDispatcher("add_success.jsp");
                rd.forward(request, response);
      }
      else{
      try{
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");

         // Open a connection
         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         //check if duplicate entry exists
         Statement stmt1 = conn.createStatement();
         String sql;
         sql = "SELECT * FROM recipe";
         ResultSet rs = stmt1.executeQuery(sql);
         Boolean duplicate = false;
         while(rs.next()){
            //Retrieve by column name
            if(rs.getString("name").equals(recipe_name)){
                duplicate = true;
                break;
            }
         }
         
         if(duplicate){
                //HttpSession session = request.getSession();
                session.setAttribute("addmessage", "Recipe already in database!");
                RequestDispatcher rd = request.getRequestDispatcher("add_success.jsp");
                rd.forward(request, response);
                // Clean-up environment
                rs.close();
                stmt1.close();
                conn.close();
         }
         else {
         // Execute SQL query
         PreparedStatement stmt=conn.prepareStatement
                  ("insert into recipe values(?,?,?,?,?,?)");
        stmt.setInt(1, 0);
        stmt.setString(2, recipe_name);
        stmt.setString(3, recipe_type);
        stmt.setInt(4, recipe_rating);
        stmt.setString(5, recipe_process);
        stmt.setInt(6, u.getId() );
        int i=stmt.executeUpdate();
        
          if(i>0)
          {
                System.out.println("You have sucessfully added recipe");
                Statement stmt2 = conn.createStatement();
                String sql2;
                sql2 = "SELECT * FROM ingredients";
                ResultSet rs2 = stmt2.executeQuery(sql2);
                while(rs2.next()){
                   //Retrieve by column name
                   if(ilist.contains(rs2.getString("name"))){
                       ilist.remove(rs2.getString("name"));
                   }
                }
                int listindex=0;
                while(listindex < ilist.size()){
                    PreparedStatement stmt3=conn.prepareStatement
                            ("insert into ingredients values(?,?,?,?)");
                  stmt3.setInt(1, 0);
                  stmt3.setString(2, ilist.get(listindex));
                  stmt3.setString(3, recipe_type);
                  stmt3.setInt(4, recipe_rating);
                  int j=stmt3.executeUpdate();
                  if(j>0){
                    Statement stmt4 = conn.createStatement();
                    String sql4;
                    sql4 = "SELECT * FROM recipe";
                    ResultSet rs4 = stmt4.executeQuery(sql4);
                    int recipe_id = 0;
                    while(rs4.next()){
                       //Retrieve by column name
                       if(rs4.getString("name").equals(recipe_name)){
                           recipe_id = rs4.getInt("id");
                       }
                    }
                    sql4 = "SELECT * FROM ingredients";
                    List<Integer> inlist = new ArrayList<Integer>();
                    rs4 = stmt4.executeQuery(sql4);
                    while(rs4.next()){
                       //Retrieve by column name
                       if(itemp.contains(rs4.getString("name"))){
                           inlist.add(rs4.getInt("id"));
                       }
                    }
                    int tempindex = 0;
                    while(tempindex < inlist.size()){
                        stmt3=conn.prepareStatement
                            ("insert into recipe_ingred values(?,?,?,?)");
                        stmt3.setInt(1, 0);
                        stmt3.setInt(2, recipe_id);
                        stmt3.setInt(3, inlist.get(tempindex));
                        stmt3.setString(4, "1 tbsp");
                        int k=stmt3.executeUpdate();
                        tempindex++;
                    }
                  }
                  listindex++;
                }
                //HttpSession session = request.getSession();
                session.setAttribute("addmessage", "Successfully added recipe!");
                RequestDispatcher rd = request.getRequestDispatcher("add_success.jsp");
                rd.forward(request, response);
                // Clean-up environment
                stmt.close();
                conn.close();
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
