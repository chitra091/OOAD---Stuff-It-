package com.stuffit.www.data;

import DAO.DAO;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.util.List;
import com.stuffit.www.data.objects.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author Bikramjit
 */
public class DataManager extends DAO {

    static String dbUrl = "jdbc:mysql://localhost:3306/stuffit";
    static String dbClass = "com.mysql.jdbc.Driver";
    static Statement stmt;
    static PreparedStatement p = null;
    static Connection conn;
    private static List<Recipe> currentRecipe = new ArrayList<Recipe>();
    private static List<Ingredient> currentIngredient = new ArrayList<Ingredient>();

    private static Map<String, List<String>> ingredmap = new HashMap<String, List<String>>();

    MysqlDataSource dataSource = new MysqlDataSource();

    public DataManager() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(dbUrl, "root", "");
        } catch (Exception e) {

        }

    }

    public Connection getConnectionToDB(){
        return conn;
    }
    public List<Ingredient> getAllIngredients() {
        String query = "SELECT * FROM ingredients";
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

    public List<Recipe> getRecipes() {
        String query = "SELECT * FROM ingredients";
        List<Recipe> ingred = new ArrayList<Recipe>();
        try {

            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next() == true) {
                Recipe in = new Recipe();
                in.setProcedure(rs.getString("process"));
                in.setName(rs.getString("name"));
                in.setRecipeId(Integer.parseInt("id"));
                //in.setRating(Integer.parseInt("rating"));
                in.setType(rs.getString("type"));
                if (ingred != null) {
                    ingred.add(in); //  Add the ingredient to the list.
                }
            }
        } catch (Exception e) {

        }
        return ingred;

    }

    public Recipe getRecipeById(int id) {
        String query = "SELECT * FROM recipe WHERE id=" + id;
        Recipe recipe1 = new Recipe();
        try {

            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next() == true) {
                Recipe recipe = new Recipe();
                recipe.setType(rs.getString("process"));
                recipe.setName(rs.getString("name"));
                recipe.setRecipeId(Integer.parseInt("id"));
//                recipe.setRating(Integer.parseInt("rating"));
                //recipe1 = recipe;
            }
        } catch (Exception e) {

        }finally {
            System.out.println(recipe1.getName() + "recipe return");
            return recipe1;
        }
    }

    public void getRecipeIngredient() {
        String query = "SELECT * FROM recipe_ingred";
        try {

            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println(rs.getFetchSize());

            while (rs.next()) {
                System.out.println(rs.getInt("recipe_id") + " " + rs.getInt("ingredient_id"));
                List<String> ingreds = new ArrayList<String>();
                int recipe_id = rs.getInt("recipe_id");
                int ingredient_id = rs.getInt("ingredient_id");
                //Sys=tem.out.println(ingredient_id);
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
        } catch (Exception e) {

        }
        System.out.println(ingredmap.toString());

    }

    public List<Recipe> getRecipesForIngredients(List<String> l) {
        // select from recipe-ingredient table and send.
        String query = "SELECT * FROM recipe_ingred";
        List<Recipe> recipeList = new ArrayList<Recipe>();

        // Create a sortable map for the best match list of recipes.
        SortedMap<Integer, List<String>> sortedRecipes = new TreeMap<Integer, List<String>>(
                Collections.reverseOrder()
        /*new Comparator() {
            @Override
            public int compare(Object r, Object m) {
                String r1 = r.toString();
                String r2 = m.toString();
                if (Integer.parseInt(r1) < Integer.parseInt(r2)) {
                    return -1;
                } else {
                    return 1;
                }
            }

        }*/);

        System.out.println("begin");
        try {
            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                List<String> ingreds = new ArrayList<String>();
                int recipe_id = rs.getInt("recipe_id");
                int ingredient_id = rs.getInt("ingredient_id");
                //Sys=tem.out.println(ingredient_id);
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

            //System.out.println(ingredmap.toString());
            // Now check the ingredients in the recipe-ingred map.
            List<String> recipe_ingreds = new ArrayList<String>();
            for (Map.Entry<String, List<String>> entry : ingredmap.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
                int matchcount = 0;
                if (entry.getValue() != null) {
                    for (String ingr : l) {             // For all the ingredients in the argument.
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
                //recipe_ranked.add(entry.getKey().toString());
                String t = entry.getKey().toString();
                for (String k : entry.getValue()) {
                    recipeList.add(getRecipeById(Integer.parseInt(k)));
                }
            }
            //return recipeList;
        } catch (Exception e) {
        } 
        finally {
            return recipeList;
        }
    }

    public List<Ingredient> getListOfIngredients() {
        return null;
    }

    public List<Ingredient> getRecipeByPopularity() {
        // We will use TreeMap SortedMap for sorting by rating.
        return null;
    }

}
