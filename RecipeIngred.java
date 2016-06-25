/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Chitra
 */
@Entity
@Table(name = "recipe_ingred")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecipeIngred.findAll", query = "SELECT r FROM RecipeIngred r"),
    @NamedQuery(name = "RecipeIngred.findById", query = "SELECT r FROM RecipeIngred r WHERE r.id = :id"),
    @NamedQuery(name = "RecipeIngred.findByRecipeId", query = "SELECT r FROM RecipeIngred r WHERE r.recipeId = :recipeId"),
    @NamedQuery(name = "RecipeIngred.findByIngredientId", query = "SELECT r FROM RecipeIngred r WHERE r.ingredientId = :ingredientId"),
    @NamedQuery(name = "RecipeIngred.findByAmount", query = "SELECT r FROM RecipeIngred r WHERE r.amount = :amount")})
public class RecipeIngred implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recipe_id")
    private int recipeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ingredient_id")
    private int ingredientId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "amount")
    private String amount;

    public RecipeIngred() {
    }

    public RecipeIngred(Integer id) {
        this.id = id;
    }

    public RecipeIngred(Integer id, int recipeId, int ingredientId, String amount) {
        this.id = id;
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecipeIngred)) {
            return false;
        }
        RecipeIngred other = (RecipeIngred) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RecipeIngred[ id=" + id + " ]";
    }
    
}
