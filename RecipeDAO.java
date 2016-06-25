package DAO;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.stuffit.www.data.DataManager;
import com.stuffit.www.data.objects.Ingredient;
import com.stuffit.www.data.objects.Rate;
import com.stuffit.www.data.objects.Recipe;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author Bikramjit
 */
public class RecipeDAO extends DAO {

    private DataManager dm;

    private static List<Recipe> currentRecipe = new ArrayList<Recipe>();
    private static List<Ingredient> currentIngredient = new ArrayList<Ingredient>();
    private static Map<String, List<String>> ingredmap = new HashMap<String, List<String>>();

    private DataManager getDataManager() {
        dm = new DataManager();
        return dm;
    }

    public Recipe getRecipe(String recipeName) {
        String query = "UPDATE user set type = ?, process = ? WHERE id = ?";
        return null;
    }

    public Recipe getRecipe(Recipe recipe) {
        return null;
    }

    public void updateRecipe(Recipe recipe) {
        // implement
        String query = "UPDATE recipe set type = ?, process = ? WHERE id = ?";

        Connection conn = getDataManager().getConnectionToDB(); // get a Connection object
        //PreparedStatement pstmt = null;

        try {
            //Statement stmt = (Statement) conn.createStatement();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setString(1, recipe.getType());
            ps.setString(2, recipe.getProcedure());
            ps.setInt(3, recipe.getId());
            int yes = ps.executeUpdate();
            if (yes > 0) {
                System.out.println(" Recipe updated with details (recipe type, recipe name) : " + recipe.getType() + ", " + recipe.getName());
            }
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addRating(Recipe recipe, int rating, String comment) {
        Rate r = (new RateDAO()).getRateInstance(rating, comment);

    }

    public List<Recipe> getAllRecipes() {
        String query = "SELECT * FROM recipe";
        List<Recipe> recipeList = new ArrayList<Recipe>();

        Connection conn = getDataManager().getConnectionToDB(); // get a Connection object
        try {

            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next() == true) {
                Recipe recipe = new Recipe();
                recipe.setType(rs.getString("process"));
                recipe.setName(rs.getString("name"));
                recipe.setRecipeId(Integer.parseInt("id"));
                // Create a Rate object 
                List<Rate> list = getRatingsForRecipe(recipe);
                recipe.setRating(list);
                recipeList.add(recipe);
            }
            conn.close();
            rs.close();
            stmt.close();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            //System.out.println(recipeList.getName() + "recipe return");

        }
        return recipeList;
    }

    public List<Rate> getRatingsForRecipe(Recipe recipe) {
        String query = "SELECT * FROM recipe-rate WHERE recipe_id = " + recipe.getId();
        Connection conn = getDataManager().getConnectionToDB(); // get a Connection object
        List<Rate> listOfRatings = new ArrayList<Rate>();
        try {
            Statement stmt = (Statement) conn.createStatement();
            Rate nr = new Rate();
            ResultSet rs1 = stmt.executeQuery(query);
            while (rs1.next() == true) {
                nr = (new RateDAO()).getRatingById(rs1.getInt("rate_id"));
                listOfRatings.add(nr);
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfRatings;
    }

    public List<Ingredient> getAllIngredientsForRecipe(Recipe recipe) {
        return null;
    }

    public List<Recipe> getRecipesByIngredients(List<String> ingred) {

        String query = "SELECT * FROM recipe_ingred";
        List<Recipe> recipeList = new ArrayList<Recipe>();

        // Create a sortable map for the best match list of recipes.
        SortedMap<Integer, List<String>> sortedRecipes = new TreeMap<Integer, List<String>>(
                Collections.reverseOrder());

        Connection conn = getDataManager().getConnectionToDB(); // get a Connection object
        System.out.println("begin");

        try {
            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                List<String> ingreds = new ArrayList<String>();
                int recipe_id = rs.getInt("recipe_id");
                int ingredient_id = rs.getInt("ingredient_id");
                String r = new String("" + recipe_id);
                String i = new String("" + ingredient_id);
                List<String> finalist = new ArrayList<String>();

                ingreds = ingredmap.get("" + recipe_id);

                if (ingreds == null) {
                    List<String> newlist = new ArrayList<String>();
                    newlist.add(i);
                    finalist = newlist;
                } else if (ingreds != null) {
                    if (!ingreds.contains(i)) {
                        List<String> newlist = new ArrayList<String>();
                        newlist = ingreds;
                        newlist.add(i);
                        finalist = newlist;
                    } else {
                        finalist = ingreds;
                    }
                } else {

                }
                ingredmap.put(r, finalist);

            }
            rs.close();

            // Now check the ingredients in the recipe-ingred map.
            List<String> recipe_ingreds = new ArrayList<String>();
            for (Map.Entry<String, List<String>> entry : ingredmap.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
                int matchcount = 0;
                if (entry.getValue() != null) {
                    for (String ingr : ingred) {             // For all the ingredients in the argument.
                        if (entry.getValue().contains(ingr)) {
                            matchcount++;
                        }
                    }
                }
                String rec = entry.getKey();
                String line[];
                List<String> value = new ArrayList<String>();
                if (sortedRecipes.containsKey(matchcount)) {
                    value = sortedRecipes.get(matchcount);
                    if (value.contains(rec)) {

                    } else {
                        value.add(rec);
                    }
                    sortedRecipes.put(matchcount, value);
                } else {

                    value.add(rec);
                    sortedRecipes.put(matchcount, value);

                }

            }
            System.out.println("sorted List print");
            System.out.println(sortedRecipes.toString());
            List<Recipe> newlist = new ArrayList<Recipe>();
            for (Map.Entry<Integer, List<String>> entry : sortedRecipes.entrySet()) {
                String t = entry.getKey().toString();
                for (String k : entry.getValue()) {
                    recipeList.add(getRecipeById(Integer.parseInt(k)));
                }
            }
            //return recipeList;
        } catch (Exception e) {
        } finally {
            return recipeList;
        }

    }

    public Recipe getRecipeById(int id) throws SQLException {
        String query = "SELECT * FROM recipe WHERE id=" + id;
        Recipe recipe1 = new Recipe();

        Connection conn = getDataManager().getConnectionToDB(); // get a Connection object
        try {

            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next() == true) {
                Recipe recipe = new Recipe();
                recipe.setType(rs.getString("process"));
                recipe.setName(rs.getString("name"));
                recipe.setRecipeId(Integer.parseInt("id"));
                //recipe.setRating(Integer.parseInt("rating"));
                //recipe1 = recipe;
            }
        } catch (Exception e) {

        } finally {
            System.out.println(recipe1.getName() + "recipe return");
            conn.close();
            return recipe1;
        }
    }
}
