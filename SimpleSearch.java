
import com.stuffit.www.data.DataManager;
import com.stuffit.www.data.objects.Recipe;
import com.stuffit.www.interfaces.Search;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bikramjit
 */
public class SimpleSearch implements Search {

    private static DataManager dm = null;
    List<Recipe> recipes = new ArrayList<Recipe>();
    Connection conn;

    private DataManager getDataManager() {
        dm = new DataManager();
        return dm;
    }

    @Override
    public List<Recipe> searchRecipes(List<String> l) {

        conn = getDataManager().getConnectionToDB();
        Statement stmt = null;
        String recipeName = "";
        if (l != null) {
            for (String r : l) {

                recipeName = r;
            }
        }
        try {
            //Search Logic
            stmt = conn.createStatement();
            if (!recipeName.isEmpty()) {
                System.out.println(recipeName);
                String sql = "SELECT * FROM recipe WHERE name LIKE '%" + recipeName + "%'";
                ResultSet rs = stmt.executeQuery(sql);
                // Extract data from result set
                while (rs.next()) {
                    //Retrieve by column name
                    Recipe rp = new Recipe();
                    rp.setRecipeId(rs.getInt("id"));
                    rp.setName(rs.getString("name"));
                    rp.setType(rs.getString("type"));
                    //rp.setRating(rs.getInt("rating"));
                    rp.setProcedure(rs.getString("process"));

                    recipes.add(rp);

                }
                rs.close();
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return recipes;
    }

}
