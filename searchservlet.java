/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DAO.RateDAO;
import DAO.RecipeDAO;
import com.stuffit.www.data.SearchCaller;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
@WebServlet(urlPatterns = {"/searchservlet"})
public class searchservlet extends HttpServlet {

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
        final String DB_URL = "jdbc:mysql://localhost/stuffit";

        //  Database credentials
        final String USER = "root";
        final String PASS = "";

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute SQL query
            Statement stmt = conn.createStatement();
            Map<Integer, List<Integer>> map1 = new HashMap<Integer, List<Integer>>();
            Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
            Map<Integer, List<Integer>> map3 = new TreeMap(Collections.reverseOrder());
            List<Recipe> recipeList = new ArrayList<Recipe>();
            String sql;
            String l = request.getParameter("l");
            String[] templ = l.split(",");
            List<String> listIngredients = new ArrayList<String>();
            List<Recipe> listRecipes = new ArrayList<Recipe>();

            // Converting this array into a list. And, we send this to the RelevantSearch class object
            for (String data : templ) {

                listIngredients.add(data);
            }

            //Call Relevant Search
            SearchCaller sc = new SearchCaller(new RelevantSearch(), listIngredients);
            recipeList = sc.executeSearch(listIngredients);

            /*
            for (Map.Entry<Integer, List<Integer>> entry : map1.entrySet()) {
                int key = entry.getKey();
                List<Integer> it = new ArrayList<Integer>();
                it = entry.getValue();
                for (Integer recid : it) {
                    if (map2.containsKey(recid)) {
                        int count = 0;
                        count = map2.get(recid);
                        count++;
                        map2.put(recid, count);
                    } else {
                        int count = 1;
                        map2.put(recid, count);
                    }
                }
            }
            for (Map.Entry<Integer, Integer> entry : map2.entrySet()) {
                int key = entry.getKey();
                int value = entry.getValue();
                if (map3.containsKey(value)) {
                    List<Integer> values = new ArrayList<Integer>();
                    values = map3.get(value);
                    if (!values.contains(key)) {
                        values.add(key);
                    }
                    map3.put(value, values);
                } else {
                    List<Integer> values = new ArrayList<Integer>();
                    values.add(key);
                    map3.put(value, values);
                }
            }
            for (Map.Entry<Integer, List<Integer>> entry : map3.entrySet()) {
                for (Integer i : entry.getValue()) {
                    sql = "SELECT * FROM recipe WHERE id=" + i;
                    ResultSet rs = stmt.executeQuery(sql);
                    List<Rate> listOfRatings = new ArrayList<Rate>();
                    // Extract data from result set
                    while (rs.next()) {
                        //Retrieve by column name
                        Recipe rp = new Recipe();
                        rp.setRecipeId(rs.getInt("id"));
                        rp.setName(rs.getString("name"));
                        rp.setType(rs.getString("type"));
                        //rp.setRating(rs.getInt("rating"));
                        Rate r = new Rate();
                        r = (new RateDAO()).getRateInstance(rs.getInt("rating"), "");
                        RecipeDAO rep = new RecipeDAO();
                        listOfRatings = rep.getRatingsForRecipe(rp);// Get all the ratings listed for this recipe.
                        listOfRatings.add(r);
                        rp.setRating(listOfRatings);
                        rp.setProcedure(rs.getString("process"));
                        //out.println(rp.toString());
                        recipeList.add(rp);
                    }
                    rs.close();
                }
            }
             */
            HttpSession session = request.getSession();
            session.setAttribute("recipeList", recipeList);
            RequestDispatcher rd = request.getRequestDispatcher("search_results.jsp");
            rd.include(request, response);
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
