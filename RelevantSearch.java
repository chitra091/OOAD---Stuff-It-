
import com.stuffit.www.data.DataManager;
import com.stuffit.www.data.objects.Recipe;
import com.stuffit.www.interfaces.Search;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Bikramjit
 */
public class RelevantSearch implements Search {

    private static DataManager dm;
    private DataManager getDataManager() {
        dm = new DataManager();
        return dm;
    }

    @Override
    public List<Recipe> searchRecipes(List<String> l) {

        Connection conn = getDataManager().getConnectionToDB();
        Map<Integer, List<Integer>> map1 = new HashMap<Integer, List<Integer>>();
        Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
        Map<Integer, List<Integer>> map3 = new TreeMap(Collections.reverseOrder());
        List<Recipe> recipeList = new ArrayList<Recipe>();
        List<String> listIngredients = new ArrayList<String>();

        try {

            // Open a connection
            // Execute SQL query
            Statement stmt = conn.createStatement();

            String sql;

            listIngredients = l;
            for (String data : listIngredients) {
                sql = "SELECT * FROM recipe_ingred WHERE ingredient_id=" + data;
                ResultSet rs = stmt.executeQuery(sql);
                List<Integer> temprecipes = new ArrayList<Integer>();
                // Extract data from result set
                while (rs.next()) {
                    //Retrieve by column name
                    temprecipes.add(rs.getInt("recipe_id"));
                }
                map1.put(Integer.parseInt(data), temprecipes);
                rs.close();
            }
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
                    // Extract data from result set
                    while (rs.next()) {
                        //Retrieve by column name
                        Recipe rp = new Recipe();
                        rp.setRecipeId(rs.getInt("id"));
                        rp.setName(rs.getString("name"));
                        rp.setType(rs.getString("type"));
                        //rp.setRating(rs.getInt("rating"));
                        rp.setProcedure(rs.getString("process"));
                        //out.println(rp.toString());
                        recipeList.add(rp);
                    }
                    rs.close();
                }
            }

            //return recipeList;
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return recipeList;
    }

}
