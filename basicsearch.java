/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.stuffit.www.data.SearchCaller;
import com.stuffit.www.data.objects.Recipe;
//import entity.Recipe;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Clock;
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
@WebServlet(urlPatterns = {"/basicsearch"})
public class basicsearch extends HttpServlet {

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
  
      try{
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");

         // Open a connection
         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

         // Execute SQL query
         Statement stmt = conn.createStatement();
         String sql;
         String searchtext = request.getParameter("searchtext");
         List<Recipe> recipes = new ArrayList<Recipe>();
         List<String> recipeName = new ArrayList<String>();
            recipeName.add(searchtext);
         // call Search Caller
            SearchCaller sc = new SearchCaller(new SimpleSearch(), recipeName);
            recipes = sc.executeSearch(recipeName);
            // below this
             
            //
            //
            // SEARCH LOGIC
            //
            //
            if (!searchtext.isEmpty()) {
                System.out.println(searchtext);
                sql = "SELECT * FROM recipe WHERE name LIKE '%" + searchtext + "%'";
                ResultSet rs = stmt.executeQuery(sql);
                // Extract data from result set
                while (rs.next()) {
                    //Retrieve by column name
                    //Recipe rp = new Recipe(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getInt("rating"),rs.getString("process")  );
                    Recipe rp = new Recipe();
                    rp.setRecipeId(rs.getInt("id"));
                    rp.setName(rs.getString("name"));
                    rp.setType(rs.getString("type"));
//                    rp.setRating(rs.getInt("rating"));
                    rp.setProcedure(rs.getString("process"));
 
                    recipes.add(rp);
                    
                }
                rs.close();
            }
            //
            // END OF SEARCH LOGIC
            //
         /*
         if(!searchtext.isEmpty()){
            System.out.println(searchtext);
            sql = "SELECT * FROM recipe WHERE name LIKE '%"+searchtext+"%'";
            ResultSet rs = stmt.executeQuery(sql);
         // Extract data from result set
         while(rs.next()){
            //Retrieve by column name
            Recipe rp = new Recipe(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getInt("rating"),rs.getString("process")  );
            recipes.add(rp);
            System.out.println("1");
            }
            rs.close();
         }*/
            HttpSession session = request.getSession();
            session.setAttribute("recipes", recipes);
            session.setAttribute("keyword", searchtext);
            RequestDispatcher rd = request.getRequestDispatcher("basicsearch.jsp");
            rd.include(request, response);
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
