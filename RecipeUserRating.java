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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Chitra
 */
@Entity
@Table(name = "recipe_user_rating")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecipeUserRating.findAll", query = "SELECT r FROM RecipeUserRating r"),
    @NamedQuery(name = "RecipeUserRating.findById", query = "SELECT r FROM RecipeUserRating r WHERE r.id = :id"),
    @NamedQuery(name = "RecipeUserRating.findByUserId", query = "SELECT r FROM RecipeUserRating r WHERE r.userId = :userId"),
    @NamedQuery(name = "RecipeUserRating.findByRecipeId", query = "SELECT r FROM RecipeUserRating r WHERE r.recipeId = :recipeId"),
    @NamedQuery(name = "RecipeUserRating.findByComments", query = "SELECT r FROM RecipeUserRating r WHERE r.comments = :comments"),
    @NamedQuery(name = "RecipeUserRating.findByRating", query = "SELECT r FROM RecipeUserRating r WHERE r.rating = :rating")})
public class RecipeUserRating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recipe_id")
    private int recipeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "comments")
    private int comments;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rating")
    private int rating;

    public RecipeUserRating() {
    }

    public RecipeUserRating(Integer id) {
        this.id = id;
    }

    public RecipeUserRating(Integer id, int userId, int recipeId, int comments, int rating) {
        this.id = id;
        this.userId = userId;
        this.recipeId = recipeId;
        this.comments = comments;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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
        if (!(object instanceof RecipeUserRating)) {
            return false;
        }
        RecipeUserRating other = (RecipeUserRating) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RecipeUserRating[ id=" + id + " ]";
    }
    
}
