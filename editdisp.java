/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DAO.RateDAO;
import DAO.RecipeDAO;
import com.stuffit.www.data.objects.Rate;
import com.stuffit.www.data.objects.Recipe;
import entity.Ingredients;
import entity.User;
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
@WebServlet(urlPatterns = {"/editdisp"})
public class editdisp extends HttpServlet {

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
            out.println("<title>Servlet editdisp</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editdisp at " + request.getContextPath() + "</h1>");
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
        String r = request.getParameter("r");
        HttpSession session = request.getSession();
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
            User u = (User) session.getAttribute("user");
            List<Integer> ing_id = new ArrayList<Integer>();
            List<Ingredients> ing = new ArrayList<Ingredients>();
            int recipeid = 0;
            //if(!searchtext.isEmpty()){
            //    System.out.println(searchtext);
            sql = "SELECT * FROM recipe";
            ResultSet rs = stmt.executeQuery(sql);
            Recipe rp = new Recipe();
            List<Recipe> recipeList= new ArrayList<Recipe>();
            List<Rate> listOfRatings = new ArrayList<Rate>();

            // Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                if (rs.getString("name").equals(r)) {
                    recipeid = rs.getInt("id");
                    rp = new Recipe();
                    //rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getInt("rating"),rs.getString("process")  
                    rp.setRecipeId(rs.getInt("id"));
                    rp.setName(rs.getString("name"));
                    rp.setType(rs.getString("type"));
                    rp.setProcedure("process");
                    Rate rate = new Rate();
                    rate = (new RateDAO()).getRateInstance(rs.getInt("rating"), "");
                    RecipeDAO rep = new RecipeDAO();
                    listOfRatings = rep.getRatingsForRecipe(rp);// Get all the ratings listed for this recipe.
                    listOfRatings.add(rate);
                    rp.setRating(listOfRatings);
                    recipeList.add(rp);

                    break;
                }
            }
            rs.close();
            sql = "SELECT * FROM recipe_ingred";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getInt("recipe_id") == recipeid) {
                    ing_id.add(rs.getInt("ingredient_id"));
                }
            }
            rs.close();
            sql = "SELECT * FROM ingredients";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (ing_id.contains(rs.getInt("id"))) {
                    ing.add(new Ingredients(rs.getInt("id"), rs.getString("name"), rs.getString("type"), rs.getInt("rating")));
                }
            }
            //}
            session.setAttribute("editdisp", rp);
            session.setAttribute("editingred", ing);
            RequestDispatcher rd = request.getRequestDispatcher("editdisp.jsp");
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
