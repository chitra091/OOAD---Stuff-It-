package DAO;

import com.mysql.jdbc.Statement;
import com.stuffit.www.data.DataManager;
import com.stuffit.www.data.objects.Rate;
import com.stuffit.www.data.objects.Recipe;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bikramjit
 */
public class RateDAO {

    private DataManager dm;
    private static Connection conn;

    private DataManager getDataManager() {
        dm = new DataManager();
        return dm;
    }
    
    public void RateDAO(){
        conn = getDataManager().getConnectionToDB();
    }

    public List<Rate> getAllRatingsByRecipe(Recipe r) {
        //List<Recipe> allRecipes = 
        return null;
    }

    public Rate getRatingById(int rate_id) {
        String query = "SELECT * from rate WHERE ID = " + rate_id;
        conn = getDataManager().getConnectionToDB();
        Rate r = new Rate();
        try {
            Statement stmt = (Statement) conn.createStatement();
            
            ResultSet rs1 = stmt.executeQuery(query);
            while (rs1.next() == true) {
                r.setRate(rs1.getInt("rate"));
                r.setComment(rs1.getString("comment"));
               // listOfRates.add(r);
               break;
            }
            conn.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public Rate getRateInstance(int rating, String comment) {
        Rate r = new Rate();
        r.setRate(rating);
        r.setComment(comment);
        return r;
    }

}
