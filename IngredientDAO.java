package DAO;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.stuffit.www.data.DataManager;
import com.stuffit.www.data.objects.Ingredient;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bikramjit
 */
public class IngredientDAO {

    DataManager dm;

    private DataManager getDataManager() {
        dm = new DataManager();
        return dm;
    }

    public void saveIngredient(Ingredient ingredient) {

        String query = "SELECT * FROM ingredients where id=" + ingredient.getIngredientId();

        Connection conn = getDataManager().getConnectionToDB(); // get a Connection object
        List<Ingredient> currentIngredient = new ArrayList<Ingredient>();
        try {

            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Ingredient in;
            while (rs.next() == true) {
                in = new Ingredient();
                in.setIngredientId(rs.getInt("id"));
                in.setName(rs.getString("name"));
                in.setType(rs.getString("type"));
                in.setRating(rs.getInt("rating"));

            }
            in = ingredient;
            query = "UPDATE ingredients set name = ?, type = ? WHERE id = " + in.getIngredientId();
            
        } catch (Exception e) {

        }

    }

    public List<Ingredient> getAllIngredient() { //TEST USAGE

        String query = "SELECT * FROM ingredients";

        Connection conn = getDataManager().getConnectionToDB(); // get a Connection object
        List<Ingredient> currentIngredient = new ArrayList<Ingredient>();
        try {

            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next() == true) {
                Ingredient in = new Ingredient();
                in.setIngredientId(rs.getInt("id"));
                in.setName(rs.getString("name"));
                in.setType(rs.getString("type"));
                in.setRating(rs.getInt("rating"));

                if (currentIngredient != null) {
                    currentIngredient.add(in); //  Add the ingredient to the list.
                }
            }
        } catch (Exception e) {

        }
        return currentIngredient;

    }

    public boolean updateIngredient(Ingredient ingredient) {
        return true;
    }
}
