/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import com.stuffit.www.data.objects.Recipe;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(urlPatterns = {"/edit"})
public class edit extends HttpServlet {

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
            out.println("<title>Servlet edit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet edit at " + request.getContextPath() + "</h1>");
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
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
      final String DB_URL="jdbc:mysql://localhost/stuffit";

      //  Database credentials
      final String USER = "root";
      final String PASS = "";
      HttpSession session = request.getSession();
      Recipe rec = (Recipe)session.getAttribute("editdisp");
      String recipe_name = request.getParameter("recipename");
      String recipe_type = request.getParameter("type");
//      int recipe_rating = Integer.parseInt(request.getParameter("rating"));
      String recipe_process = request.getParameter("process");
      List<String> ilist  = new ArrayList<String>();
      List<String> itemp  = new ArrayList<String>();
      String[] iarray = request.getParameterValues("ing");
      for(int i=0;i<iarray.length;i++){
          System.out.println("ing: " + iarray[i]);
          if(!iarray[i].isEmpty()){
            ilist.add(iarray[i]);
            itemp.add(iarray[i]);
          }
      }
//      itemp = ilist;
      for(int i=0;i<itemp.size();i++){
        System.out.println("itemp : " +itemp.get(i));
      }
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
             
             // Execute SQL query
         PreparedStatement stmt=conn.prepareStatement
                  ("update recipe set name = ?, type= ?, process = ? WHERE id = ?");
        stmt.setInt(4, rec.getId());
        stmt.setString(1, recipe_name);
        stmt.setString(2, recipe_type);
        //stmt.setInt(3, recipe_rating);
        stmt.setString(3, recipe_process);
        int i=stmt.executeUpdate();
        
          if(i>0)
          {
                System.out.println("You have sucessfully updated recipe");
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
                    stmt3.setInt(4, 0);
                  int j=stmt3.executeUpdate();
                  listindex++;
                }
                  //if(j>0){
                    Statement stmt4 = conn.createStatement();
                    String sql4,sql5;
                    sql4 = "SELECT * FROM recipe";
                    ResultSet rs4 = stmt4.executeQuery(sql4);
                    int recipe_id = 0;
                    while(rs4.next()){
                       //Retrieve by column name
                       if(rs4.getString("name").equals(recipe_name)){
                           recipe_id = rs4.getInt("id");
                           break;
                       }
                    }
                    rs4.close();
                    Statement stmt5 = conn.createStatement();
                    sql5 = "SELECT * FROM ingredients";
                    List<Integer> inlist = new ArrayList<Integer>();
                    Map<Integer,Integer> old_in_list = new HashMap<Integer,Integer>();
                    ResultSet rs5 = stmt5.executeQuery(sql5);
                    
                        System.out.println("itemp : " +itemp.size());
                    
                    while(rs5.next()){
                        //System.out.println("loop ing name " + rs5.getString("name"));
                       //Retrieve by column name
                       if(itemp.contains(rs5.getString("name"))){
                           System.out.println("ing name " + rs5.getString("name"));
                           inlist.add(rs5.getInt("id"));
                       }
                    }
                    rs5.close();
                    int tempindex = 0;
                    sql4 = "SELECT * FROM recipe_ingred";
                        rs4 = stmt4.executeQuery(sql4);
                        while(rs4.next()){
                            if(rs4.getInt("recipe_id")==recipe_id){
                                old_in_list.put(rs4.getInt("id"),rs4.getInt("ingredient_id"));   
                            }
                        }
                    while(tempindex < inlist.size()){
                        System.out.println("inlist list: " + inlist.get(tempindex));
                        if(!old_in_list.containsValue(inlist.get(tempindex))){
                            System.out.println("adding ing: " + inlist.get(tempindex));
                            PreparedStatement stmt3=conn.prepareStatement
                            ("insert into recipe_ingred values(?,?,?,?)");
                            stmt3.setInt(1, 0);
                            stmt3.setInt(2, recipe_id);
                            stmt3.setInt(3, inlist.get(tempindex));
                            stmt3.setString(4, "1 tbsp");
                            int k=stmt3.executeUpdate();
                        }
                        /*else{
                            System.out.println("deleting ing: " + inlist.get(tempindex));
                            for(int m=0; m<old_in_list.size();m++){
                                if(old_in_list.get(m)==inlist.get(tempindex)){
                                    old_in_list.remove(m);
                                    break;
                                }
                            }
                        }*/
                        tempindex++;
                    }
                    System.out.println(old_in_list.toString());
                    for(Integer x: old_in_list.keySet()){
                        System.out.println("old list: " + old_in_list.get(x));
                        if(!inlist.contains(old_in_list.get(x))){
                            System.out.println("deleting: " + x);
                            String query = "delete from recipe_ingred where id = ?";
                            PreparedStatement preparedStmt = conn.prepareStatement(query);
                            preparedStmt.setInt(1, x);
                            // execute the preparedstatement
                            preparedStmt.execute();
                        }   
                    }
                 // }
                //}
                //HttpSession session = request.getSession();
                session.setAttribute("editmessage", "Successfully updated recipe!");
                RequestDispatcher rd = request.getRequestDispatcher("edit_success.jsp");
                rd.forward(request, response);
                // Clean-up environment
                stmt.close();
                conn.close();
            }
         }
         else {
              //HttpSession session = request.getSession();
                session.setAttribute("editmessage", "Recipe not in database!");
                RequestDispatcher rd = request.getRequestDispatcher("edit_success.jsp");
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
