/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.User;
import DAO.RateDAO;
import DAO.RecipeDAO;
import com.stuffit.www.data.objects.Rate;
import com.stuffit.www.data.objects.Recipe;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
@WebServlet(urlPatterns = {"/editrecipe"})
public class editrecipe extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet editrecipe</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editrecipe at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost/stuffit";

        //  Database credentials
        final String USER = "root";
        final String PASS = "";

        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute SQL query
            Statement stmt = conn.createStatement();
            String sql;
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("user");
            System.out.println("user: " + u.getFirstName());
            List<Recipe> recipes = new ArrayList<Recipe>();
            //if(!searchtext.isEmpty()){
            //    System.out.println(searchtext);
            sql = "SELECT * FROM user";
            ResultSet rs = stmt.executeQuery(sql);
            int user_id = 0;
            while (rs.next()) {
                if (rs.getString("first_name").equals(u.getFirstName())) {
                    user_id = rs.getInt("id");
                    break;
                }
            }
            rs.close();
            sql = "SELECT * FROM recipe";
            rs = stmt.executeQuery(sql);
            List<Rate> listOfRatings = new ArrayList<Rate>();
            // Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                if (rs.getInt("user") == user_id) {
                    Recipe rp = new Recipe();
                    //rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getInt("rating"),rs.getString("process")  );
                    rp.setName(rs.getString("name"));
                    rp.setProcedure(rs.getString("process"));
                    rp.setType(rs.getString("type"));
                    rp.setRecipeId(rs.getInt("id"));

                    // Adding the Rating
                    Rate r = new Rate();
                    r = (new RateDAO()).getRateInstance(rs.getInt("rating"), "");
                    RecipeDAO rep = new RecipeDAO();
                    listOfRatings = rep.getRatingsForRecipe(rp);// Get all the ratings listed for this recipe.
                    listOfRatings.add(r);
                    rp.setRating(listOfRatings);
                    recipes.add(rp);
                }
            }
            rs.close();

            conn.close();
            stmt.close();
            //}
            session.setAttribute("editrecipes", recipes);
            RequestDispatcher rd = request.getRequestDispatcher("editrecipe.jsp");
            rd.forward(request, response);
            // Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

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
