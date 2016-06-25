package com.stuffit.www.data.objects;

/**
 *
 * @author Bikramjit
 */
public class Ingredient {

    private int id;
    private String name;
    private String type;
    private int rating;

    public int getIngredientId() {
        return this.id;
    }

    public void setIngredientId(int id){
        this.id = id;
    }
    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public int getRating() {
        return this.rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
